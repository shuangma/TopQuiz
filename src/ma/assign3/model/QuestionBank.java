package ma.assign3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

import ma.assign3.common.Utils;

/**
 * A class storing all the question objects
 * @author Shuang Ma
 *
 */

public class QuestionBank extends Observable{
	private HashMap<String, ArrayList<Question>> topicQuestionMap = new HashMap<String, ArrayList<Question>>();

	private String currentTopic = null;
	private Question currentQuestion = null;
	
	/**
	 * The constructor method, loading all the questions into QuestionBank
	 */
	public QuestionBank() {
		loadQuestions(Utils.QUESTIONS_FILE);
	}
	
	/**
	 * Choose an unanswered question in a specified topic and notify observers
	 * @param topic topic of the question
	 */
	public void publish(String topic){
		currentTopic = topic;
		chooseQuestion(topic);
		
		setChanged();
		notifyObservers(currentQuestion);
	}
	
	private void loadQuestions(String inputQuestionsPath){
		String questionsContent = Utils.readFile(inputQuestionsPath);
		JSONArray questionsJson = new JSONArray(questionsContent);
		for(int i=0; i < questionsJson.length(); i ++){
			JSONObject questionJson = questionsJson.getJSONObject(i);
			String question = questionJson.getString(Utils.QUESTION_KEY);
			String topic = questionJson.getString(Utils.TOPIC_KEY);
			int points = questionJson.getInt(Utils.POINTS_KEY);
			String questionType = questionJson.getString(Utils.QUESTION_TYPE_KEY);
			String pictureName = questionJson.getString(Utils.PICTURE_KEY);
			JSONArray choices = questionJson.getJSONArray(Utils.CHOICES_KEY);
			ArrayList<String> choiceArray = new ArrayList<String>();
			for(int j=0; j < choices.length(); j++)
				choiceArray.add(choices.getString(j));
			JSONArray answeres = questionJson.getJSONArray(Utils.ANSWER_KEY);
			ArrayList<String> answerArray = new ArrayList<String>();
			for(int j=0; j < answeres.length(); j++)
				answerArray.add(answeres.getString(j));
			Question questionObj = new Question(question, topic, questionType, choiceArray,
					answerArray, points, pictureName);
			addQuestion(questionObj);
			
		}
	}
	
	private void addQuestion(Question question){
		String topic = question.getTopic();
		if(!topicQuestionMap.containsKey(topic)){
			ArrayList<Question> questions = new ArrayList<Question>();
			topicQuestionMap.put(topic, questions);
		}
		
		ArrayList<Question> questions = topicQuestionMap.get(topic);
		questions.add(question);
	}
	
	private void chooseQuestion(String topic){
		boolean allAnswered = true;
		ArrayList<Question> topicQuestions = topicQuestionMap.get(topic);
		if(topicQuestions == null)
			return;
		// Check if all questions have been answered to avoid infinite loop in the random selection
		for(Question question : topicQuestions){
			if(!question.isAnswered())
				allAnswered = false;
		}
		
		if(allAnswered){
			currentQuestion = null;
			return;
		}
		
		int totalQuestions = topicQuestions.size();
		Random rand = new Random();
		
		while(true){
			int questionIndex = rand.nextInt(totalQuestions);
			Question question = topicQuestions.get(questionIndex);
			if(!question.isAnswered()){
				currentQuestion = question;
				break;
			}
		}
	}
	
	/**
	 * Get the topic of question that the user is answering
	 * @return the topic of question which is being answered
	 */
	public String getCurrentTopic() {
		return currentTopic;
	}
	
	/**
	 * Get the current question that the user is answering
	 * @return the question which is being answered
	 */
	public Question getCurrentQuestion(){
		return currentQuestion;
	}
	
	/**
	 * Get all the topics in question bank
	 * @return a list of question topics
	 */
	public ArrayList<String> getAllTopics(){
		ArrayList<String> allTopics = new ArrayList<String>();
		for(String key : topicQuestionMap.keySet())
			allTopics.add(key);
		return allTopics;
	}
}
