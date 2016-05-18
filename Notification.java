import java.util.*;

public class Notification {
	
	//Instantiates variables
	private String name, details;
	private int display;
	private boolean sound;
	private Date time;
	
	//Empty constructor if details unknown
	public Notification(){
	}
	
	//Constructs Notification object and initial variable values
	public Notification(String name, String details, int display, boolean sound, Date time){
		this.name=name;
		this.details=details;
		this.display=display;
		this.sound=sound;
		this.time=time;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getDetails(){
		return details;
	}
	
	public void setDetails(String details){
		this.details=details;
	}
	
	public int getDisplay(){
		return display;
	}
	
	public void setDisplay(int display){
		this.display=display;
	}
	
	public boolean getSound(){
		return sound;
	}
	
	public void setSound(boolean sound){
		this.sound=sound;
	}
	
	public Date getTime(){
		return time;
	}
	
	
	public void setSound(Date time){
		this.time=time;
	}
}
