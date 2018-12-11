package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.spi.DirStateFactory.Result;

import Server.Ticket;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class userDatabase {

	private static final String url = "jdbc:postgresql://mod-msc-sw1.cs.bham.ac.uk:5432/rome";
	private static final String user = "rome";
	private static final String passwordDB = "9njiaprt0d";

	// JDBC variables for opening and managing connection
	private static Connection con = null;
	private static Statement stmt = null;
	private static PreparedStatement pstmt = null;
	private static ResultSet rs = null;

	
	/**
	 * When a user sign in the method checks if the username and the password(hashed password) match.
	 * @return boolean
	 */
	
	public static boolean checkPassword(String username, String password)
			throws SQLException, NoSuchAlgorithmException {

		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			pstmt = con.prepareStatement("SELECT username, password FROM users WHERE username = ?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (username.equals(rs.getString(1)))
					if (hashPassword(password).equals(rs.getString(2)))
						return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		return false;
	}

	
	/**
	 * Method hashPassword runs the password through a hash encryption algorithm.
	 * @param password user's input
	 * @return hashed password
	 */

	public static String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256"); // MessageDigest part of the Java security package
		md.update(password.getBytes()); // encodes the string into a sequence of bytes.
		byte[] b = md.digest(); // turns the sequence of bytes into a byte array.
		StringBuffer sb = new StringBuffer(); // StringBuffer initialised.
		for (byte b1 : b) {
			sb.append(Integer.toHexString(b1 & 0xff).toString());// turns each byte to a Hex String, not sure what 0xff
																	// is...

		}
		return sb.toString();
	}

	
	/**
	 * Method checks if the password that a user typed in matches with its hashed equivalent from the database.
	 * @param password       :   user's input
	 * @param hashedPassword :   hashed password from the database
	 * @return boolean
	 */
	public static boolean verifyPassword(String password, String hashedPassword) {
		boolean passwordVerified = false; // initialised as false.

		try {
			if (hashPassword(password).equals(hashedPassword)) {
				passwordVerified = true; // simply, after hashing, if its the same as the hash stored it's true.
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return passwordVerified;

	}

	
	/**
	 * Method for signing up a new user
	 * @return boolean if the sign up was successful or not
	 */
	public static boolean signUp(String username, String password, String name, String lastName, String email,
			String phoneNumber) throws SQLException, NoSuchAlgorithmException {
		boolean signedUp = true;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		ResultSet result;
		int count = 0;

		try {

			con = DriverManager.getConnection(url, user, passwordDB);
			stmt = con.createStatement();

			statement2 = con.prepareStatement("SELECT * FROM users WHERE username = ? OR email = ?");
			statement2.setString(1, username);
			statement2.setString(2, email);
			result = statement2.executeQuery();

			while (result.next()) {
				count++;
			}

			if (count > 0) {
				signedUp = false;
			} else {
				statement = con.prepareStatement(
						"INSERT INTO users (username, password, name, last_name, email, phone_number) VALUES (? ,? ,? ,? ,? ,?)");

				statement.setString(1, username);
				statement.setString(2, hashPassword(password));
				statement.setString(3, name);
				statement.setString(4, lastName);
				statement.setString(5, email);
				statement.setString(6, phoneNumber);

				stmt.executeUpdate(statement.toString());
				signedUp = true;
			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			con.close();
			stmt.close();
		}
		return signedUp;
	}
	
	
	/**
	 * If there are any not-hashed passwords in the database, the method will be used to hash them.
	 */
	public static boolean newPassword(String username, String password) throws SQLException, NoSuchAlgorithmException {
		boolean check = true;
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			
			
			pstmt = con.prepareStatement("UPDATE users SET password = ? WHERE id = ?;");
			pstmt.setString(1, hashPassword(password));
			pstmt.setInt(2, listingsDatabase.getID(username));
			
			pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
			check = false;
			return check;
		} finally {
			con.close();
			pstmt.close();
		}
		return check;
	}
	
	
	/**
	 * The method searches for the user's email in the database and returns it
	 * @param username
	 * @return user's email
	 */
	public static String getEmail(String username) throws SQLException {
		
		String email = null;
		
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			pstmt = con.prepareStatement("SELECT email FROM users WHERE username = ?");
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
	
			if(rs.next()) {
				email = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}
		return email;	
	}
	
	public static String getEmailById(int id) throws SQLException {
		
		String email = null;
		
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			pstmt = con.prepareStatement("SELECT email FROM users WHERE id = ?");
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
	
			if(rs.next()) {
				email = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}
		return email;	
	}
	
	public static String getFirstName(String username) throws SQLException {
		
		String firstName = username;
		
		try {
			con = DriverManager.getConnection(url, user, passwordDB);
			pstmt = con.prepareStatement("SELECT name FROM users WHERE username = ?");
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
	
			if(rs.next()) {
				firstName = rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			pstmt.close();
		}
		return firstName;	
	}
	
	/**
	 * All our tests
	 */
	public static void main(String args[]) throws SQLException, NoSuchAlgorithmException {

		/**
		 * Database connection and taking information from the Database
		 */

		//signUp(null, "IKnowNothing", "Jon", "Snow", "jon.snow@winterfell.com", "07547365839");
		//System.out.println(signUp("Buckleberry", "Popsickle", "Bob", "Tinsbury", "Buckleberry@gmail.com", "011113848484"));
		//signUp("SeaShellsSoup", null, "Vladimir", "Putin", "vladPootin@kremlin.com", "01216666666");
//		System.out.println(checkPassword("NippleMad", "LloydWebber"));
//		System.out.println(checkPassword("Buckleberry", "Popsickle"));
		System.out.println(getEmail("Lord Vader"));
	}

}
