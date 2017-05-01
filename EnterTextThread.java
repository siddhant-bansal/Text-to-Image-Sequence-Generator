import java.util.List;

import javax.swing.JTextArea;

public class EnterTextThread extends Thread {
	public SentenceThread root;
	public int firstdelimiter;
	public int lastdelimiter;
	public boolean basecase;
	public boolean running;
	JTextArea textArea;
	int problemSentenceNo;

	public EnterTextThread() {
		root = null;
		basecase = false;
		running = true;
		lastdelimiter = 0;
		this.problemSentenceNo = -1;
	}

	@Override
	public void run() {
		String storystring;
		System.out.println("Run begins");
		while (MyFrame.twostorytxt == null) {
			continue;
		}
		textArea = MyFrame.twostorytxt;
		while (true) {
			while ((storystring = MyFrame.twostorytxt.getText()) == null) {
				continue;
			}
			
//			System.out.println(storystring+ "       qqqqq      ");
			int cursorLocation = MyFrame.twostorytxt.getCaretPosition();
			String [] sentences = storystring.split("\\.");
			MyFrame.sentences = sentences.length;
			int cursorCurrentSentence = -1;
			try{
				cursorCurrentSentence = storystring.substring(0, cursorLocation).split("\\.").length - 1;
			}
			catch(StringIndexOutOfBoundsException e){
				continue;
			}
			
			//System.out.println("Cursor current sentence is " + cursorCurrentSentence + " and number of sentences are " + sentences.length);
			if (cursorCurrentSentence == sentences.length - 1) {
				// user is towards the end and is editing things
				while (basecase == false) {
					while (storystring == null) {
						storystring = MyFrame.getEntertxt();
					}
					if (storystring.indexOf(".") == -1) {
						storystring = MyFrame.getEntertxt();
						continue;
					} else {
						String substring = storystring.substring(0, storystring.indexOf("."));
						System.out.println(substring + " append sentence");
						System.out.println("Created new thread for first sentence/ base case");
						MyFrame.sentences++;
						//root = new SentenceThread(storystring, substring, false, 0); 									
						//root.start();
						MyFrame.sentenceThreadFn(storystring, substring, false, 0);
						lastdelimiter = storystring.indexOf(".") + 1;
						basecase = true;
					}
				}
				while (running) {
					//System.out.println("inside while " + lastdelimiter);
					
					// System.out.println(storystring);
					// System.out.println(lastdelimiter + "\n");
					if(textArea.getCaretPosition()<lastdelimiter)
						break;
					storystring = MyFrame.twostorytxt.getText();
					if(storystring==null)
						continue;
					if (storystring.indexOf(".", lastdelimiter) == -1) {
						storystring = MyFrame.twostorytxt.getText();
						continue;
					} else {
						String substring = storystring.substring(lastdelimiter,
								storystring.indexOf(".", lastdelimiter));
						lastdelimiter = storystring.indexOf(".", lastdelimiter) + 1;
						if(substring.trim().equals(""))
							continue;
						MyFrame.sentences++;
						System.out.println(substring + "append sentence");
						System.out.println("Creating thread which is new sentence");
						//insertNode(storystring, substring, false, storystring.split("\\.").length-1);
						MyFrame.sentenceThreadFn(storystring, substring, false, storystring.split("\\.").length-1);
					}
					

				}
//				System.out.println("broken out");
			} else {
				// user is in the middle and is editing some string with errors
				System.out.println("editing string");
				
				storystring = MyFrame.twostorytxt.getText();
				problemSentenceNo = cursorCurrentSentence;
				StoryProblemObject[] problems = MyFrame.detectProblems(problemSentenceNo);
				System.out.println("Problem List: \n");
				for(StoryProblemObject problem: problems){
					System.out.println(problem.errorMessage);
				}
				cursorLocation = textArea.getCaretPosition();
				cursorCurrentSentence = storystring.substring(0, cursorLocation).split("\\.").length - 1;
				String tmp_storystring=storystring;
				while (cursorCurrentSentence == problemSentenceNo) {
//					System.out.println("The user is inside problem sentence");
					tmp_storystring = MyFrame.twostorytxt.getText();
					cursorLocation = textArea.getCaretPosition();
					cursorCurrentSentence = tmp_storystring.substring(0, cursorLocation).split("\\.").length - 1;
					continue;
				}
				lastdelimiter += (tmp_storystring.length() - storystring.length());
				storystring = tmp_storystring;
				// sentence editting complete
				System.out.println("Thread creaated which is updating edited sentence");
				String newSentence = storystring.split("\\.")[problemSentenceNo];
//				Thread root = new SentenceThread(storystring, newSentence, true, problemSentenceNo);
//				root.start();
				MyFrame.sentenceThreadFn(storystring, newSentence, true, problemSentenceNo);
				System.out.println(newSentence + " updateSentence");
			}
		}
	}

//	public void stop() {
//		this.running = false;
//	}

	public void insertNode(String storystring, String string, Boolean isUpdate, int index) {
		SentenceThread temp = root;
		while (temp != null) {
			temp = temp.next;
		}
		temp = new SentenceThread(storystring, string, isUpdate, index);
		temp.start();
		return;
	}
}
