package finalGUI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class BidOffer {

	JFrame frame1;
	private JTextField txtEnterBidOffer;
	private JButton btnNewButton;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BidOffer window = new BidOffer();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BidOffer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame1 = new JFrame();
	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there 
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
		
		textField = new JTextField();
		textField.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setBounds(28, 89, 382, 104);
		frame1.getContentPane().add(textField);
		textField.setColumns(10);
		frame1.setBounds(100, 100, 437, 222);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}






