package Server;

import Protocol.SimpleProtocol;
import db.Emails;
import db.listingsDatabase;
import db.userDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.mail.MessagingException;

import Email.Email;

public class ServerThread implements Runnable {

	private SimpleProtocol protocol;
	private boolean signedIn = false;
	private String user;
	private PrintWriter toClient;
	private BufferedReader fromClient;

	public ServerThread(Socket clientSocket) {

		this.protocol = new SimpleProtocol();

		try {
			System.out.println("try initalise PrintWriter");
			toClient = new PrintWriter(clientSocket.getOutputStream(), true);
			System.out.println("try init BuffRead");
			fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to initialise Writer/Reader");
		}
	}

	// TODO add sign-in, sign-up and update offer to run method.
	@Override
	public void run() {
		try {

			toClient.println(protocol.createMessage("welcome", "Welcome to my Server"));
			System.out.println("Welcome12345");
			String fromC = fromClient.readLine();
			System.out.println(fromC);
			System.out.println("decode to fields");
			String[] fields = protocol.decodeMessage(fromC);
			System.out.println(fields);
			String method = fields[0];

			// fields = protocol.decodeMessage(fromClient.readLine());
			// method = fields[0];

			System.out.println("ifs");
			if (method.equals("sign-up")) {
				System.out.println("sign-up");
				try {
					toClient.println(signUp(fields));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				// Thread interrupted after the sign up method, as if a user signs up they might
				// go on the sign up someone else and not sign in so the thread will be left
				// open.
				Thread.currentThread().interrupt();
			} else if (method.equals("sign-in")) {
				try {
					System.out.println("cooomooonnn");
					toClient.println(signIn(fields));
				} catch (NoSuchAlgorithmException e) {
					toClient.println("qwerty");

					e.printStackTrace();
				} catch (SQLException e) {
					toClient.println("qwerty2");

					e.printStackTrace();
				}
			} else if (method.equals("get-listings")) {
				getListings(fields);
			} else if (method.equals("newBid")) {
				newBid(fields);
			} else if (method.equals("get-lh")) {
				getLH(fields);
			} else if (method.equals("get-ul")) {
				getUsersLists(fields);
			} else if (method.equals("checkUserPass")) {
				try {
					checkUserPass(fields);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (method.equals("newListing")) {
				newListing(fields);
			} else if (method.equals("startEndTimes")) {
				startEndTimes(fields);
			} else if (method.equals("previousListings")) {
				previousListings(fields);
			} else if (method.equals("changePassword")) {
				changePassword(fields);
			} else if (method.equals("forgottenPassword")) {
				forgottenPassword(fields);
			} else if (method.equals("checkFinished")) {
				try {
					sendEmail();
				} catch (MessagingException e) {
					System.out.println("ServerThread: sendEmail(MessagingException)");
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			Server.onlineUsers.remove(user);
			Thread.currentThread().interrupt();
		}
	}

	private void previousListings(String[] fields) {
		String user = fields[1];
		ArrayList<Ticket> arrT;
		try {
			arrT = listingsDatabase.previousListings(user);
			toClient.println(Integer.toString(arrT.size()));
			for (Ticket el : arrT) {
				System.out.println(Integer.toString(el.getBuyerId()));
				System.out.println(Integer.toString(el.getSeller()));
				toClient.println(protocol.createMessage("get-listing", "true", Integer.toString(el.getListingId()),
						Integer.toString(el.getSeller()), el.getEventName(), el.getEventDate(), el.getEventVenue(),
						el.getEventCity(), el.getEventPostcode(), el.getListingStart(), el.getListingEnd(),
						el.getHighestBid(), Integer.toString(el.getBuyerId()), Boolean.toString(el.isEmailSent())));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			toClient.println(protocol.createMessage("previousListings", "false", "get tickets unsuccessful"));
		}
	}

	private String signUp(String[] fields) throws IOException, NoSuchAlgorithmException, SQLException {
		String user = fields[1];
		String pass = fields[2];
		String name = fields[3];
		String lastName = fields[4];
		String email = fields[5];
		String phoneNumber = fields[6];
		boolean signUpCall = userDatabase.signUp(user, pass, name, lastName, email, phoneNumber);

		try {
			if (user.length() >= 5 && user.length() <= 20 && pass.length() >= 8 && pass.length() <= 32) {
				if (signUpCall == false) {
					return protocol.createMessage("sign-up", "false",
							"Sign up unsuccessful: \n" + "Username or email  exists.");
				} else {
					return protocol.createMessage("sign-up", "true", "Sign up Successful");
				}
			} else {
				return protocol.createMessage("sign-up", "false",
						"Sign up unsuccessful: \n" + "The username should be between 5 and 20 characters long. \n"
								+ "The password should be at least 8 characters long with a miximum of 32.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return protocol.createMessage("sign-up", "false", "Sign up Unsuccessful");
		}
	}

	// TODO check with karl the check pass/usern method
	private String signIn(String[] fields) throws NoSuchAlgorithmException, SQLException {
		String usern = fields[1];
		String pass = fields[2];
		boolean signInCall = userDatabase.checkPassword(usern, pass);
		System.out.println("123");
		if (signInCall == true) {
			if (!Server.onlineUsers.contains(usern)) {
				this.signedIn = true;
				user = usern;
				Server.onlineUsers.add(usern);
				System.out.println("here");
				return protocol.createMessage("Sign-in", "true", "Sign-in Successful\n");
			} else
				return protocol.createMessage("Sign-in", "false", "Sign-in Unsuccessful: \n" + "already signed-in");
		} else
			return protocol.createMessage("Sign-in", "false",
					"Sign-in Unsuccessful: \n" + "Username, password combination incorrect.");
	}

	private void getListings(String[] fields) {
		ArrayList<Ticket> arrT;
		try {
			arrT = listingsDatabase.selectAllByDateEnding();
			toClient.println(Integer.toString(arrT.size()));
			for (Ticket el : arrT) {
				System.out.println(Integer.toString(el.getBuyerId()));
				System.out.println(Integer.toString(el.getSeller()));
				toClient.println(protocol.createMessage("get-listing", "true", Integer.toString(el.getListingId()),
						Integer.toString(el.getSeller()), el.getEventName(), el.getEventDate(), el.getEventVenue(),
						el.getEventCity(), el.getEventPostcode(), el.getListingStart(), el.getListingEnd(),
						el.getHighestBid(), Integer.toString(el.getBuyerId()), Boolean.toString(el.isEmailSent())));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			toClient.println(protocol.createMessage("get-listing", "false", "get message unsuccessful"));
		}
	}

	private void getUsersLists(String[] fields) {
		ArrayList<Ticket> arrT;
		String username = fields[1];
		try {
			arrT = listingsDatabase.selectAuctionsByUser(username);
			toClient.println(Integer.toString(arrT.size()));
			for (Ticket el : arrT) {
				System.out.println(Integer.toString(el.getBuyerId()));
				System.out.println(Integer.toString(el.getSeller()));
				toClient.println(protocol.createMessage("get-listing", "true", Integer.toString(el.getListingId()),
						Integer.toString(el.getSeller()), el.getEventName(), el.getEventDate(), el.getEventVenue(),
						el.getEventCity(), el.getEventPostcode(), el.getListingStart(), el.getListingEnd(),
						el.getHighestBid(), Integer.toString(el.getBuyerId()), Boolean.toString(el.isEmailSent())));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			toClient.println(protocol.createMessage("get-listing", "false", "get message unsuccessful"));
		}
	}

	private void newListing(String[] fields) {

		String username = fields[1];
		String eventName = fields[2];
		String eventVenue = fields[3];
		String eventCity = fields[4];
		String eventPostcode = fields[5];
		String eventYear = fields[6];
		String eventMonth = fields[7];
		String eventDay = fields[8];
		String eventHour = fields[9];
		String eventMin = fields[10];
		String duration = fields[11];
		String dateVariable = fields[12];

		try {
			listingsDatabase.createNewListing(username, eventName, eventVenue, eventCity, eventPostcode, eventYear,
					eventMonth, eventDay, eventHour, eventMin, duration, dateVariable);
			toClient.println(protocol.createMessage("new-listing", "true", "new listing submitted successfully."));
		} catch (ParseException e) {
			toClient.println(protocol.createMessage("new-listing", "false", "new listing not submitted."));
		}
	}

	private void getLH(String[] fields) {
		ArrayList<Ticket> arrT;
		try {
			arrT = listingsDatabase.selectAllByLowestHighest();

			for (Ticket el : arrT) {
				toClient.println(Integer.toString(arrT.size()));
				toClient.println(protocol.createMessage("get-listing", "true", Integer.toString(el.getBuyerId()),
						Integer.toString(el.getSeller()), el.getEventName(), el.getEventDate(), el.getEventVenue(),
						el.getEventCity(), el.getEventPostcode(), el.getListingStart(), el.getListingEnd(),
						el.getHighestBid(), Integer.toString(el.getBuyerId()), Boolean.toString(el.isEmailSent())));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			toClient.println(protocol.createMessage("get-listing", "false", "get message unsuccessful"));
		}
	}

	private String startListingTime() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = date.format(new Date());

		return time;
	}

	private String endListingTime(String start, int listingLength) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Date d = df.parse(start);
		Calendar gc = new GregorianCalendar();
		gc.setTime(d);
		gc.add(Calendar.HOUR, 2);
		Date d2 = gc.getTime();
		String endTime = d2.toString();

		return endTime;
	}

	private void newBid(String[] fields) {
		System.out.println("in new bid");
		String listingIdString = fields[1];
		int listingId = Integer.parseInt(listingIdString);
		String offer = fields[2];
		double numericOffer = Double.parseDouble(offer);
		String username = fields[3];
		try {
			if (numericOffer > listingsDatabase.getCurrentHighestBid(listingId)) {
				listingsDatabase.updateBid(listingId, numericOffer, username);
				toClient.println(protocol.createMessage("newBid", "true", "highest offer updated successfully."));
			} else {
				toClient.println(protocol.createMessage("newBid", "false", "bid less than highest bid."));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			toClient.println(protocol.createMessage("newBid", "false", "Trouble updating bid, try again later."));
		}
	}

	private void checkUserPass(String[] fields) throws NoSuchAlgorithmException, SQLException {
		String usern = fields[1];
		String pass = fields[2];
		boolean signInCall = userDatabase.checkPassword(usern, pass);
		if (signInCall == true) {
			toClient.println(protocol.createMessage("Check-User/Pass", "true", "username/password correct. \n"));
		} else {
			toClient.println(protocol.createMessage("Check-User/Pass", "true", "username/password incorrect. \n"));
			// + "Username, password combination incorrect.\n");

		}
	}
	
	private void startEndTimes(String[] fields) {
		String listingId = fields[1];
		try {
			long difference = listingsDatabase.getStartEndTime(listingId);
			String differenceString = Long.toString(difference);
			toClient.println(protocol.createMessage("startEndTimes", "true", differenceString));
		} catch (SQLException e) {
			toClient.println(protocol.createMessage("startEndTimes", "database call failed \n"));
			e.printStackTrace();
		}
	}
	
	private void changePassword(String[] fields) {
		String user = fields[1];
		String pass = fields[2];

		try {
				if (userDatabase.newPassword(user, pass)) {
					toClient.println(protocol.createMessage("sign-up", "true", "Sign up Successful"));
				} else {
					toClient.println(protocol.createMessage("sign-up", "false",
							"Sign up unsuccessful: Username or email  exists."));
				}
		} catch (Exception e) {
			e.printStackTrace();
			toClient.println(protocol.createMessage("sign-up", "false", "Sign up Unsuccessful"));
		}
	}

	private void forgottenPassword(String[] fields) {
		String user = fields[1];
		String pass = fields[2];
		System.out.println("fields" + fields[2]);
		try {
				if (userDatabase.newPassword(user, pass)) {
					String email = userDatabase.getEmail(user);
					if(email != null) {
						String firstName = userDatabase.getFirstName(user);
						
							String message = "Hi " + firstName + ",\n" + "As requested, we have changed you're password.\n" +
							"Your new password is: " + pass + ".\n" + "Next time you log in use this password, and you can change it on the My Profile page." +
							"\nSee you soon,\nThe Tick-it Team";
					        
							Email.sendEmail(email, "Tick-it - forgotten password", message);
					}
					toClient.println(protocol.createMessage("forgottenPassword", "true",
							"Password changed and email sent successfully"));
				} else {
					toClient.println(protocol.createMessage("sign-up", "false",
							"database update error."));
				}
		} catch (Exception e) {
			e.printStackTrace();
			toClient.println(protocol.createMessage("sign-up", "false", "NoSuchAlgorithmException, SQLException, IOException, MessagingException"));
		}
	}
	
	private void sendEmail() throws IOException, MessagingException {
		try {
			ArrayList<int[]> sold = Emails.soldList();
			if(sold.size()>0) {
				for(int[] el : sold) {
					String sellerEmail = userDatabase.getEmailById(el[0]);
					String sellerMessage = Emails.emailToSeller(el[2]);
					if(sellerMessage != null) {
						Email.sendEmail(sellerEmail, "Congratulations - you've sold your ticket!", sellerMessage);
					}
					String buyerEmail = userDatabase.getEmailById(el[1]);
					String buyerMessage = Emails.emailToBuyer(el[2]);
					if(buyerMessage != null) {
						Email.sendEmail(buyerEmail, "Congratulations - you won the ticket", buyerMessage);
					}
				}
			}
			ArrayList<int[]> notSold = Emails.notSoldList();
			if(notSold.size()>0) {
				for(int[] el : notSold) {
					String sellerEmail = userDatabase.getEmailById(el[0]);
					String notSoldMessage = Emails.failureMessage(el[1]);
					Email.sendEmail(sellerEmail, "Tick-it - No Sale, we're sorry", notSoldMessage);
				}
			}
			toClient.println(protocol.createMessage("checkFinished", "true",
					"Email sent successfully"));
		} catch (SQLException e) {
			System.out.println("sendEmail: SQLException");
			e.printStackTrace();
			toClient.println(protocol.createMessage("checkFinished", "true",
					"Email sent successfully"));
		}
	}
}