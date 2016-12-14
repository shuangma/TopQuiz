package ma.assign3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ma.assign3.model.QuestionBank;

/**
 * Responding to the selection of a topic
 * @author Shuang Ma
 *
 */

public class TopicController implements ActionListener {
	private QuestionBank questionBank;
	
	/**
	 * The constructor method to create a TopicController instance
	 * @param questionBank a collection to store all the question instances
	 */
	public TopicController(QuestionBank questionBank) {
		this.questionBank = questionBank;
	}
	
	public void actionPerformed(ActionEvent event) {
		String topic = event.getActionCommand();
		questionBank.publish(topic);
	}
}
