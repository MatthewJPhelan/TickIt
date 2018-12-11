package finalGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.Client;
import Server.Server;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.mail.MessagingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ServerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private Timer timer;
	private Thread thread;
	Client client = new Client();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerGUI frame = new ServerGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws UnknownHostException 
	 */
	public ServerGUI() throws UnknownHostException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(36, 199, 249));
		
		JLabel lblNewLabel = new JLabel("LAUNCH SERVER");
		lblNewLabel.setFont(new Font("Gill Sans", Font.PLAIN, 25));
		lblNewLabel.setBounds(22, 21, 260, 45);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(110, 101, 225, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(Inet4Address.getLocalHost().getHostAddress());
		textField.setEditable(false);
		
		JLabel lblHost = new JLabel("HOST");
		lblHost.setHorizontalAlignment(SwingConstants.CENTER);
		lblHost.setBounds(191, 78, 40, 16);
		contentPane.add(lblHost);
		
		JLabel lblPort = new JLabel("PORT");
		lblPort.setHorizontalAlignment(SwingConstants.CENTER);
		lblPort.setBounds(191, 145, 40, 16);
		contentPane.add(lblPort);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setColumns(10);
		textField_1.setBounds(110, 168, 225, 26);
		contentPane.add(textField_1);
		textField_1.setText("50281");
		
		JButton btnRun = new JButton("RUN");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int portNumber = Integer.parseInt(textField_1.getText());
				JOptionPane.showMessageDialog(contentPane, "Server Started");
				try {
					Server.main(portNumber);
				} catch (IOException | MessagingException | InterruptedException e1) {
					JOptionPane.showMessageDialog(contentPane, "Sorry, can't launch server at this time");
					e1.printStackTrace();
				}
			}
		});
		btnRun.setBounds(165, 219, 117, 29);
		contentPane.add(btnRun);
	}
	
//	public void StartCheckingMessages(){
//		client.start(); 
//		thread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while(true){
//					
//					String[] response = client.getResponse();
//					if(response[0].equals("CheckFinished") && response[1].equals("true")){
//						try {
//							Thread.sleep(60000);
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//	
//		});
//		thread.start();
//		timer = new Timer();
//		timer.scheduleAtFixedRate(new TimerTask() {
//			@Override
//			public void run() {
//				client.checkFinished();
//			}
//		}, 1000, 2000);
//
//	}
//	public void StopCheckingMessages(){
//		timer.cancel();
//	}
}