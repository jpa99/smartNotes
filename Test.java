/*
       _            _            _               _                                                                                                                      
      | |          | |     /\   | |             | |                                                                                                                     
      | | ___   ___| |    /  \  | |__  _ __ __ _| |__   __ _ _ __ ___                                                                                                   
  _   | |/ _ \ / _ \ |   / /\ \ | '_ \| '__/ _` | '_ \ / _` | '_ ` _ \                                                                                                  
 | |__| | (_) |  __/ |  / ____ \| |_) | | | (_| | | | | (_| | | | | | |                                                                                                 
  \____/ \___/ \___|_| /_/    \_\_.__/|_|  \__,_|_| |_|\__,_|_| |_| |_|                                                                                                 
 __          __       _                           _   _____ ____     _____                            _               _____      _                       _____          
 \ \        / /      | |                         | | |_   _|  _ \   / ____|                          | |             / ____|    (_)                     |_   _|   /\    
  \ \  /\  / /__  ___| |___      _____   ___   __| |   | | | |_) | | |     ___  _ __ ___  _ __  _   _| |_ ___ _ __  | (___   ___ _  ___ _ __   ___ ___    | |    /  \   
   \ \/  \/ / _ \/ __| __\ \ /\ / / _ \ / _ \ / _` |   | | |  _ <  | |    / _ \| '_ ` _ \| '_ \| | | | __/ _ \ '__|  \___ \ / __| |/ _ \ '_ \ / __/ _ \   | |   / /\ \  
    \  /\  /  __/\__ \ |_ \ V  V / (_) | (_) | (_| |  _| |_| |_) | | |___| (_) | | | | | | |_) | |_| | ||  __/ |     ____) | (__| |  __/ | | | (_|  __/  _| |_ / ____ \ 
     \/  \/ \___||___/\__| \_/\_/ \___/ \___/ \__,_| |_____|____/   \_____\___/|_| |_| |_| .__/ \__,_|\__\___|_|    |_____/ \___|_|\___|_| |_|\___\___| |_____/_/    \_\
                                                                                         | |                                                                            
                                                                                         |_|                                                                                                                                                                                     
 
 */



import javax.swing.*;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.*;
public class Test {

	private static JFrame menu, add_frame, change_frame, cancel_frame, calendar_frame, notes_frame, timer_frame;
	private static JLabel notification_system_label;
	private static JTextArea notes_text_area;
	private static JTextField notes_text_field;
	private JTextField textField;
	private JTextField textField_1;
	String[] months={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	String[] days;
	String[] years;
	String[] hours;
	String[] minutes;
	
	public static void main(String[] args) throws Exception{
		Date a=new Date();
		
		System.out.println(a.getYear());
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				Test window = null;
				try {
					window = new Test();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				window.menu.setVisible(true);
			}
		});*/
	}

	
	/**
	 * @wbp.parser.entryPoint
	 */
	public Test() throws Exception {
		initialize();
	}
	

	
	private void initialize() throws Exception {
		Clip clip = AudioSystem.getClip();
        AudioInputStream inputStream = AudioSystem.getAudioInputStream(Test.class.getResourceAsStream("/Users/Joel/Documents/workspace/CS\\ IA/Notification_Sound.wav"));
        clip.open(inputStream);
        clip.start(); 
		menu = new JFrame();
		menu.getContentPane().setForeground(Color.LIGHT_GRAY);
		menu.getContentPane().setFont(new Font("AppleGothic", Font.PLAIN, 14));
		menu.setLocation(0, 0);
		menu.setBounds(0, 100, 464, 328);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.getContentPane().setLayout(null);
		
		JButton exit_button = new JButton("Exit");
		exit_button.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		exit_button.setBounds(17, 17, 76, 39);
		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.getContentPane().add(exit_button);
		
		
		
		
		/////////
		String name, details;
		boolean sound;
		int display;
		Date time;
		Date date=new Date();
		
		JButton btnNewButton = new JButton("Sumbit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButton.setBounds(69, 240, 154, 39);
		menu.getContentPane().add(btnNewButton);
		
		textField = new JTextField();
		textField.setBounds(316, 298, 187, 28);
		menu.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterNotificationName = new JLabel("Enter Notification Name: ");
		lblEnterNotificationName.setBounds(42, 68, 378, 153);
		menu.getContentPane().add(lblEnterNotificationName);
	
		String[] x={"Sound A", "Sound B"};
		
		JComboBox comboBox = new JComboBox(x);
		comboBox.setBounds(198, 201, 106, 27);
		menu.getContentPane().add(comboBox);
		
		JLabel lblEnterNotificationDetails = new JLabel("Enter Notification Details:");
		lblEnterNotificationDetails.setBounds(17, 170, 163, 16);
		menu.getContentPane().add(lblEnterNotificationDetails);
		
		JLabel lblSelectDisplayType = new JLabel("Select Display Type");
		lblSelectDisplayType.setBounds(17, 205, 134, 16);
		menu.getContentPane().add(lblSelectDisplayType);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(390, 105, -181, 39);
		menu.getContentPane().add(textArea);
		
		JLabel lblEnterAlertTime = new JLabel("Enter Alert Time");
		lblEnterAlertTime.setBounds(17, 103, 106, 22);
		menu.getContentPane().add(lblEnterAlertTime);
		
		//months combo box
		JComboBox month_combo_box = new JComboBox(months);
		month_combo_box.setBounds(150, 102, 105, 27);
		menu.getContentPane().add(month_combo_box);
		month_combo_box.setSelectedIndex(date.getMonth());
		
		//days combo box
		days=new String[31];
		for(int i=0;i<31;i++){
			days[i]=String.valueOf(i+1);
		}
		JComboBox day_combo_box = new JComboBox(days);
		day_combo_box.setBounds(267, 105, 66, 27);
		menu.getContentPane().add(day_combo_box);
		day_combo_box.setSelectedIndex(date.getDate()-1);
		
		//years combo box
		years=new String[4];
		for(int i=2016;i<2020;i++){
			years[i-2016]=String.valueOf(i);
		}
		JComboBox year_combo_box = new JComboBox(years);
		year_combo_box.setBounds(338, 105, 82, 27);
		menu.getContentPane().add(year_combo_box);
		year_combo_box.setSelectedIndex(date.getYear()-116);
		
		//hours combo box
		hours=new String[24];
		for(int i=1;i<25;i++){
			hours[i-1]=String.valueOf(i);
		}
		JComboBox hour_combo_box = new JComboBox(hours);
		hour_combo_box.setBounds(221, 132, 73, 27);
		menu.getContentPane().add(hour_combo_box);
		hour_combo_box.setSelectedIndex(date.getHours());
		
		//minutes combo box
		minutes=new String[60];
		for(int i=0;i<60;i++){
			minutes[i]=String.valueOf(i);
		}
		JComboBox minute_combo_box = new JComboBox(minutes);
		minute_combo_box.setBounds(306, 132, 72, 27);
		menu.getContentPane().add(minute_combo_box);
		minute_combo_box.setSelectedIndex(date.getMinutes());
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(198, 164, 187, 28);
		menu.getContentPane().add(textField_1);
		
		JRadioButton sound_disabled_radioButton = new JRadioButton("Sound Disabled");
		sound_disabled_radioButton.setBounds(316, 201, 141, 23);
		menu.getContentPane().add(sound_disabled_radioButton);
		
		JRadioButton sound_enabled_radioButton = new JRadioButton("Sound Enabled");
		sound_enabled_radioButton.setBounds(316, 239, 141, 23);
		menu.getContentPane().add(sound_enabled_radioButton);
		sound_enabled_radioButton.setSelected(true);
		
		JButton button = new JButton("Sumbit");
		button.setBounds(250, 240, 154, 39);
		menu.getContentPane().add(button);
	}
}
