package ma.assign3.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ma.assign3.model.QuestionBank;
import ma.assign3.view.main.ButtonView;
import ma.assign3.view.main.TimingView;
import ma.assign3.view.main.TopicView;

/**
 * Responding to start button
 * @author Shuang Ma
 *
 */

public class StartController implements ActionListener {
	private QuestionBank questionBank;
	
	private TopicView topicView;
	private TimingView timingView;
	private ButtonView buttonView;
	
	public StartController(QuestionBank questionBank, TopicView topicView, TimingView timingView, ButtonView buttonView) {
		this.questionBank = questionBank;
		this.topicView = topicView;
		this.timingView = timingView;
		this.buttonView = buttonView;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		questionBank.publish(topicView.getTopic());
		timingView.startTimer();
		buttonView.enableButton();
		buttonView.disableStartButton();
	}

}
