package ma.assign3.view.main;


import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import ma.assign3.common.Utils;
import ma.assign3.view.question.AnswerView;
import ma.assign3.view.question.QuestionContentView;

/**
 * A view showing question content, related image and choices of this question
 * @author shuang
 *
 */

public class QuestionView extends JPanel {
	private static final String TITLE = "Question";
	
	private QuestionContentView questionContentView;
	private AnswerView answerView;
	
	public QuestionView() {
		initBorder();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Leave some space to the top
		add(Box.createVerticalStrut(10));
				
		questionContentView = new QuestionContentView();
		add(questionContentView);
		
		add(Box.createVerticalStrut(10));
		
		answerView = new AnswerView();
		add(answerView);
	}
	
	private void initBorder(){
		Utils.initMainPanelBorder(this, TITLE);
	}
	
	public QuestionContentView getQuestionContentView() {
		return questionContentView;
	}
	
	public AnswerView getAnswerView() {
		return answerView;
	}
}