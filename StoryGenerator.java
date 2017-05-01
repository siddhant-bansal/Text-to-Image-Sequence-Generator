import java.util.*;
import java.util.List;

public class StoryGenerator {
	
	enum StoryAlgorithm { 
		RK9, KIPPS
	}
	
	private ArrayList<DigitalObject> objects;
	private int MAX_LENGTH;
	private int MIN_LENGTH;
	private StoryAlgorithm sa;
	
	public StoryGenerator(ArrayList<DigitalObject> objs) {
		objects = objs;
		setStoryAlgorithm(StoryAlgorithm.KIPPS); //default story algo
	}
	
	public StoryGenerator(ArrayList<DigitalObject> objs, StoryAlgorithm e) {
		objects = objs;
		setStoryAlgorithm(e);
	}
	
	public String writeStory() {
		Random rng = new Random();
		//randomly pick a story length
		int length = rng.nextInt(MAX_LENGTH-MIN_LENGTH) + MIN_LENGTH;
		String result = "";
		//set up initial state of the world
		for(int i = 0; i < objects.size(); i++) {
			for(int j = 0; j < objects.get(i).affordances.size(); j++) {
				for(int k = 0; k < objects.get(i).affordances.get(j).instances.size(); k++) {
					objects.get(i).affordances.get(j).instances.get(k).active = false;
				}
			}
		}
		switch(sa) {
		case RK9:
			//for each line of the story
			for(int i = 0; i < length; i++) {
				//pick an active object to perform an action 
				boolean active = false;
				int obj = 0;
				while (!active) {
					obj = rng.nextInt(objects.size());
					if(!objects.get(obj).isPassive) {
						active = true;
					}
				}
				result += objects.get(obj).name + " ";
				//pick an action for it to perform
				int act = rng.nextInt(objects.get(obj).affordances.size());
				result += objects.get(obj).affordances.get(act).name + " ";
				//pick at least one object for it to happen to
				boolean[] affs = new boolean[objects.get(obj).affordances.get(act).instances.size()];
				boolean min = false;
				while(!min) {
					for(int j = 0; j < affs.length; j++) {
						affs[j] = rng.nextBoolean();
						if(affs[j]) {
							min = true;
							result += objects.get(obj).affordances.get(act).instances.get(j).affordee.name + " ";
						}
					}
				}
				result += "\n";
			}
			break;
		case KIPPS:
			String line = "";
			ArrayList<DigitalObject> activeObjs = new ArrayList<DigitalObject>();
			ArrayList<DigitalObject> terminatedObjs = new ArrayList<DigitalObject>();
			ArrayList<DigitalObject> dormantObjs = new ArrayList<DigitalObject>();
			for(int i = 0; i < objects.size(); i++) {
				if(!objects.get(i).isPassive) {
					activeObjs.add(objects.get(i));
				}
			}
			DigitalObject lastAfforder = null;
			DigitalAffordance lastAffordance = null;
			DigitalObject lastAffordee = null;
			//for each line of the story
			for(int i = 0; i < length; i++) {
				line = "";
				//pick an active object to perform an action
				int obj = rng.nextInt(activeObjs.size());
				line += activeObjs.get(obj).name + " ";
				//check for affordances
				if(activeObjs.get(obj).affordances.size() < 1) {
					i--;
					continue;
				}
				//pick one of its affordances
				int act = rng.nextInt(activeObjs.get(obj).affordances.size());
				line += activeObjs.get(obj).affordances.get(act).name + " ";
				//pick an affordee
				int inst = rng.nextInt(activeObjs.get(obj).affordances.get(act).instances.size());
				//check that affordee hasn't been terminated
				boolean alive = true;
				for(int j = 0; j < terminatedObjs.size() && alive; j++) {
					if(terminatedObjs.get(j).name.equalsIgnoreCase(activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee.name)) {
						alive = false;
						continue;
					} 
				}
				//chech that affordee isn't dormant
				for(int j = 0; j < dormantObjs.size() && alive; j++) {
					if(dormantObjs.get(j).name.equalsIgnoreCase(activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee.name)) {
						alive = false;
						continue;
					} 
				}
				if(!alive) {
					i--;
					continue;
				}
				//check that action hasn't already been done
 				boolean setting = activeObjs.get(obj).affordances.get(act).instances.get(inst).active;
				if(!setting || activeObjs.get(obj).affordances.get(act).instances.get(inst).type == ActionType.NORM) {
					//update state
					activeObjs.get(obj).affordances.get(act).instances.get(inst).active = true;
					line += activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee.name + " ";
				} else {
					i--;
					continue;
				}
				//check for repitition
				if(i==0) {
					lastAfforder = activeObjs.get(obj);
					lastAffordance = activeObjs.get(obj).affordances.get(act);
					lastAffordee = activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee;
				} else {
					if(activeObjs.get(obj).equals(lastAfforder) && activeObjs.get(obj).affordances.get(act).equals(lastAffordance) && activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee.equals(lastAffordee)) {
						i--;
						continue;
					}
				}
				//update last variables
				lastAfforder = activeObjs.get(obj);
				lastAffordance = activeObjs.get(obj).affordances.get(act);
				lastAffordee = activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee;
				//handle different actiontypes
				//not sure what the difference is here in terms of implentation so leaving it out for now
				/*
				switch(activeObjs.get(obj).affordances.get(act).instances.get(inst).type) {
				case NORM:
					
					break;
				case TERM:
					
					break;
				case DORM:
					
					break;
				}*/
				//handle different actioneffects
				switch(activeObjs.get(obj).affordances.get(act).instances.get(inst).effect) {
				case LTERM:
					terminatedObjs.remove(activeObjs.get(obj));
					terminatedObjs.add(activeObjs.get(obj));
					break;
				case RTERM:
					terminatedObjs.remove(activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee);
					terminatedObjs.add(activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee);
					break;
				case LRTERM:
					terminatedObjs.remove(activeObjs.get(obj));
					terminatedObjs.add(activeObjs.get(obj));
					terminatedObjs.remove(activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee);
					terminatedObjs.add(activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee);
					break;
				case LDORM:
					dormantObjs.remove(activeObjs.get(obj));
					dormantObjs.add(activeObjs.get(obj));
					break;
				case RDORM:
					dormantObjs.remove(activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee);
					dormantObjs.add(activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee);
					break;
				case LRDORM:
					dormantObjs.remove(activeObjs.get(obj));
					dormantObjs.add(activeObjs.get(obj));
					dormantObjs.remove(activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee);
					dormantObjs.add(activeObjs.get(obj).affordances.get(act).instances.get(inst).affordee);
					break;
				}
				result += line + "\n";
			}
			break;
		}
		return result;
	}
	
	public void setStoryAlgorithm(StoryAlgorithm e) {
		sa = e;
		switch(e) {
			case RK9:
				MAX_LENGTH = 10;
				MIN_LENGTH = 5;
				break;
			case KIPPS:
				MAX_LENGTH = 40;
				MIN_LENGTH = 10;
				break;
			default:
				setStoryAlgorithm(StoryAlgorithm.KIPPS);
				break;
		}
	}
}