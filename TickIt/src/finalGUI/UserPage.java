/*
 * Bogdan Ciuculescu (Design)
 */

package finalGUI;

import javax.swing.JFrame;
import javax.swing.JTable;

import Client.Client;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.sql.Connection;
import java.util.ArrayList;
import java.awt.Scrollbar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserPage {

	JFrame frame;
	private JTable table;
	Client c = new Client();
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public UserPage(String Username) {
		initialize(Username);
		c.start();
	}

	
	//Initialises the frame
	private void initialize(String Username) {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(36, 199, 249));
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 900, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// JLabel created to be filled in by the user's username
		JLabel lblUsersName = new JLabel(Username);
		lblUsersName.setBounds(30, 23, 221, 44);
		frame.getContentPane().add(lblUsersName);
		
//		ArrayList<String[]> arrl = c.get_UsersLists(Username);
//		String[] arr;
//		if(arrl.size()>0) {
//			arr = new String[arrl.size()];
//			for(int i = 0; i<arrl.size(); i++) {
//				arr[i] = arrl.get(i).toString();
//			}
//		} else {
//			arr = new String[1];
//			arr[0] = "Sorry no open listings currently.";
//		}
//		JList<String> list = new JList<String>(arr);
		JScrollPane scrollPane1 = new JScrollPane(/*list*/);
		scrollPane1.setBounds(65, 108, 722, 199);
		frame.getContentPane().add(scrollPane1);
		
		table = new JTable();
		scrollPane1.setViewportView(table);
		
		// ScrollBar
		Scrollbar scrollbar = new Scrollbar();
		scrollPane1.setRowHeaderView(scrollbar);
		
		JLabel lblOpenTicketListings = new JLabel("Open ticket listings:");
		lblOpenTicketListings.setBounds(66, 79, 146, 16);
		frame.getContentPane().add(lblOpenTicketListings);
		
		JLabel lblPreviousPurchases = new JLabel("Previous purchases:");
		lblPreviousPurchases.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblPreviousPurchases.setBounds(66, 350, 131, 16);
		frame.getContentPane().add(lblPreviousPurchases);
		
//		ArrayList<String[]> arrl2 = c.getPreviousListings(Username);
//		String[] arr2;
//		if(arrl2.size()>0) {
//			arr2 = new String[arrl2.size()];
//			for(int i = 0; i<arrl2.size(); i++) {
//				arr2[i] = arrl2.get(i).toString();
//			}
//		} else {
//			arr2 = new String[1];
//			arr2[0] = "Sorry no listing previously bought.";
//		}
//		JList<String> list2 = new JList<String>(arr2);
		JScrollPane scrollPane2 = new JScrollPane(/*list2*/);
		scrollPane2.setBounds(66, 373, 722, 199);
		frame.getContentPane().add(scrollPane2);
		
		Scrollbar scrollbar2 = new Scrollbar();
		scrollPane2.setRowHeaderView(scrollbar2);
		
		//Back Button
		JButton btnBack = new JButton("Logout");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Server.Server.onlineUsers.remove(Username);
				Start s = new Start();
				JOptionPane.showConfirmDialog(frame, "Goodbye, thank you for using Tick-it!");
				s.frame.setVisible(true);
				frame.dispose();
				c.stop();
			}
		});
		btnBack.setBounds(655, 32, 140, 29);
		frame.getContentPane().add(btnBack);
		
		//Selling button
		JButton btnNewButton_1 = new JButton("Sell Ticket");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SellTicket st = new SellTicket(Username);
				st.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton_1.setBounds(404, 32, 143, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		//Buying button
		JButton btnBuyTicket = new JButton("Buy Tickets");
		btnBuyTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BuyTicket bt = new BuyTicket(Username);
					bt.frame.setVisible(true);
					frame.dispose();
				} catch (InterruptedException e1) {
					JOptionPane.showMessageDialog(frame, "Sorry can't access buy ticket page right now!");
					e1.printStackTrace();
				}
			}
		});
		btnBuyTicket.setBounds(275, 32, 131, 29);
		frame.getContentPane().add(btnBuyTicket);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassword cp = new ChangePassword(Username);
				cp.frame.setVisible(true);
			}
		});
		btnChangePassword.setBounds(655, 60, 140, 29);
		frame.getContentPane().add(btnChangePassword);
		
	}

}
