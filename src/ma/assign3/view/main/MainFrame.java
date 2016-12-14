package ma.assign3.view.main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import ma.assign3.controller.SubmitSwitchController;
import ma.assign3.controller.TimingController;
import ma.assign3.common.Utils;
import ma.assign3.controller.EndController;
import ma.assign3.controller.StartController;
import ma.assign3.controller.TopicController;
import ma.assign3.model.QuestionBank;
import ma.assign3.model.QuestionStatistic;
import ma.assign3.model.QuestionTime;
import ma.assign3.view.main.QuestionView;

/**
 * The main frame combining all the view
 * @author Shuang Ma
 *
 */

public class MainFrame extends JFrame {
	public static final String TITLE = "Top Quiz";
	
	private JFrame mainFrame;
	
	public MainFrame() {
		init();
	}
	
	public void startup() {
		mainFrame.setSize(Utils.FRAME_WIDTH, Utils.FRAME_HEIGHT);
		mainFrame.setVisible(true);
	}
	
	private void init(){
		//initiate model
		QuestionBank questionBank = new QuestionBank();
		QuestionStatistic questionStatistic = new QuestionStatistic(questionBank.getAllTopics());
		QuestionTime questionTime = new QuestionTime();	
				
		mainFrame = new JFrame(TITLE);
		setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//add topic view to the west of main frame
		TopicView topicView = new TopicView(questionBank.getAllTopics());
		mainFrame.getContentPane().add(topicView, BorderLayout.WEST);
		
		//add question view to the center of main frame
		QuestionView questionView = new QuestionView();
		mainFrame.getContentPane().add(questionView, BorderLayout.CENTER);
		
		//add button view to the south of main frame
		ButtonView questionButtonView = new ButtonView();
		questionButtonView.disableButton();
		mainFrame.getContentPane().add(questionButtonView, BorderLayout.SOUTH);
		
		//add review view to the east of main frame 
		StatisticView reviewView = new StatisticView();
		mainFrame.getContentPane().add(reviewView, BorderLayout.EAST);
		
		//add timing bar view to the north of main frame
		TimingView timingView = new TimingView();
		mainFrame.getContentPane().add(timingView, BorderLayout.NORTH);
		
		// add action listeners, observers
		TopicController topicController = new TopicController(questionBank);
		StartController startController = new StartController(questionBank, topicView, timingView, questionButtonView);
		SubmitSwitchController submitSwitchController = new SubmitSwitchController(questionBank, questionStatistic, questionTime, topicView, questionView.getAnswerView());
		TimingController timingController = new TimingController(topicView, questionTime, questionBank);
		EndController endController = new EndController(questionTime, questionStatistic, timingView);
		
		topicView.addTopicSelectionListener(topicController);
		questionButtonView.addStartListener(startController);
		questionButtonView.addSubmitSwitchListener(submitSwitchController);
		questionButtonView.addQuizEndContinueListener(endController);
		timingView.addTimingListener(timingController);
		
		questionBank.addObserver(questionView.getQuestionContentView());
		questionBank.addObserver(questionView.getAnswerView());
		questionStatistic.addObserver(reviewView);
		questionTime.addObserver(timingView);
		
//		questionBank.publish(topicView.getTopic());
//		
//		timingView.startTimer();
	}
}
