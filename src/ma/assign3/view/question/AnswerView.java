package ma.assign3.view.question;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import ma.assign3.common.Utils;
import ma.assign3.model.Question;

/**
 * Combining the image view and choice view
 * @author Shuang Ma
 *
 */

public class AnswerView extends JPanel implements Observer {
	private ImageView imageView;
	private ChoiceView choiceView;
	
	public AnswerView() {
		setLayout(new GridLayout(1,2));
		
		imageView = null;
		choiceView = null;
	}

	public void update(Observable observable, Object object) {
		if(object == null){
			if(imageView != null)
				remove(imageView);
			if(choiceView != null)
				remove(choiceView);
			return;
		}
		
		Question question = (Question) object;
		
		//Update image of this question
		if(imageView != null)
			remove(imageView);
		
		imageView = new ImageView();
		String imagePath = question.getPictureFilePath();
		imageView.setImage(imagePath);
		add(imageView);
		
		//Update choiceView of question area
		if(choiceView != null)
			remove(choiceView);
		choiceView = createChoiceView(question);
		add(choiceView);
		choiceView.displayChoice(question);
		revalidate();
	}
	
	private ChoiceView createChoiceView(Question question) {
		ChoiceView choiceView;
		String type = question.getQuestionType();
		
		if(type.equals(Utils.INPUT_TYPE)) {
			choiceView = new InputViewer();
		}else if(type.equals(Utils.SINGLE_TYPE)) {
			choiceView = new SingChoiceViewer();
		}else if(type.equals(Utils.MULTIPLE_TYPE)) {
			choiceView = new MultiChoiceViewer();
		}else
			return null;
		
		return choiceView;
	}
	
	public ChoiceView getChoiceView() {
		return choiceView;
	}

	
}
