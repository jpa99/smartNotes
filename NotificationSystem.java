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
import java.io.*;
import sun.audio.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer;

public class NotificationSystem {

	private static JFrame menu, add_frame, change_frame, cancel_frame, calendar_frame, notes_frame, timer_frame;
	private static JLabel menu_label, add_label, change_label, cancel_label, calendar_label, notes_label, timer_label, name_label, details_label, time_label, display_label, chronometer;
	private static JButton add_button, change_button, cancel_button, calendar_button, notes_button, timer_button, exit_button, back, submit, change_submit, cancel_submit, view, start, reset;
	private static JTextField name_text_field, details_text_field;
	private static JComboBox<String[]> month_combo_box, day_combo_box, year_combo_box, hour_combo_box, minute_combo_box, display_combo_box, name_combo_box;
	private static JRadioButton sound_enabled_radioButton, sound_disabled_radioButton;
	private static TextArea notes_text_area;
	private static String notes;
	private static String[] names, days, years, hours, minutes, displays={"Pop-up", "Banner", "Corner"}, months={"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	private static Date date;
	static ArrayList<Notification> notifications=new ArrayList<Notification>();
	static ArrayList<Timer> timers=new ArrayList<Timer>();
	
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				NotificationSystem window = new NotificationSystem();
				window.menu.setVisible(true);
			}
		});
	}

	
	public NotificationSystem() {
		initialize();
	}
	

	
	private void initialize() {
		//Creates menu frame and adds components
		menu = new JFrame();
		add_frame=new JFrame();
		change_frame=new JFrame();
		cancel_frame=new JFrame();
		calendar_frame=new JFrame();
		notes_frame=new JFrame();
		timer_frame=new JFrame();
		menu.setForeground(Color.LIGHT_GRAY);
		menu.setFont(new Font("AppleGothic", Font.PLAIN, 14));
		menu.setBounds(100, 100, 450, 300);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setLayout(null);
		
		menu_label = new JLabel("Notification System");
		menu_label.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		menu_label.setBounds(117, 26, 226, 30);
		menu.add(menu_label);
		
		//Back Button
		back = new JButton("<---");
		back.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		back.setBounds(17, 17, 76, 39);
		
		//Add Button
		createAddFrame();
		add_button = new JButton("Add Notification");
		add_button.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		add_button.setBounds(17, 105, 129, 70);
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_frame.add(back);
				menu.setVisible(false);
				add_frame.setVisible(true);
			}
		});
		menu.add(add_button);
		
		//Change Button
		change_button = new JButton("Change Notification");
		change_button.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		change_button.setBounds(158, 105, 129, 70);
		change_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createChangeFrame();
				change_frame.add(back);
				menu.setVisible(false);
				change_frame.setVisible(true);
				name_combo_box.addItemListener(new ItemListener(){
		            public void itemStateChanged(ItemEvent e){
		                if(e.getStateChange()==ItemEvent.SELECTED){
		                	month_combo_box.setSelectedIndex(notifications.get(name_combo_box.getSelectedIndex()).getTime().getMonth());
		    				day_combo_box.setSelectedIndex(notifications.get(name_combo_box.getSelectedIndex()).getTime().getDate());
		    				year_combo_box.setSelectedIndex(notifications.get(name_combo_box.getSelectedIndex()).getTime().getYear()-116);
		    				hour_combo_box.setSelectedIndex(notifications.get(name_combo_box.getSelectedIndex()).getTime().getHours()-1);
		    				minute_combo_box.setSelectedIndex(notifications.get(name_combo_box.getSelectedIndex()).getTime().getMinutes());
		    				details_text_field.setText(notifications.get(name_combo_box.getSelectedIndex()).getDetails());
		    				display_combo_box.setSelectedIndex(notifications.get(name_combo_box.getSelectedIndex()).getDisplay());
		    				sound_disabled_radioButton.setSelected(!notifications.get(name_combo_box.getSelectedIndex()).getSound());
		    				sound_enabled_radioButton.setSelected(notifications.get(name_combo_box.getSelectedIndex()).getSound());
		                }
		            }
			   });
			}
		});
		menu.add(change_button);
		
		//Cancel Button
		cancel_button = new JButton("Cancel Notification");
		cancel_button.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		cancel_button.setBounds(299, 105, 129, 70);
		cancel_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCancelFrame();
			}
		});
		menu.add(cancel_button);
		
		//Calendar Button
		calendar_button = new JButton("Calendar View");
		calendar_button.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		calendar_button.setBounds(17, 187, 129, 70);
		calendar_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createCalendarFrame();
			}
		});
		menu.add(calendar_button);
		
		//Notes Button
		notes_button = new JButton("Notes");
		notes_button.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		notes_button.setBounds(158, 187, 129, 70);
		notes_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createNotesFrame();
			}
		});
		menu.add(notes_button);
		
		//Timer Button
		createTimerFrame();
		timer_button = new JButton("Timer");
		timer_button.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		timer_button.setBounds(299, 187, 129, 70);
		timer_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.setVisible(false);
				timer_frame.setVisible(true);
				
			}
		});
		menu.add(timer_button);
		
		//Exit Button
		exit_button = new JButton("Exit");
		exit_button.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		exit_button.setBounds(17, 17, 76, 39);
		exit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(exit_button);
	}

	
	//Creates new Add JFrame and changes visibilitiy of main menu
	//Provides option to add notification
	public static void createAddFrame(){
		buildComponents();
		add_frame.setForeground(Color.LIGHT_GRAY);
		add_frame.setFont(new Font("AppleGothic", Font.PLAIN, 14));
		add_frame.setBounds(100, 100, 450, 300);
		add_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add_frame.setLayout(null);
		
		//Initializes add label
		add_label = new JLabel("Add Notification");
		add_label.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		add_label.setBounds(117, 26, 226, 30);
		add_frame.add(add_label);

		//Initializes name label and text field
		name_label = new JLabel("Enter Notification Name: ");
		name_label.setBounds(17, 68, 159, 22);
		add_frame.add(name_label);
		
		name_text_field = new JTextField();
		name_text_field.setBounds(198, 65, 187, 28);
		add_frame.add(name_text_field);
		name_text_field.setColumns(10);  
		
	
		add_frame.add(time_label);
		add_frame.add(month_combo_box);
		add_frame.add(day_combo_box);
		add_frame.add(year_combo_box);
		add_frame.add(hour_combo_box);
		add_frame.add(minute_combo_box);

		add_frame.add(details_label);
		add_frame.add(details_text_field);

		add_frame.add(display_label);
		add_frame.add(display_combo_box);

		add_frame.add(sound_disabled_radioButton);
		add_frame.add(sound_enabled_radioButton);
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				Date a=new Date(year_combo_box.getSelectedIndex()+116, month_combo_box.getSelectedIndex(), day_combo_box.getSelectedIndex()+1, hour_combo_box.getSelectedIndex(), minute_combo_box.getSelectedIndex());
				notifications.add(new Notification(name_text_field.getText(), details_text_field.getText(), display_combo_box.getSelectedIndex(), sound_enabled_radioButton.isSelected(), a));
				JOptionPane.showMessageDialog(null, "You have sucessfully created a new notification: \n"+notifications.get(notifications.size()-1).getName()+" for "+notifications.get(notifications.size()-1).getTime(), "New Notification Created", JOptionPane.INFORMATION_MESSAGE);
				name_text_field.setText(null);
				details_text_field.setText(null);
				timers.add(new Timer());
				timers.get(timers.size()-1).schedule(new TimerTask(){ 
					public void run(){
						try {
							alert(notifications.get(notifications.size()-1));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, a);
				System.out.println(timers.get(0));
			}
		});
		add_frame.add(submit);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				add_frame.setVisible(false);
				menu.setVisible(true);
			}
		});
		add_frame.add(back);
	
	}
	
	
	//Creates new Change JFrame and changes visibilitiy of main menu
	//Provides option to change notification 
	public static void createChangeFrame(){
		buildComponents();
		change_frame.setForeground(Color.LIGHT_GRAY);
		change_frame.setFont(new Font("AppleGothic", Font.PLAIN, 14));
		change_frame.setBounds(100, 100, 450, 300);
		change_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		change_frame.setLayout(null);
		
		//Initializes change label
		change_label = new JLabel("Change Notification");
		change_label.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		change_label.setBounds(117, 26, 250, 30);
		change_frame.add(change_label);

		//Initializes name label and text field
		name_label = new JLabel("Choose Notification:");
		name_label.setBounds(17, 68, 159, 22);
		change_frame.add(name_label);
		
		//Initializes name combo box with array of existing notification names
		names=new String[notifications.size()];
		for(int i=0;i<notifications.size();i++){
			names[i]=notifications.get(i).getName();
		}
		name_combo_box = new JComboBox(names);
		name_combo_box.setBounds(198, 65, 187, 28);
		change_frame.add(name_combo_box);
		
	
		//Add Components and set initial indices as those of the selected notification
		change_frame.add(time_label);
		change_frame.add(month_combo_box);
		change_frame.add(day_combo_box);
		change_frame.add(year_combo_box);
		change_frame.add(hour_combo_box);
		change_frame.add(minute_combo_box);
		
		change_frame.add(details_label);
		change_frame.add(details_text_field);
		
		change_frame.add(display_label);
		change_frame.add(display_combo_box);
	
		change_frame.add(sound_disabled_radioButton);
		change_frame.add(sound_enabled_radioButton);

		change_submit = new JButton("Submit");
		change_submit.setBounds(150, 233, 154, 39);
		change_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("deprecation")
				Date b=new Date(year_combo_box.getSelectedIndex()+116, month_combo_box.getSelectedIndex(), day_combo_box.getSelectedIndex()+1, hour_combo_box.getSelectedIndex(), minute_combo_box.getSelectedIndex());
				int index=name_combo_box.getSelectedIndex();
				try{
					notifications.set(index, new Notification(name_text_field.getText(), details_text_field.getText(), display_combo_box.getSelectedIndex(), sound_enabled_radioButton.isSelected(), b));
				}catch(Exception exception){
					JOptionPane.showMessageDialog(null, "Please Enter a Valid Input", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				JOptionPane.showMessageDialog(null, "You have sucessfully changed the notification: \n"+notifications.get(notifications.size()-1).getName()+" for "+notifications.get(notifications.size()-1).getTime(), "Notification Changed", JOptionPane.INFORMATION_MESSAGE);
				timers.get(index).cancel();
				timers.set(index, new Timer());
				timers.get(index).schedule(new TimerTask(){ 
					public void run(){ 
						try {
							alert(notifications.get(index));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, b);
			}
		});
		change_frame.add(change_submit);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				change_frame.setVisible(false);
				menu.setVisible(true);
			}
		});
		change_frame.add(back);
	}

	
	//Creates new Cancel JFrame and changes visibilitiy of main menu
	//Provides option to cancel notification
	public static void createCancelFrame(){
		menu.setVisible(false);
		cancel_frame.setForeground(Color.LIGHT_GRAY);
		cancel_frame.setFont(new Font("AppleGothic", Font.PLAIN, 14));
		cancel_frame.setBounds(100, 100, 450, 300);
		cancel_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cancel_frame.setLayout(null);
		cancel_frame.setVisible(true);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancel_frame.setVisible(false);
				menu.setVisible(true);
			}
		});
		cancel_frame.add(back);
		
		//Initializes change label
		cancel_label = new JLabel("Cancel Notification");
		cancel_label.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		cancel_label.setBounds(117, 26, 250, 30);
		cancel_frame.add(cancel_label);

		//Initializes name label and text field
		name_label = new JLabel("Choose Notification:");
		name_label.setBounds(17, 68, 159, 22);
		cancel_frame.add(name_label);
		
		//Initializes name combo box with array of existing notification names
		names=new String[notifications.size()];
		for(int i=0;i<notifications.size();i++){
			names[i]=notifications.get(i).getName();
		}
		name_combo_box = new JComboBox(names);
		name_combo_box.setBounds(198, 65, 187, 28);
		cancel_frame.add(name_combo_box);
		
		cancel_submit = new JButton("Submit");
		cancel_submit.setBounds(150, 233, 154, 39);
		cancel_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=name_combo_box.getSelectedIndex();
				JOptionPane.showMessageDialog(null, "You have sucessfully canceled the notification: \n"+notifications.get(notifications.size()-1).getName()+" for "+notifications.get(notifications.size()-1).getTime(), "Notification Canceled", JOptionPane.INFORMATION_MESSAGE);
				timers.get(index).cancel();
			}
		});
		cancel_frame.add(cancel_submit);
	}
	
	//Creates new Calendar JFrame and changes visibilitiy of main menu
	//Displays calendar of events 
	public static void createCalendarFrame(){
		menu.setVisible(false);
		calendar_frame.setForeground(Color.LIGHT_GRAY);
		calendar_frame.setFont(new Font("AppleGothic", Font.PLAIN, 14));
		calendar_frame.setBounds(100, 100, 450, 300);
		calendar_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		calendar_frame.setLayout(null);
		calendar_frame.setVisible(true);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calendar_frame.setVisible(false);
				menu.setVisible(true);
			}
		});
		calendar_frame.add(back);
		
		calendar_label = new JLabel("View Notifications");
		calendar_label.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		calendar_label.setBounds(117, 26, 250, 30);
		calendar_frame.add(calendar_label);

		//Initializes name label and text field
		name_label = new JLabel("Choose Notification:");
		name_label.setBounds(17, 68, 159, 22);
		calendar_frame.add(name_label);
		
		//Initializes name combo box with array of existing notification names
		names=new String[notifications.size()];
		for(int i=0;i<notifications.size();i++){
			names[i]=notifications.get(i).getName();
		}
		name_combo_box = new JComboBox(names);
		name_combo_box.setBounds(198, 65, 187, 28);
		calendar_frame.add(name_combo_box);
		
		//Initializes view button
		view = new JButton("View");
		view.setBounds(150, 233, 154, 39);
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=name_combo_box.getSelectedIndex();
				JOptionPane.showMessageDialog(null, "You have the notification: \n"+notifications.get(index).getName()+" scheduled for "+notifications.get(index).getTime()+":\n "+notifications.get(index).getDetails(), "Notification Information", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		calendar_frame.add(view);
	}

	//Replaces Menu Jframe with Notes JFrame and adds components
	//Saves text previously input in the Notes TextArea and displays upon opening
	public static void createNotesFrame(){
		menu.setVisible(false);
		notes_frame.setForeground(Color.LIGHT_GRAY);
		notes_frame.setFont(new Font("AppleGothic", Font.PLAIN, 14));
		notes_frame.setBounds(100, 100, 450, 300);
		notes_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		notes_frame.setLayout(null);
		notes_frame.setVisible(true);
		
		notes_label = new JLabel("Notes");
		notes_label.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		notes_label.setBounds(117, 26, 226, 30);
		notes_frame.add(notes_label);
		
		notes_text_area = new TextArea();
		notes_text_area.setBounds(50, 77, 345, 158);
		notes_frame.add(notes_text_area);
		notes_text_area.setText(notes);
		
		//Stores text in notes text area and changes visibility to return to main menu
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				notes=notes_text_area.getText();
				notes_frame.setVisible(false);
				menu.setVisible(true);
			}
		});
		notes_frame.add(back);
	}
	
	//Creates new Timer JFrame and changes visibilitiy of main menu
	//Displays timer
	public static void createTimerFrame(){
		menu.setVisible(false);
		timer_frame.setForeground(Color.LIGHT_GRAY);
		timer_frame.setFont(new Font("AppleGothic", Font.PLAIN, 14));
		timer_frame.setBounds(100, 100, 450, 300);
		timer_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		timer_frame.setLayout(null);
		timer_frame.setVisible(true);
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer_frame.setVisible(false);
				menu.setVisible(true);
			}
		});
		timer_frame.add(back);
		
		timer_label = new JLabel("Timer");
		timer_label.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		timer_label.setBounds(117, 26, 226, 30);
		timer_frame.add(timer_label);
		
		chronometer=new JLabel();
		chronometer.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		chronometer.setBounds(42, 68, 378, 153);
		chronometer.setText("00:00:00");
		timer_frame.add(chronometer);
		
		//Initialize start button to update chronometer text each second and reflect time elapsed
		Timer timer=new Timer();
		start = new JButton("Start");
		start.setBounds(69, 240, 154, 39);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long start_time=System.currentTimeMillis();
				timer.schedule(new TimerTask(){ 
					public void run(){
						chronometer.setText(setTime(System.currentTimeMillis()-start_time));
					}
				}, new Date(), 1000);
			}
		});
		timer_frame.add(start);

		//Initialize reset button to reset chronometer text and clear timer
		reset=new JButton("Reset");
		reset.setBounds(250, 240, 154, 39);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chronometer.setText("00:00:00");
				timer.purge();
			}
		});
		timer_frame.add(reset);
		
		
	}
	
	//Converts elapsed time in milliseconds to hours, minutes, and seconds
	public static String setTime(long millis){
		String hours=String.valueOf((int)((millis / (1000*60*60)) % 24));
		if(hours.length()==1){
			hours="0"+hours;
		}
		String minutes=String.valueOf((int)((millis / (1000*60)) % 60));
		if(minutes.length()==1){
			minutes="0"+minutes;
		}
		String seconds=String.valueOf((int)((millis / 1000) % 60));
		if(seconds.length()==1){
			seconds="0"+seconds;
		}
		return hours+":"+minutes+":"+seconds;
	}
	
	//Instantiates buttons, labels, text fields, text areas, etc. to add to frames
	public static void buildComponents(){
		Date date=new Date();
		//Initializes alert time label and each combo box
		time_label = new JLabel("Enter Alert Time:");
		time_label.setBounds(17, 103, 106, 22);
	
		//Initializes month combo box
		month_combo_box = new JComboBox(months);
		month_combo_box.setBounds(150, 102, 105, 27);
		month_combo_box.setSelectedIndex(date.getMonth());
		
		//Initializes days array and day combo box
		days=new String[31];
		for(int i=0;i<31;i++){
			days[i]=String.valueOf(i+1);
		}
		day_combo_box = new JComboBox(days);
		day_combo_box.setBounds(267, 105, 66, 27);
		day_combo_box.setSelectedIndex(date.getDate()-1);
		
		//Initializes years array and year combo box
		years=new String[4];
		for(int i=2016;i<2020;i++){
			years[i-2016]=String.valueOf(i);
		}
		year_combo_box = new JComboBox(years);
		year_combo_box.setBounds(338, 105, 82, 27);
		year_combo_box.setSelectedIndex(date.getYear()-116);
		
		//Initializes hours array and hour combo box
		hours=new String[24];
		for(int i=0;i<24;i++){
			hours[i]=String.valueOf(i);
		}
		hour_combo_box = new JComboBox(hours);
		hour_combo_box.setBounds(221, 132, 73, 27);
		hour_combo_box.setSelectedIndex(date.getHours());
		
		//Initializes minutes array and minute combo box
		minutes=new String[60];
		for(int i=0;i<60;i++){
			if(i<10){
				minutes[i]= "0"+String.valueOf(i);
			}  else{
				minutes[i]=String.valueOf(i);
			}
		}
		minute_combo_box = new JComboBox(minutes);
		minute_combo_box.setBounds(306, 132, 72, 27);
		minute_combo_box.setSelectedIndex(date.getMinutes());
		
		//Initializes details label and combo box
		details_label = new JLabel("Enter Notification Details:");
		details_label.setBounds(17, 170, 163, 16);
		
		details_text_field = new JTextField();
		details_text_field.setColumns(10);
		details_text_field.setBounds(198, 164, 187, 28);
		
		//Initializes display label and combo box
		display_label = new JLabel("Select Display Type:");
		display_label.setBounds(17, 205, 134, 16);
		
		display_combo_box = new JComboBox(displays);
		display_combo_box.setBounds(198, 201, 106, 27);
		
		//Initializes sound radio buttons
		sound_disabled_radioButton = new JRadioButton("Sound Disabled");
		sound_disabled_radioButton.setBounds(316, 201, 141, 23);
		
		sound_enabled_radioButton = new JRadioButton("Sound Enabled");
		sound_enabled_radioButton.setBounds(316, 239, 141, 23);
		sound_enabled_radioButton.setSelected(true);
		if(sound_disabled_radioButton.isSelected()==true){
			sound_enabled_radioButton.setSelected(false);
		}
		
		//Initializes submit button
		submit = new JButton("Submit");
		submit.setBounds(150, 233, 154, 39);
	}
	
	//Displays alert message and sound as specified by notification details
	public static void alert(Notification n) throws Exception{
		if(n.getSound()){
	        InputStream in = new FileInputStream(new File("Notification_Sound.wav"));
	        AudioStream audioStream = new AudioStream(in);
	        AudioPlayer.player.start(audioStream);
		}
		JOptionPane.showMessageDialog(null, n.getName() +"\n"+n.getDetails(), "New Notification", JOptionPane.INFORMATION_MESSAGE);
	}

}
