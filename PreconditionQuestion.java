import javax.swing.JFrame;

public class PreconditionQuestion implements Question {
	DigitalStoryWorld world;
	String s;
	String[] sA;
	boolean[][] matrix;
	
	public PreconditionQuestion(DigitalStoryWorld world, String storystring) {
		this.world = world;
		s=storystring;
	}

	@Override
	public void promptUser() {
		sA = (s.length()==0)? new String[0] : s.split("\\n");
		
		
		
		matrix = new boolean[sA.length][sA.length];
		
		newPreconditionFrame frame1 = new newPreconditionFrame("Please Select the Preconditions for the following Sentences", sA, sA, matrix);
		frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.pack();
		frame1.setVisible(true);
		// matrix1 populated

	}

	@Override
	public void applyAnswer() {
		// SEND matrix1 TO DATABASE
		for (int i = 0; i<matrix.length; i++) {
			for (int j = 0; j<matrix[i].length; j++) {
				if (matrix[i][j]) {
					DbHelper.inputEdge(world, sA[i], sA[j]);
				}
			}
		}
	}

}
