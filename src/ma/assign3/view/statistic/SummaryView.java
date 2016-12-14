package ma.assign3.view.statistic;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import ma.assign3.common.Utils;
import ma.assign3.model.QuestionStatistic;

public class SummaryView extends JPanel {
	private static final String TITLE = "Review";
	private static final int ROW_NUM = 8;
	private static final int COLUMN_NUM = 23;
	
	private JTextArea reviewArea;
	
	public SummaryView() {
		Utils.initSubPanelBorder(this, TITLE);
		
		reviewArea = new JTextArea(ROW_NUM, COLUMN_NUM);
		reviewArea.setFont(Utils.getFormatFont());
		
		reviewArea.setLineWrap(true);
		reviewArea.setEditable(false);
		add(reviewArea);
	}
	
	public void update(QuestionStatistic questionStatistic) {
		ArrayList<String> skilledTopics = questionStatistic.getSkilledTopics();
		ArrayList<String> weakedTopics = questionStatistic.getWeakedTopics();
		StringBuilder showMsgBuilder = new StringBuilder();
		if(skilledTopics.size() > 0){
			String goodAtShowMsg = String.format("You are good at: %s", Utils.join(skilledTopics, ","));
			showMsgBuilder.append(goodAtShowMsg + System.getProperty("line.separator"));
		}
		if(weakedTopics.size() > 0){
			String weakAtShowMsg = String.format("You are weak at: %s", Utils.join(weakedTopics, ","));
			showMsgBuilder.append(weakAtShowMsg + System.getProperty("line.separator"));
			
		}
		reviewArea.setText(showMsgBuilder.toString());
		
	}
	
}
