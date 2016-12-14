package ma.assign3.view.statistic;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ma.assign3.model.QuestionStatistic;

public class ScoreView extends JPanel{
	private static final String SCORE = "Score: ";
	private static final String RIGHT_NUM = "Right: ";
	private static final String TOTAL_NUM = "Total Answered: ";
	
	private JLabel scoreLabel;
	private JLabel rightAnswerNumLabel;
	private JLabel totalAnsweredNumLabel;
	
	
	public ScoreView() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		scoreLabel = new JLabel(SCORE + 0, JLabel.CENTER);
		scoreLabel.setFont(getFormatFont());
		add(scoreLabel);
		
		add(Box.createHorizontalStrut(5));
		
		rightAnswerNumLabel = new JLabel(RIGHT_NUM + 0, JLabel.CENTER);
		rightAnswerNumLabel.setFont(getFormatFont());
		add(rightAnswerNumLabel);
		
		add(Box.createHorizontalStrut(5));
		
		totalAnsweredNumLabel = new JLabel(TOTAL_NUM + 0, JLabel.CENTER);
		totalAnsweredNumLabel.setFont(getFormatFont());
		add(totalAnsweredNumLabel);
	}
	
	private Font getFormatFont(){
		Font font = new Font("Times New Roman", Font.BOLD, 16);
		return font;
	}
	
	public void update(QuestionStatistic questionStatistic) {
		scoreLabel.setText(SCORE + questionStatistic.getScore());
		rightAnswerNumLabel.setText(RIGHT_NUM + questionStatistic.getRightAns()); 
		totalAnsweredNumLabel.setText(TOTAL_NUM + questionStatistic.getTotalAnsweredNum());
	}
}
