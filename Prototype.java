import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

public class Prototype {

	private JFrame frame;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prototype window = new Prototype();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Prototype() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().setFont(new Font("AppleGothic", Font.PLAIN, 14));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JButton btnNewButton = new JButton("Add Notification");
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnNewButton.setBounds(17, 105, 129, 70);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1;
		btnNewButton_1 = new JButton("Calendar View");
		btnNewButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		btnNewButton_1.setBounds(17, 187, 129, 70);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNotificationSystem = new JLabel("Notification System");
		lblNotificationSystem.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNotificationSystem.setBounds(117, 26, 226, 30);
		frame.getContentPane().add(lblNotificationSystem);
		
		JButton button = new JButton("Change Notification");
		button.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		button.setBounds(158, 105, 129, 70);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("Notes");
		button_1.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		button_1.setBounds(158, 187, 129, 70);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("Cancel Notification");
		button_2.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		button_2.setBounds(299, 105, 129, 70);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("Timer");
		button_3.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		button_3.setBounds(299, 187, 129, 70);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("Exit");
		button_4.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_4.setBounds(17, 30, 76, 39);
		frame.getContentPane().add(button_4);
	}
}
