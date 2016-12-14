package ma.assign3.view.question;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

import ma.assign3.common.Utils;
import ma.assign3.model.Question;

/**
 * Showing the choices or input area of the question
 * @author Shuang Ma
 *
 */

public abstract class ChoiceView extends JPanel {	
	public abstract void displayChoice(Question question);
	
	public abstract ArrayList<String> getUserAnswer();
}
	
class InputViewer extends ChoiceView {
	private static final int ROW_NUM = 10;
	private static final int COLUMN_NUM = 19;
	
	private static final String TITLE = "Your Answer";
	private JTextArea inputArea;
	
	public InputViewer() {
		Utils.initSubPanelBorder(this, TITLE);
		inputArea = new JTextArea(ROW_NUM, COLUMN_NUM);
		add(inputArea);
	}
	
	@Override
	public void displayChoice(Question question) {
		inputArea.setFont(Utils.getFormatFont());
	}
	
	@Override
	public ArrayList<String> getUserAnswer() {
		ArrayList<String> userAnswer = new ArrayList<String>();
		userAnswer.add(inputArea.getText().trim());
		return userAnswer;
	}
}
	
//show choices if it is a single choice answer
class SingChoiceViewer extends ChoiceView {
	private static final String TITLE = "Single Choice";
	private ButtonGroup choiceGroup;
	private ArrayList<JRadioButton> choiceButtons;
	
	public SingChoiceViewer() {
		Utils.initSubPanelBorder(this, TITLE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void displayChoice(Question question) {
		choiceGroup = new ButtonGroup();
		choiceButtons = new ArrayList<JRadioButton>();
		
		for (String choice : question.getChoices()) {
			JRadioButton button = new JRadioButton(choice);
			button.setFont(Utils.getFormatFont());
			choiceGroup.add(button);
			choiceButtons.add(button);
			add(button);
		}
	}
	
	public ArrayList<String> getUserAnswer() {
		ArrayList<String> userAnswer = new ArrayList<String>();
		for (JRadioButton button : choiceButtons) {
            if (button.isSelected()) {
            	userAnswer.add(button.getText().trim());
            }
        }
		return userAnswer;
	}
}
	
//show choices if it is a multiple choice answer
class MultiChoiceViewer extends ChoiceView {
	private static final String TITLE = "Multiple Choice";
	private ArrayList<JCheckBox> checkBoxes;
	
	public MultiChoiceViewer() {
		Utils.initSubPanelBorder(this, TITLE);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void displayChoice(Question question) {
		checkBoxes = new ArrayList<JCheckBox>();
		for (String choice: question.getChoices()) {
			JCheckBox box = new JCheckBox(choice);
			box.setFont(Utils.getFormatFont());
			checkBoxes.add(box);
			add(box);
		}
	}
	
	
	public ArrayList<String> getUserAnswer() {
		ArrayList<String> userAnswer = new ArrayList<String>();
		
		for (JCheckBox box : checkBoxes) {
			if (box.isSelected()) {
				String choice = box.getText();
				userAnswer.add(choice);
			}
		}
		
		return userAnswer;
	}
}