package scheduler;

public enum Days {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
	
	LinkedList <Event> schedule;
	
	private Days () {
		schedule = new LinkedList <Event> ();
	}
}
