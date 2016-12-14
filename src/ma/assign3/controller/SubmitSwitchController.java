package ma.assign3.controller;

import ma.assign3.model.QuestionStatistic;
import ma.assign3.model.QuestionTime;
import ma.assign3.view.main.TopicView;
import ma.assign3.view.question.ChoiceView;
import ma.assign3.view.question.AnswerView;
import ma.assign3.model.Question;
import ma.assign3.model.QuestionBank;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Responding to submit button and next button
 * @author Shuang Ma
 *
 */

public class SubmitSwitchController implements ActionListener {
	private TopicView topicView;
	private AnswerView answerView;
	private QuestionBank questionBank;
	private QuestionStatistic questionStatistic;
	private QuestionTime questionTime;
	
	public SubmitSwitchController(QuestionBank questionBank, QuestionStatistic questionStatistic, QuestionTime questionTime,
			TopicView topicView, AnswerView answerView) {
		this.questionBank = questionBank;
		this.questionStatistic = questionStatistic;
		this.questionTime = questionTime;
		this.topicView = topicView;
		this.answerView = answerView;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
		if (command.equals("next")) {
			nextQuestion();
		}else if (command.equals("submit")){
			submitAnswer();
		}
	}
	
	private void nextQuestion(){
		String topic = topicView.getTopic();
		if(topic == null)
			return;
		questionBank.publish(topic);
		// Reset left time
		questionTime.resetLeftTime();
	}
	
	private void submitAnswer(){
		boolean isRight = this.isAnswerRight();
		// Notify the model to update views
		questionStatistic.publish(isRight, questionBank.getCurrentQuestion());
		// Jump to next question
		nextQuestion();
	}
	
	//Judge whether user's answer if right or not
	private boolean isAnswerRight(){
		Question currentQuestion = questionBank.getCurrentQuestion();
		if(currentQuestion == null)
			return false;
		// Set this question is answered
		currentQuestion.questionAnswered();
		
		ChoiceView choiceView = answerView.getChoiceView();
		if(choiceView == null)
			return false;
		ArrayList<String> userAnswer = choiceView.getUserAnswer();
		return currentQuestion.isAnswerRight(userAnswer);
	}
}
