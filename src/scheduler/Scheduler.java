package scheduler;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * Objective: Create a weekly scheduling application.
 * 
 * You may create any additional enums, classes, methods or variables needed
 * to accomplish the requirements below:
 * 
 * - You should use an array filled with enums for the days of the week and each
 *   enum should contain a LinkedList of events that includes a time and what is 
 *   happening at the event.
 * 
 * - The user should be able to to interact with your application through the
 *   console and have the option to add events, view events or remove events by
 *   day.
 *   
 * - Each day's events should be sorted by chronological order.
 *  
 * - If the user tries to add an event on the same day and time as another event
 *   throw a SchedulingConflictException(created by you) that tells the user
 *   they tried to double book a time slot.
 *   
 * - Make sure any enums or classes you create have properly encapsulated member
 *   variables.
 */
public class Scheduler {
	static Scanner scan = new Scanner (System.in);
    public static void main(String[] args) throws SchedulingConflictException {
    	
    	ArrayList <Days> days = new ArrayList ();
    	days.add(Days.MONDAY);
    	days.add(Days.TUESDAY);
    	days.add(Days.WEDNESDAY);
    	days.add(Days.THURSDAY);
    	days.add(Days.FRIDAY);
    	days.add(Days.SATURDAY);
    	days.add(Days.SUNDAY);
    	
    	int day;
    	int command;
    	
    	while (true) {
    		day = askDay ();
    		command = askCommand ();
    		
    		if (command == 1) {
    			System.out.println("What event would you like to add? (Description)");
    			String description = scan.nextLine();
    			System.out.println("What event would you like to add? (Time In Minutes)");
    			int time = Integer.parseInt (scan.nextLine());
    			
    			if (days.get(day).schedule.size()>1) {
	    			boolean timeOpen = true;
	    			Node <Event> node = days.get(day).schedule.getHead();
	    			int i = 0;
	    			while (days.get(day).schedule.size() < i) {
	    				if (!(node.getValue().getTimeRaw()==time)) {
	    					timeOpen = false;
	    				}
	    				i++;
	    				node = node.getNext();
	    			}
	    			if (!timeOpen) {
	    				throw new SchedulingConflictException();
	    				//keep going
	    			}else {
	    				Node <Event> event = days.get(day).schedule.getHead();
		    			int j = 0;
		    			while (days.get(day).schedule.size() < j) {
		    				if (event.getValue().getTimeRaw()<time && event.getNext().getValue().getTimeRaw() > time) {
		    					Node <Event> thing = new Node <Event> (new Event (time, description));
		    					Node <Event> next = event.getNext();
		    					event.setNext(thing);
		    					thing.setPrev(event);
		    					thing.setNext(next);
		    					next.setPrev(thing);
		    				}
		    				j++;
		    				event = event.getNext();
		    			}
		    			if (days.get(day).schedule.getHead().getValue().getTimeRaw()>time) {
	    					Node <Event> thing = new Node <Event> (new Event (time, description));
	    					Node <Event> head = days.get(day).schedule.getHead();
	    					thing.setNext(head);
	    					head.setPrev (thing);
	    					days.get(day).schedule.setHead(thing);
	    				}else if (event.getValue().getTimeRaw()<time) {
	    					Node <Event> thing = new Node <Event> (new Event (time, description));
	    					Node <Event> tail = days.get(day).schedule.getTail();
	    					thing.setPrev(tail);
	    					tail.setNext (thing);
	    					days.get(day).schedule.setTail(thing);
	    				}
	    			}
    			}else if (days.get(day).schedule.size()==1) {
    				if (days.get(day).schedule.getHead().getValue().getTimeRaw()>time) {
    					Node <Event> thing = new Node <Event> (new Event (time, description));
    					days.get(day).schedule.getHead().setNext(thing);
    					thing.setPrev(days.get(day).schedule.getHead());
    					days.get(day).schedule.setTail(thing);
    				}else {
    					days.get(day).schedule.add(new Event (time, description));
    				}
    			}
    			else {
    				days.get(day).schedule.add(new Event (time, description));
    			}
    		}
    		else if (command == 2) {
    			days.get(day).schedule.print();
    		}else if (command == 3) {
    			days.get(day).schedule.print();
    			System.out.println("What event would you like to delete? (Input time)");
    			int time = scan.nextInt();
    			Node <Event> node = days.get(day).schedule.getHead();
    			int i = 0;
    			while (days.get(day).schedule.size() < i) {
    				if (node.getValue().getTimeRaw()==time) {
    					days.get(day).schedule.remove(i);
    					break;
    				}
    				i++;
    				node = node.getNext();
    			}
    		}
    	}
    }
    
    public static int askDay () {
    	System.out.println("What Day?");
    	System.out.println("1: MONDAY");
    	System.out.println("2: TUESDAY");
    	System.out.println("3: WEDNESDAY");
    	System.out.println("4: THURSDAY");
    	System.out.println("5: FRIDAY");
    	System.out.println("6: SATURDAY");
    	System.out.println("7: SUNDAY");
    	return scan.nextInt();
    }
    
    public static int askCommand () {
    	System.out.println("What Command?");
    	System.out.println("1: Add Events");
    	System.out.println("2: View Events");
    	System.out.println("3: Remove Events");
    	return scan.nextInt ();
    }
}
    
