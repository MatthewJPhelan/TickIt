package finalGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLException;

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

public class SignUp {

	JFrame frame;
	private JTextField txtSignUp;
	private JTextField txtF;
	private JTextField txtMobile;
	private JTextField txtEmail;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel label;
	private JLabel lblNewLabel;
	private JButton button;
	private JTextField LName;
	private JButton btnBack;

	/**
	 * Create the application.
	 */
	public SignUp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(36, 199, 249));
		frame.setBounds(100, 100, 800, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(701, 21, 70, 70);
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon("Task_logo.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));
		frame.getContentPane().add(lblNewLabel);
		
		txtSignUp = new JTextField();
		txtSignUp.setText("SIGN UP");
		txtSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		txtSignUp.setForeground(Color.WHITE);
		txtSignUp.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 45));
		txtSignUp.setEditable(false);
		txtSignUp.setColumns(10);
		txtSignUp.setBackground(Color.ORANGE);
		txtSignUp.setBounds(226, 21, 351, 80);
		frame.getContentPane().add(txtSignUp);
		
		txtF = new JTextField();
		txtF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtF.setText("");
			}
		});
		txtF.setText("FIRST NAME");
		txtF.setForeground(Color.WHITE);
		txtF.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		txtF.setColumns(10);
		txtF.setBackground(Color.ORANGE);
		txtF.setBounds(226, 112, 351, 53);
		frame.getContentPane().add(txtF);
		
		txtMobile = new JTextField();
		txtMobile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txtMobile.setText("");
			}
		});
		txtMobile.setText("MOBILE NUMBER");
		txtMobile.setForeground(Color.WHITE);
		txtMobile.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		txtMobile.setColumns(10);
		txtMobile.setBackground(Color.ORANGE);
		txtMobile.setBounds(226, 251, 351, 53);
		frame.getContentPane().add(txtMobile);
		
		txtEmail = new JTextField();
		txtEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtEmail.setText("");
			}
		});
		txtEmail.setText("EMAIL");
		txtEmail.setForeground(Color.WHITE);
		txtEmail.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		txtEmail.setColumns(10);
		txtEmail.setBackground(Color.ORANGE);
		txtEmail.setBounds(226, 325, 351, 53);
		frame.getContentPane().add(txtEmail);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
			}
		});
		textField.setText("USERNAME");
		textField.setForeground(Color.WHITE);
		textField.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		textField.setColumns(10);
		textField.setBackground(Color.ORANGE);
		textField.setBounds(226, 400, 351, 53);
		frame.getContentPane().add(textField);
		
		JPasswordField textField_1 = new JPasswordField();
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_1.setText("");
			}
		});
		textField_1.setText("PASSWORD");
		textField_1.setForeground(Color.WHITE);
		textField_1.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.ORANGE);
		textField_1.setBounds(226, 464, 351, 53);
		frame.getContentPane().add(textField_1);
		
		button = new JButton("ENTER");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				
					Client c = new Client();
					c.start();
					
					String username = textField.getText();
					String password = textField_1.getText();
					String fName = txtF.getText();
					String lName = LName.getText();
					String email = txtEmail.getText();
					String phoneNumber = txtMobile.getText();
					
					String[] response = c.signup(username, password, fName, lName, email, phoneNumber);
					if(response[1].equals("true")){
						
						JOptionPane.showMessageDialog(frame, "GREAT You're signed up!");
						frame.dispose();
						
						String message;
						BufferedReader reader = new BufferedReader(new FileReader ("src/Email/SignUpEmail"));
					    String         line = null;
					    StringBuilder  stringBuilder = new StringBuilder();
					    String         ls = System.getProperty("line.separator");

					    try {
					        while((line = reader.readLine()) != null) {
					            stringBuilder.append(line);
					            stringBuilder.append(ls);
					        }
					        message= "Hi " + fName + stringBuilder.toString();
					        
					        Email.Email.sendEmail(email, "Welcome to Tick-it!", message);
					    } finally {
					        reader.close();
					    }
						
					}else{
						JOptionPane.showMessageDialog(frame,
							    response[2],
							    "Sign-in Error",
							    JOptionPane.WARNING_MESSAGE);
						c.stop();
					}
		
					frame.dispose();
					Start bt=new Start();
					bt.frame.setVisible(true);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(frame, "Unable to connect.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		button.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		button.setBackground(SystemColor.textHighlight);
		button.setBounds(331, 529, 162, 53);
		frame.getContentPane().add(button);
	
		LName = new JTextField();
		LName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LName.setText("");
			}
		});
		LName.setText("LAST NAME");
		LName.setForeground(Color.WHITE);
		LName.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 25));
		LName.setColumns(10);
		LName.setBackground(Color.ORANGE);
		LName.setBounds(226, 176, 351, 53);
		frame.getContentPane().add(LName);
		
		btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Start st = new Start();
				st.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("Dialog", Font.PLAIN, 25));
		btnBack.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnBack.setBackground(SystemColor.textHighlight);
		btnBack.setBounds(415, 529, 162, 53);
		frame.getContentPane().add(btnBack);
		
	}
}
