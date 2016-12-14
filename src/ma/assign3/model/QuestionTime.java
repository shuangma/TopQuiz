package ma.assign3.model;

import java.util.Observable;

/**
 * A fixed time for each question
 * @author Shuang Ma
 *
 */

public class QuestionTime extends Observable{
	// Total answer time is 15s
	public static final int TOTAL_TIME = 15;
	// Decrease by 1s each time
	public static final int DELAY_TIME = 1;
	
	private int leftTime = TOTAL_TIME;
	
	public void publish(){
		leftTime -= DELAY_TIME;
		
		setChanged();
		notifyObservers();
	}
	
	public int getLeftTime(){
		return leftTime;
	}
	
	public boolean isTimeRunOut(){
		if(leftTime <= 0)
			return true;
		return false;
	}
	
	public boolean isShowSmileEmotion(){
		if(leftTime > (TOTAL_TIME/3) *2)
			return true;
		return false;
	}
	
	public boolean isShowCryEmotion(){
		if(leftTime < (TOTAL_TIME/3))
			return true;
		return false;
	}
	
	public boolean isShowNeutralEmomotion(){
		if(!isShowSmileEmotion() && !isShowCryEmotion())
			return true;
		return false;
	}
	
	public void resetLeftTime(){
		leftTime = TOTAL_TIME;
		
		setChanged();
		notifyObservers();
	}
	
}
