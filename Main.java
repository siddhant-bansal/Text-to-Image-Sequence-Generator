import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JFrame;

import org.python.core.PyException;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

public class Main {

	public static void main(String[] args) throws IOException {
		
		System.out.println("Enter text to be analyzed.");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String userInput = "";
		userInput = reader.readLine();
		
		//File outputFile = new File();
		 String outputFile = "";
		
		
		 
		//NLPCOnnector.converttoOAO(pass in result of analyze method)
		// pass result to OAOtoProlog
		 
		ArrayList<ArrayList<String>> AllObjects = new ArrayList<ArrayList<String>>();
		
		String stringResult = "";
		
		String[] inputArray = userInput.split("\\.");
		for(int i = 0; i<inputArray.length; i++){
		
			String sentence = inputArray[i];
			//System.out.println("current sentence: " + 	sentence);
			//System.out.println("converting user input to OAO ===========");
			String NLPtext = NLPConnector.analyze(sentence);
			String OAOresult = NLPConnector.convertNLPToOAO(NLPtext);
			//System.out.println("OAO result: " + OAOresult);
			//System.out.println("converting OAO to Prolog ============");
			//String prolog = NLPConnector.convertOAOToProlog(OAOresult, outputFile);
			//System.out.println("OAO result: " + OAOresult);
			//System.out.println("\nprolog queries: \n" + prolog);
			
			//System.out.println("objects: \n" + NLPConnector.getObjects(OAOresult, NLPtext));
			
			//get list of objects for current sentence
			ArrayList<String> currentObjects = NLPConnector.getObjects(OAOresult, NLPtext);
			for(String word : currentObjects){
				stringResult += word;
			}
			stringResult += ". ";
			
			//add objects to list
			AllObjects.add(currentObjects);
			
			//System.out.println("current objects: " + currentObjects);
			//IPC.callPython(currentObjects);

		}
		
		System.out.println("\n \n String result: " + stringResult);
		//System.out.println("all objects: \n" + AllObjects.toString());
		
		//send objects to pythonInput.py
		IPC.callPython(stringResult);
	}

}
