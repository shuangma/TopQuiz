package ma.assign3.view.question;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import ma.assign3.common.Utils;
import ma.assign3.model.Question;

/**
 * Showing each question's content
 * @author Shuang Ma
 *
 */

public class QuestionContentView extends JPanel implements Observer {
	private static final int ROW_NUM = 10;
	private static final int COLUMN_NUM = 40;
	
	private static final String NO_QUESTION = "NO MORE QUESTIONS UNDER THIS TOPIC, PLEASE CHOOSE ANOTHER TOPIC TO CONTINUE";
	
	private JTextArea questionArea;
	
	public QuestionContentView() {
		questionArea = new JTextArea(ROW_NUM, COLUMN_NUM);
		questionArea.setFont(Utils.getFormatFont());
		questionArea.setLineWrap(true);
		add(questionArea);
	}
	
	public void update(Observable observable, Object object) {
		if(object == null)
			questionArea.setText(NO_QUESTION);
		else{
			Question question = (Question) object;
			questionArea.setText(question.getQuestionContent());
		}
	}

}
