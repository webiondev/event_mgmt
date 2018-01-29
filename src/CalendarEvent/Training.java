package CalendarEvent;

import javax.swing.JOptionPane;

public class Training extends Event {

	private String title;
	private double fee;

	public Training(int eventId,String date, String time,String venue, String description, String title, double fee) {

		super(eventId,date, time,venue, description);
		this.title=title;
		this.fee=fee;


	}
	public Training(){

	}


	public void setFee(double fee) {
		this.fee = fee;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public double getFee() {
		return fee;
	}


	@Override
	public Event scheduleEvent() {
		int eventId=0; 
		String date="";
		String time="";
		String venue="";
		String description="";
		String title="";
		double fee=0.0;

		String event=JOptionPane.showInputDialog(null, "Enter Event ID");
		eventId=Integer.parseInt(event);
		date=JOptionPane.showInputDialog(null, "Enter date YYYY-MM-DD: ");
		time=JOptionPane.showInputDialog(null, "Enter time HH:mm :");
		venue=JOptionPane.showInputDialog(null, "Enter venue");
		description=JOptionPane.showInputDialog(null, "Enter description");
		title=JOptionPane.showInputDialog(null, "Enter title");
		String fee_=JOptionPane.showInputDialog(null, "Enter fee");
		fee=Double.parseDouble(fee_);

		Training t=new Training(eventId,date, time, venue, description, title,  fee);
		return t;
	}
}
