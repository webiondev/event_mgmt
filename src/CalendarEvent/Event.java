/**
 * The Member class implements all the functionalities that for events
 *
 * @author  Mohammad Mushfequr Rahman
 * @version 3.0 
 */

package CalendarEvent;

import java.util.ArrayList;
import java.util.List;



public abstract class Event {

	private int eventId;
	private String date;
	private String time;
	public String venue;
	public String description;
	public int numOfParticipants;
	private List<Member> participants=new ArrayList<Member>();
	private List<Group> group=new ArrayList<Group>();


	public Event(int eventId,String date, String time,String venue, String description){
		this.eventId=eventId;
		this.date=date;
		this.time=time;
		this.venue=venue;
		this.description=description;
		

	}

	public Event(){}


	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public void setVenue(String venue) {
		this.venue = venue;
	}
	public void setParticipants(List<Member> participants) {
		this.participants = participants;
	}
	public void setGroup(List<Group> group) {
		this.group = group;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setNumOfParticipants(int numOfParticipants) {
		this.numOfParticipants = numOfParticipants;
	}
	public void setTime(String time) {
		this.time = time;
	}


	public List<Group> getGroup() {
		return group;
	}

	public int getEventId() {
		return eventId;
	}
	public String getDate() {
		return date;
	}
	public String getTime() {
		return time;
	}
	public String getVenue() {
		return venue;
	}
	public String getDescription() {
		return description;
	}
	public int getNumOfParticipants() {
		return numOfParticipants;
	}
	public List<Member> getParticipants() {
		return participants;
	}

	public void displayMembers(String event){

	}

	abstract public Event scheduleEvent();


}
