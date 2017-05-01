import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import gnu.prolog.io.OperatorSet;
import gnu.prolog.io.ReadOptions;
import gnu.prolog.io.WriteOptions;
import gnu.prolog.term.AtomTerm;
import gnu.prolog.term.AtomicTerm;
import gnu.prolog.term.CompoundTerm;
import gnu.prolog.term.Term;
import gnu.prolog.term.VariableTerm;
import gnu.prolog.vm.Environment;
import gnu.prolog.vm.Interpreter;
import gnu.prolog.vm.Interpreter.Goal;
import gnu.prolog.vm.PrologCode;
import gnu.prolog.vm.PrologException;

public class PrologQueryMaster {
	public static final String DIR = "prolog";
	public static final String ACTION_F = "prolog/actionf.pro";
	public static final String ACTION_R1 = "prolog/actionr1.pro";
	public static final String ACTION_R2 = "prolog/actionr2.pro";
	public static final String TRAIT_F = "prolog/traitf.pro";
	public static final String TRAIT_R = "prolog/traitr.pro";
	public static final String TYPE_F = "prolog/typef.pro";
	public static final String ERROR_R = "prolog/errorr.pro";
	
	//processing files
	public static final String FACTS_FILE = "prolog/facts.pro";
	public static final String TMP = "prolog/tmp.pro";
	
	public static final String[] ALL_FILES_TO_CREATE = {
			ACTION_F, 
			TRAIT_F,
			TYPE_F
			};
	
	private Environment env;
	private Interpreter interpreter;
	
	public static void updateFacts() {
		try {
			boolean isFirstWrite = true;
			for (String file : new String[]
					{ACTION_F, ACTION_R1,
					 TRAIT_F, TRAIT_R,
					 TYPE_F,
					 ERROR_R}
			) {
				if (isFirstWrite) {
					Files.write(Paths.get(FACTS_FILE), 
							Files.readAllBytes(Paths.get(file)));
					isFirstWrite = false;
				} else {
					Files.write(Paths.get(FACTS_FILE), 
							Files.readAllBytes(Paths.get(file)), 
							StandardOpenOption.APPEND);
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PrologQueryMaster(String fileName) {
		env = new Environment();
		env.ensureLoaded(AtomTerm.get(fileName));
		interpreter = env.createInterpreter();
		env.runInitialization(interpreter);
	}
	
	public boolean verify(String fnName, String[] argNames) {
		CompoundTerm goalTerm = prep(fnName, argNames);
		try {
			int rc = interpreter.runOnce(goalTerm);
			if (rc == PrologCode.SUCCESS || rc == PrologCode.SUCCESS_LAST) {
				return true;
			}
		} catch (IllegalArgumentException e) {
			return false;
		} catch (PrologException e) {
			System.out.println(e.getStackTrace());
		}
		return false;
	}
	public String[][] query(String fnName, String[] argNames) {
		CompoundTerm goalTerm = prep(fnName, argNames);
		ArrayList<ArrayList<String>> output = 
				new ArrayList<ArrayList<String>>();
		try {
			Goal goal = interpreter.prepareGoal(goalTerm);
			while (true) {
				int rc = interpreter.execute(goal);
				switch (rc) {
				case PrologCode.SUCCESS:
				case PrologCode.SUCCESS_LAST:
					ArrayList<String> row = new ArrayList<String>();
					for (Term t : goalTerm.args) {
						row.add(t.dereference().toString());
					}
					output.add(row);
					if (rc==PrologCode.SUCCESS_LAST) {
						return output.stream().map(
							(u) -> u.toArray(new String[0]))
							.toArray(String[][]::new);
					}
					break;
				case PrologCode.FAIL:
					interpreter.stop(goal);
					break;
				case PrologCode.HALT:
					System.out.println("HALT");
					break;
				}
			}
		} catch (IllegalArgumentException e) {
			// for some reason, always reaches here at the end
			//e.printStackTrace();
			return output.stream().map(
					(u) -> u.toArray(new String[0]))
					.toArray(String[][]::new);
		} catch (PrologException e) {
			System.out.println(e.getStackTrace());
		} 
		return null;
	}
	private CompoundTerm prep(String fnName, String[] argNames) {
		Term[] args = new Term[argNames.length];
		for (int i = 0; i<args.length; i++) {
			argNames[i] = argNames[i].trim();
			if (Character.isLowerCase(argNames[i].charAt(0))) {
				args[i] = AtomTerm.get(argNames[i]);
			} else if (argNames[i].equals("_")) {
				args[i] = new VariableTerm();
			} else {
				args[i] = new VariableTerm(argNames[i]);
			}
		}
		return new CompoundTerm(AtomTerm.get(fnName), args);
	}
	
	
	public static boolean[][] guessPreconditions() {
		boolean[][] ret = null;
		try {
			List<String> actions = Files.readAllLines(Paths.get(ACTION_F));
			List<String> otherStmts = new ArrayList<String>();
			for (String file : new String[]
					{ACTION_R1, ACTION_R2, // include precondition rules
					 TRAIT_F, TRAIT_R,
					 TYPE_F}
			) {
				otherStmts.addAll(Files.readAllLines(Paths.get(file)));
			}
			ret = new boolean[actions.size()][actions.size()];
			for (int i = 0 ; i<actions.size(); i++) {
				for (int j = i+1; j<actions.size(); j++) {
					Files.write(Paths.get(TMP), actions.get(i).getBytes());
					Files.write(Paths.get(TMP), otherStmts, 
							StandardOpenOption.APPEND);
					PrologQueryMaster pqm = new PrologQueryMaster(TMP);
					// split action into fnName and argNames
					String[] arr = actions.get(j).split("[\\(\\)]");
					ret[i][j] = pqm.verify(arr[0], arr[1].split(","));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static String[] getError(String fnName, String[] argNames) {
		try {
			String[] fns = {"action", "trait", "type", "error"};
			String[][] files = {
					{ACTION_F, ACTION_R1},
					{TRAIT_F, TRAIT_R},
					{TYPE_F},
					{ERROR_R}
				};
			final int NUM_MSG_ARGS = 2;
			Files.write(Paths.get(TMP), "".getBytes());
			
			for (int i = 0; i<fns.length; i++) {
				if (fnName.equals(fns[i])) {
					String stmt;
					if (fnName.equals("action")) {
						stmt = String.format(NLPConnector.ACTION, argNames[0], argNames[1], argNames[2]);
					} else if (fnName.equals("trait")) {
						stmt = String.format(NLPConnector.TRAIT, argNames[0], argNames[1]);
					} else {
						stmt = "";
					}
					Files.write(Paths.get(TMP), stmt.getBytes(), StandardOpenOption.APPEND);
				}
				for (String file : files[i]) {
					Files.write(Paths.get(TMP), Files.readAllBytes(Paths.get(file)), StandardOpenOption.APPEND);
				}
			}
			
			String[] errorArgNames = new String[argNames.length+NUM_MSG_ARGS+1];
			errorArgNames[0] = "_";
			errorArgNames[1] = "_";
			errorArgNames[2] = fnName;
			for (int i = 0; i<argNames.length; i++) {
				errorArgNames[i+NUM_MSG_ARGS+1] = argNames[i];
			}
			PrologQueryMaster pqm = new PrologQueryMaster(TMP);
			String[][] rs = pqm.query("error", errorArgNames);
			String[] ret = new String[rs.length];
			for (int i = 0; i<rs.length; i++) {
				ret[i] = String.format(rs[i][0].substring(1, rs[i][0].length()-1).replace("\\x20\\", " "), 
						(Object[])rs[i][1].substring(1, rs[i][1].length()-1).split(","));
			}
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static StoryProblemObject[] getError(int index, 
			HashMap<Integer, String> sentences, 
			String oaoText, String nLText) {
		try {
			String[] fns = {"action", "trait", "type", "error"};
			// NOTE: don't actually use ACTION_F or TRAIT_F
			String[][] files = {
					{ACTION_F, ACTION_R1},
					{TRAIT_F, TRAIT_R},
					{TYPE_F},
					{ERROR_R}
				};
			Files.write(Paths.get(TMP), "".getBytes());
			
			// set up replacement for ACTION_F and TRAIT_F
			ArrayList<String> actionf = new ArrayList<String>();
			ArrayList<String> traitf = new ArrayList<String>();
			for (String oao : sentences.values()) {
				String prolines = NLPConnector.convertOAOToProlog(oao, null);
				actionf.add(prolines.split("---", -1)[0]);
				traitf.add(prolines.split("---", -1)[1]);
			}
			

			// Create TMP file
				
			String prolines = NLPConnector.convertOAOToProlog(oaoText, null);
			String actions = prolines.split("---", -1)[0];
			String traits = prolines.split("---", -1)[1];
			if (prolines.trim().length()==0) {
				return new StoryProblemObject[]{};
			}
			for (int i = 0; i<fns.length; i++) {
				if (fns[i].equals("action")) {
					// place new actions to test into action section of TMP file
					Files.write(Paths.get(TMP), actions.getBytes(), 
						StandardOpenOption.APPEND);
				} else if (fns[i].equals("trait")) {
					// place new traits to test into trait section of TMP file
					Files.write(Paths.get(TMP), traits.getBytes(),
						StandardOpenOption.APPEND);
				}
				for (String file : files[i]) {
					if (file.equals(ACTION_F)) {
						// use replacement of ACTION_F instead
						Files.write(Paths.get(TMP), actionf, StandardOpenOption.APPEND);
					} else if (file.equals(TRAIT_F)) {
						// use replacement of TRAIT_F instead
						Files.write(Paths.get(TMP), traitf, StandardOpenOption.APPEND);
					} else {
						Files.write(Paths.get(TMP), Files.readAllBytes(Paths.get(file)), StandardOpenOption.APPEND);
					}
					Files.write(Paths.get(TMP), "\n".getBytes(), StandardOpenOption.APPEND);
				}
			}
			
			// test for errors		
			String[] errorArgNames = {"_", "_", "_"};
			PrologQueryMaster pqm = new PrologQueryMaster(TMP);
			String[][] rs = pqm.query("error", errorArgNames);
			if (rs==null) {
				System.out.println("issue with errors");
				return new StoryProblemObject[]{};
			}
			StoryProblemObject[] ret = new StoryProblemObject[rs.length];
			for (int i = 0; i<rs.length; i++) {
				ret[i] = new StoryProblemObject(
						Integer.parseInt(rs[i][0]),
						new String[]{oaoText}, // oaoText
						nLText, // nLText
						String.format(rs[i][1].substring(1, rs[i][1].length()-1).replace("\\x20\\", " ").replace("\\n", "\n"), 
						(Object[])rs[i][2].substring(1, rs[i][2].length()-1).split(",")));
			}
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
