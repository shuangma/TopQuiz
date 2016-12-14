package ma.assign3.controller;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ma.assign3.common.Utils;
import ma.assign3.model.QuestionStatistic;
import ma.assign3.model.QuestionTime;
import ma.assign3.view.main.EndView;
import ma.assign3.view.main.TimingView;

/**
 * Responding to end button
 * @author Shuang Ma
 *
 */

public class EndController implements ActionListener {
	public static final int VIEW_WIDTH = 450;
	public static final int VIEW_HEIGHT = 450;
	
	private QuestionStatistic questionStatistic;
	private QuestionTime questionTime;
	
	private TimingView timingView;
	
	public EndController(QuestionTime questionTime, QuestionStatistic questionStatistic, TimingView timingView) {
		this.questionTime = questionTime;
		this.questionStatistic = questionStatistic;
		this.timingView = timingView;
	} 

	@Override
	public void actionPerformed(ActionEvent event) {
		if(event.getActionCommand().equals("end")){
			timingView.stopTimer();
			showEndDialog();
		}else if(event.getActionCommand().equals("continue"))
			timingView.startTimer();
	}
	
	private void showEndDialog() {
		int dialogButton = JOptionPane.OK_CANCEL_OPTION;
		JLabel msgLabel = new JLabel("Are you sure to end the test?");
		msgLabel.setFont(Utils.getFormatFont(Font.BOLD | Font.ITALIC, 16));
		
		int dialogRes = JOptionPane.showConfirmDialog(null, msgLabel, "", dialogButton);
		
		if (dialogRes == JOptionPane.OK_OPTION) {
			showEndView();
		} else {
			timingView.startTimer();
		}
	}
	
	private void showEndView(){
		JPanel endView = new EndView(questionStatistic);
		final JOptionPane pane = new JOptionPane(endView);
	    final JDialog dialog = pane.createDialog(null, "Your Answering Result");
	    dialog.setPreferredSize(new Dimension(VIEW_WIDTH, VIEW_HEIGHT));
	    dialog.setVisible(true);
	}
}
