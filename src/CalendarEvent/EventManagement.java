//@deprecated class

/*package CalendarEvent;




import java.util.ArrayList;

import java.util.HashMap;

import java.util.List;
import java.util.Map;


import javax.swing.JOptionPane;

public class EventManagement {

	

	public void runProg() {


		List<Event> events=new ArrayList<Event>();//hold all events as created
		Map<Integer, Event>eventsToInvite=new HashMap<Integer,Event>();//create events queue for invitation
		Map<User,String> newUsers=new HashMap<User,String>();//put in queue new requests
		boolean sceduled_and_invited=false;//imagine no events created;
		int choice = 0;//admin/user choice of menu
		String id,user,password,role;//for user 
		DBOperation db=new DBOperation();
		User usr=null;//for creating new user
		//initialise some objects for database operation input/output
		Member m=new Member();
		Admin a=new Admin();
		Group g=new Group();
		Event e=null;


		//main loop
		while(true){
			try{
				//create new db connection
				db=new DBOperation();
				db.makecon();

				choice= Integer.parseInt(JOptionPane.showInputDialog(null, "1. New User\n2.Existing User\n3.Quit", "Log in",JOptionPane.QUESTION_MESSAGE));
			}
			
			catch(NumberFormatException n) {
				choice=0;//reset choice value
			}
			
			


			try{
				switch (choice){

				//new user
				case 1: //enter new user name and password
					id= JOptionPane.showInputDialog(null, "Enter ID ", "New User Registration", JOptionPane.INFORMATION_MESSAGE);
					user= JOptionPane.showInputDialog(null, "Enter user name ", "New User Registration", JOptionPane.INFORMATION_MESSAGE);
					password= JOptionPane.showInputDialog(null, "Enter user password ", "New User Registration", JOptionPane.INFORMATION_MESSAGE); ;


					//register as member or admin?

					role=JOptionPane.showInputDialog(null, "Register as member or admin?", "Set Role", JOptionPane.QUESTION_MESSAGE).toLowerCase();
					//if member create member object
					if (role.equals("member")){
						usr=new Member(id,user,password);	
						usr.setPos(role);
						newUsers.put(usr,role);
					}
					//else if admin create admin object
					else if (role.equals("admin")){
						usr=new Admin(id,user,password);	
						usr.setPos(role);
						newUsers.put(usr,role);

					}
					else 
						JOptionPane.showMessageDialog(null, "Apply as member or admin", "Error", JOptionPane.WARNING_MESSAGE);




					break;
				//existing user log in
				case 2: //try three times only
					ArrayList<String> userr=new ArrayList<String>();
					int i=1;

					boolean correct=false;//to check if log in successful
					//enter user id and password
					try{
						while(i<=3){//max 3 tries for log in
							
						
							String idd=JOptionPane.showInputDialog(null, "Enter user ID", "Log in", JOptionPane.QUESTION_MESSAGE).toLowerCase();
							String passw=JOptionPane.showInputDialog(null, "Enter password", "Log in", JOptionPane.QUESTION_MESSAGE);
								

							
							//search db
							userr=db.searchUserEx(idd, passw);

							if(userr!=null){
								//check if member or admin
								
								//if member assign to member object values
								if(userr.get(3).equals("member")){

									m.setId(userr.get(0));
									m.setName(userr.get(1));
									m.setPassword(userr.get(2));
									m.setPos(userr.get(3));
									m.setStatus(userr.get(4));
									correct=true;
									break;

								}


								else{
									//assign to admin object
									a.setId(userr.get(0));
									a.setName(userr.get(1));
									a.setPassword(userr.get(2));
									a.setPos(userr.get(3));
									correct=true;
									break;

								}
							}
							if(correct==false)
								JOptionPane.showMessageDialog(null, "Wrong username/password", "Error Login", JOptionPane.WARNING_MESSAGE);
							
							if (idd.equals(null))//if user input nothing break loop
								i=4;
							
							i++;
						}



					}
					catch (Exception exp){
						exp.printStackTrace();
					}
					//check if member or admin
					if (correct==true){
						//if admin display admin menu
						if(userr.get(3).equals("admin")){

							while(true){
								int option=Admin.displayMenu();

								switch (option){

								//add user by admin
								case 1:
									id= JOptionPane.showInputDialog(null, "Enter ID ", "New User Registration", JOptionPane.INFORMATION_MESSAGE);
									user= JOptionPane.showInputDialog(null, "Enter user name ", "New User Registration", JOptionPane.INFORMATION_MESSAGE);
									password= JOptionPane.showInputDialog(null, "Enter user password ", "New User Registration", JOptionPane.INFORMATION_MESSAGE); ;
									//register as member or admin?
									usr=new Member(id,user,password);

									role=JOptionPane.showInputDialog(null, "Register as member or admin?", "Set Role", JOptionPane.QUESTION_MESSAGE);
									usr.setPos(role);
									newUsers.put(usr,role);//add new user to queue for approval.
									break;
								
								//delete user	
								case 2: 

									boolean removed = false;
									String userToRemove=JOptionPane.showInputDialog(null, "Enter user ID to remove", "Delete User", JOptionPane.QUESTION_MESSAGE);

									//db opeartion
									if (db.searchUser(userToRemove)!=null){

										//remove from group
										if(db.search_mbr_in_grp(userToRemove)!=null)
											db.delete_mbr_in_grp(userToRemove);
										//remove from event
										if(db.search_mbr_in_event(userToRemove)!=null)
											db.delete_mbr_in_event(userToRemove);
										//delete user
										db.deleteUser(userToRemove);
										removed=true;
									}


									if (removed==false)
										JOptionPane.showMessageDialog(null, "User Removal Failed", "Removal Fail", JOptionPane.QUESTION_MESSAGE);


									break;
								//approval. Only works if someone is added to newUsers queue
								case 3: 
									/*ArrayList<Object> grp=new ArrayList<Object>();
									for (Map.Entry<User, String> entry:newUsers.entrySet()){
										int approval=JOptionPane.showConfirmDialog(null, "Approve New User [Pending] "+entry.getKey().getId()+" ?", "Approval", JOptionPane.OK_CANCEL_OPTION);;


										if (approval==0){//if approved

											//if member is approved
											if (entry.getValue().equals("member")){
												
												//ask admin for approval
												JOptionPane.showMessageDialog(null, a.register(entry.getKey().getId()));//approved?

												//if register as member request add to group
												String group_=entry.getKey().register(entry.getValue());//add to which group;

												db.addUser(entry.getKey(), entry.getValue());//add member to user
												//search if group exists
												if(db.searchGroup(group_)!=null){
													grp=db.searchGroup(group_);
													g.setGroup((String)grp.get(0));
													//search if member exists in group
													if(db.search_mbr_in_grp(entry.getKey().getId())==null){
														m=(Member)entry.getKey();
														m.setStatus("active");
														m.setMember();//increase total member size by 1 for this group
														db.add_mbr_in_grp(m, g);

													}

												}

												else
													JOptionPane.showMessageDialog(null, "Member already exists in this group", "Fail!", JOptionPane.ERROR_MESSAGE);
											}	

											else{

												//add to user as admin

												db.addUser(entry.getKey(),"N/A");

											}	

										}

										newUsers.remove(entry.getKey());//after add remove from temporary queue
									}
									
									break;
								//create new group	
								case 4:
									String newGrp=JOptionPane.showInputDialog(null, "Enter group name", "Group Creation", JOptionPane.INFORMATION_MESSAGE);
									//search db if group exists
									if(db.searchGroup(newGrp)==null){


										//creating new group
										g.setGroup(newGrp);//set name
										db.addGroup(g);

										JOptionPane.showMessageDialog(null, "New group created\n\nTotal members "+g.getMembers(), "Success!", JOptionPane.PLAIN_MESSAGE);
										//adding members
										int numMembers=Integer.parseInt(JOptionPane.showInputDialog(null, "How many members to add?", "Member Add", JOptionPane.QUESTION_MESSAGE));
										for (int j=0;j<numMembers;j++){
											String idMem=JOptionPane.showInputDialog(null, "Enter member id", "Member Addition", JOptionPane.PLAIN_MESSAGE);
											//if member exists in user table 
											if(db.searchUser(idMem)!=null){
												m.setId(idMem);
												//add to mbr in grp 
												
												if(db.search_mbr_in_a_grp(idMem, g.getGroup())==null){//check if member does not exist in group
													db.add_mbr_in_grp(m, g);
													g.setMembers(); //update total members in db table
													db.updateMember(g.getGroup(), g.getMembers());

													JOptionPane.showMessageDialog(null, "Member added", "Existing Member Add Success!", JOptionPane.ERROR_MESSAGE);
											}
												else{
													JOptionPane.showMessageDialog(null, "Member exists in group", "Existing Member Add Fail!", JOptionPane.ERROR_MESSAGE);
													
												}

											}
											else 
												JOptionPane.showMessageDialog(null, "Member does not exist", "Existing Member Add Fail!", JOptionPane.ERROR_MESSAGE);


										}}
									else
										JOptionPane.showMessageDialog(null, "Group already exists", "Creation Fail", JOptionPane.QUESTION_MESSAGE);
									break;
								//events for this member from database table
								case 5: 


									ArrayList<Object> eventsMember=new ArrayList<Object>();//hold event info for member
									e=new Training();//event object
									String memid=JOptionPane.showInputDialog(null, "Enter member ID?", "Member Events", JOptionPane.QUESTION_MESSAGE);
									String strtDate= JOptionPane.showInputDialog(null, "Enter start date YYYY-MM-DD?", "Member Events", JOptionPane.QUESTION_MESSAGE);
									String endDate=JOptionPane.showInputDialog(null, "Enter end date YYYY-MM-DD?", "Member Events", JOptionPane.QUESTION_MESSAGE);


									if (db.searchUser(memid)!=null){//if member exists

										//if member exists in mbr_in_event table get the event info
										eventsMember=db.search_mbr_in_event(memid);
										
										for(int k=1;k<eventsMember.size();k+=2){
											//if event id exists in event table
											if(eventsMember.get(k).equals((db.searchEventDate((int)eventsMember.get(k), strtDate,endDate)).getEventId())){
												//search the event if falls with in the date range
												e=db.searchEventDate((int)eventsMember.get(k), strtDate,endDate);

												m.displayEvents( e);//display event info
											}
										}
									}


									break;


								//display all members for a event [invitation accepted]	
								case 6: 

									ArrayList<String> members=new ArrayList<String>();//hold member ids
									//event id to display members of
									int eventid=Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Event ID", "Members in Event", JOptionPane.PLAIN_MESSAGE));
									if(db.searchEvent(eventid)!=null){//if event exists
										if(db.search_mbrs_in_event(eventid)!=null){//if member exists in event
											members=db.search_mbrs_in_event(eventid);//get all members for this event
											for(int j=0;j<members.size();j++)
												//iterate and display member ids for this event
												JOptionPane.showMessageDialog(null, "Member "+members.get(j)+"\n", "Members in Event", JOptionPane.PLAIN_MESSAGE);
										}
									}
									//search records
									break;
								case 7: 
									while(true){
										int searchOption=Integer.parseInt(JOptionPane.showInputDialog(null, "Search by ID\n\n1.User\n2.Group\n3.Event\n\n", "Search Database", JOptionPane.PLAIN_MESSAGE));
										switch (searchOption){
									
										case 1:
											ArrayList<String> foundUser=new ArrayList<String>();//hold user info
											foundUser=db.searchUser(JOptionPane.showInputDialog(null, "Enter User ID: ", "Search User", JOptionPane.QUESTION_MESSAGE));
											if(foundUser!=null){//if user exists
												JOptionPane.showMessageDialog(null, foundUser.get(0)+"\n"+foundUser.get(1)+"\n"+foundUser.get(3)+"\n"+foundUser.get(4)+"\n\n", "User Info", JOptionPane.PLAIN_MESSAGE);
											}
											else
												JOptionPane.showMessageDialog(null, "User not found", "User Search Fail", JOptionPane.ERROR_MESSAGE);
											break;
										
										
										case 2:
											ArrayList<Object> foundgrp=new ArrayList<Object>();//hold group info
											foundgrp=db.searchGroup(JOptionPane.showInputDialog(null, "Enter Group Name: ", "Search Group", JOptionPane.QUESTION_MESSAGE));
											if(foundgrp!=null){//if group found
												JOptionPane.showMessageDialog(null, foundgrp.get(0)+"\n"+(int)foundgrp.get(1)+"\n\n", "Group Info", JOptionPane.PLAIN_MESSAGE);
											}
											else
												JOptionPane.showMessageDialog(null, "Group not found", "Group Search Fail", JOptionPane.ERROR_MESSAGE);
											break;
									
										case 3:
											ArrayList<Object> foundEvent=new ArrayList<Object>();//hold event info
											foundEvent=db.searchEvent((Integer.parseInt((JOptionPane.showInputDialog(null, "Enter Event ID: ", "Search Event", JOptionPane.QUESTION_MESSAGE)))));
											if(foundEvent!=null){//if event found
												JOptionPane.showMessageDialog(null, (int)foundEvent.get(0)+"\n"+foundEvent.get(1)+"\n"+foundEvent.get(2)+"\n"+foundEvent.get(3)+"\n"+foundEvent.get(4)+"\n\n", "Event Info", JOptionPane.PLAIN_MESSAGE);
											}
											else
												JOptionPane.showMessageDialog(null, "Event not found", "Event Search Fail", JOptionPane.ERROR_MESSAGE);
											break;
										default:JOptionPane.showMessageDialog(null, "Choice not recognised", "Invalid Choice", JOptionPane.ERROR_MESSAGE);
										 
										}
									}

								}//end switch

							}

						}
						//else display member menu
						else if (userr.get(3).equals("member")){
							while(true){
								int option=Member.displayMenu();
								

								switch (option){
								//create new event
								case 1: int event=Integer.parseInt(JOptionPane.showInputDialog(null, "Choose type of event?\n 1.Training 2.Meeting", "Event", JOptionPane.QUESTION_MESSAGE)) ;
								if (event==1)	
								{
									e=new Training();//Initialize event
									e=e.scheduleEvent();//get event info to be scheduled
									events.add(e);//add to events list
									eventsToInvite.put(e.getEventId(), e);//add the events for which invitation is pending
									if(db.searchEvent(e.getEventId())==null){//if event not exists create
										if(db.addEvent(e) && db.addEventTrain((Training)e))//add new event
											JOptionPane.showMessageDialog(null,"Event created\n");
									
									sceduled_and_invited=true;//invitation sent to all members
									}
								}
								else if (event==2){
									//same as training 
									e=new Meeting();
									e=e.scheduleEvent();
									events.add(e.scheduleEvent());
									eventsToInvite.put(e.getEventId(),e);
									if(db.searchEvent(e.getEventId())==null){
										if(db.addEvent(e) && db.addEventMeet((Meeting)e))
											JOptionPane.showMessageDialog(null,"Event created\n");
									}
									sceduled_and_invited=true;//invitation sent to all members
								}

								else{
									JOptionPane.showMessageDialog(null, "Event does not exist");
								}
									break;
								case 2: //accept/Reject invitation for every member when they log in
									boolean is_added=false;
									if (sceduled_and_invited)//pending invitation
									{
										for (Event e_:eventsToInvite.values()){
											int choice__=JOptionPane.showConfirmDialog(null, "Accept invitation to event "+e_.getDescription()+"?", "Invitation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
											
											if(choice__==0)//if approved
												 is_added=db.add_mbr_in_event(m, e_);

											//show message if add success
											if (is_added)
												JOptionPane.showMessageDialog(null, "Event added for this user");
										}
									}
									else{
										JOptionPane.showMessageDialog(null, "No pending invitations", "Invitation", JOptionPane.INFORMATION_MESSAGE);
									}
									
									break;
								//view events for this member in a time interval
								case 3:
									//events for this member from databse table
									ArrayList<Object> eventsMember=new ArrayList<Object>();
									Event event_ = null;
									String strtDate= JOptionPane.showInputDialog(null, "Enter start date YYYY-MM-DD?", "Member Events", JOptionPane.QUESTION_MESSAGE);
									String endDate=JOptionPane.showInputDialog(null, "Enter end date YYYY-MM-DD?", "Member Events", JOptionPane.QUESTION_MESSAGE);

									eventsMember=db.search_mbr_in_event(m.getId());//check if member is in event

									for(int j=1;j>eventsMember.size();j+=2){//only get the event id which is indexed at odd numbers

										if(eventsMember.get(j).equals(db.searchEventDate((int)eventsMember.get(j), strtDate,endDate)))
											event_=db.searchEventDate((int)eventsMember.get(j), strtDate,endDate);

										m.displayEvents( event_);
									}
									break;
								default: JOptionPane.showMessageDialog(null, "Choice not recognised", "Invalid Choice", JOptionPane.ERROR_MESSAGE);
								

								}//end switch

							}
						}
					}
					//end
					break;
					//exit
				case 3: 
					db.closeCon();
					System.exit(0);
					
				default: if (choice>3){
							JOptionPane.showMessageDialog(null, "Choice not recognised", "Error", JOptionPane.WARNING_MESSAGE);
					
							System.exit(1);
				}

				}//endSwitch
			}//end try
			catch (Exception exp){
				exp.printStackTrace();
			}
			
			
			
		}//end while




	}

}*/
