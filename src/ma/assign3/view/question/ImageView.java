package ma.assign3.view.question;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ma.assign3.common.ViewHelper;

/**
 * Showing the image related to the question
 * @author Shuang Ma
 *
 */

public class ImageView extends JLabel {
	private final int WIDTH = 250;
	private final int HEIGHT = 260;
	
	public ImageView() {
		setSize(HEIGHT, WIDTH);
	}
	
	public void setImage(String imagePath) {
		ImageIcon newImageIcon = ViewHelper.getResizeImageIcon(WIDTH, HEIGHT, imagePath);
		setIcon(newImageIcon);
		setVisible(true);
	}
}
