import java.util.ArrayList;

public class DigitalObject {

	//object class for each object in the story
	public String name;
	public DigitalObject ObjectType;
	public ArrayList<DigitalState> states = new ArrayList<DigitalState>();
	public ArrayList<DigitalAffordance> affordances=new ArrayList<DigitalAffordance>();
	boolean isPassive;
	public StateType type;
	public static final DigitalObject EMPTY_OBJECT = new DigitalObject("");
	public DigitalObject(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.isPassive = false;
	}
	public void addAffordance(DigitalAffordance affordance)
	{
		int i = indexOf(affordance);
		if(i < 0) {
			affordances.add(affordance);
		} else {
			for(int j = 0; j < affordance.instances.size(); j++) {
				affordances.get(i).addActionTuple(affordance.instances.get(j).affordee, affordance.instances.get(j).type);
			}
			
		}
	}
	
	public int indexOf(DigitalAffordance affordance) {
		for(int i = 0; i < affordances.size(); i++) {
			if(affordances.get(i).name.equalsIgnoreCase(affordance.name)) {
				return i;
			}
		}
		return -1;
	}
	
//	public void addState(DigitalState state)
//	{
//		states.add(state);
//	}
}
