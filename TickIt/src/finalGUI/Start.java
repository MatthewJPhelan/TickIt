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
import javax.swing.SwingConstants;

public class Start {

	JFrame frame;

	private JLabel lblNewLabel;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Start() {
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
		
		btnNewButton = new JButton("Login");
		btnNewButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				Login su=new Login();
				su.frame.setVisible(true);
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(256, 285, 351, 80);
		frame.getContentPane().add(btnNewButton);
		
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(766, 29, 70, 70);
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon("Task_logo.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));

		frame.getContentPane().add(lblNewLabel);
		
		btnNewButton = new JButton("SIGN UP");
		btnNewButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SignUp su=new SignUp();
				su.frame.setVisible(true);
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(256, 402, 351, 80);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblWelcome = new JLabel("Welcome to Tick IT!");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 25));
		lblWelcome.setBackground(Color.ORANGE);
		lblWelcome.setBounds(175, 98, 584, 107);
		frame.getContentPane().add(lblWelcome);
	}
}
