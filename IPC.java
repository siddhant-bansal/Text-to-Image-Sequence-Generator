import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class IPC {

	public static void callPython(String stringResult) {
		
		//Location of the python interpreter - use fully qualified path
		String python = "C:\\Users\\bansa\\Anaconda3\\python.exe";
		//Location of the script you want to run - use fully qualified path
		//String script = "C:\\Users\\bansa\\workspace\\NLPConnector\\lib\\Query.py";
		String script = "C:\\Users\\bansa\\workspace\\Python\\Query.py";
		
		String command = python+" "+script+" "+stringResult;
		
		try{
			
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader in = new BufferedReader( new InputStreamReader(p.getInputStream()) );
			String line;
			ArrayList<String> pythonReturnedData = new ArrayList<String>();
			
			while( (line = in.readLine()) != null ){
				pythonReturnedData.add(line);
				System.out.println(line);
			}
			in.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}

}
