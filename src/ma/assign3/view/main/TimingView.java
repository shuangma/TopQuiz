package ma.assign3.view.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;

import ma.assign3.common.Utils;
import ma.assign3.common.ViewHelper;
import ma.assign3.model.QuestionTime;

/**
 * A view showing time bar
 * @author shuang
 *
 */

public class TimingView extends JPanel implements Observer {
	private static final String TITLE = "Time Left: ";
	private static final int EMOJI_NUM = 3;
	private static final int BAR_HEIGHT = 30;
	
	private JLabel label;
	private JProgressBar progressBar;
	private ArrayList<JLabel> emojiLabels = new ArrayList<JLabel>();
    
	private Timer timer;

    TimingView() {
    	setLayout(new FlowLayout());
    	
    	label = new JLabel(TITLE);
    	label.setFont(Utils.getFormatFont(Font.ITALIC | Font.BOLD, 16));

        progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, QuestionTime.TOTAL_TIME);
        progressBar.setPreferredSize(new Dimension(Utils.FRAME_WIDTH-200, BAR_HEIGHT));
        
        progressBar.setStringPainted(true);
        progressBar.setForeground(Color.blue);
        
        setProgressBarValue(QuestionTime.TOTAL_TIME);
        
        add(label);
        add(progressBar);
        
        initEmojiLabels();
    }
    
    private void initEmojiLabels(){
    	for(int i=0; i < EMOJI_NUM; i++){
        	JLabel emojiLabel = ViewHelper.createImageLabel(BAR_HEIGHT, BAR_HEIGHT, 
        			new File(Utils.PICATURE_DIR, "smile.png").getPath());
        	emojiLabel.setSize(BAR_HEIGHT, BAR_HEIGHT);
        	emojiLabels.add(emojiLabel);
        	add(emojiLabel);
        }
    }
    
    public void addTimingListener(ActionListener listener) {
    	timer = new Timer(QuestionTime.DELAY_TIME * 1000, listener);
    }
    
    public void startTimer(){
    	timer.start();
    }
    
    public void stopTimer(){
    	timer.stop();
    }
    
    private void setProgressBarValue(int leftTime) {
    	progressBar.setValue(leftTime);
    	progressBar.setString(String.valueOf(leftTime));
    	progressBar.setFont(Utils.getFormatFont(Font.ITALIC | Font.BOLD, 16));
    }
    
    private void checkAndShowEmoji(QuestionTime questionTime){
    	String emojiPath = null;
    	if(questionTime.isShowSmileEmotion())
    		emojiPath = new File(Utils.PICATURE_DIR, "smile.png").getPath();
    	else if(questionTime.isShowCryEmotion())
    		emojiPath = new File(Utils.PICATURE_DIR, "cry.png").getPath();
    	else
    		emojiPath = new File(Utils.PICATURE_DIR, "neutral.png").getPath();
    	if(emojiPath == null)
    		return;
    	for(JLabel emojiLabel : emojiLabels)
    		emojiLabel.setIcon(ViewHelper.getResizeImageIcon(BAR_HEIGHT, BAR_HEIGHT, emojiPath));
    }

	@Override
	public void update(Observable observable, Object object) {
		QuestionTime questionTime = (QuestionTime) observable;
	
		int leftTime = questionTime.getLeftTime();
		setProgressBarValue(leftTime);
		checkAndShowEmoji(questionTime);
	}
}
