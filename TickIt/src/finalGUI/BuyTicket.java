package finalGUI;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;
import java.util.TooManyListenersException;
import java.util.Timer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Client.Client;

public class BuyTicket {

	JFrame frame3;
	JFrame frame1;
	private JTextField txtEnterBidOffer;
	private JButton btnNewButton;
	JTextField textField1;
	JTextField textField2;
	JTextField textField3;
	JFrame frame;
	JTextField textTimer;
	JTextField textField_3;
	JTextField textField_5;
	static ResultSet RS;
	
	static ArrayList<String[]> arr;
	Client c = new Client();
	Panel panel;
	int index;
	Thread thread;
	
	String username;
	Timer timer;
	String Username;

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//
//					BuyTicket window = new BuyTicket("def");
//					window.frame.setVisible(true);
//
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public BuyTicket(String Username, ArrayList<String[]> arr) throws InterruptedException {
		this.username = Username;
		initialize();
		getNext(arr);
	}

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 */
	/**
	 * @wbp.parser.constructor
	 */
	public BuyTicket(String Username) throws InterruptedException {
		this.username = Username;
		initialize();
		arr = getListingInfo();
		getNext(arr);
	}
	
	public ArrayList<String[]> getListingInfo() {
		c.start();
		ArrayList<String[]> arrList = new ArrayList<String[]>();
		arrList = c.get_listings();
		return arrList;

	}
	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(36, 199, 249));
		frame.setBounds(100, 100, 969, 627);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("BUY A TICKET");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 45));
		lblNewLabel.setBounds(32, 24, 388, 73);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Server.Server.onlineUsers.remove(Username);
				Start s = new Start();
				JOptionPane.showMessageDialog(frame, "Goodbye, thank you for using Tick-it!");
				s.frame.setVisible(true);
				frame.dispose();
				c.stop();
			}
		});
		btnLogout.setForeground(Color.LIGHT_GRAY);
		btnLogout.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnLogout.setBackground(Color.ORANGE);
		btnLogout.setBounds(785, 97, 132, 31);
		frame.getContentPane().add(btnLogout);
		
		JButton btnSell = new JButton("SELL");
		btnSell.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SellTicket ST = new SellTicket(username);
				ST.frame.setVisible(true);
			}
		});
		btnSell.setForeground(Color.LIGHT_GRAY);
		btnSell.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnSell.setBackground(Color.ORANGE);
		btnSell.setBounds(785, 132, 132, 31);
		frame.getContentPane().add(btnSell);
		
		JButton btnRefresh = new JButton("REFRESH");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyTicket t = null;
				try {
					t = new BuyTicket(username);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				t.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnRefresh.setForeground(Color.LIGHT_GRAY);
		btnRefresh.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnRefresh.setBackground(Color.ORANGE);
		btnRefresh.setBounds(785, 177, 132, 31);
		frame.getContentPane().add(btnRefresh);
		
		JButton button_2 = new JButton("My Profile");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserPage up = new UserPage(Username);
				up.frame.setVisible(true);
				frame.dispose();
			}
		});
		button_2.setForeground(Color.LIGHT_GRAY);
		button_2.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		button_2.setBackground(Color.ORANGE);
		button_2.setBounds(382, 518, 249, 49);
		frame.getContentPane().add(button_2);
	}
	
	private void getNext(ArrayList<String[]> arr2) throws InterruptedException {
		int labelPos = 140;
		int txtPos = 182;
		
		if(arr2.size()>3) {
	
			for(int i = 0; i<3; i++) {
				int listId = Integer.parseInt(arr2.get(i)[2]);
				int sellerId = Integer.parseInt(arr2.get(i)[3]);
				String event = arr2.get(i)[4];
				String date = arr2.get(i)[5];
				String venue = arr2.get(i)[6];
				String city = arr2.get(i)[7];
				String postc = arr2.get(i)[8];
				String start = arr2.get(i)[9];
				String end = arr2.get(i)[10];
				String highestBid = arr2.get(i)[11];
				String buyerId = arr2.get(i)[12];
				String emailSent = arr2.get(i)[13];
				System.out.println("listId1: " + listId);
				JLabel lblNewLabel_2 = new JLabel("EVENT: "+ event + "     DATE/TIME: " + date);
				lblNewLabel_2.setForeground(Color.WHITE);
				lblNewLabel_2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
				lblNewLabel_2.setBounds(137, labelPos, 548, 31);
				frame.getContentPane().add(lblNewLabel_2);
				
				CountdownTime cdt = new CountdownTime(listId);
				String diff = cdt.getStart();
//				timer = new Timer();
//				timer.schedule(textTimer.setText(cdt.run(textTimer.getText())), 1000);
//				
				textTimer = new JTextField();
				textTimer.setHorizontalAlignment(SwingConstants.CENTER);
				textTimer.setBackground(Color.RED);
				textTimer.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
				textTimer.setForeground(Color.WHITE);
				textTimer.setText(diff);
				textTimer.setBounds(137, txtPos, 192, 50);
				frame.getContentPane().add(textTimer);
				textTimer.setColumns(10);
				
				JButton btnBid = new JButton("BID");
				btnBid.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("listId2: " + listId);

						frame1 = new JFrame();
						
							frame1.setUndecorated(true);
							frame1.setResizable(false);
							frame1.getContentPane().setFont(new Font("Arial", Font.BOLD, 20));
							frame1.getContentPane().setBackground(Color.WHITE);
							frame1.getContentPane().setLayout(null);
							
							txtEnterBidOffer = new JTextField();
							txtEnterBidOffer.setBackground(Color.ORANGE);
							txtEnterBidOffer.setForeground(Color.WHITE);
							txtEnterBidOffer.setHorizontalAlignment(SwingConstants.CENTER);
							txtEnterBidOffer.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
							txtEnterBidOffer.setText("ENTER BID OFFER");
							txtEnterBidOffer.setEditable(false);
							txtEnterBidOffer.setBounds(28, 26, 382, 53);
							frame1.getContentPane().add(txtEnterBidOffer);
							txtEnterBidOffer.setColumns(10);
							
							textField1 = new JTextField("bid");
							textField1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
							textField1.setHorizontalAlignment(SwingConstants.LEFT);
							textField1.setFont(new Font("Tahoma", Font.PLAIN, 20));
							textField1.setBounds(28, 89, 382, 53);
							frame1.getContentPane().add(textField1);
							textField1.setColumns(10);
							
							textField2 = new JTextField("username");
							textField2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
							textField2.setHorizontalAlignment(SwingConstants.LEFT);
							textField2.setFont(new Font("Tahoma", Font.PLAIN, 20));
							textField2.setBounds(28, 142, 382, 53);;
							frame1.getContentPane().add(textField2);
							textField2.setColumns(10);
							
							textField3 = new JTextField("password: " + listId);
							textField3.setBorder(BorderFactory.createLineBorder(Color.RED));
							textField3.setHorizontalAlignment(SwingConstants.LEFT);
							textField3.setFont(new Font("Tahoma", Font.PLAIN, 20));
							textField3.setBounds(28, 195, 382, 53);
							frame1.getContentPane().add(textField3);
							textField3.setColumns(10);
							
							JTextField textField4;
							textField4 = new JTextField(listId);
							textField4.setBorder(BorderFactory.createLineBorder(Color.RED));
							textField4.setHorizontalAlignment(SwingConstants.LEFT);
							textField4.setFont(new Font("Tahoma", Font.PLAIN, 20));
							textField4.setBounds(28, 600, 382, 53);
							frame1.getContentPane().add(textField4);
							textField4.setColumns(10);
							
							btnNewButton = new JButton("Confirm");
							btnNewButton.setIcon(new ImageIcon(new ImageIcon("cross.png").getImage().getScaledInstance(36, 32, Image.SCALE_DEFAULT)));
							
									btnNewButton.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											try {
												double offer = Double.parseDouble(textField1.getText());
												//int listingId = Integer.parseInt(textField4.getText());
												Client c1 = new Client();
												c1.start();
												System.out.println("check");
												String[] arr = c1.checkUserPass(textField2.getText(), textField3.getText());
												System.out.println(arr[1]);
												String t = "true";
												if(arr[1].equals(t)) {
													System.out.println(listId);
													System.out.println(offer);
													System.out.println(textField2.getText());
													Client c2 = new Client();
													c2.start();
													c2.newBid(listId, offer, textField2.getText());
													JOptionPane.showMessageDialog(frame,
															"Bid made!", " ",
														    JOptionPane.WARNING_MESSAGE);
													frame1.dispose();
												} else {
													JOptionPane.showMessageDialog(frame,
															"Incorrect username/password", " ",
														    JOptionPane.WARNING_MESSAGE);
												}
											} catch (Exception e2) {
												e2.printStackTrace();
												System.out.println("unable to do new bid");
											}
										}
									});
							btnNewButton.setBounds(28, 248, 382, 53);
							frame1.getContentPane().add(btnNewButton);
							
							frame1.setBounds(150, 150, 437, 400);
							frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							frame1.setVisible(true);
							
							
		
						
						
						//frame.setEnabled(false);
						
						//b.frame1.setVisible(true);
						
						
					}
				});
				btnBid.setForeground(Color.WHITE);
				btnBid.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
				btnBid.setBackground(Color.ORANGE);
				btnBid.setBounds(489, txtPos, 225, 50);
				frame.getContentPane().add(btnBid);
				
//				JButton btnBuyItNow = new JButton("BUY IT NOW $8.00");
//				btnBuyItNow.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//					}
//				});
//				btnBuyItNow.setForeground(Color.WHITE);
//				btnBuyItNow.setFont(new Font("Arial", Font.BOLD, 20));
//				btnBuyItNow.setBackground(Color.ORANGE);
//				btnBuyItNow.setBounds(511, txtPos, 279, 50);
//				frame.getContentPane().add(btnBuyItNow);
//				
				textField_3 = new JTextField();
				textField_3.setBackground(Color.ORANGE);
				textField_3.setFont(new Font("Arial", Font.BOLD, 20));
				textField_3.setForeground(Color.WHITE);
				textField_3.setText(highestBid);
				textField_3.setBounds(341, txtPos, 132, 49);
				frame.getContentPane().add(textField_3);
				textField_3.setColumns(10);
				
				labelPos = labelPos + 120;
				txtPos = txtPos + 120;
			
			}
		} else {
			for(int i = 0; i<arr2.size(); i++) {
				int listId = Integer.parseInt(arr2.get(i)[2]);
				int sellerId = Integer.parseInt(arr2.get(i)[3]);
				String event = arr2.get(i)[4];
				String date = arr2.get(i)[5];
				String venue = arr2.get(i)[6];
				String city = arr2.get(i)[7];
				String postc = arr2.get(i)[8];
				String start = arr2.get(i)[9];
				String end = arr2.get(i)[10];
				String highestBid = arr2.get(i)[11];
				String buyerId = arr2.get(i)[12];
				String emailSent = arr2.get(i)[13];
				System.out.println("listId4: " + listId);

				JLabel lblNewLabel_2 = new JLabel("EVENT: "+ event + "     DATE/TIME: " + date);
				lblNewLabel_2.setForeground(Color.WHITE);
				lblNewLabel_2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
				lblNewLabel_2.setBounds(137, labelPos, 548, 31);
				frame.getContentPane().add(lblNewLabel_2);
				
				CountdownTime cdt = new CountdownTime(listId);
				String diff = cdt.getStart();

				textTimer = new JTextField();
				textTimer.setHorizontalAlignment(SwingConstants.CENTER);
				textTimer.setBackground(Color.RED);
				textTimer.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
				textTimer.setForeground(Color.WHITE);
				textTimer.setText(diff);
				textTimer.setBounds(137, txtPos, 134, 50);
				frame.getContentPane().add(textTimer);
				textTimer.setColumns(10);
				
				JButton btnBid = new JButton("BID");
				btnBid.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("listId5: " + listId);

						frame1 = new JFrame();
						
							frame1.setUndecorated(true);
							frame1.setResizable(false);
							frame1.getContentPane().setFont(new Font("Arial", Font.BOLD, 20));
							frame1.getContentPane().setBackground(Color.WHITE);
							frame1.getContentPane().setLayout(null);
							
							btnNewButton = new JButton("Confirm");
							btnNewButton.setIcon(new ImageIcon(new ImageIcon("cross.png").getImage().getScaledInstance(36, 32, Image.SCALE_DEFAULT)));
							
									btnNewButton.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											System.out.println("listId6: " + listId);
											try {
												double offer = Double.parseDouble(textField1.getText());
												//int listingId = Integer.parseInt(textField4.getText());
												Client c1 = new Client();
												c1.start();
												System.out.println("check");
												String[] arr = c1.checkUserPass(textField2.getText(), textField3.getText());
												System.out.println(arr[1]);
												String t = "true";
												if(arr[1].equals(t)) {
													System.out.println(listId);
													System.out.println(offer);
													System.out.println(textField2.getText());
													Client c2 = new Client();
													c2.start();
													c2.newBid(listId, offer, textField2.getText());
													JOptionPane.showMessageDialog(frame,
															"Bid made!", " ",
														    JOptionPane.WARNING_MESSAGE);
													frame1.dispose();
												} else {
													JOptionPane.showMessageDialog(frame,
															"Incorrect username/password", " ",
														    JOptionPane.WARNING_MESSAGE);
												}
											} catch (Exception e2) {
												e2.printStackTrace();
												System.out.println("unable to do new bid");
											}
										}
									});
							btnNewButton.setBounds(28, 248, 382, 53);
							frame1.getContentPane().add(btnNewButton);
							
							txtEnterBidOffer = new JTextField();
							txtEnterBidOffer.setBackground(Color.ORANGE);
							txtEnterBidOffer.setForeground(Color.WHITE);
							txtEnterBidOffer.setHorizontalAlignment(SwingConstants.CENTER);
							txtEnterBidOffer.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
							txtEnterBidOffer.setText("ENTER BID OFFER");
							txtEnterBidOffer.setEditable(false);
							txtEnterBidOffer.setBounds(28, 26, 382, 53);
							frame1.getContentPane().add(txtEnterBidOffer);
							txtEnterBidOffer.setColumns(10);
							
							textField1 = new JTextField();
							textField1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
							textField1.setHorizontalAlignment(SwingConstants.LEFT);
							textField1.setFont(new Font("Tahoma", Font.PLAIN, 20));
							textField1.setBounds(28, 89, 382, 104);
							frame1.getContentPane().add(textField1);
							textField1.setColumns(10);
							
							textField2 = new JTextField("username");
							textField2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
							textField2.setHorizontalAlignment(SwingConstants.LEFT);
							textField2.setFont(new Font("Tahoma", Font.PLAIN, 20));
							textField2.setBounds(28, 142, 382, 53);;
							frame1.getContentPane().add(textField2);
							textField2.setColumns(10);
							
							textField3 = new JTextField("password");
							textField3.setBorder(BorderFactory.createLineBorder(Color.RED));
							textField3.setHorizontalAlignment(SwingConstants.LEFT);
							textField3.setFont(new Font("Tahoma", Font.PLAIN, 20));
							textField3.setBounds(28, 195, 382, 53);
							frame1.getContentPane().add(textField3);
							textField3.setColumns(10);
							
							frame1.setBounds(150, 150, 437, 222);
							frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							frame1.setVisible(true);
							
							
		
						
						
						//frame.setEnabled(false);
						
						//b.frame1.setVisible(true);
						
						
					}
				});
				btnBid.setForeground(Color.WHITE);
				btnBid.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
				btnBid.setBackground(Color.ORANGE);
				btnBid.setBounds(489, txtPos, 225, 50);
				frame.getContentPane().add(btnBid);
				
//				JButton btnBuyItNow = new JButton("BUY IT NOW $8.00");
//				btnBuyItNow.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//					}
//				});
//				btnBuyItNow.setForeground(Color.WHITE);
//				btnBuyItNow.setFont(new Font("Arial", Font.BOLD, 20));
//				btnBuyItNow.setBackground(Color.ORANGE);
//				btnBuyItNow.setBounds(511, txtPos, 279, 50);
//				frame.getContentPane().add(btnBuyItNow);
				
				textField_3 = new JTextField();
				textField_3.setBackground(Color.ORANGE);
				textField_3.setFont(new Font("Arial", Font.BOLD, 20));
				textField_3.setForeground(Color.WHITE);
				textField_3.setText(highestBid);
				textField_3.setBounds(285, txtPos, 100, 49);
				frame.getContentPane().add(textField_3);
				textField_3.setColumns(10);
				
				labelPos = labelPos + 120;
				txtPos = txtPos + 120;
			}
		}
		if (arr2.size()>3) {
			JButton btnPageDown = new JButton("Page Down");
			btnPageDown.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ArrayList<String[]> arr3 = new ArrayList<String[]>();
						for(int i = 3; i<arr2.size(); i++) {
							arr3.add(arr2.get(i));
						}
						System.out.println(arr3.toString());
						BuyTicket t = null;
						try {
							t = new BuyTicket(username, arr3);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						t.frame.setVisible(true);
						frame.dispose();
						
				}
			});
			
			btnPageDown.setBounds(785, 456, 132, 31);
			frame.getContentPane().add(btnPageDown);
			} else {
				
			}
	
	}
//	
//	private void initialize() {
//		frame = new JFrame();
//		frame.setResizable(false);
//		frame.getContentPane().setBackground(SystemColor.textHighlight);
//		frame.setBounds(100, 100, 969, 627);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.getContentPane().setLayout(null);
//
//		JLabel lblNewLabel = new JLabel("BUY A TICKET");
//		lblNewLabel.setForeground(Color.WHITE);
//		lblNewLabel.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 45));
//		lblNewLabel.setBounds(32, 24, 388, 73);
//		frame.getContentPane().add(lblNewLabel);
//
//		JLabel lblNewLabel_1 = new JLabel("");
//		lblNewLabel_1.setBounds(32, 152, 90, 90);
//		lblNewLabel_1.setIcon(new ImageIcon(
//				new ImageIcon("ListingIcon1.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
//		frame.getContentPane().add(lblNewLabel_1);
//
//		String Event = null;
//		String Date = null;
//		String Time = null;
//		int end = 0;
//		int bid = 0;
//
//		try {
//			if (RS.next()) {
//
//				Event = RS.getString(3);
//				Date = RS.getString(4);
//				Time = RS.getString(5);
//				bid = RS.getInt(6);
//				end = RS.getInt(7);
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//
//		}
//
//		JLabel lblNewLabel_2 = new JLabel("EVENT: " + Event + " DATE: " + Date + " Time: " + Time);
//		lblNewLabel_2.setForeground(Color.WHITE);
//		lblNewLabel_2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
//		lblNewLabel_2.setBounds(137, 140, 548, 31);
//		frame.getContentPane().add(lblNewLabel_2);
//
//		textField_3 = new JTextField();
//		textField_3.setBackground(Color.ORANGE);
//		textField_3.setFont(new Font("Arial", Font.BOLD, 20));
//		textField_3.setForeground(Color.WHITE);
//		textField_3.setText("$" + bid);
//		textField_3.setBounds(285, 183, 100, 49);
//		frame.getContentPane().add(textField_3);
//		textField_3.setColumns(10);
//
//		JButton btnBid = new JButton("BID");
//		btnBid.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				frame1 = new JFrame();
//
//				frame1.setUndecorated(true);
//				frame1.setResizable(false);
//				frame1.getContentPane().setFont(new Font("Arial", Font.BOLD, 20));
//				frame1.getContentPane().setBackground(Color.WHITE);
//				frame1.getContentPane().setLayout(null);
//
//				textField1 = new JTextField();
//				textField1.setBorder(BorderFactory.createLineBorder(Color.WHITE));
//				textField1.setHorizontalAlignment(SwingConstants.LEFT);
//				textField1.setFont(new Font("Tahoma", Font.PLAIN, 20));
//				textField1.setBounds(28, 89, 382, 104);
//				frame1.getContentPane().add(textField1);
//				textField1.setColumns(10);
//
//				btnNewButton = new JButton("");
//				btnNewButton.setIcon(new ImageIcon(
//						new ImageIcon("cross.png").getImage().getScaledInstance(36, 32, Image.SCALE_DEFAULT)));
//
//				btnNewButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//
//						String s = textField1.getText();
//						String countdown = textField.getText();
//						String previous_bid = textField_3.getText();
//
//						double current = Double.parseDouble(s.replaceAll("^.*?(-?\\d+(\\.\\d+)?).*$", "$1"));
//						double bid = Double.parseDouble(previous_bid.replaceAll("^.*?(-?\\d+(\\.\\d+)?).*$", "$1"));
//
//						double count = Double.parseDouble(countdown.replaceAll("^.*?(-?\\d+(\\.\\d+)?).*$", "$1"));
//
//						if (current > bid)
//
//						{
//							textField_3.setText("$" + s);
//							if (count == 0.0) {
//
//							} else {
//								try {
//									String G = "insert into bid values ('" + Username + "'," + 1 + "," + s + ")";
//									Query.QueryUpdate(G);
//								} catch (SQLException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//								}
//
//								frame1.dispose();
//								frame.setVisible(true);
//							}
//
//						} else {
//							frame1.dispose();
//							frame.setVisible(true);
//						}
//					}
//				});
//				btnNewButton.setBounds(363, 36, 36, 32);
//				frame1.getContentPane().add(btnNewButton);
//
//				txtEnterBidOffer = new JTextField();
//				txtEnterBidOffer.setBackground(Color.ORANGE);
//				txtEnterBidOffer.setForeground(Color.WHITE);
//				txtEnterBidOffer.setHorizontalAlignment(SwingConstants.CENTER);
//				txtEnterBidOffer.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
//				txtEnterBidOffer.setText("ENTER BID OFFER");
//				txtEnterBidOffer.setEditable(false);
//				txtEnterBidOffer.setBounds(28, 26, 382, 53);
//				frame1.getContentPane().add(txtEnterBidOffer);
//				txtEnterBidOffer.setColumns(10);
//
//				frame1.setBounds(150, 150, 437, 222);
//				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				frame1.setVisible(true);
//
//			}
//		});
//		btnBid.setForeground(Color.WHITE);
//		btnBid.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
//		btnBid.setBackground(Color.ORANGE);
//		btnBid.setBounds(395, 182, 106, 50);
//		frame.getContentPane().add(btnBid);
//
//		JButton btnBuyItNow = new JButton("BUY IT NOW");
//
//		btnBuyItNow.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				frame3 = new JFrame();
//
//				frame3.setUndecorated(true);
//				frame3.setResizable(false);
//				frame3.getContentPane().setFont(new Font("Arial", Font.BOLD, 20));
//				frame3.getContentPane().setBackground(Color.WHITE);
//				frame3.getContentPane().setLayout(null);
//
//				JButton btnNewButton = new JButton("");
//				btnNewButton.setIcon(new ImageIcon(
//						new ImageIcon("cross.png").getImage().getScaledInstance(36, 32, Image.SCALE_DEFAULT)));
//				btnNewButton.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//
//						frame3.dispose();
//						frame.setVisible(true);
//					}
//				});
//				btnNewButton.setBounds(366, 22, 36, 32);
//				btnBuyItNow.setEnabled(false);
//				frame3.getContentPane().add(btnNewButton);
//
//				JTextField txtCongrats = new JTextField();
//				txtCongrats.setEditable(false);
//				txtCongrats.setBackground(Color.ORANGE);
//				txtCongrats.setForeground(Color.WHITE);
//				txtCongrats.setHorizontalAlignment(SwingConstants.LEFT);
//				txtCongrats.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
//				txtCongrats.setText("Congrats on your purchase");
//				txtCongrats.setBounds(18, 11, 391, 57);
//				frame3.getContentPane().add(txtCongrats);
//				txtCongrats.setColumns(10);
//				String FName = null;
//				String LName = null;
//				String Number = null;
//
//				try {
//					String Q = "select FName,LName , mobileno from Client ,ticket where Client.username=ticket.username  and ticket.ticketid = 1";
//					ResultSet R = Query.QueryExecute(Q);
//					R.next();
//					FName = R.getString(1);
//					LName = R.getString(2);
//					Number = R.getString(3);
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
////				timer4.stop();
//				JLabel lblNewLabel3 = new JLabel(
//						"You may contact the seller " + FName + " " + LName + " at\r\n\r\n " + Number);
//				lblNewLabel3.setFont(new Font("Tahoma", Font.PLAIN, 18));
//				lblNewLabel3.setHorizontalAlignment(SwingConstants.LEFT);
//				lblNewLabel3.setBounds(10, 82, 427, 97);
//				frame3.getContentPane().add(lblNewLabel3);
//
//				frame3.setBounds(150, 150, 437, 222);
//				frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				frame3.setVisible(true);
//
//			}
//		});
//		btnBuyItNow.setForeground(Color.WHITE);
//		btnBuyItNow.setFont(new Font("Arial", Font.BOLD, 20));
//		btnBuyItNow.setBackground(Color.ORANGE);
//		btnBuyItNow.setBounds(511, 182, 279, 50);
//		btnBuyItNow.setEnabled(false);
//		frame.getContentPane().add(btnBuyItNow);
//
//		Timer timer = new Timer(1000, null);
//		timer.setInitialDelay(end * 100);
//		timer.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				btnBuyItNow.setEnabled(true);
//			}
//		});
//		timer.start();
//
//		textField = new JTextField();
//		textField.setEditable(false);
//		textField.setHorizontalAlignment(SwingConstants.CENTER);
//		textField.setBackground(Color.RED);
//		textField.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
//		textField.setForeground(Color.WHITE);
//
//		textField.setBounds(137, 182, 134, 50);
//		frame.getContentPane().add(textField);
//		textField.setColumns(10);
//		Date D = new Date(0);
//		long end1 = end;
//		timer4 = new Timer(100, null);
//		timer4.addActionListener(new ActionListener() {
//			// endtime - currenttime
//			// int
//
//			long count = end1 - D.getTime();
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// int convert hrs//min//ss
//				textField.setText(count-- + "");
//				if (count == -200)
//				{
//					try {
//						Query.QueryUpdate("update ticket set status='Not Sell' where ticketid=1 ");
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					timer4.stop();
//			}
//		}});
//		timer4.start();
//
//		JLabel label = new JLabel("");
//		label.setBounds(32, 271, 90, 90);
//		label.setIcon(new ImageIcon(
//				new ImageIcon("ListingIcon2.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
//		frame.getContentPane().add(label);
//		Event = null;
//		Date = null;
//		Time = null;
//		end = 0;
//		bid = 0;
//		try {
//			if (RS.next()) {
//				Event = RS.getString(3);
//				Date = RS.getString(4);
//				Time = RS.getString(5);
//				bid = RS.getInt(6);
//				end = RS.getInt(7);
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//
//		}
//
//		Timer timer2 = new Timer(100, null);
//		timer2.setInitialDelay(end * 100);
//		timer2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				btnBuyItNow_1.setEnabled(true);
//			}
//		});
//		timer2.start();
//		Date D2 = new Date(0);
//
//		long end2 = end;
//		timer5 = new Timer(100, null);
//		timer5.addActionListener(new ActionListener() {
//			// endtime - currenttime
//			// int
//
//			long count = end2 - D2.getTime();
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// int convert hrs//min//ss
//				textField_1.setText(count-- + "");
//				if (count == -201)
//				{
//					try {
//						Query.QueryUpdate("update ticket set status='Not Sell' where ticketid=2 ");
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					timer5.stop();
//			}
//		}});
//		timer5.start();
//
//		JLabel label_2 = new JLabel("");
//		label_2.setBounds(32, 394, 90, 90);
//		label_2.setIcon(new ImageIcon(
//				new ImageIcon("ListingIcon3.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
//
//		frame.getContentPane().add(label_2);
//
//		Event = null;
//		Date = null;
//		Time = null;
//		end = 0;
//		bid = 0;
//		try {
//			if (RS.next()) {
//				Event = RS.getString(3);
//				Date = RS.getString(4);
//				Time = RS.getString(5);
//				bid = RS.getInt(6);
//				end = RS.getInt(7);
//			}
//		} catch (SQLException e1) {
//			// F
//
//		}
//
//		Timer timer3 = new Timer(100, null);
//		timer3.setInitialDelay(end * 100);
//		timer3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				btnBuyItNow_2.setEnabled(true);
//			}
//		});
//		timer3.start();
//
//		long end3 = end;
//
//		timer6 = new Timer(100, null);
//		timer6.addActionListener(new ActionListener() {
//			// endtime - currenttime
//			// int
//
//			long count = end3 - D2.getTime();
//
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// int convert hrs//min//ss
//				textField_2.setText(count-- + "");
//				if (count == -200)
//				{
//					try {
//						Query.QueryUpdate("update ticket set status='Not Sell' where ticketid=3 ");
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					timer6.stop();
//			}
//		}});
//		timer6.start();
//
//		JButton btnSell = new JButton("SELL");
//		btnSell.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.dispose();
//				SellTicket ST = new SellTicket(Username);
//				ST.frame.setVisible(true);
//			}
//		});
//		btnSell.setForeground(Color.WHITE);
//		btnSell.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
//		btnSell.setBackground(Color.ORANGE);
//		btnSell.setBounds(785, 132, 132, 31);
//		frame.getContentPane().add(btnSell);
//
//		JButton btnLogout = new JButton("Logout");
//		btnLogout.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frame.dispose();
//				Login Lg = new Login();
//				Lg.frame.setVisible(true);
//			}
//		});
//		btnLogout.setForeground(Color.WHITE);
//		btnLogout.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
//		btnLogout.setBackground(Color.ORANGE);
//		btnLogout.setBounds(785, 97, 132, 31);
//		frame.getContentPane().add(btnLogout);
//
//		JLabel lblNewLabel_3 = new JLabel("");
//		lblNewLabel_3.setBounds(847, 11, 70, 70);
//		lblNewLabel_3.setIcon(new ImageIcon(
//				new ImageIcon("Task_logo.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
//		frame.getContentPane().add(lblNewLabel_3);
//
//		JButton button = new JButton("Confirmed Purchase");
//		button.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String Q = "select buy.ticketid,eventname,eventdate,eventtime from buy inner join ticket on buy.ticketid=ticket.ticketid where buy.username='"
//						+ Username + "'";
//				ResultSet RS = Query.QueryExecute(Q);
//				frame1 = new JFrame();
//				String[] Colume = { "TicketID", "Event Name", "Event Date", "Event Time" };
//
//				DefaultTableModel table_model = new DefaultTableModel(Colume, 0);
//				JTable table = new JTable(table_model);
//
//				try {
//					while (RS.next()) {
//						String data1 = RS.getString(1);
//						String data2 = RS.getString(2);
//						String data3 = RS.getString(3);
//						String data4 = RS.getString(4);
//
//						Object[] row = { data1, data2, data3, data4 };
//
//						DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//						model.addRow(row);
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				JPanel panel = new JPanel();
//				panel.add(new JScrollPane(table));
//
//				frame1.setBounds(100, 100, 500, 500);
//				frame1.getContentPane().add(panel);
//				frame1.setVisible(true);
//			}
//		});
//		button.setForeground(Color.WHITE);
//		button.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
//		button.setBackground(Color.ORANGE);
//		button.setBounds(32, 518, 317, 49);
//		frame.getContentPane().add(button);
//
//		JButton button_2 = new JButton("Ticket On Sale");
//		button_2.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String Q = "select ticketid,eventname,eventdate,eventtime from ticket  where status = 'sale'";
//				ResultSet RS = Query.QueryExecute(Q);
//				frame1 = new JFrame();
//				String[] Colume = { "TicketID", "Event Name", "Event Date", "Event Time" };
//
//				DefaultTableModel table_model = new DefaultTableModel(Colume, 0);
//				JTable table = new JTable(table_model);
//
//				try {
//					while (RS.next()) {
//						String data1 = RS.getString(1);
//						String data2 = RS.getString(2);
//						String data3 = RS.getString(3);
//						String data4 = RS.getString(4);
//
//						Object[] row = { data1, data2, data3, data4 };
//
//						DefaultTableModel model = (DefaultTableModel) table.getModel();
//
//						model.addRow(row);
//					}
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				JPanel panel = new JPanel();
//				panel.add(new JScrollPane(table));
//
//				frame1.setBounds(100, 100, 500, 500);
//				frame1.getContentPane().add(panel);
//				frame1.setVisible(true);
//			}
//		});
//		button_2.setForeground(Color.WHITE);
//		button_2.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
//		button_2.setBackground(Color.ORANGE);
//		button_2.setBounds(382, 518, 249, 49);
//		frame.getContentPane().add(button_2);
//
//		JButton button_3 = new JButton("Lost \t Auction");
//		button_3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//
//				frame1 = new JFrame();
//				String[] Colume = { "Colume 1", "Colume 2", "Colume 3", "Colume 4" };
//
//				DefaultTableModel table_model = new DefaultTableModel(Colume, 10);
//				JTable table = new JTable(table_model);
//				//
//				// try {
//				// while (RS.next()) {
//				// String data1 = RS.getString(1);
//				// String data2 = RS.getString(2);
//				// String data3 = RS.getString(3);
//				// String data4 = RS.getString(4);
//				//
//				// Object[] row = { data1, data2, data3, data4 };
//				//
//				// DefaultTableModel model = (DefaultTableModel) table.getModel();
//				//
//				// model.addRow(row);
//				// }
//				// } catch (SQLException e1) {
//				// // TODO Auto-generated catch block
//				// e1.printStackTrace();
//				// }
//
//				JPanel panel = new JPanel();
//				panel.add(new JScrollPane(table));
//
//				frame1.setBounds(100, 100, 500, 500);
//				frame1.getContentPane().add(panel);
//				frame1.setVisible(true);
//			}
//		});
//		button_3.setForeground(Color.WHITE);
//		button_3.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
//		button_3.setBackground(Color.ORANGE);
//		button_3.setBounds(664, 518, 270, 49);
//		frame.getContentPane().add(button_3);
//
//	}
}
