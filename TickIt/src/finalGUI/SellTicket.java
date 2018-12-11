package finalGUI;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.SystemColor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Client.Client;

import javax.swing.JButton;
import javax.swing.BorderFactory;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

public class SellTicket {

	JFrame frame3;
	JFrame frame1;
	JFrame frame;
	private JTextField txtName;
	private JTextField txtVenue;
	private JTextField txt;
	private JButton btnCon;
	private JButton button;
	private JButton btnBuy;
	private JLabel lblNewLabel_1;
	String Username;
	private JButton button_2;
	Client c = new Client();
	private JTextField txtEventPostcode;
	private JTextField txtYear;
	private JTextField txtMonth;
	private JTextField txtDay;
	private JTextField txtHour;
	private JTextField txtMin;
	private JTextField txtAmount;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SellTicket window = new SellTicket("ABC");
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public SellTicket(String Username) {
		this.Username = Username;
		c.start();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setResizable(false);
		frame.setBounds(100, 100, 841, 636);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblSellATicket = new JLabel("SELL A TICKET");
		lblSellATicket.setForeground(Color.WHITE);
		lblSellATicket.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 45));
		lblSellATicket.setBounds(10, 34, 436, 73);
		frame.getContentPane().add(lblSellATicket);

		txtName = new JTextField();
		txtName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtName.setText("");
			}
		});
		txtName.setText("EVENT NAME");
		txtName.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 15));
		txtName.setForeground(Color.WHITE);
		txtName.setBackground(SystemColor.textHighlight);
		txtName.setBounds(147, 140, 210, 29);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);

		txtVenue = new JTextField();
		txtVenue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtVenue.setText("");
			}
		});
		txtVenue.setText("EVENT VENUE");
		txtVenue.setForeground(Color.WHITE);
		txtVenue.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 15));
		txtVenue.setColumns(10);
		txtVenue.setBackground(SystemColor.textHighlight);
		txtVenue.setBounds(400, 140, 210, 29);
		frame.getContentPane().add(txtVenue);

		txt = new JTextField();
		txt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txt.setText("");
			}
		});
		txt.setText("EVENT CITY");
		txt.setForeground(Color.WHITE);
		txt.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 15));
		txt.setColumns(10);
		txt.setBackground(SystemColor.textHighlight);
		txt.setBounds(147, 181, 210, 25);
		frame.getContentPane().add(txt);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(147, 404, 118, 27);
		comboBox.addItem("days");
		comboBox.addItem("hours");
		comboBox.addItem("minutes");
		frame.getContentPane().add(comboBox);

		btnCon = new JButton("CONFIRM LISTING");
		btnCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frame1 = new JFrame();
				
					frame1.setUndecorated(true);
					frame1.setResizable(false);
					frame1.getContentPane().setFont(new Font("Arial", Font.BOLD, 20));
					frame1.getContentPane().setBackground(Color.WHITE);
					frame1.getContentPane().setLayout(null);
					
					JTextField txtEnterBidOffer = new JTextField();
					txtEnterBidOffer.setBackground(Color.ORANGE);
					txtEnterBidOffer.setForeground(Color.WHITE);
					txtEnterBidOffer.setHorizontalAlignment(SwingConstants.CENTER);
					txtEnterBidOffer.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
					txtEnterBidOffer.setText("ENTER USERNAME AND PASSWORD");
					txtEnterBidOffer.setEditable(false);
					txtEnterBidOffer.setBounds(28, 26, 382, 53);
					frame1.getContentPane().add(txtEnterBidOffer);
					txtEnterBidOffer.setColumns(10);
					
					JTextField textField2 = new JTextField("username");
					textField2.setBorder(BorderFactory.createLineBorder(Color.BLUE));
					textField2.setHorizontalAlignment(SwingConstants.LEFT);
					textField2.setFont(new Font("Tahoma", Font.PLAIN, 20));
					textField2.setBounds(28, 142, 382, 53);;
					frame1.getContentPane().add(textField2);
					textField2.setColumns(10);
					
					JTextField textField3 = new JTextField("password:");
					textField3.setBorder(BorderFactory.createLineBorder(Color.RED));
					textField3.setHorizontalAlignment(SwingConstants.LEFT);
					textField3.setFont(new Font("Tahoma", Font.PLAIN, 20));
					textField3.setBounds(28, 195, 382, 53);
					frame1.getContentPane().add(textField3);
					textField3.setColumns(10);

					JButton btnNewButton = new JButton("Confirm");
					btnNewButton.setIcon(new ImageIcon(new ImageIcon("cross.png").getImage().getScaledInstance(36, 32, Image.SCALE_DEFAULT)));
					
							btnNewButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									try {
										
										Client c1 = new Client();
										c1.start();
										String[] arr = c1.checkUserPass(textField2.getText(), textField3.getText());
										System.out.println(arr[1]);
										String t = "true";
										if(arr[1].equals(t)) {
											try {
												Client c = new Client();
												c.start();
												
												String[] arr1 = c.newListing(textField2.getText(), txtName.getText(), txtVenue.getText(), txt.getText(), txtEventPostcode.getText(),
												txtYear.getText(), txtMonth.getText(), txtDay.getText(), txtHour.getText(), txtMin.getText(), txtAmount.getText(), String.valueOf(comboBox.getSelectedItem()));
												String t1 = "true";
												if(arr1[1].equals(t1)) {
													JOptionPane.showMessageDialog(frame,
														    "Listing made!", " ",
														    JOptionPane.WARNING_MESSAGE);
													frame1.dispose();
													BuyTicket bt = new BuyTicket(Username);
													bt.frame.setVisible(true);
												} else {
													JOptionPane.showMessageDialog(frame,
														    "Sorry can't submit listing right now!", " ",
														    JOptionPane.WARNING_MESSAGE);
												}
											} catch (Exception e2) {
												e2.printStackTrace();
												System.out.println("unable to submit listing");
											}
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
			}
		});
			
		btnCon.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnCon.setVerticalAlignment(SwingConstants.TOP);
		btnCon.setForeground(Color.WHITE);
		btnCon.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		btnCon.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnCon.setBackground(SystemColor.textHighlight);
		btnCon.setBounds(284, 491, 299, 53);
		frame.getContentPane().add(btnCon);

		button = new JButton("Logout");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Server.Server.onlineUsers.remove(Username);
				Start s = new Start();
				JOptionPane.showConfirmDialog(frame, "Goodbye, thank you for using Tick-it!");
				s.frame.setVisible(true);
				frame.dispose();
				c.stop();
			}
		});
		button.setForeground(Color.LIGHT_GRAY);
		button.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		button.setBackground(Color.ORANGE);
		button.setBounds(185, 97, 132, 35);
		frame.getContentPane().add(button);

		btnBuy = new JButton("BUY");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					BuyTicket Lg = new BuyTicket(Username);
					Lg.frame.setVisible(true);
					frame.dispose();
				} catch (InterruptedException e1) {
					JOptionPane.showMessageDialog(frame, "Sorry can't load tickets right now");
					MainMenu mm = new MainMenu(Username);
					mm.frame.setVisible(true);
					e1.printStackTrace();
				}
			}
		});
		btnBuy.setForeground(Color.lightGray);
		btnBuy.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnBuy.setBackground(Color.ORANGE);
		btnBuy.setBounds(784, 132, 132, 35);
		frame.getContentPane().add(btnBuy);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(690, 34, 70, 70);
		lblNewLabel_1.setIcon(new ImageIcon(
				new ImageIcon("Task_logo.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("EVENT");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(31, 110, 118, 31);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtEventPostcode = new JTextField();
		txtEventPostcode.setText("EVENT POSTCODE");
		txtEventPostcode.setForeground(Color.WHITE);
		txtEventPostcode.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtEventPostcode.setColumns(10);
		txtEventPostcode.setBackground(SystemColor.textHighlight);
		txtEventPostcode.setBounds(400, 181, 210, 29);
		frame.getContentPane().add(txtEventPostcode);
		
		JLabel lblDate = new JLabel("DATE");
		lblDate.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblDate.setBounds(31, 235, 118, 31);
		frame.getContentPane().add(lblDate);
		
		txtYear = new JTextField();
		txtYear.setText("YYYY");
		txtYear.setForeground(Color.WHITE);
		txtYear.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtYear.setColumns(10);
		txtYear.setBackground(SystemColor.textHighlight);
		txtYear.setBounds(147, 265, 75, 29);
		frame.getContentPane().add(txtYear);
		
		txtMonth = new JTextField();
		txtMonth.setText("MM");
		txtMonth.setForeground(Color.WHITE);
		txtMonth.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtMonth.setColumns(10);
		txtMonth.setBackground(SystemColor.textHighlight);
		txtMonth.setBounds(234, 265, 75, 29);
		frame.getContentPane().add(txtMonth);
		
		txtDay = new JTextField();
		txtDay.setText("DD");
		txtDay.setForeground(Color.WHITE);
		txtDay.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtDay.setColumns(10);
		txtDay.setBackground(SystemColor.textHighlight);
		txtDay.setBounds(321, 265, 75, 29);
		frame.getContentPane().add(txtDay);
		
		JLabel label = new JLabel("-");
		label.setBounds(418, 272, 13, 16);
		frame.getContentPane().add(label);
		
		txtHour = new JTextField();
		txtHour.setText("Hour");
		txtHour.setForeground(Color.WHITE);
		txtHour.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtHour.setColumns(10);
		txtHour.setBackground(SystemColor.textHighlight);
		txtHour.setBounds(447, 265, 75, 29);
		frame.getContentPane().add(txtHour);
		
		JLabel label_1 = new JLabel(":");
		label_1.setBounds(525, 272, 13, 16);
		frame.getContentPane().add(label_1);
		
		txtMin = new JTextField();
		txtMin.setText("Min");
		txtMin.setForeground(Color.WHITE);
		txtMin.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtMin.setColumns(10);
		txtMin.setBackground(SystemColor.textHighlight);
		txtMin.setBounds(535, 265, 75, 29);
		frame.getContentPane().add(txtMin);
		
		JLabel lblAuction = new JLabel("AUCTION LENGTH");
		lblAuction.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblAuction.setBounds(40, 333, 182, 31);
		frame.getContentPane().add(lblAuction);
		
		JLabel lblTimeScale = new JLabel("TIME SCALE");
		lblTimeScale.setBounds(147, 376, 100, 16);
		frame.getContentPane().add(lblTimeScale);
		
		txtAmount = new JTextField();
		txtAmount.setText("AMOUNT");
		txtAmount.setForeground(Color.WHITE);
		txtAmount.setFont(new Font("Dialog", Font.PLAIN, 15));
		txtAmount.setColumns(10);
		txtAmount.setBackground(SystemColor.textHighlight);
		txtAmount.setBounds(267, 403, 92, 29);
		frame.getContentPane().add(txtAmount);
	}
}

