package ma.assign3.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * Make a statistic of all answered questions
 * @author Shuang Ma
 *
 */

public class QuestionStatistic extends Observable {
	private static final double SKILL_ACCURACY_THRESHOLD = 0.7;
	private static final int ABILITITY_CHECK_THRESHOLD = 3;
	
	private HashMap<String, TopicStatistic> topicStatisticMap = new HashMap<String, TopicStatistic>();
	private int rightNum = 0;
	private int totalAnsweredNum = 0;
	private int score = 0;
	
	/**
	 * The constructor method to create a QuestionStatistic instance
	 * @param questionTopics a list of topics of all the questions
	 */
	public QuestionStatistic(ArrayList<String> questionTopics) {
		if(questionTopics == null)
			return;
		for(String topic : questionTopics){
			topicStatisticMap.put(topic, new TopicStatistic(topic));
		}
	}
	
	/**
	 * Make a statistic of total right and wrong answers according to the user's answer
	 * @param isAnswerRight a flag indicating if user's answer is right or not
	 * @param question the question instance the user already answered
	 */
	public void publish(boolean isAnswerRight, Question question) {
		if(question == null)
			return;
		
		totalAnsweredNum += 1;
		
		String topic = question.getTopic();
		TopicStatistic topicStatistic = topicStatisticMap.get(topic);
		
		if(isAnswerRight) {
			rightNum++;
			score += question.getPoints();
			
		}
		if(topicStatistic != null)
			topicStatistic.update(isAnswerRight, question.getPoints());
					
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Make a conclusion that which topics the user is good at
	 * @return a list of topics that the user is good at
	 */
	public ArrayList<String> getSkilledTopics(){
		ArrayList<String> topics = new ArrayList<String>();
		
		for(String topic : topicStatisticMap.keySet()){
			TopicStatistic topicStatistic = topicStatisticMap.get(topic);
			if(topicStatistic.getTotalAnsweredNum() > ABILITITY_CHECK_THRESHOLD && 
					topicStatistic.getAccuracy() >= SKILL_ACCURACY_THRESHOLD){
				topics.add(topic);
			}
		}
		return topics;
	}
	
	/**
	 * Make a conclusion that which topics the user is weak at
	 * @return a list of topics the user is weak at
	 */
	public ArrayList<String> getWeakedTopics(){
		ArrayList<String> topics = new ArrayList<String>();
		
		for(String topic : topicStatisticMap.keySet()){
			TopicStatistic topicStatistic = topicStatisticMap.get(topic);
			if(topicStatistic.getTotalAnsweredNum() > ABILITITY_CHECK_THRESHOLD && 
					topicStatistic.getAccuracy() < (1 -SKILL_ACCURACY_THRESHOLD))
				topics.add(topic);
		}
		return topics;
	}
	
	/**
	 * Get the number of all the user's right answers
	 * @return the number of all the user's right answers
	 */
	public int getRightAns() {
		return rightNum;
	}
	
	/**
	 * Get the number of all the answered questions
	 * @return the number of all the answered questions
	 */
	public int getTotalAnsweredNum() {
		return totalAnsweredNum;
	}
	
	/**
	 * Get the user's achieved score
	 * @return the user's achieved score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Get the statistic of answered questions
	 * @return a map of statistics in each topic
	 */
	public HashMap<String, TopicStatistic> getTopicStatisticMap() {
		return topicStatisticMap;
	}
}