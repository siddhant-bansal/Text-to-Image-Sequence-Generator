import org.python.core.PyException;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;


public class JythonTest {

	public static void main(String[] args) {
        // Create an instance of the PythonInterpreter
		PythonInterpreter interp = new PythonInterpreter();

        // The exec() method executes strings of code
        interp.exec("import GID_organizeforuse");
        interp.exec("GID_organizeforuse.QueryGID.whatlabels('img1259','img1001')");

        // Set variable values within the PythonInterpreter instance
        interp.set("a", new PyInteger(42));
        interp.exec("print a");
        interp.exec("x = 2+2");

        // Obtain the value of an object from the PythonInterpreter and store it
        // into a PyObject.
        PyObject x = interp.get("x");
        System.out.println("x: " + x);
	}

}
