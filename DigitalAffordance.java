import java.util.ArrayList;

class ActionTuple {
	public DigitalObject affordee;
	public boolean active;
	public ActionType type;
	public ActionEffect effect;
	
	public ActionTuple(DigitalObject affordee, boolean active, ActionType type) {
		this.affordee = affordee;
		this.active = active;
		this.type = type;
		this.effect = ActionEffect.NORM;
	}
	
	@Override
	public String toString() {
		return affordee.name;
	}
}

public class DigitalAffordance {
	
	//add state here
	public String name;
	public ArrayList<ActionTuple> instances;
	public DigitalAffordance(String name) {
		this.name = name;
		instances = new ArrayList<ActionTuple>();
	}
	public DigitalAffordance(String name, ArrayList<ActionTuple> instances) {
		// TODO Auto-generated constructor stub
		this.name = name;
		if (instances==null)
			this.instances=new ArrayList<ActionTuple>();
		else
			this.instances = instances;
	}
	
	public void addActionTuple(DigitalObject affordee, ActionType type) {
		if(!contains(affordee, type)) {
			instances.add(new ActionTuple(affordee, false, type));
		}
	}
	
	public void addActionTuple(ArrayList<DigitalObject> affordees, ActionType type) {
		for(int i=0; i < affordees.size(); i++) {
			addActionTuple(affordees.get(i), type);
		}
	}
	
	public boolean contains(DigitalObject affordee, ActionType type) {
		for(int i = 0; i < instances.size(); i++) {
			ActionTuple a = instances.get(i);
			if(a.affordee.equals(affordee) && a.type == type) {
				//duplicate action
				//side effect for repeated actions
				return true;
			}
		}
		return false;
	}
	public boolean isIdentical(ActionTuple a1, ActionTuple a2)
	{
		if(!a1.affordee.name.equals(a2.affordee.name))
			return false;
		if(a1.effect!=a2.effect)
			return false;
		if(a1.type!=a2.type)
			return false;
		return true;
	}
}

