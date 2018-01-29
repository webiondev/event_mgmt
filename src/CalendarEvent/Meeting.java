package CalendarEvent;

import javax.swing.JOptionPane;

public class Meeting extends Event{

	private String status;
	private String type;
	public Meeting(int eventId, String date, String time, String venue, String description, String status, String type) {
		super(eventId, date, time, venue, description);
		this.status=status;
		this.type=type;

	}
	public Meeting(){}

	public void setStatus(String status) {
		this.status = status;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public String getType() {
		return type;
	}
	public Event scheduleEvent() {

		int eventId=0; 
		String date="";
		String time="";
		String venue="";
		String description="";
		String status="";
		String type="";

		String event=JOptionPane.showInputDialog(null, "Enter Event ID");
		eventId=Integer.parseInt(event);
		date=JOptionPane.showInputDialog(null, "Enter date YYYY-MM-DD: ");
		time=JOptionPane.showInputDialog(null, "Enter time HH:mm :");
		venue=JOptionPane.showInputDialog(null, "Enter venue");
		description=JOptionPane.showInputDialog(null, "Enter description");
		status=JOptionPane.showInputDialog(null, "Enter title");
		type=JOptionPane.showInputDialog(null, "Enter fee");

		Meeting m=new Meeting(eventId,date, time, venue, description, status,  type);
		return m;

	}




}
