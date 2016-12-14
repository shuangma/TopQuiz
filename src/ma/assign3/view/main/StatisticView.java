package ma.assign3.view.main;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ma.assign3.common.Utils;
import ma.assign3.model.QuestionStatistic;
import ma.assign3.view.statistic.ChartView;
import ma.assign3.view.statistic.ScoreView;
import ma.assign3.view.statistic.SummaryView;

/**
 * A view showing current score, a pie chart of right and wrong answers, summary
 * @author Shuang Ma
 *
 */
public class StatisticView extends JPanel implements Observer {
	public static final int VIEW_WIDTH = 300;
	
	private ScoreView scoreView;
	private ChartView chartView;
	private SummaryView summaryView;
	
	public StatisticView() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(VIEW_WIDTH, Utils.FRAME_HEIGHT));

		scoreView = new ScoreView();
		add(scoreView);
		
		add(Box.createVerticalStrut(10));
		
		chartView = new ChartView();
		add(chartView);
		
		add(Box.createVerticalStrut(10));
		
		summaryView = new SummaryView();
		add(summaryView);
	}

	public void update(Observable observable, Object object) {
		QuestionStatistic quesStatistic = (QuestionStatistic) observable;
		scoreView.update(quesStatistic);
		chartView.update(quesStatistic);
		summaryView.update(quesStatistic);
	}
}
