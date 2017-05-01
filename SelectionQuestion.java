import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.swing.JFrame;

/**
 * 
 * @author Patrick
 *
 * given a list of affordances 
 * or objects and a property, 
 * asks the user to divide the 
 * list into two sublists: 
 * members who have the 
 * property and members who do 
 * not have the property
 */
public abstract class SelectionQuestion<T> implements Question {
	private String prompt;
	public List<T> list;
	//public HashSet<T> selected;
	ActionEffect[] selected;
	//public boolean[] selected2;
	ActionEffect[] selected2;
	
	public SelectionQuestion(String prompt, List<T> list) {
		this.prompt = prompt;
		this.list = list;
	}
	
	public void setDefaultSelection(HashMap<T, ActionEffect> selection) {
		// TODO: remove from interface
	}
	
	public void promptUser() {
		if (list.isEmpty()) {
			return;
		}
		String[] list2 = new String[list.size()];
		for (int i = 0; i<list.size(); i++) {
			list2[i] = getName(list.get(i));
		}
//		selected2 = new boolean[list.size()];
//		for (int i = 0; i<list.size(); i++) {
//			if (selected.contains(list.get(i))) {
//				selected2[i] = true;
//			}
//		}
		selected2 = new ActionEffect[list.size()];
		SelectionFrame frame = new SelectionFrame(list2, selected2, prompt);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		// after dialog is closed
		for (int i = 0; i<list.size(); i++) {
			selected = selected2.clone();
		}

		return;
	}
	
	public abstract void applyAnswer();
	
	public abstract String getName(T t);
}