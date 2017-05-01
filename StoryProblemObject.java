public class StoryProblemObject {
	public int severity;
	public String[] oaoText;
	public String originalNLText;
	public String errorMessage;
	
	public StoryProblemObject(int severity, String[] oaoText, String originalNLPText, String errorMessage) {
		this.severity = severity;
		this.oaoText = oaoText;
		this.originalNLText = originalNLPText;
		this.errorMessage = errorMessage;
	}
}
