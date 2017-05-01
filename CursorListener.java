import java.util.List;
import java.util.function.Function;

import javax.swing.JTextArea;

public class CursorListener implements Runnable {

	List<StoryProblemObject> problems;
	JTextArea textArea;
	int problemSentenceNo;
	public CursorListener(JTextArea textArea, List<StoryProblemObject> problems) {
		this.textArea = textArea;
		this.problems  = problems;
		this.problemSentenceNo = -1;
	}
	@Override
	public void run() {
		String storytext = textArea.getText();
		String sentences[] = storytext.split(".?!");
		int cursorLocation = textArea.getCaretPosition();
		int cursorCurrentSentence = storytext.substring(0,cursorLocation).split(".?!").length;
		StoryProblemObject obj = getProblemIfPresent(cursorCurrentSentence);
		if(obj == null)
		{
			//no problem, do nothing
			if(cursorCurrentSentence != problemSentenceNo && problemSentenceNo != -1)
			{
				String newSentence = storytext.split("!.?")[problemSentenceNo];
				problemSentenceNo = -1;
				//pass new sentence to NLPtoOAO and check if problem TODO
			}
		}
		else
		{
			//yes problem DISPLAY PROBlEM TODO
			problemSentenceNo = cursorCurrentSentence;
			
		}
	}
	
	/**
	 * TODO Patrick: implement this
	 * @param originalNLPSentence Original NLP Sentence to look for in problems
	 * @return returns a Problem if there is Problem for the string, else null 
	 */
	public StoryProblemObject getProblemIfPresent(int sentenceIndex) {
		return null;
	}

}
