public class ActionBundle {
	public DigitalObject actor;
	public DigitalAffordance affordance;
	public ActionTuple instance;
	
	public static String getTypeName(DigitalObject o) {
		DigitalObject o2 = o;
		while (o2.ObjectType!=null && o2.ObjectType!=o2) {
			o2 = o2.ObjectType;
		}
		return o2.name;
	}
	
	public static ActionBundle getBundle(DigitalStoryWorld world, String line) {
		String[] lineA = line.split(" ");
		String actorName = lineA[0].trim();
		String affordanceName = lineA[1].trim();
		String affordeeName;
		if (lineA.length<3) {
			affordeeName = "";
		} else {
			affordeeName = lineA[2].trim();
		}
		
		ActionBundle ret = new ActionBundle();
		
		for (DigitalObject o : world.objects) {
			if (o.name.equals(actorName)) {
				ret.actor = o;
				break;
			}
		}
		for (DigitalAffordance aff : ret.actor.affordances) {
			if (aff.name.equals(affordanceName)) {
				ret.affordance = aff;
				break;
			}
		}
		for (ActionTuple at : ret.affordance.instances) {
			if (getTypeName(at.affordee).equals(affordeeName)) {
				ret.instance = at;
				break;
			}
		}
		
		return ret;
	}
}
