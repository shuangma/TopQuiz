package ma.assign3.model;

import java.io.File;
import java.util.ArrayList;

import ma.assign3.common.Utils;

/**
 * A class representing a question
 * @author Shuang Ma
 *
 */

public class Question {
	private String questionContent;
	private String topic;
	private String questionType;
	private ArrayList<String> choices;
	private ArrayList<String> answers;
	private int points;
	private String pictureName;  
	
	private boolean isAnswered = false;
	
	/**
	 * The constructor method to create a question instance
	 * @param questionContent question content
	 * @param topic the topic which this question belongs to
	 * @param type the answer type, maybe a single choice, multiple choices, etc.
	 * @param choices the choices provided for user to choose
	 * @param answers the correct answer for this question
	 * @param points the number of points this question values
	 * @param pictureName the file name of the related picture
	 */
	
	public Question(String questionContent, String topic, String type, ArrayList<String> choices, 
			ArrayList<String> answers, int points, String pictureName) {
		this.questionContent = questionContent;
		this.topic = topic;
		this.questionType = type;
		this.choices = choices;
		this.answers = answers;
		this.points = points;
		this.pictureName = pictureName;
	}
		
	/**
	 * Check if the user's answer is right or not
	 * @param userAnswer an ArrayList of the user answers
	 * @return true if answers are right, otherwise return false
	 */
	public boolean isAnswerRight(ArrayList<String> userAnswer){
		if(userAnswer == null)
			return false;
		if(userAnswer.size() != answers.size())
			return false;
		for(String answer : answers){
			if(!userAnswer.contains(answer))
				return false;
		}
		return true;
	}
	
	/**
	 * Check if the question has been answered by user or not
	 * @return true if the question has been answered otherwise return false
	 */
	public boolean isAnswered(){
		return isAnswered;
	}
	
	/**
	 * Mark the question has been answered
	 */
	public void questionAnswered(){
		isAnswered = true;
	}
	
	/**
	 * Get the content of question
	 * @return content of this question
	 */
	public String getQuestionContent() {
		return questionContent;
	}
	
	/**
	 * Get the topic of question
	 * @return topic of this question
	 */
	public String getTopic() {
		return topic;
	}
	
	/**
	 * Get the question type, should be single choice, multiple choice or user input
	 * @return type of this question
	 */
	public String getQuestionType() {
		return questionType;
	}
	
	/**
	 * Get the choices of question for user to choose
	 * @return a list of all the choices
	 */
	public ArrayList<String> getChoices() {
		return choices;
	}
	
	/**
	 * Get the answer of question
	 * @return a list of right answers
	 */
	public ArrayList<String> getAnswers() {
		return answers;
	}
	
	/**
	 * Get the points of question
	 * @return an integer represents the points of this question
	 */
	public int getPoints() {
		return points;
	}
	
	/**
	 * Get the path of pictures related to the question
	 * @return a String represents the file path
	 */
	public String getPictureFilePath(){
		return new File(Utils.PICATURE_DIR, String.format("%s.jpeg", pictureName)).getPath();
	}
	
}
