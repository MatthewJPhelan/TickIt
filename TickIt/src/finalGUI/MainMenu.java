package finalGUI;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MainMenu {

	JFrame frame;

	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton button;
	private String Username;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainMenu window = new MainMenu("ABC");
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
	public MainMenu(String Username) {
		this.Username = Username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(36, 199, 249));
		frame.setBounds(100, 100, 851, 601);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnNewButton = new JButton("My Profile");
		btnNewButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserPage su = new UserPage(Username);
				su.frame.setVisible(true);
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(256, 181, 350, 70);
		frame.getContentPane().add(btnNewButton);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(766, 29, 70, 70);
		lblNewLabel.setIcon(new ImageIcon(
				new ImageIcon("Task_logo.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));

		frame.getContentPane().add(lblNewLabel);

		btnNewButton = new JButton("Sell A Ticket");
		btnNewButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SellTicket su = new SellTicket(Username);
				su.frame.setVisible(true);
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(256, 402, 351, 80);
		frame.getContentPane().add(btnNewButton);

		JLabel lblWelcome = new JLabel("Welcome to Tick It!");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 25));
		lblWelcome.setBackground(Color.ORANGE);
		lblWelcome.setBounds(143, 11, 584, 107);
		frame.getContentPane().add(lblWelcome);

		button = new JButton("Buy A Ticket");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuyTicket su;
				System.out.println("before try");
				try {
					System.out.println("try in");
					su = new BuyTicket(Username);
					System.out.println("frame to vis");
					su.frame.setVisible(true);
				} catch (InterruptedException e1) {
					System.out.println("catch it");
					System.out.println("Thread interupted!");
					JOptionPane.showMessageDialog(frame, "Sorry can't view tickets right now");
					MainMenu mm = new MainMenu(Username);
					mm.frame.setVisible(true);
					e1.printStackTrace();
				}
				System.out.println("after tc");
				frame.dispose();
			}
		});
		button.setVerticalAlignment(SwingConstants.TOP);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		button.setBackground(Color.ORANGE);
		button.setBounds(256, 284, 351, 80);
		frame.getContentPane().add(button);
	}
}
