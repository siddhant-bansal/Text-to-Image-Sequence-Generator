import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyQuestions {
	public static final String IS_PASSIVE_TEXT = "Please check all smart objects that are passive.";
	public static final String IS_TERMINAL_TEXT = "Please check all actions that result in termination for either the actor or the affordees.";
	public static final String DISCONNECT_ERROR = "Only reachable if there is disconnect between world and storystring.";
	
	public static void ask(DigitalStoryWorld world, String storystring) {
		ArrayList<DigitalObject> objects2 = world.objects;
		
		int i = 0;
		
//		List<DigitalObject> passiveCandidates = new ArrayList<DigitalObject>();
//		int i = 0;
//		while(i < objects2.size()){
//			if(objects2.get(i).affordances.isEmpty()){
//				passiveCandidates.add(objects2.get(i));
//			}
//			i++;
//		}
//		SelectionQuestion<DigitalObject> isPassive = 
//				new SelectionQuestion<DigitalObject>(
//						IS_PASSIVE_TEXT, 
//						passiveCandidates) {
//			@Override
//			public void applyAnswer() {
//				for (DigitalObject o : list) {
//					o.isPassive = selected.contains(o);
//				}
//			}
//			@Override
//			public String getName(DigitalObject o) {
//				return o.name;
//			}
//		};
//		isPassive.setDefaultSelection(passiveCandidates);
		
		HashMap<String, DigitalObject> objectLookup = 
				new HashMap<String, DigitalObject>();
		for (DigitalObject o : objects2) {
			objectLookup.put(o.name, o);
		}
		HashMap<DigitalObject, ActionBundle> LTermCandidates = 
				new HashMap<DigitalObject, ActionBundle>();
		HashMap<DigitalObject, ActionBundle> RTermCandidates =
				new HashMap<DigitalObject, ActionBundle>();
		String[] sentences = storystring.split("\n");
		for (i = 0; i<sentences.length; i++) {
			String[] sentence = sentences[i].trim().split(" ");
			if (sentence.length<=2) {
				ActionBundle a = new ActionBundle();
				a.actor = objectLookup.get(sentence[0]);
				a.affordance = affordanceLookup(a.actor, sentence[1]);
				a.instance = a.affordance.instances.get(0);
				LTermCandidates.put(a.actor, a);
			} else {
				if (sentence[1].equals(DigitalStoryWorld.KEYWORD_IS)) {
					continue;
				}
				for (int j = 2; j<sentence.length; j++) {
					ActionBundle a = new ActionBundle();
					//a.actor = objectLookup.get(sentence[0]);
					//a.affordance = affordanceLookup(a.actor, sentence[1]);
					//a.instance = instanceLookup(a.affordance, objectLookup.get(sentence[j]));
					//LTermCandidates.put(a.actor, a);
					//RTermCandidates.put(a.instance.affordee, a);
				}
			}		
		}
		
		HashSet<ActionBundle> uniqueActions = new HashSet<ActionBundle>();
		uniqueActions.addAll(LTermCandidates.values());
		uniqueActions.addAll(RTermCandidates.values());
		SelectionQuestion<ActionBundle> isTerminal = 
				new SelectionQuestion<ActionBundle>(
						IS_TERMINAL_TEXT, 
						new ArrayList<ActionBundle>(uniqueActions)) {

							@Override
							public void applyAnswer() {
								for (int i = 0; i<list.size(); i++) {
									ActionBundle a = list.get(i);
									switch(selected[i]) {
									case LDORM:
									case RDORM:
									case LRDORM:
										a.instance.type = ActionType.DORM;
										break;
									case LTERM:
									case RTERM:
									case LRTERM:
										a.instance.type = ActionType.TERM;
										break;
									default:
									}
									a.instance.effect = selected[i];
									DbHelper.updateActionEffect(a);
								}
							}

							@Override
							public String getName(ActionBundle t) {
								StringBuilder s = new StringBuilder();
								s.append(t.actor.name);
								s.append(" ");
								s.append(t.affordance.name);
								if (null!=t.instance) {
									s.append(" ");
									s.append(t.instance.affordee.name);
								}
								return s.toString();
							}
			
		};
		
		// PRECONDITION QUESTION Rush
		PreconditionQuestion isPrecondition = new PreconditionQuestion(world, storystring);
		
		
//		Question[] questions = {isPassive, isTerminal};
		Question[] questions = {isTerminal, isPrecondition};
		for (Question q : questions) {
			q.promptUser();
			q.applyAnswer();
		}
		
		// OUTPUT TO CONSOLE
//		System.out.println("isPassive");
//		System.out.println("---------");
//		for (DigitalObject o : objects2) {
//			System.out.println(o.name + ": " + ((o.isPassive)? "Passive" : "Active"));
//		}
//		System.out.println();
	}
	
	private static ActionTuple instanceLookup(DigitalAffordance affordance, DigitalObject affordee) {
		DigitalObject affordee2 = affordee;
		while (affordee2.ObjectType!=null && affordee2.ObjectType!=affordee2) {
			affordee2 = affordee2.ObjectType;
		}
		for (ActionTuple instance : affordance.instances) {
			if (instance.affordee.name.equals(affordee2.name) || 
					//TODO: remove below workaround for null-pointer
					instance.affordee.name.equals(affordee.name)) {
				return instance;
			}
		}
		// only reachable if there is disconnect between world and storystring
		System.err.println(affordance.name + " " + affordee.name + "->" + affordee2.name);
		new Exception(DISCONNECT_ERROR).printStackTrace();
		return null;
	}

	public static DigitalAffordance affordanceLookup(DigitalObject actor, String name) {
		
		for (DigitalAffordance aff : actor.affordances) {
			if (aff.name.equals(name)) {
				return aff;
			}
		}
		// only reachable if there is disconnect between world and storystring
		System.err.println(actor.name + " " + name);
		new Exception(DISCONNECT_ERROR).printStackTrace();
		return null;
	}
}