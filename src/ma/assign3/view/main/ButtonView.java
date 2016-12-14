package ma.assign3.view.main;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import ma.assign3.common.Utils;

/**
 * A view containing submit, next, end and continue button
 * @author Shuang Ma
 *
 */

public class ButtonView extends JPanel{
	private JButton startButton;
	private JButton submitButton;
	private JButton nextButton;
	private JButton endButton;
	private JButton continueButton;
	
	public ButtonView() {
		setLayout(new FlowLayout());
		
		startButton = new JButton("Start");
		startButton.setFont(Utils.getFormatFont());
		add(startButton);
		
		nextButton = new JButton("Next");
		nextButton.setActionCommand("next");
		nextButton.setFont(Utils.getFormatFont());
		add(nextButton);
		
		submitButton = new JButton("Submit");
		submitButton.setActionCommand("submit");
		submitButton.setFont(Utils.getFormatFont());
		add(submitButton);
		
		endButton = new JButton("End");
		endButton.setActionCommand("end");
		endButton.setFont(Utils.getFormatFont());
		add(endButton);
		
		continueButton = new JButton("Continue");
		continueButton.setActionCommand("continue");
		continueButton.setFont(Utils.getFormatFont());
		add(continueButton);
	}
	
	public void enableButton() {
		nextButton.setEnabled(true);
		submitButton.setEnabled(true);
		continueButton.setEnabled(true);
		endButton.setEnabled(true);
	}
	
	public void disableButton() {
		nextButton.setEnabled(false);
		submitButton.setEnabled(false);
		continueButton.setEnabled(false);
		endButton.setEnabled(false);
	}
	
	public void disableStartButton() {
		startButton.setEnabled(false);
	}
	
	public void addStartListener(ActionListener listener) {
		startButton.addActionListener(listener);
	}

	public void addSubmitSwitchListener(ActionListener listener){
		submitButton.addActionListener(listener);
		nextButton.addActionListener(listener);
	}
	
	public void addQuizEndContinueListener(ActionListener listener){
		endButton.addActionListener(listener);
		continueButton.addActionListener(listener);
	}
}
