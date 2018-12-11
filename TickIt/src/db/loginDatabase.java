package db;

import java.sql.*;
import java.util.Date;
/* @author Karl Tanczos
 * @group name: Rome
 * 
 * 
 * @class loginDatabase contains the database calls in relation to the logins table of the database.
 */

public class loginDatabase {

	
	// String variables declared for accessing the database.
	private static final String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/rome";
	private static final String user = "rome";
	private static final String passwordDB = "9njiaprt0d";

	// JDBC variables for opening and managing connection
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	private static Timestamp loginTime;
	
	/* Method loginSave takes a username and inserts a new column in the logins table of the database.
	 * The time that the method is called is gained and stored, along with the username, the user's id (through
	 * the getID method).
	 * 
	 * @variable loginTime - the timestamp of the immediate time the loginSave method is called upon a users
	 * login to the application, in the form of long time, which is .get of the current date.
	 * 
	 */
	
	public static void loginSave(String username) throws SQLException {
		Date dateNow = new Date(); // date variable initialised.
		try {
			con = DriverManager.getConnection(url,user,passwordDB);
			
			long time = dateNow.getTime(); 
			loginTime = new Timestamp(time);
			
			pstmt = con.prepareStatement("INSERT INTO logins (user_id, username, login_time) VALUES (?,?,?);");
			
			pstmt.setInt(1, listingsDatabase.getID(username));
			pstmt.setString(2, username);
			pstmt.setTimestamp(3, loginTime);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}	
	}
	

	

	public static void main(String[] args) throws SQLException {
	//	loginSave("mattyJ");

	}

}
