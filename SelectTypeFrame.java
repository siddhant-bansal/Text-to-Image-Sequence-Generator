import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SelectTypeFrame extends JDialog implements ActionListener{
	private JLabel question = new JLabel("Select a type for each character");
	private JButton ok = new JButton("OK");
	private String[] characters;
	private String[] response;
	private String[] types;
	private String[] results;
	private JLabel[] listcharacters;
	private JComboBox[] typelist;
	private JPanel buttonpanel = new JPanel();
	private JPanel gridpanel = new JPanel();
	
	public SelectTypeFrame( String[] characters, String[] types, String[] results){
		this.setModal(true);
		this.characters = characters;
		this.types = types;
		this.results = results;
		this.setLayout(new BorderLayout(10,10));
		gridpanel.setLayout(new GridLayout(characters.length,2));
		buttonpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonpanel.add(ok);
		typelist = new JComboBox[characters.length];
		listcharacters = new JLabel[characters.length];
		for(int i = 0; i < characters.length ; i++){
			typelist[i] = new JComboBox(types);
//			typelist[i].setSelectedIndex(32);
			typelist[i].setMaximumRowCount(3);
			listcharacters[i] = new JLabel(characters[i]);
			gridpanel.add(listcharacters[i]);
			gridpanel.add(typelist[i]);
		}
		this.add(question, BorderLayout.NORTH);
		this.add(gridpanel,BorderLayout.CENTER);
		this.add(buttonpanel, BorderLayout.SOUTH);
		ok.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "OK":
			for(int i = 0; i < characters.length; i++){
				results[i] = types[typelist[i].getSelectedIndex()];
			}
			this.dispose();
		}
		
	}

}
