package finalGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import Client.Client;

public class Login {

	JFrame frame;
	private JTextField txtUsernam;
	private JPasswordField txtPassword;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton btnBack;
	private JButton btnForgottenPassword;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login window = new Login();
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
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(36, 199, 249));
		frame.setBounds(100, 100, 851, 601);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtUsernam = new JTextField();
		txtUsernam.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtUsernam.setText("");
			}
		});
		txtUsernam.setForeground(Color.WHITE);
		txtUsernam.setBackground(Color.ORANGE);
		txtUsernam.setText("USERNAME");
		txtUsernam.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		txtUsernam.setBounds(252, 249, 351, 53);
		frame.getContentPane().add(txtUsernam);
		txtUsernam.setColumns(10);
		
		btnNewButton = new JButton("Login");
		btnNewButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Client c = new Client();
				c.start();
				
				String username = txtUsernam.getText();
				String password = txtPassword.getText();
				
				String[] response = c.signin(username, password);
				if(response[1].equals("true")){
					frame.dispose();
					MainMenu mm = new MainMenu(username);
					mm.frame.setVisible(true);
					
				}else{
					JOptionPane.showMessageDialog(frame,
						    response[2],
						    "Sign-in Error",
						    JOptionPane.WARNING_MESSAGE);
					c.stop();
				}
				
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		btnNewButton.setBounds(252, 418, 170, 53);
		frame.getContentPane().add(btnNewButton);
		
		txtPassword = new JPasswordField();
		txtPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtPassword.setText("");
			}
		});
		txtPassword.setText("PASSWORD");
		txtPassword.setForeground(Color.WHITE);
		txtPassword.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		txtPassword.setColumns(10);
		
		txtPassword.setBackground(Color.ORANGE);
		txtPassword.setBounds(252, 332, 351, 53);
		frame.getContentPane().add(txtPassword);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(766, 29, 70, 70);
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon("Task_logo.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));

		frame.getContentPane().add(lblNewLabel);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start st = new Start();
				st.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnBack.setVerticalAlignment(SwingConstants.TOP);
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Dialog", Font.PLAIN, 25));
		btnBack.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnBack.setBackground(SystemColor.textHighlight);
		btnBack.setBounds(434, 418, 170, 53);
		frame.getContentPane().add(btnBack);
		
		btnForgottenPassword = new JButton("forgotten password");
		btnForgottenPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame1 = new JFrame();
				frame1.setAlwaysOnTop(true);
				frame1.setVisible(true);
				frame1.setResizable(false);
				frame1.getContentPane().setFont(new Font("Arial", Font.BOLD, 20));
				frame1.getContentPane().setBackground(new Color(36, 199, 249));
				frame1.getContentPane().setLayout(null);
				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame1.setBounds(100, 100, 450, 300);
				
				JLabel lblInsertUsername = new JLabel("INSERT USERNAME");
				lblInsertUsername.setBounds(6, 22, 291, 31);
				lblInsertUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
				lblInsertUsername.setHorizontalAlignment(SwingConstants.CENTER);
				frame1.getContentPane().add(lblInsertUsername);
				
				JTextField txtUsername = new JTextField();
				txtUsername.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						txtUsername.setText("");
					}
				});
				txtUsername.setText("USERNAME");
				txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
				txtUsername.setBounds(104, 98, 239, 51);
				frame1.getContentPane().add(txtUsername);
				txtUsername.setColumns(10);
				
				JButton btnConfirm = new JButton("CONFIRM");
				btnConfirm.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						Client c = new Client();
						c.start();
						Random rand = new Random();
						int  n = rand.nextInt(1000000000) + 1;
						String password = Integer.toString(n);
						c.forgottenPassword(txtUsername.getText(), password);
						JOptionPane.showMessageDialog(frame1, "You will recieve an email!");
						frame1.dispose();
					}
				});
				btnConfirm.setBounds(104, 173, 117, 29);
				frame1.getContentPane().add(btnConfirm);
				
				JButton btnBack = new JButton("BACK");
				btnBack.setBackground(new Color(238, 238, 238));
				btnBack.setBounds(226, 173, 117, 29);
				frame1.getContentPane().add(btnBack);
			}
		});
		btnForgottenPassword.setVerticalAlignment(SwingConstants.TOP);
		btnForgottenPassword.setForeground(Color.WHITE);
		btnForgottenPassword.setFont(new Font("Dialog", Font.PLAIN, 15));
		btnForgottenPassword.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnForgottenPassword.setBackground(SystemColor.textHighlight);
		btnForgottenPassword.setBounds(342, 513, 170, 21);
		frame.getContentPane().add(btnForgottenPassword);
	}
}
