/**
 * The Member class implements all the functionalities that a member object and class should possess
 *
 * @author  Mohammad Mushfequr Rahman
 * @version 3.0 
 */
package CalendarEvent;

import java.util.ArrayList;

import java.util.List;

import javax.swing.JOptionPane;

public class Member extends User {

	private String status="";//to check if membership is activated 
	private static Member logged_mem=null;
	private static List<Member>newMembers=new ArrayList<Member> ();
	public Member(String id, String name, String password) {
		super(id, name,password);

	}
	public Member(){}


	
	public static void add_newLogin(Member m){
		logged_mem=new Member();
		logged_mem=m;
	}
	
	public static Member getLoggedMem(){
		
		return logged_mem;
	}
	
	
	public static void addMember(Member m){
		newMembers.add(m);
	}
	
	//add a static int variable in AdminFrame class to approve new Members
	public static Member getMember (int indexMem ){
		
		
			return (newMembers.get(indexMem));
	}
	
	public static void removeMem(int indexMem){
		
		newMembers.remove(indexMem);
	}
	
	
	
	public static int getMembers(){
		return newMembers.size();
	}

	public void displayEvents(Event e){
		
			JOptionPane.showMessageDialog(null, "Event ID: "+e.getEventId()+"\n"+"Description "+e.getDescription()+"\n"+"Date "+e.getDate()+"\n"+"Venue "+e.getVenue()+"\n", "Member Event", JOptionPane.PLAIN_MESSAGE);
		

	}
	
	


	public void setStatus(String status_){
		status=status_;
	}

	
	public String getStatus(){
		return status;
	}

	

	public static int displayMenu(){
		int choice=Integer.parseInt(JOptionPane.showInputDialog(null, "1.Schedule Event\n"+"2.Accept OR Reject Invitation\n"+"3.View All Events in a time interval\n\n", "Member Access", JOptionPane.INFORMATION_MESSAGE));
		return choice;
	}

}
