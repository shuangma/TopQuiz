package ma.assign3.view.main;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ma.assign3.common.Utils;
import ma.assign3.common.ViewHelper;

/**
 * A view showing different topics for user to select
 * @author Shuang Ma
 *
 */
public class TopicView extends JPanel {
	private static final String TITLE = "Question Topic";
	private ArrayList<JRadioButton> topicButtons = new ArrayList<JRadioButton>();
	
	public TopicView(ArrayList<String> topics) {
		Collections.sort(topics);
		
		initBorder();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Leave some space to the top
		add(Box.createVerticalStrut(10));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton topicButton = null;
		int topicCount = 0;
		for(String topic : topics){
			topic = topic.toUpperCase();
			// The first one is selected as default
			if(topicCount == 0)
				topicButton = new JRadioButton(topic, true);
			else
				topicButton = new JRadioButton(topic);
			setRadioButtonStyle(topicButton);
			topicButtons.add(topicButton);
			topicButton.setActionCommand(topic.toLowerCase());
			buttonGroup.add(topicButton);
			add(topicButton);
			add(Box.createVerticalStrut(10));
			topicCount += 1;
		}
		
		add(Box.createVerticalGlue());
		
		JLabel logoLabel = ViewHelper.createImageLabel(200, 150, Utils.LOGO_FILE);
		add(logoLabel);
	}
	
	private void initBorder(){
		Utils.initMainPanelBorder(this, TITLE);
	}
	
	private void setRadioButtonStyle(JRadioButton radioButton){
		Font font = new Font("Times New Roman", Font.BOLD, 18);
		radioButton.setFont(font);
	}
	
	public String getTopic(){
		String topic = null;
		for(JRadioButton button : topicButtons){
			if(button.isSelected()){
				topic = button.getText().toLowerCase();
				break;
			}
		}
		
		return topic;
	}
	
	public void addTopicSelectionListener(ActionListener listener) {
		for(JRadioButton topicButton : topicButtons){
			topicButton.addActionListener(listener);
		}
	}
}
