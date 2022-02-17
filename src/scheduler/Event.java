package scheduler;

public class Event {
	private int time;
	private String description;
	
	public Event (int t, String d) {
		time = t;
		description = d;
	}
	
	public int getTimeRaw() {
		return time;
	}
	
	public String getTimeFormatted () {
		return time/24 + ":" + time%60;
	}
	
	public void setTime (int t) {
		time = t;
	}
	
	public String getDesc () {
		return description;
	}
	
	public void setDesc (String d) {
		description = d;
	}
	
	public String toString () {
		return "Time: " + time + " Description: " + description;
	}
}
