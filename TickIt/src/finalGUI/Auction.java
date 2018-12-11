package finalGUI;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.SystemColor;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Auction {
	
	
	
	JFrame frame1;
	private JTextField txtEnterBidOffer;
	private JButton btnNewButton;
	private JTextField textField1;

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				//	BuyTicket window = new BuyTicket();
				//	window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Auction() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 969, 552);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BUY A TICKET");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 45));
		lblNewLabel.setBounds(32, 24, 388, 73);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(32, 152, 90, 90);
		lblNewLabel_1.setIcon(new ImageIcon(new ImageIcon("ListingIcon1.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("EVENT: BALL DATE: 10/03/08 Time: 19:00");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(137, 140, 548, 31);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBackground(Color.RED);
		textField.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		textField.setForeground(Color.WHITE);
		textField.setText("00:00:00");
		textField.setBounds(137, 182, 134, 50);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnBid = new JButton("BID");
		btnBid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				frame1 = new JFrame();
				
					frame1.setUndecorated(true);
					frame1.setResizable(false);
					frame1.getContentPane().setFont(new Font("Arial", Font.BOLD, 20));
					frame1.getContentPane().setBackground(Color.WHITE);
					frame1.getContentPane().setLayout(null);
					
					btnNewButton = new JButton("");
					btnNewButton.setIcon(new ImageIcon(new ImageIcon("cross.png").getImage().getScaledInstance(36, 32, Image.SCALE_DEFAULT)));
					
							btnNewButton.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									frame1.dispose();
								}
							});
							btnNewButton.setBounds(363, 36, 36, 32);
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
		btnBid.setBounds(395, 182, 106, 50);
		frame.getContentPane().add(btnBid);
		
		JButton btnBuyItNow = new JButton("BUY IT NOW $8.00");
		btnBuyItNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuyItNow.setForeground(Color.WHITE);
		btnBuyItNow.setFont(new Font("Arial", Font.BOLD, 20));
		btnBuyItNow.setBackground(Color.ORANGE);
		btnBuyItNow.setBounds(511, 182, 279, 50);
		frame.getContentPane().add(btnBuyItNow);
		
		JLabel label = new JLabel("");
		label.setBounds(32, 271, 90, 90);
		label.setIcon(new ImageIcon(new ImageIcon("ListingIcon2.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));

		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("EVENT: BALL DATE: 10/03/08 Time: 19:00");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		label_1.setBounds(137, 259, 548, 31);
		frame.getContentPane().add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setText("00:00:00");
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setForeground(Color.WHITE);
		textField_1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.RED);
		textField_1.setBounds(137, 301, 134, 50);
		frame.getContentPane().add(textField_1);
		
		JButton button_1 = new JButton("BID");
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		button_1.setBackground(Color.ORANGE);
		button_1.setBounds(395, 301, 106, 50);
		frame.getContentPane().add(button_1);
		
		JButton btnBuyItNow_1 = new JButton("BUY IT NOW $8.00\r\n");
		btnBuyItNow_1.setForeground(Color.WHITE);
		btnBuyItNow_1.setFont(new Font("Arial", Font.BOLD, 20));
		btnBuyItNow_1.setBackground(Color.ORANGE);
		btnBuyItNow_1.setBounds(511, 301, 279, 50);
		frame.getContentPane().add(btnBuyItNow_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(32, 394, 90, 90);
		label_2.setIcon(new ImageIcon(new ImageIcon("ListingIcon3.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT)));

		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("EVENT: BALL DATE: 10/03/08 Time: 19:00");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		label_3.setBounds(137, 382, 548, 31);
		frame.getContentPane().add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setText("00:00:00");
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setForeground(Color.WHITE);
		textField_2.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 20));
		textField_2.setColumns(10);
		textField_2.setBackground(Color.RED);
		textField_2.setBounds(137, 424, 134, 50);
		frame.getContentPane().add(textField_2);
		
		JButton button_4 = new JButton("BID");
		button_4.setForeground(Color.WHITE);
		button_4.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		button_4.setBackground(Color.ORANGE);
		button_4.setBounds(395, 424, 106, 50);
		frame.getContentPane().add(button_4);
		
		JButton btnBuyItNow_2 = new JButton("BUY IT NOW $8.00");
		btnBuyItNow_2.setForeground(Color.WHITE);
		btnBuyItNow_2.setFont(new Font("Arial", Font.BOLD, 20));
		btnBuyItNow_2.setBackground(Color.ORANGE);
		btnBuyItNow_2.setBounds(511, 424, 279, 50);
		frame.getContentPane().add(btnBuyItNow_2);
		
		textField_3 = new JTextField();
		textField_3.setBackground(Color.ORANGE);
		textField_3.setFont(new Font("Arial", Font.BOLD, 20));
		textField_3.setForeground(Color.WHITE);
		textField_3.setText("$4.00");
		textField_3.setBounds(285, 183, 100, 49);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setText("$4.00");
		textField_4.setForeground(Color.WHITE);
		textField_4.setFont(new Font("Arial", Font.BOLD, 20));
		textField_4.setColumns(10);
		textField_4.setBackground(Color.ORANGE);
		textField_4.setBounds(281, 302, 100, 49);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setText("$4.00");
		textField_5.setForeground(Color.WHITE);
		textField_5.setFont(new Font("Arial", Font.BOLD, 20));
		textField_5.setColumns(10);
		textField_5.setBackground(Color.ORANGE);
		textField_5.setBounds(281, 425, 100, 49);
		frame.getContentPane().add(textField_5);
		
		JButton btnSell = new JButton("SELL");
		btnSell.setForeground(Color.WHITE);
		btnSell.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnSell.setBackground(Color.ORANGE);
		btnSell.setBounds(785, 132, 132, 31);
		frame.getContentPane().add(btnSell);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnLogout.setBackground(Color.ORANGE);
		btnLogout.setBounds(785, 97, 132, 31);
		frame.getContentPane().add(btnLogout);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(847, 11, 70, 70);
		lblNewLabel_3.setIcon(new ImageIcon(new ImageIcon("Task_logo.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
		frame.getContentPane().add(lblNewLabel_3);
		
	
		
	}
}
