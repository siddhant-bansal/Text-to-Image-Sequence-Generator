import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class newPreconditionFrame extends JDialog implements ActionListener{
	private JPanel gridpanel = new JPanel();
	private JButton done = new JButton("OK");
	private JPanel bottom = new JPanel();
	private JList[] mycomboboxes;
	private JLabel[] sentencelabels;
	private String[] sentences;
	private JLabel storyline = new JLabel("Story Line");
	private JLabel question;
	private JScrollPane[] listpane;
	private JScrollPane pane;
	public boolean[][] matrix;
	public boolean isDone;
	
	public newPreconditionFrame(String question, String[] sentences, String[] top, boolean[][] matrix){
		this.matrix = matrix;
		this.setModal(true);
		this.question = new JLabel(question);
		this.sentences = sentences;
		this.sentencelabels = new JLabel[sentences.length];
		this.mycomboboxes = new JList[sentences.length];
		this.gridpanel.setLayout(new GridLayout(sentences.length,2));
		this.listpane = new JScrollPane[sentences.length];
		
		isDone = false;
		for(int i =0; i < sentences.length; i++){
			sentencelabels[i] = new JLabel(sentences[i]);
			mycomboboxes[i] = new JList(sentences);
			mycomboboxes[i].setVisibleRowCount(2);
			this.listpane[i] = new JScrollPane(mycomboboxes[i]);
			this.gridpanel.add(sentencelabels[i]);
			this.gridpanel.add(listpane[i]);
		}
		this.setLayout(new BorderLayout(10,10));
		bottom.setLayout(new FlowLayout(FlowLayout.CENTER));
		done.addActionListener(this);
		bottom.add(done);
		pane = new JScrollPane(gridpanel);
		this.add(this.question,BorderLayout.NORTH);
		this.add(pane, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "OK":
			for(int k = 0; k < sentences.length; k++){
				int[] templist = mycomboboxes[k].getSelectedIndices();
				for(int i =0 ; i < templist.length; i++){
					matrix[k][templist[i]] = true;
				}
			}
			this.isDone = true;
			this.dispose();
		}
	}
}
