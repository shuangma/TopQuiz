package ma.assign3.view.statistic;

import java.awt.Dimension;
import java.awt.Font;
import java.text.AttributedString;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import ma.assign3.common.Utils;
import ma.assign3.model.QuestionStatistic;
import ma.assign3.view.main.StatisticView;

public class ChartView extends JPanel {
	private static final int VIEW_HEIGHT = 265;
	
	public ChartView() {
		this.setPreferredSize(new Dimension(StatisticView.VIEW_WIDTH, VIEW_HEIGHT));
	}

	public void update(QuestionStatistic questionStatistic) {
		removeAll();
		drawPieChart(questionStatistic);
	}
	
	private void drawPieChart(QuestionStatistic questionStatistic) {
		int rightAns = questionStatistic.getRightAns();
		int wrongAns = questionStatistic.getTotalAnsweredNum() - rightAns;
		
		// Init dataset
		PieDataset pieDataSet = this.createDataset(rightAns, wrongAns);
		
		JFreeChart chart = createChart(pieDataSet);
        
        // add the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(StatisticView.VIEW_WIDTH, VIEW_HEIGHT-10));
        add(chartPanel);
	}
	
	private PieDataset createDataset(int rightAns, int wrongAns) {
        final DefaultPieDataset result = new DefaultPieDataset();
        result.setValue("Right", rightAns);
        result.setValue("Wrong", wrongAns);
        return result;
        
    }
	
	private JFreeChart createChart(PieDataset dataset) {
        String chartTitle = "Question Answer Results";
        
        final JFreeChart chart = ChartFactory.createPieChart3D(
            chartTitle,  					
            dataset,                		
            true,                   		
            true,
            false
        );

        // Apply some styles
        chart.setTitle(new TextTitle(chartTitle, Utils.getBorderTitleFont(16)));
        chart.getLegend().setItemFont(Utils.getFormatFont(Font.ITALIC, 14));
        
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setLabelFont(Utils.getFormatFont(Font.ITALIC, 16));
        plot.setLabelGenerator(new CustomLabelGenerator());
        
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        
        return chart;
        
    }
	
	static class CustomLabelGenerator implements PieSectionLabelGenerator{

		@Override
		public AttributedString generateAttributedSectionLabel(PieDataset arg0, Comparable arg1) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String generateSectionLabel(PieDataset dataset, Comparable key) {
			return String.format("%.0f",dataset.getValue(key));
		}
		
	}
}
