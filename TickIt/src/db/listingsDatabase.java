package db;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.util.Calendar;
import Server.Server;
import Server.Ticket;
import Server.User;


public class listingsDatabase {

	private static final String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/rome";
	private static final String user = "rome";
	private static final String passwordDB = "9njiaprt0d";

	// JDBC variables for opening and managing connection
	private static Connection con = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;

	/**
	 * Method searches for all the auctions related to the user (both sold and bought).
	 * @param username
	 * @return an ArrayList of all auctions
	 */
	public static ArrayList<Ticket> selectAuctionsByUser(String username) throws SQLException {
		ArrayList<Ticket> arrT = new ArrayList<>();
		Date dateNow = new Date();
		long time1 = dateNow.getTime();
		Timestamp ts = new Timestamp(time1);
		
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			pstmt = con.prepareStatement(
					"SELECT * FROM listings WHERE seller_id = ? AND listing_end > ?");

			pstmt.setInt(1, getID(username));
			pstmt.setTimestamp(2, ts);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			  	int listingId = rs.getInt(1);
	            int sellerId = rs.getInt(2);
	            String eventName = rs.getString(3);
	            String eventDate = rs.getString(4);
	            String eventVenue = rs.getString(5);
	            String eventCity = rs.getString(6);
	            String eventPostcode = rs.getString(7);
	            String listingStart = rs.getString(8);
	            String listingEnd = rs.getString(9);         
	            String price = rs.getString(10);
	            int buyerId = rs.getInt(11);
	            boolean emailSent = rs.getBoolean(12);
			
			Ticket t = new Ticket(listingId, sellerId, eventName, eventDate, eventVenue, eventCity, eventPostcode, listingStart, listingEnd, price, buyerId, emailSent);
			arrT.add(t);	
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		return arrT;
	}

	
	// (The User Page method) Select Auction/s current and past sold by = Username
	// Has not been tested as far as I'm aware - Karl.
	/**
	 * 
	 * @param username
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Ticket> selectAuctionsCurrentAndSold(String username) throws SQLException {
		ArrayList<Ticket> arrT = new ArrayList<>();
		try {

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			java.sql.Date d = new java.sql.Date(0);
			String date = df.format(new Date());

			con = DriverManager.getConnection(url, user, passwordDB);
			pstmt = con.prepareStatement(
					"SELECT * FROM listings JOIN users u ON listings.seller_id = u.id WHERE username = ? AND listing_end < ? OR buyer_id NOTNULL");
			pstmt.setString(1, username);
			pstmt.setDate(2, (java.sql.Date) d);

			rs = pstmt.executeQuery();

			while (rs.next()) {
			
			  	int listingId = rs.getInt(1);
	            int sellerId = rs.getInt(2);
	            String eventName = rs.getString(3);
	            String eventDate = rs.getString(4);
	            String eventVenue = rs.getString(5);
	            String eventCity = rs.getString(6);
	            String eventPostcode = rs.getString(7);
	            String listingStart = rs.getString(8);
	            String listingEnd = rs.getString(9);         
	            String price = rs.getString(10);
	            int buyerId = rs.getInt(11);
	            boolean emailSent = rs.getBoolean(12);
			
			Ticket t = new Ticket(listingId, sellerId, eventName, eventDate, eventVenue, eventCity, eventPostcode, listingStart, listingEnd, price, buyerId, emailSent);
			arrT.add(t);	
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		return arrT;
	}

	
	/**
	 * Method searches for all the auctions depending on search given
	 * @param event_city
	 * @return ArrayList of auctions
	 */
	public static ArrayList<Ticket> selectAuctions(String search) throws SQLException {
		ArrayList<Ticket> arrT = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("SELECT * FROM listings WHERE lower (event_city) LIKE lower ('%" + search + "%') OR  lower (event_name) LIKE lower ('%" + search + "%') OR lower (event_venue) LIKE" +
					" lower ('%" + search + "%') OR lower (event_postcode) LIKE lower ('%" + search + "%');");

			while (rs.next()) {
				
			  	int listingId = rs.getInt(1);
	            int sellerId = rs.getInt(2);
	            String eventName = rs.getString(3);
	            String eventDate = rs.getString(4);
	            String eventVenue = rs.getString(5);
	            String eventCity = rs.getString(6);
	            String eventPostcode = rs.getString(7);
	            String listingStart = rs.getString(8);
	            String listingEnd = rs.getString(9);         
	            String price = rs.getString(10);
	            int buyerId = rs.getInt(11);
	            boolean emailSent = rs.getBoolean(12);
			
			Ticket t = new Ticket(listingId, sellerId, eventName, eventDate, eventVenue, eventCity, eventPostcode, listingStart, listingEnd, price, buyerId, emailSent);
			arrT.add(t);
			
            System.out.println(listingId);
            System.out.println(sellerId); 
            System.out.println(eventName);
            System.out.println(eventDate);
            System.out.println(eventVenue);
            System.out.println(eventCity);
            System.out.println(eventPostcode);
            System.out.println(listingStart);
            System.out.println(listingEnd);
            System.out.println(price);
            System.out.println(buyerId);
            System.out.println(emailSent);
		}		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			con.close();
			rs.close();
		}
		return arrT;
	}
	

	/**
	 * Methods sorts all the auctions in ascending order
	 * @return ArrayList of auctions
	 */
	public static ArrayList<Ticket> selectAllByDateEnding() throws SQLException {
		ArrayList<Ticket> arrT = new ArrayList<>();
		Date dateNow = new Date();
		try {
			con = DriverManager.getConnection(url, user, passwordDB);

			pstmt = con.prepareStatement("SELECT * FROM listings WHERE email_sent IS FALSE AND listing_end > ? ORDER BY listing_end ASC;");
			
			long time = dateNow.getTime();
			Timestamp endTime = new Timestamp(time);
			
			pstmt.setTimestamp(1, endTime);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			
				  	int listingId = rs.getInt(1);
		            int sellerId = rs.getInt(2);
		            String eventName = rs.getString(3);
		            String eventDate = rs.getString(4);
		            String eventVenue = rs.getString(5);
		            String eventCity = rs.getString(6);
		            String eventPostcode = rs.getString(7);
		            String listingStart = rs.getString(8);
		            String listingEnd = rs.getString(9);         
		            String price = rs.getString(10);
		            int buyerId = rs.getInt(11);
		            boolean emailSent = rs.getBoolean(12);
				
		            System.out.println(listingId);
		            System.out.println(sellerId); 
		            System.out.println(eventName);
		            System.out.println(eventDate);
		            System.out.println(eventVenue);
		            System.out.println(eventCity);
		            System.out.println(eventPostcode);
		            System.out.println(listingStart);
		            System.out.println(listingEnd);
		            System.out.println(price);
		            System.out.println(buyerId);
		            System.out.println(emailSent);
				
				Ticket t = new Ticket(listingId, sellerId, eventName, eventDate, eventVenue, eventCity, eventPostcode, listingStart, listingEnd, price, buyerId, emailSent);
				arrT.add(t);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		return arrT;
		
	}
	
	
	/**
	 * Method sorts all the auctions ordered by price in ascending order
	 * @return ArrayList of auctions
	 * @throws SQLException
	 */

	public static ArrayList<Ticket> selectAllByLowestHighest() throws SQLException {
		ArrayList<Ticket> arrT = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, passwordDB);

			stmt = con.createStatement();

			rs = stmt.executeQuery(
					"SELECT * FROM listings WHERE email_sent = false ORDER BY highest_bid ASC;");
			

			while (rs.next()) {
				
			  	int listingId = rs.getInt(1);
	            int sellerId = rs.getInt(2);
	            String eventName = rs.getString(3);
	            String eventDate = rs.getString(4);
	            String eventVenue = rs.getString(5);
	            String eventCity = rs.getString(6);
	            String eventPostcode = rs.getString(7);
	            String listingStart = rs.getString(8);
	            String listingEnd = rs.getString(9);         
	            String price = rs.getString(10);
	            int buyerId = rs.getInt(11);
	            boolean emailSent = rs.getBoolean(12);
	            
	            System.out.println(listingId);
	            System.out.println(sellerId); 
	            System.out.println(eventName);
	            System.out.println(eventDate);
	            System.out.println(eventVenue);
	            System.out.println(eventCity);
	            System.out.println(eventPostcode);
	            System.out.println(listingStart);
	            System.out.println(listingEnd);
	            System.out.println(price);
	            System.out.println(buyerId);
	            System.out.println(emailSent);
			
			Ticket t = new Ticket(listingId, sellerId, eventName, eventDate, eventVenue, eventCity, eventPostcode, listingStart, listingEnd, price, buyerId, emailSent);
			arrT.add(t);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			con.close();
			stmt.close();
			rs.close();
		}
		return arrT;
	}

	public static double getCurrentHighestBid(int listingsId) throws SQLException {
		double currentHighestBid = 0;
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			pstmt = con.prepareStatement("SELECT highest_bid FROM listings WHERE listing_id = ?");

			pstmt.setInt(1, listingsId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
	            String price = rs.getString(1);
	            String price2 = price.substring(1);
	            currentHighestBid = Double.parseDouble(price2);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		return currentHighestBid;
	}
	public static void updateBid(int listingsId, double bid, String username) throws SQLException {
		BigDecimal bidBD = BigDecimal.valueOf(bid);
		int id = listingsDatabase.getID(username);
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			pstmt = con.prepareStatement("UPDATE listings SET highest_bid = ?, buyer_id = ? WHERE listing_id = ?");

			pstmt.setBigDecimal(1, bidBD);
			pstmt.setInt(2, id);
			pstmt.setInt(3, listingsId);

			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
	}
	/**
	 * Bid method - Update price with amount given by user.
	 * @param listingsId
	 * @param bid
	 * @return the ticket with the price updated
	 */
	public static ArrayList<Ticket> bid(int listingsId, double bid) throws SQLException {
		ArrayList<Ticket> arrT = new ArrayList<>();
		
		BigDecimal newBid = new BigDecimal(bid);
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			pstmt = con.prepareStatement("UPDATE listings SET highest_bid = ? where listing_id = ?");

			pstmt.setBigDecimal(1, newBid);
			pstmt.setInt(2, listingsId);

			pstmt.executeUpdate();
			
			pstmt = con.prepareStatement("SELECT * FROM listings WHERE listing_id = ?");

			pstmt.setInt(1, listingsId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				int listingId = rs.getInt(1);
	            	int sellerId = rs.getInt(2);
	            String eventName = rs.getString(3);
	            String eventDate = rs.getString(4);
	            String eventVenue = rs.getString(5);
	            String eventCity = rs.getString(6);
	            String eventPostcode = rs.getString(7);
	            String listingStart = rs.getString(8);
	            String listingEnd = rs.getString(9);         
	            String price = rs.getString(10);
	            int buyerId = rs.getInt(11);
	            boolean emailSent = rs.getBoolean(12);
			
			Ticket t = new Ticket(listingId, sellerId, eventName, eventDate, eventVenue, eventCity, eventPostcode, listingStart, listingEnd, price, buyerId, emailSent);
			arrT.add(t);
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		return arrT;
	}
	
	
	/**
	 * Method that takes a username and gets the ID from the database.
	 * @param username
	 * @return user's ID
	 * @throws SQLException
	 */
	public static int getID(String username) throws SQLException {
		int ID = 0;
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			pstmt = con.prepareStatement("SELECT id FROM users WHERE username = ?");
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
			ID = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		return ID;
	}

	
	// createNewListing
	/**
	 * Method creates a new listing when a seller wants to sell a new ticket
	 * @param username
	 * @param eventName
	 * @param eventDate
	 * @param eventVenue
	 * @param eventCity
	 * @param eventPostcode
	 * @param startingTime
	 * @param endingTime
	 * @param startingPrice
	 */
	public static boolean createNewListing(String username, String eventName, String eventVenue, String eventCity, 
			String eventPostcode, String eventYear, String eventMonth, String eventDay, String eventHour, 
			String eventMin, String duration, String dateVariable) throws ParseException {
		
		boolean done = true;
		Calendar cal = Calendar.getInstance(); // creates calendar
	    cal.setTime(new Date()); // sets calendar time/date
	    Date startTime = cal.getTime();
	    long timeS = startTime.getTime();
	    Timestamp startTS = new Timestamp(timeS);

	    Date eventDate = new GregorianCalendar(Integer.parseInt(eventYear), Integer.parseInt(eventMonth), Integer.parseInt(eventDay), 
	    		Integer.parseInt(eventHour), Integer.parseInt(eventMin)).getTime();
		long time1 = eventDate.getTime();
		Timestamp eventDateTS = new Timestamp(time1);
		
		Timestamp endTS = null;
		int durationInt = Integer.parseInt(duration);
		
		if (dateVariable.equals("days")) {
			Calendar cal2 = Calendar.getInstance(); 
			cal2.setTime(new Date());
			cal.add(Calendar.DAY_OF_MONTH, durationInt);
			Date endTime = cal.getTime();
		    long timeE = endTime.getTime();
		    endTS = new Timestamp(timeE);
		} else if (dateVariable.equals("hours")) {
			Calendar cal2 = Calendar.getInstance(); 
			cal2.setTime(new Date());
			cal.add(Calendar.HOUR_OF_DAY, durationInt);
			Date endTime = cal.getTime();
		    long timeE = endTime.getTime();
		    endTS = new Timestamp(timeE);
		} else if (dateVariable.equals("minutes")) {
			Calendar cal2 = Calendar.getInstance(); 
			cal2.setTime(new Date());
			cal.add(Calendar.MINUTE, durationInt);
			Date endTime = cal.getTime();
		    long timeE = endTime.getTime();
		    endTS = new Timestamp(timeE);
		}

		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			String insertNewListing = "INSERT INTO listings (listing_id, seller_id, event_name, event_date, event_venue, "
					+ "event_city, event_postcode, listing_start, listing_end, highest_bid)"
					+ "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT) ";
			PreparedStatement pstmt1 = con.prepareStatement(insertNewListing);

			pstmt1.setInt(1, getID(username));
			pstmt1.setString(2, eventName);
			pstmt1.setObject(3, eventDateTS);
			pstmt1.setString(4, eventVenue);
			pstmt1.setString(5, eventCity);
			pstmt1.setString(6, eventPostcode);
			pstmt1.setObject(7, startTS);
			pstmt1.setObject(8, endTS);

			pstmt1.executeUpdate();

			
			pstmt.close();
			pstmt1.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Select user id failed.");
			done = false;
		}
		
		return done;
	}
	
	
	/**
	 * Method updates the email_sent field in the database to true once the ticket has been sold and the
	 * emails have been sent to a seller and to a buyer.
	 * @param listingID
	 */
	public static void emailSent(int listingID) throws SQLException {

		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			pstmt = con.prepareStatement("UPDATE listings SET email_sent = true WHERE listing_id = ?");
			pstmt.setInt(1, listingID);
			
			pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}

	}
	
	
	/**
	 * Methods searches for all the auctions associated with a key word typed by a user.
	 * @param search - the key word typed by a user
	 * @return ArrayList of auctions
	 */
 	public static ArrayList<Ticket> generalSearch(String search) throws SQLException {
 		
		ArrayList<Ticket> arrT = new ArrayList<>();
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			String query = "SELECT * FROM listings WHERE event_name LIKE '%" + search + "%' "
					+ "OR event_venue LIKE '%" + search + "%' OR event_city LIKE '%" + search + "%'";
			pstmt = con.prepareStatement(query);
			
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
			  	int listingId = rs.getInt(1);
	            int sellerId = rs.getInt(2);
	            String eventName = rs.getString(3);
	            String eventDate = rs.getString(4);
	            String eventVenue = rs.getString(5);
	            String eventCity = rs.getString(6);
	            String eventPostcode = rs.getString(7);
	            String listingStart = rs.getString(8);
	            String listingEnd = rs.getString(9);         
	            String price = rs.getString(10);
	            int buyerId = rs.getInt(11);
	            boolean emailSent = rs.getBoolean(12);
			
			Ticket t = new Ticket(listingId, sellerId, eventName, eventDate, eventVenue, eventCity, eventPostcode, listingStart, listingEnd, price, buyerId, emailSent);
			arrT.add(t);	
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}
       return arrT;
	}
 	
 	public static long getStartEndTime(String listId) throws SQLException {
 		int lId = Integer.parseInt(listId);
 		long difference = 0;
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			String query = "SELECT listing_end FROM listings WHERE listing_id = ?;";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, lId);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				Timestamp listEnd = rs.getTimestamp(1);
				
				difference = listEnd.getTime();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
       return difference;
	}
 	
 	public static ArrayList<Ticket>previousListings(String username) throws SQLException {
		ArrayList<Ticket> arrT = new ArrayList<>();
		Date d = new Date();
		
			try {
				con = DriverManager.getConnection(url, user, passwordDB);
				
				pstmt = con.prepareStatement("SELECT * FROM listings WHERE seller_id = ? AND listing_end < ?");
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long time = d.getTime();
				
				Timestamp dateNow = new Timestamp(time);
				
				pstmt.setInt(1, getID(username));
				pstmt.setTimestamp(2, dateNow);
				
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					
				  	int listingId = rs.getInt(1);
		            int sellerId = rs.getInt(2);
		            String eventName = rs.getString(3);
		            String eventDate = rs.getString(4);
		            String eventVenue = rs.getString(5);
		            String eventCity = rs.getString(6);
		            String eventPostcode = rs.getString(7);
		            String listingStart = rs.getString(8);
		            String listingEnd = rs.getString(9);         
		            String price = rs.getString(10);
		            int buyerId = rs.getInt(11);
		            boolean emailSent = rs.getBoolean(12);
		            
		            Ticket t = new Ticket(listingId, sellerId, eventName, eventDate, eventVenue, eventCity, eventPostcode, listingStart, listingEnd, price, buyerId, emailSent);
					arrT.add(t);	
					
					System.out.println(listingId);
		            System.out.println(sellerId); 
		            System.out.println(eventName);
		            System.out.println(eventDate);
		            System.out.println(eventVenue);
		            System.out.println(eventCity);
		            System.out.println(eventPostcode);
		            System.out.println(listingStart);
		            System.out.println(listingEnd);
		            System.out.println(price);
		            System.out.println(buyerId);
		            System.out.println(emailSent);
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.close();
				pstmt.close();
				rs.close();
			}
		
		
		return arrT;
	}

	public static void main(String[] args) throws SQLException, ParseException {
//selectAuctionsByUser(1);
		
		//System.out.println(selectAuctionsByCity("Birmingham"));
		// selectAuctionsCurrentAndSold("McLovin");
		//selectAllByLowestHighest();
//		selectAllByDateEnding();
//		Timestamp eventDate = new Timestamp(2017,02,28,23,0,0,0); 
//		Timestamp listingStart = new Timestamp(2017,02,25,20,0,0,0);
//		Timestamp listingEnd = new Timestamp(2017,02,28,20,0,0,0);
		
//		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
//		Date date = dateFormat.parse("2018.09.15.22.00.00");
//		long time = date.getTime();
//		new Timestamp(time);
//		Date time = new Date
//		BigDecimal price = new BigDecimal(1.50);
//		createNewListing("Buckleberry", "Get Drunk", "2018.09.15.22.00.00", "Circo", "Birmingham", "B2N 33L", "2018.09.15.22.00.00", "2018.09.15.22.00.00", 10.50);
//		System.out.println(getID("McLovin"));

		selectAuctions("bir");
		
//		bid(21, 2.50);		
//		listingsDatabase ldb = new listingsDatabase();
//		System.out.println(ldb.selectAllByDateEnding().get().getHighestBid());
//		System.out.println(selectAllByLowestHighest());
//		emailSent(19);
		
	System.out.println(selectAllByDateEnding());

	}

}
