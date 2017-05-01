import java.util.ArrayList;

public class DigitalAction {
	
	//add state here
	public DigitalState state;
	public DigitalAffordance affordance;
	public ArrayList<DigitalObject> affordees;
	boolean isTerminal;
	public DigitalAction(DigitalAffordance affordance, ArrayList<DigitalObject> affordees, DigitalState state) {
		// TODO Auto-generated constructor stub
		this.affordance = affordance;
		this.affordees = affordees; 
		this.state=state;
		this.isTerminal = false;
	}
}

