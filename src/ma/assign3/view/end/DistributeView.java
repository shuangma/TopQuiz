package ma.assign3.view.end;

import java.awt.Font;
import java.util.HashMap;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import ma.assign3.common.Utils;
import ma.assign3.controller.EndController;
import ma.assign3.model.QuestionStatistic;
import ma.assign3.model.TopicStatistic;

public class DistributeView extends JPanel {
	private static final int VIEW_HEIGHT = 280;
	private QuestionStatistic questionStatistic;
	
	public DistributeView(QuestionStatistic questionStatistic) {
		 this.questionStatistic = questionStatistic;
		 drawBarGraph();
	}
	
    private DefaultCategoryDataset createDataset(){
	    	DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
	    	HashMap<String, TopicStatistic> topicStatisticMap = questionStatistic.getTopicStatisticMap();
	    	for(String topic : this.questionStatistic.getTopicStatisticMap().keySet()){
	    		TopicStatistic topicStatistic = topicStatisticMap.get(topic);
	    		dataSet.setValue(topicStatistic.getRightNum(), "Right Num", topicStatistic.getTopic());
	    	}
	    	return dataSet;
    }
    
    private JFreeChart createChart(DefaultCategoryDataset dataSet){
    	String chartTitle = "Question Answer Results";
    	
    	JFreeChart barChart = ChartFactory.createBarChart3D(
    			chartTitle,             
		        "",             
		        "Right Number",             
		        dataSet,            
		        PlotOrientation.VERTICAL,             
		        false, true, false);
    	barChart.setTitle(new TextTitle(chartTitle, Utils.getBorderTitleFont(16)));
    	
    	// Apply some styles
    	CategoryPlot categoryPlot = (CategoryPlot) barChart.getPlot();
    	// Set Y axis font and range
    	NumberAxis numberAxis = (NumberAxis) categoryPlot.getRangeAxis();
		numberAxis.setTickLabelFont(Utils.getFormatFont(Font.ITALIC | Font.BOLD, 12));
		numberAxis.setLabelFont(Utils.getFormatFont(Font.ITALIC | Font.BOLD, 14));
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		// Set X axis font
		CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
		categoryAxis.setTickLabelFont(Utils.getFormatFont(Font.ITALIC | Font.BOLD, 12));
		
    	return barChart;
    }
    
	private void drawBarGraph() {
		DefaultCategoryDataset dataSet = createDataset();
		JFreeChart chart = createChart(dataSet);
		ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(EndController.VIEW_WIDTH, VIEW_HEIGHT));
        add(chartPanel);
	}
	
}
