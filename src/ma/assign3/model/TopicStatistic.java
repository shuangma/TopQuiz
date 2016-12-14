package ma.assign3.model;

/**
 * Make a statistic of answered questions under specified topics
 * @author Shuang Ma
 *
 */

public class TopicStatistic{
	private String topic;
	
	private int rightNum = 0;
	private int totalAnsweredNum = 0;
	private int score = 0;
	private double accuracy = 0.00;
	
	/**
	 * The constructor method to create a TopicStatistic instance
	 * @param topic the specified topic of question
	 */
	public TopicStatistic(String topic){
		this.topic = topic;
	}
	
	/**
	 * update the statistic according to the user's answer
	 * @param isAnswerRight a flag indicating the user's answer is right or not
	 * @param points the points of this question
	 */
	public void update(boolean isAnswerRight, int points){
		totalAnsweredNum += 1;
		if(isAnswerRight){
			rightNum += 1;
			score += points;
		}
		accuracy = (double) rightNum / totalAnsweredNum;
	}
	
	/**
	 * Get the current topic
	 * @return the current topic
	 */
	public String getTopic(){
		return topic;
	}
	
	/**
	 * Get the number of right answers in current topic
	 * @return the number of right answers in current topic
	 */
	public int getRightNum(){
		return rightNum;
	}
	
	/**
	 * Get the number of all answered questions in current topic
	 * @return the number of all answered questions in current topic
	 */
	public int getTotalAnsweredNum(){
		return totalAnsweredNum;
	}
	
	/**
	 * Get the score of current topic
	 * @return the score of current topic
	 */
	public int getScore(){
		return score;
	}
	
	/**
	 * Get the accuracy of current topic
	 * @return accuracy of current topic
	 */
	public double getAccuracy(){
		return accuracy;
	}
	
	public String getFormatedAccuracy(){
		return String.format("%.2f", accuracy);
	}
}
