package finalGUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class UserTicket {

	JFrame frame;
	JFrame frame1;
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JButton button;
	private String Username;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public UserTicket(String Username) {
		this.Username = Username;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAlwaysOnTop(false);
		frame.setResizable(false);
		frame.getContentPane().setBackground(SystemColor.textHighlight);
		frame.setBounds(100, 100, 851, 601);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		btnNewButton = new JButton("User Ticket Sold ");
		btnNewButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Q = "select ticketid,eventname,eventdate,status from ticket  where status = 'sold' and username='"
						+ Username+"'";
				ResultSet RS = Query.QueryExecute(Q);
				frame1 = new JFrame();
				String[] Colume = { "TicketID", "Event Name", "Event Date", "Status" };

				DefaultTableModel table_model = new DefaultTableModel(Colume, 0);
				JTable table = new JTable(table_model);

				try {
					while (RS.next()) {
						String data1 = RS.getString(1);
						String data2 = RS.getString(2);
						String data3 = RS.getString(3);
						String data4 = RS.getString(4);

						Object[] row = { data1, data2, data3, data4 };

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						model.addRow(row);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				JPanel panel = new JPanel();
				panel.add(new JScrollPane(table));

				frame1.setBounds(100, 100, 500, 500);
				frame1.getContentPane().add(panel);
				frame1.setVisible(true);

			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(236, 162, 399, 70);
		frame.getContentPane().add(btnNewButton);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(766, 29, 70, 70);
		lblNewLabel.setIcon(new ImageIcon(
				new ImageIcon("Task_logo.png").getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT)));

		frame.getContentPane().add(lblNewLabel);

		btnNewButton = new JButton("Main Menu");
		btnNewButton.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MainMenu su = new MainMenu(Username);
				su.frame.setVisible(true);
			}
		});
		btnNewButton.setVerticalAlignment(SwingConstants.TOP);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 20));
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(256, 402, 351, 80);
		frame.getContentPane().add(btnNewButton);

		JLabel lblWelcome = new JLabel("Welcome Tick It!");
		lblWelcome.setForeground(Color.WHITE);
		lblWelcome.setFont(new Font("Gill Sans Ultra Bold", Font.BOLD, 25));
		lblWelcome.setBackground(Color.ORANGE);
		lblWelcome.setBounds(143, 11, 584, 107);
		frame.getContentPane().add(lblWelcome);

		button = new JButton("User Ticket Purchased");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Q = "select buy.ticketid,eventname,eventdate,eventtime from buy inner join ticket on buy.ticketid=ticket.ticketid where buy.username='"+Username+"'";
				ResultSet RS = Query.QueryExecute(Q);
				frame1 = new JFrame();
				String[] Colume = { "TicketID", "Event Name", "Event Date", "Event Time" };

				DefaultTableModel table_model = new DefaultTableModel(Colume, 0);
				JTable table = new JTable(table_model);

				try {
					while (RS.next()) {
						String data1 = RS.getString(1);
						String data2 = RS.getString(2);
						String data3 = RS.getString(3);
						String data4 = RS.getString(4);

						Object[] row = { data1, data2, data3, data4 };

						DefaultTableModel model = (DefaultTableModel) table.getModel();

						model.addRow(row);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JPanel panel = new JPanel();
				panel.add(new JScrollPane(table));

				frame1.setBounds(100, 100, 500, 500);
				frame1.getContentPane().add(panel);
				frame1.setVisible(true);
		
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
