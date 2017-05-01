import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DbHelper {
	static Connection con = MyFrame.con;
	static Statement st = MyFrame.stmt;
	static ResultSet rs = MyFrame.resultset;
	
	public static void updateActionEffect(
			ActionBundle action) {
		try {
			st.execute("UPDATE Nodes SET `ActionEffect`='" +
					action.instance.effect.name() +
					"' WHERE `Name`='" +  action.affordance.name +
					"' AND `AfforderType`='" + ActionBundle.getTypeName(action.actor) +
					"' AND `AffordeeType`='" + 
					ActionBundle.getTypeName(action.instance.affordee) + "';");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void inputEdge(DigitalStoryWorld world, String line1, String line2) {
		ActionBundle bundle1 = ActionBundle.getBundle(world, line1);
		ActionBundle bundle2 = ActionBundle.getBundle(world, line2);
		try {
			int n1;
			int n2;
			PreparedStatement pst;
			pst = con.prepareStatement("SELECT * FROM Nodes WHERE Name=? AND AfforderType=? AND AffordeeType=?;");
			pst.setString(1, bundle1.affordance.name);
			pst.setString(2, ActionBundle.getTypeName(bundle1.actor));
			pst.setString(3, ActionBundle.getTypeName(bundle1.instance.affordee));
			rs = pst.executeQuery();
			if (rs.next()) {
				n1 = rs.getInt("NodeId");
			} else {
				// should never happen
				n1 = -1;
			}
			pst.setString(1, bundle2.affordance.name);
			pst.setString(2, ActionBundle.getTypeName(bundle2.actor));
			pst.setString(3, ActionBundle.getTypeName(bundle2.instance.affordee));
			rs = pst.executeQuery();
			if (rs.next()) {
				n2 = rs.getInt("NodeId");
			} else {
				// should never happen
				n2 = -1;
			}
			rs = st.executeQuery("SELECT * FROM Edges WHERE StartNode=" + n1 + " AND EndNode=" + n2 + ";");
			if (rs.next()) {
				st.execute("UPDATE Edges SET Count=Count+1 WHERE StartNode=" + n1 + " AND EndNode=" + n2 + ";");
			} else {
				pst = con.prepareStatement("INSERT INTO Edges VALUES (?, ?, 1)");
				pst.setInt(1, n1);
				pst.setInt(2, n2);
				pst.execute();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static HashMap<Integer, String> getAllNodes() {
		try {
			rs = st.executeQuery("SELECT * FROM Nodes;");
			HashMap<Integer, String> ret = new HashMap<Integer, String>();
			while (rs.next()) {
				ret.put(rs.getInt("NodeId"), rs.getString("AfforderType") + " " + 
							rs.getString("Name") + " " + rs.getString("AffordeeType"));
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static int[][] getAllEdges() {
		try {
			int[][] ret = null;
			rs = st.executeQuery("SELECT Count(*) FROM Edges;");
			if (rs.next()) {
				ret = new int[rs.getInt(1)][3];
			}
			rs = st.executeQuery("SELECT * FROM Edges;");
			int i = 0;
			while (rs.next()) {
				ret[i] = new int[]{rs.getInt(1), rs.getInt(2), rs.getInt(3)};
				i++;
			}
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
