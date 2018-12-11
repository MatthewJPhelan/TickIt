    package db;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.text.ParseException;
	import java.util.ArrayList;

	import Server.Ticket;

	public class Emails {

		private static final String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/rome";
		private static final String user = "rome";
		private static final String passwordDB = "9njiaprt0d";

		// JDBC variables for opening and managing connection
		private static Connection con = null;
		private static ResultSet rs = null;
		private static PreparedStatement pstmt = null;
		private static ArrayList<int[]> arrl;
		
		public static ArrayList<int[]> soldList() throws SQLException{
			arrl = null;
			pstmt = con.prepareStatement("SELECT seller_id, buyer_id, listing_id FROM listings WHERE listing_end < NOW() AND email_sent = FALSE AND buyer_id NOTNULL;");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int[] arr = new int[3];
				arr[0] = rs.getInt(1);
				arr[1] = rs.getInt(2);
				arr[2] = rs.getInt(3);
				arrl.add(arr);
			}
			
			return arrl;
		}
		
		public static ArrayList<int[]> notSoldList() throws SQLException{
			ArrayList<int[]> arrLInt = null;
			pstmt = con.prepareStatement("SELECT seller_id, listing_id FROM listings WHERE listing_end < NOW() AND email_sent = FALSE AND buyer_id ISNULL;");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int[] arr = new int[2];
				arr[0] = rs.getInt(1);
				arr[1] = rs.getInt(2);
				arrLInt = new ArrayList<int[]>();
				arrLInt.add(arr);
			}
			
			return arrLInt;
		}
		
	    public static String emailToSeller(int listingID) throws SQLException {
			
			String email = null;
			int buyerID = 0;
			int sellerID = 0;
			String buyerName = null;
			String buyerLastname = null;
			String buyerEmail = null;
			String buyerPhone = null;
			String sellerName = null;
			String sellerLastname = null;
			String eventName = null;
			String eventCity = null;
			String eventAddress = null;
			String eventDate = null;
			String eventPrice = null;
			
			try {
				con = DriverManager.getConnection(url, user, passwordDB);
				
				//taking sellerID from listings
				pstmt = con.prepareStatement("SELECT seller_id FROM listings WHERE listing_id = ?");
				pstmt.setInt(1, listingID);
				rs = pstmt.executeQuery();
				
				if(rs.next())
					sellerID = rs.getInt(1);
				
				//taking seller information from users
				pstmt = con.prepareStatement("SELECT name, last_name FROM users WHERE id = ?");
				pstmt.setInt(1, sellerID);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					sellerName = rs.getString(1);
				    sellerLastname = rs.getString(2);
				}	
				
				//taking buyer ID from listings
				pstmt = con.prepareStatement("SELECT buyer_id FROM listings WHERE listing_id = ?");
				pstmt.setInt(1, listingID);
				rs = pstmt.executeQuery();
				
				if(rs.next())
					buyerID = rs.getInt(1);
				
				//taking buyer information from users
				pstmt = con.prepareStatement("SELECT name, last_name, email, phone_number FROM users "
						+ "WHERE id = ?");
				pstmt.setInt(1, buyerID);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					buyerName = rs.getString(1);
					buyerLastname = rs.getString(2);
					buyerEmail = rs.getString(3);
					buyerPhone = rs.getString(4);
				}
					
				//taking event information from listings
				pstmt = con.prepareStatement("SELECT event_name, event_date, event_venue, event_city, event_postcode, "
						+ "highest_bid FROM listings WHERE listing_id = ?;");
				pstmt.setInt(1, listingID);
				rs = pstmt.executeQuery();
		
				while(rs.next()) {
					eventName = rs.getString(1);
					eventCity = rs.getString(4);
					eventAddress = rs.getString(3) + ", " + rs.getString(5);
					eventDate = rs.getString(2);
					eventPrice = rs.getString(6);
				}
				
				email = sellerName + " " + sellerLastname + ",\n\n" + "Your ticket for the " + eventName + " event has been sold. "
						+ "Now you can contact the Buyer to complete your bargain.\n\n" + "Event details\n" + "Name: "
						+ eventName + "\nCity: " + eventCity + "\nAddress: " + eventAddress + "\nDate and time: " 
						+ eventDate + "\nPrice sold: " + eventPrice + "\n\nBuyer details\nName: " + buyerName + " " 
						+ buyerLastname + "\nPhone number: " + buyerPhone + "\nEmail: " + buyerEmail 
						+ "\n\nWe hope you enjoyed using our service and we look forward to seeing you soon on TickIt!" 
						+ "\n\nYou can always contact us via our email TickItteam@gmail.com."
						+ "\n\nBest wishes,\nTickIt team.";

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.close();
				pstmt.close();
			}
			return email;
		}
	    
	public static String emailToBuyer(int listingID) throws SQLException {
			
			String email = null;
			int buyerID = 0;
			int sellerID = 0;
			String sellerName = null;
			String sellerLastname = null;
			String sellerEmail = null;
			String sellerPhone = null;
			String buyerName = null;
			String buyerLastname = null;
			String eventName = null;
			String eventCity = null;
			String eventAddress = null;
			String eventDate = null;
			String eventPrice = null;
			
			try {
				con = DriverManager.getConnection(url, user, passwordDB);
				
				//taking buyer ID from listings
				pstmt = con.prepareStatement("SELECT buyer_id FROM listings WHERE listing_id = ?");
				pstmt.setInt(1, listingID);
				rs = pstmt.executeQuery();
				
				if(rs.next())
					buyerID = rs.getInt(1);
				
				//taking seller information from users
				pstmt = con.prepareStatement("SELECT name, last_name FROM users WHERE id = ?");
				pstmt.setInt(1, buyerID);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					buyerName = rs.getString(1);
				    buyerLastname = rs.getString(2);
				}	
				
				//taking seller ID from listings
				pstmt = con.prepareStatement("SELECT seller_id FROM listings WHERE listing_id = ?");
				pstmt.setInt(1, listingID);
				rs = pstmt.executeQuery();
				
				if(rs.next())
					sellerID = rs.getInt(1);
				
				//taking seller information from users
				pstmt = con.prepareStatement("SELECT name, last_name, email, phone_number FROM users "
						+ "WHERE id = ?");
				pstmt.setInt(1, sellerID);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					sellerName = rs.getString(1);
					sellerLastname = rs.getString(2);
					sellerEmail = rs.getString(3);
					sellerPhone = rs.getString(4);
				}
					
				//taking event information from listings
				pstmt = con.prepareStatement("SELECT event_name, event_date, event_venue, event_city, event_postcode, "
						+ "highest_bid FROM listings WHERE listing_id = ?;");
				pstmt.setInt(1, listingID);
				rs = pstmt.executeQuery();
		
				while(rs.next()) {
					eventName = rs.getString(1);
					eventCity = rs.getString(4);
					eventAddress = rs.getString(3) + ", " + rs.getString(5);
					eventDate = rs.getString(2);
					eventPrice = rs.getString(6);
				}
				
				email = buyerName + " " + buyerLastname + ",\n\n" + "Congratulations! Your bid for the " + eventName + " event was the highest. "
						+ "Now you can contact the Seller to complete your bargain.\n\n" + "Event details\n" + "Name: "
						+ eventName + "\nCity: " + eventCity + "\nAddress: " + eventAddress + "\nDate and time: " 
						+ eventDate + "\nPrice sold: " + eventPrice + "\n\nSeller details\nName: " + sellerName + " " 
						+ sellerLastname + "\nPhone number: " + sellerPhone + "\nEmail: " + sellerEmail 
						+ "\n\nWe hope you enjoyed using our service and we look forward to seeing you soon on TickIt!" 
						+ "\n\nYou can always contact us via our email TickItteam@gmail.com."
						+ "\n\nBest wishes,\nTickIt team.";

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.close();
				pstmt.close();
			}
			return email;
		}

	   	 public static String failureMessage(int listingID) throws SQLException {
	    	    
	    		String email = null; 
	   	 	int sellerID = 0;
	   	 	String sellerName = null;
	   	 	String sellerLastname = null;
			String eventName = null;
			String eventCity = null;
			String eventAddress = null;
			String eventDate = null;
	    	
	   	 	try {
				con = DriverManager.getConnection(url, user, passwordDB);
				
				//taking sellerID from listings
				pstmt = con.prepareStatement("SELECT seller_id FROM listings WHERE listing_id = ?");
				pstmt.setInt(1, listingID);
				rs = pstmt.executeQuery();
				
				if(rs.next())
					sellerID = rs.getInt(1);
				
				//taking seller information from users
				pstmt = con.prepareStatement("SELECT name, last_name FROM users WHERE id = ?");
				pstmt.setInt(1, sellerID);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					sellerName = rs.getString(1);
				    sellerLastname = rs.getString(2);
				}	
					
				//taking event information from listings
				pstmt = con.prepareStatement("SELECT event_name, event_date, event_venue, event_city, event_postcode "
						+ "FROM listings WHERE listing_id = ?;");
				pstmt.setInt(1, listingID);
				rs = pstmt.executeQuery();
		
				while(rs.next()) {
					eventName = rs.getString(1);
					eventCity = rs.getString(4);
					eventAddress = rs.getString(3) + ", " + rs.getString(5);
					eventDate = rs.getString(2);
				}
				
				email = sellerName + " " + sellerLastname + ",\n\n" + "We are sorry to confirm that there was no demand on the ticket "
						+ "you posted. \n\nEvent details\n" + "Name: "
						+ eventName + "\nCity: " + eventCity + "\nAddress: " + eventAddress + "\nDate and time: " 
						+ eventDate + "\n\nWe hope to see you soon on TickIt!" 
						+ "\n\nYou can always contact us via our email TickItteam@gmail.com."
						+ "\n\nBest wishes,\nTickIt team.";

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.close();
				pstmt.close();
			}
			return email;
		}
	   	 
	   	 
	   	 
	   	 public static String greetingMessage(String username) throws SQLException {
	    	    
	    		String email = null; 
	   	 	String userName = null;
	    	
	   	 	try {
				con = DriverManager.getConnection(url, user, passwordDB);
				
				//taking userID from listings
				pstmt = con.prepareStatement("SELECT name FROM users WHERE username = ?");
				pstmt.setString(1, username);
				rs = pstmt.executeQuery();
				
				if(rs.next()) 
					userName = rs.getString(1);
				
				email = "Welcome to TickIt, " + userName + "!\n\nThank you for registering with TickIt. Now you can search for "
						+ "the most incredible events in the United Kingdom or sell your own tickets.\n\nOur team is always hear to help you with "
						+ "any issues. You can always contact us via our email TickItteam@gmail.com.\n\nWe wish you all the best,\n\nTickIt team.";

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.close();
				pstmt.close();
			}
			return email;
		}
	   	 
	 	public static void main(String[] args) throws SQLException, ParseException {
	 		
	 		System.out.println(greetingMessage("McLovin"));
	 	}
}
