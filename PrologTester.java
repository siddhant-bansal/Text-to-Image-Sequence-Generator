import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class PrologTester {
	public static void main(String[] args) {
		//queryTest(false);
		//queryTest(false);
		//preconditionTest();
		errorTest();
	}
	
	public static void errorTest() {
//		PrologQueryMaster.getError("action", new String[]{"alice", "threw", "beans"});
//		PrologQueryMaster pqm = new PrologQueryMaster(PrologQueryMaster.TMP);
//		Scanner sc = new Scanner(System.in);
//		while (sc.hasNext()) {
//			String[][] aa = pqm.query(sc.nextLine(), sc.nextLine().split(" "));
//			for ( int i = 0; aa != null && i < aa.length ; i++){
//				for (int j = 0 ; j < aa[i].length; j++){
//					System.out.print(aa[i][j].replace("\\x20\\", " ")+" ");
//				}
//				System.out.println();
//			}
//		}
		for (String s : PrologQueryMaster.getError("action", new String[]{"alice", "threw", "beans"})) {
			System.out.println(s);
		}
		System.out.println("---");
	}
	
	public static void preconditionTest() {
		List<String> actions = null;
		try {
			actions = Files.readAllLines(Paths.get(PrologQueryMaster.ACTION_F));
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean[][] arr = PrologQueryMaster.guessPreconditions();
		for (int i = 0; i<actions.size(); i++) {
			for (int j = 0; j<actions.size(); j++) {
				if (arr[i][j]) {
					System.out.print("(" + i + ") " + actions.get(i));
					System.out.print(" --> ");
					System.out.println("(" + j + ") " + actions.get(j));
					
				}
			}
		}
	}
	
	public static void queryTest(boolean isQueryNotVerify) {
		PrologQueryMaster.updateFacts();
		PrologQueryMaster pqm = new PrologQueryMaster(PrologQueryMaster.FACTS_FILE);
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			if (isQueryNotVerify) {
				String[][] aa = pqm.query(sc.nextLine(), sc.nextLine().split(" "));
				for ( int i = 0; aa != null && i < aa.length ; i++){
					for (int j = 0 ; j < aa[i].length; j++){
						System.out.print(aa[i][j]+" ");
					}
					System.out.println();
				}
			} else {
			 	System.out.println(pqm.verify(sc.nextLine(), sc.nextLine().split(" ")));
			}
		}
	}
}
