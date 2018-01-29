/**
 * The Member class implements all the GUI based functionalities for Admins
 *
 * @author  Mohammad Mushfequr Rahman
 * @version 1.0 
 */

package CalendarEvent;


import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import javax.swing.JTextField;
import javax.swing.JTree;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class AdminFrame extends JFrame {

	private JFrame frame;
	private static AdminFrame admF;
	
	//make seven text fields for admin options
	private JTextField addUser1;
	private JTextField addUser2;
	private JTextField addUser3;
	private JTextField addUser4;
	private JTextField newgrp;
	private JTextField delUser;
	private JTextField viewEveMem;
	private JTextField viewMemEve;
	private JTextField grptxtF;
	private JTextField strtDate;
	private JTextField endDate;
	
	//declare other fields and initialise
	private JButton btnAdm=new JButton("Commit");
	private JButton btnUser=new JButton("Search By User");
	private JButton btnGrp=new JButton("Search By Group");
	private JButton btnEve=new JButton("Search By Event");
	
	
	
	private static User u = null;
	private  Group g=new Group();
	
	
	
	//which choice for Admin?
	int whichCommit=0;
	private static int indexUser=0;//to access new Users who apply for themselves
	
	
	private  DBOperation db=new DBOperation();
	
	

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admF = new AdminFrame();
					admF.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AdminFrame(){
		init();
	
	}
	//tree view for users
	private void treeUser(JPanel panelTree){

		DefaultTreeModel treeModel;
		DefaultMutableTreeNode root;
		DefaultMutableTreeNode child;
		
		root=new DefaultMutableTreeNode("All Users");
		
		ArrayList<String> userIds=new ArrayList<String>();
		userIds=db.searchUser();
		//add all users from db to tree
		if(userIds!=null){
			
			for (int i=0;i<userIds.size();i++){
				child=new DefaultMutableTreeNode(userIds.get(i));
				root.add(child);
			}
			
		}
		
		
		treeModel=new DefaultTreeModel(root);
		JTree tree_1 = new JTree(treeModel);
		tree_1.setBounds(6, 6, 107, 254);
		
		JScrollPane scrollP = new JScrollPane();
		scrollP.setBounds(48, 6, 505, 254);
		scrollP.setViewportView(tree_1);
		
		panelTree.removeAll();
		//panelTree.add(tree_1);
		panelTree.add(scrollP);
		panelTree.revalidate();
		panelTree.repaint();
		
	}
	//tree view for groups
	private void treeGroup(JPanel panelTree){
		
		DefaultTreeModel treeModel;
		DefaultMutableTreeNode root;
		DefaultMutableTreeNode child;
		
		root=new DefaultMutableTreeNode("All Groups");
		
		ArrayList<Object> grp=new ArrayList<Object>();
		grp=db.searchGroup();
		//add all groups from db to tree
		if(grp!=null){
			try{
				for (int i=0;i<grp.size();i+=2){
					child=new DefaultMutableTreeNode(grp.get(i));
					root.add(child);
					
					child=new DefaultMutableTreeNode((String.valueOf(grp.get(i+1))));
					root.add(child);
					
				}
			}
			catch(IndexOutOfBoundsException exp){}
			
		}
		
		
		treeModel=new DefaultTreeModel(root);
		JTree tree_1 = new JTree(treeModel);
		tree_1.setBounds(6, 6, 107, 254);
		
		
		JScrollPane scrollP = new JScrollPane();
		scrollP.setBounds(18, 6, 505, 254);
		scrollP.setViewportView(tree_1);
		
		panelTree.removeAll();
		//panelTree.add(tree_1);
		panelTree.add(scrollP);
		panelTree.revalidate();
		panelTree.repaint();
		
	}
	
	private void treeEvent(JPanel panelTree){
		
		DefaultTreeModel treeModel;
		DefaultMutableTreeNode root;
		DefaultMutableTreeNode child;
		
		root=new DefaultMutableTreeNode("All Events");
		
		ArrayList<Object> eve=new ArrayList<Object>();
		eve=db.searchEvent();
		//add events from db to tree
		if(eve!=null){
			try{
				for (int i=0;i<eve.size();i+=2){
					child=new DefaultMutableTreeNode(eve.get(i));
					root.add(child);
					
					child=new DefaultMutableTreeNode((String.valueOf(eve.get(i+1))));
					root.add(child);
					
				}
			}
			catch(IndexOutOfBoundsException exp){}
			
		}
		
		
		treeModel=new DefaultTreeModel(root);
		JTree tree_1 = new JTree(treeModel);
		tree_1.setBounds(6, 6, 107, 254);
		
		
		JScrollPane scrollP = new JScrollPane();
		scrollP.setBounds(18, 6, 505, 254);
		scrollP.setViewportView(tree_1);
		
		panelTree.removeAll();
		//panelTree.add(tree_1);
		panelTree.add(scrollP);
		panelTree.revalidate();
		panelTree.repaint();
		
	}
	
	/**
	 * Create the frame.
	 */
	public void init() {
		frame = new JFrame("Admin Portal");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		db.makecon();
		
		String adminOptions[]={"Add New User","Delete User", "Approve\\Reject Membership", "Create New Group/Add Member To Group", "View All Events for a Member [Time Interval]", "View All Members for an Event", "Search Database"};
		
		JPanel panelAdm = new JPanel();
		panelAdm.setBounds(81, 6, 196, 80);
		frame.getContentPane().add(panelAdm);
		panelAdm.setLayout(null);
		
		JComboBox comboBox = new JComboBox(adminOptions);
		comboBox.setBounds(32, 17, 144, 46);
		panelAdm.add(comboBox);
		
		JLabel lblAdministration = new JLabel("Administration");
		lblAdministration.setBounds(45, 6, 117, 16);
		panelAdm.add(lblAdministration);
		
		JButton btnToLogIn = new JButton("To Log In");
		btnToLogIn.setBounds(6, 58, 61, 16);
		panelAdm.add(btnToLogIn);
		
		JPanel inputPanelAdm = new JPanel();
		inputPanelAdm.setBounds(91, 98, 196, 174);
		frame.getContentPane().add(inputPanelAdm);
		
		JPanel panelTree = new JPanel();
		panelTree.setBounds(289, 6, 155, 266);
		frame.getContentPane().add(panelTree);
		panelTree.setLayout(null);
		
		btnToLogIn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// if admin logs in get admin frame
				
				mainWindow.main(null);;
				AdminFrame.admF.setVisible(false);
				db.closeCon();
				
			}
		});
		
		//admin options
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JComboBox cb=(JComboBox)e.getSource();
				String choice=(String)cb.getSelectedItem();
				
				//add all admin options then add button for each option to validate
				switch(choice){
				
				case "Add New User":
					
					
						
						addUser1=new JTextField("Add New User ID");
						 addUser2=new JTextField("Add New User Name");
						 addUser3=new JTextField("Add New User Password");
						 addUser4=new JTextField("Add New User Role");
						inputPanelAdm.removeAll();
						
						inputPanelAdm.add(addUser1);
						inputPanelAdm.add(addUser2);
						inputPanelAdm.add(addUser3);
						inputPanelAdm.add(addUser4);
						inputPanelAdm.add(btnAdm);
						
						inputPanelAdm.revalidate();
						inputPanelAdm.repaint();
						
						panelTree.removeAll();
						panelTree.revalidate();
						panelTree.repaint();
					
					
					
					whichCommit=1;
					
					break;
					
				case "Delete User": 
					delUser=new JTextField("Enter User ID");
					inputPanelAdm.removeAll();
					inputPanelAdm.add(delUser);
					inputPanelAdm.add(btnAdm);
					inputPanelAdm.revalidate();
					inputPanelAdm.repaint();
					
					panelTree.removeAll();
					panelTree.revalidate();
					panelTree.repaint();
					
					treeUser(panelTree);
					
					
					whichCommit=2;
					
					break;
				case "Approve\\Reject Membership": 
					
					addUser1=new JTextField("Add New User ID");
					 addUser2=new JTextField("Add New User Name");
					 addUser3=new JTextField("Add New User Password");
					 addUser4=new JTextField("Add New User Role");
					if (Member.getMembers()>0){
						u=new Member();
						u=Member.getMember(indexUser);
						
						addUser1.setText(u.getId());
						addUser2.setText(u.getName());
						addUser3.setText(u.getPassword());
						addUser4.setText(u.getPos());
						
						Member.removeMem(indexUser);
						
						indexUser+=1;
						
					}
					
					else if (Admin.getAdmins()>0){
						u=new Admin();
						u=Admin.getAdmin(indexUser);
						addUser1.setText(u.getId());
						addUser2.setText(u.getName());
						addUser3.setText(u.getPassword());
						addUser4.setText(u.getPos());
						
						Admin.removeAdm(indexUser);
						
						indexUser+=1;
					}
					
					indexUser=0;
					
					inputPanelAdm.removeAll();
					inputPanelAdm.add(btnAdm);
					inputPanelAdm.revalidate();
					inputPanelAdm.repaint();
					
					panelTree.removeAll();
					panelTree.revalidate();
					panelTree.repaint();
					
					whichCommit=3;
					
					break;
				case "Create New Group/Add Member To Group":
					
					newgrp=new JTextField("Enter Group Name");
					grptxtF=new JTextField("Add New Member");
					
					inputPanelAdm.removeAll();
					inputPanelAdm.add(newgrp);
					inputPanelAdm.add(grptxtF);
					inputPanelAdm.add(btnAdm);
					inputPanelAdm.revalidate();
					inputPanelAdm.repaint();
					
					panelTree.removeAll();
					panelTree.revalidate();
					panelTree.repaint();
					
					
					treeGroup(panelTree);
					
				
					whichCommit=4;
					
					break;
				
				case "View All Events for a Member [Time Interval]":
					viewEveMem=new JTextField("Enter Member ID");
					strtDate=new JTextField("Start Date: YYYY-MM-DD");
					endDate=new JTextField("End Date: YYYY-MM-DD");
					
					inputPanelAdm.removeAll();
					inputPanelAdm.add(viewEveMem);
					inputPanelAdm.add(strtDate);
					inputPanelAdm.add(endDate);
					inputPanelAdm.add(btnAdm);
					inputPanelAdm.revalidate();
					inputPanelAdm.repaint();
					
					panelTree.removeAll();
					panelTree.revalidate();
					panelTree.repaint();
					
					treeUser(panelTree);

					whichCommit=5;
					
					break;
				
				case "View All Members for an Event": 
					viewMemEve=new JTextField("Enter Event Name");
					inputPanelAdm.removeAll();
					inputPanelAdm.add(viewMemEve);
					inputPanelAdm.add(btnAdm);
					inputPanelAdm.revalidate();
					inputPanelAdm.repaint();
					
					panelTree.removeAll();
					panelTree.revalidate();
					panelTree.repaint();
					
					treeEvent(panelTree);
					
					whichCommit=6;
					
					break;
				
				case "Search Database":
					
					/*btnUser=new JButton("Search By User");
					btnGrp=new JButton("Search By Group");
					btnEve=new JButton("Search By Event");*/
					
					inputPanelAdm.removeAll();
					/*inputPanelAdm.add(btnUser);
					inputPanelAdm.add(btnGrp);
					inputPanelAdm.add(btnEve);*/
					inputPanelAdm.revalidate();
					inputPanelAdm.repaint();
					
					panelTree.removeAll();
					panelTree.revalidate();
					panelTree.repaint();
					
					
					admF.frame.setVisible(false);
					SearchFrame.run();
					
					whichCommit=7;
					
					break;
				}
				
			}
			
			
		});
		
		//when admin chooses a option 
		btnAdm.addActionListener(new ActionListener(){

			//R/W admin operation to DB 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				//there are seven commits for seven operations
				switch (whichCommit){
					//add new user
					case 1: 
						if(Member.getMembers()==0 && Admin.getAdmins()==0){
							if (db.searchUser(addUser1.getText())==null){
								if (addUser4.getText().equals("member")){
									
									u=new Member();
									u.setId(addUser1.getText());
									u.setName(addUser2.getText());
									u.setPassword(addUser3.getText());
									u.setPos(addUser4.getText());
									
									JOptionPane.showMessageDialog(null, "New User awaiting apporval", "New User", JOptionPane.PLAIN_MESSAGE);
									
									
								}
								
								else{
									
									u=new Admin();
									u.setId(addUser1.getText());
									u.setName(addUser2.getText());
									u.setPassword(addUser3.getText());
									u.setPos(addUser4.getText());
									JOptionPane.showMessageDialog(null, "New User awaiting apporval", "New User", JOptionPane.PLAIN_MESSAGE);
									
								}
							}
							
							else {
								JOptionPane.showMessageDialog(null, "User already exists in database", "New User", JOptionPane.PLAIN_MESSAGE);
								
							}
							
					}
					
				else{//if new users from main frame still pending
					JOptionPane.showMessageDialog(null, "Decide on pending users before adding new", "Users Pending Registration", JOptionPane.WARNING_MESSAGE);
				}
					
					
						break;
					
					case 2:
						
							
						//delete user
						if (db.searchUser(delUser.getText())!=null){
							
							//remove from group
							if(db.search_mbr_in_grp(delUser.getText())!=null){
								
								ArrayList<String>grpInfo=new ArrayList<String>();
								ArrayList<Object>grpTotal=new ArrayList<Object>();
								grpInfo=db.search_mbr_in_grp(delUser.getText());
								
								db.delete_mbr_in_grp(delUser.getText());//delete from every group this member
								//decrease the groups's size
								for(int i=1;i<grpInfo.size();i+=2){
								
									grpTotal=db.searchGroup((String)grpInfo.get(i));
									System.out.println((String)grpTotal.get(0)+" "+(((int)grpTotal.get(1))-1));
									db.updateMember((String)grpTotal.get(0),(((int)grpTotal.get(1))-1));
								
									
								}
								
							}
							//remove from event
							if(db.search_mbr_in_event(delUser.getText())!=null)
								db.delete_mbr_in_event(delUser.getText());
							//delete user
							
							db.deleteUser(delUser.getText());
							JOptionPane.showMessageDialog(null, "User deleted", "Delete Success", JOptionPane.PLAIN_MESSAGE);

						}
						
						else{
							
							JOptionPane.showMessageDialog(null, "User does not exist", "Delete Fail", JOptionPane.ERROR_MESSAGE);
						}
						break;
					
					case 3:
						try{
							System.out.println(u.getPassword().length());
							ArrayList<Object> grp=new ArrayList<Object>();
							Group g =new Group();
							
							int ok=JOptionPane.showConfirmDialog(null, "Apporve Registration of "+u.getId()+"?", "Approval", JOptionPane.OK_CANCEL_OPTION);
							
							if(ok==0){
								
								if(u.getPos().equals("member")){
									
									
									//if register as member request add to group
									String group_=JOptionPane.showInputDialog(null, "Add to which group?", "New Member", JOptionPane.QUESTION_MESSAGE);
									;//add to which group;
	
									
									//search if group exists
									if(db.searchGroup(group_)!=null){
										grp=db.searchGroup(group_);
										g.setGroup((String)grp.get(0));
										int totalMem=(int) grp.get(1);
										
										db.addUser((Member)u, "active");
										//search if member exists in group
										if(db.search_mbr_in_grp(u.getId())==null){
											
											Member m=(Member) u;
											m.setStatus("active");
											//add and update group member count
											db.add_mbr_in_grp(m, g);
											db.updateMember(group_, totalMem+1);
											JOptionPane.showMessageDialog(null, "Member added", "Success!", JOptionPane.PLAIN_MESSAGE);
											
	
										}
	
									}
	
									else
										JOptionPane.showMessageDialog(null, "Member already exists in this group OR Group does not exist!", "Fail!", JOptionPane.ERROR_MESSAGE);
								
									
								}
								
								else if(u.getPos().equals("admin")){
									
									db.addUser((Admin)u, "N/A");
									
								}
								
								else {
									
									
									
								}
								
							}
							
							u=null;
						}
					catch (NullPointerException exp){
						
						
					}	
						break;
				case 4 : 
					try {
						ArrayList<Object> grpInfo=new ArrayList<Object>();
						Member m=new Member();
						int totalMem=0;
						
						
						
						if(db.searchGroup(newgrp.getText())==null){
							
								
								g.setGroup(newgrp.getText());
								g.setMembers();
								
								db.addGroup(g);
								
								
								JOptionPane.showMessageDialog(null, "New group created", "Create Success", JOptionPane.PLAIN_MESSAGE);
								
						}
						
						else{//if group exists add members
							
							JOptionPane.showMessageDialog(null, "Group already exists", "Creation Fail", JOptionPane.QUESTION_MESSAGE);
						}
							g.setGroup(newgrp.getText());
							g.setMembers();
							grpInfo=db.searchGroup(newgrp.getText());
							
							
							//add other members
							if(db.searchUser(grptxtF.getText())!=null){//check if user exists
								
								m.setId(grptxtF.getText());
								//add to mbr in grp 
								
								if(db.search_mbr_in_a_grp(grptxtF.getText(), g.getGroup())==null){//check if member does not exist in group
									db.add_mbr_in_grp(m, g);
									
									db.updateMember(g.getGroup(),(int)grpInfo.get(1)+1);//update total member
		
									JOptionPane.showMessageDialog(null, "Member added", "Existing Member Add Success!", JOptionPane.PLAIN_MESSAGE);
								}
									else{
										JOptionPane.showMessageDialog(null, "Member exists in group", "Existing Member Add Fail!", JOptionPane.ERROR_MESSAGE);
										
									}
	
							}
							else 
								JOptionPane.showMessageDialog(null, "Member does not exist", "Existing Member Add Fail!", JOptionPane.ERROR_MESSAGE);
	
						
			}
					
					catch(NullPointerException exp) {}

					break;
				
				
				case 5: 
					try{
						ArrayList<Object> eventsMember=new ArrayList<Object>();//hold event info for member
						
						Event eve=new Training();//event object
						if (db.searchUser(viewEveMem.getText())!=null){//if member exists
							//if member exists in mbr_in_event table get the event info
							eventsMember=db.search_mbr_in_event(viewEveMem.getText());
							for(int k=1;k<eventsMember.size();k+=2){
								
								//if event id exists in event table
								if(eventsMember.get(k).equals((db.searchEventDate((int)eventsMember.get(k), strtDate.getText(),endDate.getText())).getEventId())){
									//search the event if falls with in the date range
									eve=db.searchEventDate((int)eventsMember.get(k), strtDate.getText(),endDate.getText());
	
									(new Member()).displayEvents( eve);//display event info
									
									
									}
								
								if(k==eventsMember.size()){//if no events exist for this member in date range
									JOptionPane.showMessageDialog(null, "No events found for this member", "Events",JOptionPane.PLAIN_MESSAGE);
								
								}
							
								
							}
							
							
						}
						
						else {
							
							JOptionPane.showMessageDialog(null, "Member does not exist", "Member Search",JOptionPane.PLAIN_MESSAGE);
							
						}
					}
						
						catch(NullPointerException exp){
							//if no events exist for this member
								JOptionPane.showMessageDialog(null, "No events found for this member", "Events",JOptionPane.PLAIN_MESSAGE);
							
						}

					break;
				
				case 6:
					ArrayList<String> members=new ArrayList<String>();//hold member ids
					if(db.searchEvent(Integer.parseInt(viewMemEve.getText()))!=null){//if event exists
						if(db.search_mbrs_in_event(Integer.parseInt(viewMemEve.getText()))!=null){//if member exists in event
							members=db.search_mbrs_in_event(Integer.parseInt(viewMemEve.getText()));//get all members for this event
							for(int j=0;j<members.size();j++)
								//iterate and display member ids for this event
								JOptionPane.showMessageDialog(null, "Member "+members.get(j)+"\n", "Members in Event", JOptionPane.PLAIN_MESSAGE);
						}
					}
					
					break;
				
				case 7:
					
					
					break;
				}//endSwitch
			}
			 
			});
		
		
		
	}
}
