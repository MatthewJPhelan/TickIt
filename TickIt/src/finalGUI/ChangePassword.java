package finalGUI;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Client.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChangePassword {

	JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	Client c = new Client();

	
	public ChangePassword(String username) {
		initialize(username);
	}

	
	private void initialize(String username) {
		frame = new JFrame();
		frame.setBounds(100, 100, 400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBackground(new Color(36, 199, 249));
		
		textField = new JPasswordField();
		textField.setBounds(209, 39, 130, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(209, 77, 130, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JPasswordField();
		textField_2.setBounds(209, 115, 130, 26);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Current password:");
		lblNewLabel.setBounds(35, 44, 124, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTypeNewPassword = new JLabel("Type new password:");
		lblTypeNewPassword.setBounds(35, 82, 130, 16);
		frame.getContentPane().add(lblTypeNewPassword);
		
		JLabel lblRetypeNewPassword = new JLabel("Re-type new password:");
		lblRetypeNewPassword.setBounds(35, 120, 162, 16);
		frame.getContentPane().add(lblRetypeNewPassword);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				c.start();		
				if(textField_1.getText().equals(textField_2.getText())) {
					if(textField_1.getText().length() >= 8 && textField_1.getText().length() <= 32){
						String[] arrCP = c.checkUserPass(username, textField_1.getText());
						if(arrCP[1].equals("true")) {
							String[] arr = c.changePassword(username, textField_1.getText());
							if (arr[1].equals("true")) {
								JOptionPane.showMessageDialog(frame, "Password Updated!");
								UserPage up = new UserPage(username);
								up.frame.setVisible(true);
								frame.dispose();
							} else {
								JOptionPane.showMessageDialog(frame, "Password update failed, try again later.");
							}
						} else {
							JOptionPane.showMessageDialog(frame, "Current password incorrect.");
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Password should be at least 8 characters long with a miximum of 32.");
					}
				} else {
					JOptionPane.showMessageDialog(frame, "New passwords don't match.");
				}
			}
		});
		btnOk.setBounds(136, 148, 117, 29);
		frame.getContentPane().add(btnOk);
	}
}

