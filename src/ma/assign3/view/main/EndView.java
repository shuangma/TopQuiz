package ma.assign3.view.main;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ma.assign3.model.QuestionStatistic;
import ma.assign3.view.end.DistributeView;
import ma.assign3.view.statistic.ScoreView;

/**
 * A View showing total scores and distribution of right answers in different topic when ending the test
 * @author Shuang Ma
 *
 */

public class EndView extends JPanel {
	
	public EndView(QuestionStatistic questionStatistic) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(Box.createVerticalStrut(10));
		
		ScoreView scoreView = new ScoreView();
		scoreView.update(questionStatistic);
		add(scoreView);
		
		add(Box.createVerticalStrut(10));
		
		DistributeView dataView = new DistributeView(questionStatistic);
		add(dataView);
	}
}
