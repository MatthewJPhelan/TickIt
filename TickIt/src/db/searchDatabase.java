package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/*@author Karl Tanczos
 *@group name: Rome
 *
 * Class searchDatabase contains the database calls and insertions into the search table of the database.
 */
public class searchDatabase {
	
	// String variables declared for accessing the database.
	private static final String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/rome";
	private static final String user = "rome";
	private static final String passwordDB = "9njiaprt0d";

	// JDBC variables for opening and managing connection
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	private static Timestamp searchTime;
	
	
	/* method saveSearch takes the takes the stored user name of the user and the user's search as a String
	 * and stores it in the search table along with the time stamp of the search. The data is stored alongside
	 * an auto-incrementing unique search id.
	 * 
	 * @variable searchTime - the timestamp of the immediate time the saveSearch method is called upon a users
	 * search, in the form of long time, which is .get of the current date.
	 * 
	 * @variable saveSuccessful notifies the admin and the server if the search was successfully saved,
	 * returns false on default or if an exception is thrown.
	 */
	
	public static boolean saveSearch(String username, String search) throws SQLException {
		Date dateNow = new Date(); // date variable initialised.
		boolean saveSuccessful = false; // boolean for successful save initialised.
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			
			long time = dateNow.getTime(); 
			searchTime = new Timestamp(time);
			
			
				pstmt = con.prepareStatement("INSERT INTO searches (user_id, search, time) VALUES (?,?,?);");

				pstmt.setInt(1, listingsDatabase.getID(username));
				pstmt.setString(2, search);
				pstmt.setTimestamp(3, searchTime);
				
				pstmt.executeUpdate();
				saveSuccessful = true;
			
		} catch (Exception e) {
			e.printStackTrace();
			saveSuccessful = false;
		} finally {
			con.close();
			pstmt.close();
			
		}
		return saveSuccessful;
	}
	
	
	public static ArrayList <StringBuffer> getSearches(String username) throws SQLException {
		ArrayList <StringBuffer> searchArr = new ArrayList <StringBuffer>();
		StringBuffer searches = new StringBuffer();
		
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			
			pstmt = con.prepareStatement("SELECT * FROM searches WHERE user_id = ?;");
			
			pstmt.setInt(1, listingsDatabase.getID(username));
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				searches.append(" User ID: " + rs.getInt(1) + "\n");
				searches.append(" Username: " + username + "\n");
				searches.append(" Searched: " + rs.getString(2) + "\n");
				searches.append(" Date: " + rs.getTimestamp(3) + "\n");
			}
			searchArr.add(searches);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		
		
		return searchArr;
	}
	
	
	public static void main(String[] args) throws SQLException {

	//	System.out.println(saveSearch("mattyJ", "Get Drunk"));
		System.out.println(getSearches("mattyJ"));

	}

}
