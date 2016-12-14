package ma.assign3.common;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Some handful methods
 * @author Shuang Ma
 *
 */

public class Utils {
	// Constants
	public static final int FRAME_WIDTH = 1024;
	public static final int FRAME_HEIGHT = 620;
	
	public static final String QUESTIONS_FILE = new File("resources", "questions.json").getPath();
	public static final String PICATURE_DIR = new File("resources", "pictures").getPath();
	public static final String LOGO_FILE = new File(PICATURE_DIR, "quiz.jpg").getPath();
	
	public static final String INPUT_TYPE = "input";
	public static final String SINGLE_TYPE = "single";
	public static final String MULTIPLE_TYPE = "multi";
	
	
	public static final String PICTURE_KEY = "picture";
	public static final String QUESTION_KEY = "question";
	public static final String CHOICES_KEY = "choices";
	public static final String TOPIC_KEY = "topic";
	public static final String POINTS_KEY = "points";
	public static final String QUESTION_TYPE_KEY = "question_type";
	public static final String ANSWER_KEY = "answer";

	/**
	 * Join the arraylist to a string with the given delimiter
	 * @param input an arraylist of string
	 * @param delimiter delimiter
	 * @return a joined string
	 */
	public static String join(ArrayList<String> input, String delimiter){
		String output = "";
		int length = input.size();
		for(int i=0; i < length; i++){
			if(i == length -1)
				output += input.get(i);
			else
				output += (input.get(i) + delimiter);
		}
		
		return output;
	}
	
	public static void initMainPanelBorder(JPanel panel, String title){
		TitledBorder border = new TitledBorder(title);
		border.setTitleFont(Utils.getBorderTitleFont(25));
		panel.setBorder(border);
	}
	
	public static void initSubPanelBorder(JPanel panel, String title){
		TitledBorder border = new TitledBorder(title);
		border.setTitleFont(Utils.getBorderTitleFont(18));
		panel.setBorder(border);
	}
	
	public static Font getBorderTitleFont(int fontSize){
		Font font = new Font("Times New Roman", Font.BOLD | Font.ITALIC, fontSize);
		return font;
	}
	
	public static Font getFormatFont(){
		Font font = new Font("Times New Roman", Font.PLAIN, 16);
		return font;
	}
	
	public static Font getFormatFont(int mode, int size){
		Font font = new Font("Times New Roman", Font.PLAIN | mode, size);
		return font;
	}

	/**
	 * Read in the content of a file which is given by the file path
	 * @param filePath the path of file
	 * @return the content of file
	 */
	public static String readFile(String filePath){
		StringBuilder content = new StringBuilder();
		File inputFile = new File(filePath);
		if(inputFile.exists()){
			BufferedReader bufferedReader = null;
			try {
				bufferedReader = new BufferedReader(new FileReader(filePath));
		        String line = bufferedReader.readLine();

		        while (line != null) {
		        	content.append(line);
		            line = bufferedReader.readLine();
		        }
			} catch (Exception e) {
				e.printStackTrace();
		    } finally {
		    	if(bufferedReader != null)
		    		try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    }
		}
		return content.toString();
	}
}
