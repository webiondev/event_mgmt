/**
 * The Member class implements all the gui based functionalities for members
 *
 * @author  Mohammad Mushfequr Rahman
 * @version 1.0 
 */

package CalendarEvent;


import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JTree;

import javax.swing.JScrollPane;

public class MemberFrame extends JFrame {
	

	private JFrame frame;
	private static MemberFrame memF;
	
	private int whichCommit=0;
	
	private  DBOperation db=new DBOperation();
	
	private JTextField idEve;
	private JTextField desc;
	private JTextField venue;
	private JTextField date;
	private JTextField time;
	private JTextField title;
	private JTextField fee;
	private JTextField status_;
	private JTextField type;
	
	private JButton btnMem=new JButton("Commit");
	
	
	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					memF = new MemberFrame();
					memF.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MemberFrame() {
		init();
	}
	//tree view for events
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
		tree_1.setBounds(0, 0, 80, 254);
		
		
		JScrollPane scrollP = new JScrollPane();
		scrollP.setBounds(18, 0, 105, 254);
		scrollP.setViewportView(tree_1);
		
		panelTree.removeAll();
		//panelTree.add(tree_1);
		panelTree.add(scrollP);
		panelTree.revalidate();
		panelTree.repaint();
		
	
		
	}
	
	
	public void init() {
		frame = new JFrame("Member Portal");
		frame.setBounds(150, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		db.makecon();
		
		
		
		JPanel panelMem = new JPanel();
		panelMem.setBounds(134, 6, 142, 82);
		frame.getContentPane().add(panelMem);
		panelMem.setLayout(null);
		
		String memOptions[]={"Schedule Training Event", "Schedule Meeting Event","Accept Invitation", "View All Events in a time interval"};
		
		
		JComboBox comboBox = new JComboBox(memOptions);
		comboBox.setBounds(15, 6, 122, 54);
		panelMem.add(comboBox);
		
		JLabel lblMem = new JLabel("Members' Activities");
		lblMem.setBounds(15, 0, 139, 16);
		panelMem.add(lblMem);
		
		JPanel inputPanelMem = new JPanel();
		inputPanelMem.setBounds(284, 6, 160, 266);
		frame.getContentPane().add(inputPanelMem);
		
		JButton btnToLogIn = new JButton("To Log In");
		btnToLogIn.setBounds(6, 58, 61, 16);
		panelMem.add(btnToLogIn);
		
		JPanel panelTree = new JPanel();
		panelTree.setBounds(6, 6, 127, 266);
		frame.getContentPane().add(panelTree);
		
		btnToLogIn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				mainWindow.main(null);;
				MemberFrame.memF.setVisible(false);
				db.closeCon();
				
			}
		});
		
		comboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				JComboBox cb=(JComboBox)e.getSource();
				String choice=(String)cb.getSelectedItem();
				
				//add all admin options then add button for each option to validate
				switch(choice){
				
					case "Schedule Training Event":
						
						 idEve=new JTextField("Add Event ID [Integer]");
						 desc=new JTextField("Add Description");
						 venue=new JTextField("Add Venue");
						 date=new JTextField("Add Date: YYYY-MM-DD");
						 time=new JTextField("Add Time: HH:mm");
						 title=new JTextField("Add Event Title");
						 fee=new JTextField("Entrance Fee?");
						 
						 inputPanelMem.removeAll();
						
						inputPanelMem.add(idEve);
						inputPanelMem.add(desc);
						inputPanelMem.add(venue);
						inputPanelMem.add(date);
						inputPanelMem.add(time);
						inputPanelMem.add(title);
						inputPanelMem.add(fee);
						inputPanelMem.add(btnMem);
						
						inputPanelMem.revalidate();
						inputPanelMem.repaint();
						
						treeEvent(panelTree);
						
						whichCommit=1;
						
						break;
					
					case "Schedule Meeting Event":
						
						idEve=new JTextField("Add Event ID [Integer]");
						 desc=new JTextField("Add Description");
						 venue=new JTextField("Add Venue");
						 date=new JTextField("Add Date: YYYY-MM-DD");
						 time=new JTextField("Add Time: HH:mm");
						 status_=new JTextField("Add Event Status");
						 type=new JTextField("Event Type?");
						 
						 inputPanelMem.removeAll();
						
						inputPanelMem.add(idEve);
						inputPanelMem.add(desc);
						inputPanelMem.add(venue);
						inputPanelMem.add(date);
						inputPanelMem.add(time);
						inputPanelMem.add(status_);
						inputPanelMem.add(type);
						inputPanelMem.add(btnMem);
						
						inputPanelMem.revalidate();
						inputPanelMem.repaint();
						
						treeEvent(panelTree);
						
						whichCommit=2;
						
						break;	
					case "Accept Invitation": 
						
						
						inputPanelMem.removeAll();
						inputPanelMem.add(btnMem);
						inputPanelMem.revalidate();
						inputPanelMem.repaint();
						
						panelTree.removeAll();
						panelTree.revalidate();
						panelTree.repaint();
						
						whichCommit=3;
						
						break;
						
					case "View All Events in a time interval": 
						
						
						
						inputPanelMem.removeAll();
						inputPanelMem.add(btnMem);
						inputPanelMem.revalidate();
						inputPanelMem.repaint();
						
						treeEvent(panelTree);
						
						whichCommit=4;
						
						break;
						
			
				}
			}
		});
	
		btnMem.addActionListener(new ActionListener(){

			//R/W admin operation to DB 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
				
					//there are seven commits for seven operations
					switch (whichCommit){
						
						case 1: 
							
							if (db.searchEvent(Integer.parseInt(idEve.getText()))==null){
									
									Event eve=new Training(Integer.parseInt(idEve.getText()), date.getText(), time.getText(), venue.getText(), desc.getText(),title.getText(), Float.parseFloat(fee.getText()));
									
									
									
									try {
										if(db.addEvent(eve) && db.addEventTrain((Training)eve))//add new event
											JOptionPane.showMessageDialog(null,"Event created\n");
									} catch (HeadlessException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (ParseException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
									
								}
							
								else{
									JOptionPane.showMessageDialog(null, "Event exists", "Event Creation Fail", JOptionPane.WARNING_MESSAGE);
	
									}
								
							
							break;
						
						case 2:
							
							if (db.searchEvent(Integer.parseInt(idEve.getText()))==null){
								
								Event eve=new Meeting(Integer.parseInt(idEve.getText()), date.getText(), time.getText(), venue.getText(), desc.getText(),status_.getText(), type.getText());
								
								
								
								try {
									if(db.addEvent(eve) && db.addEventTrain((Training)eve))//add new event
										JOptionPane.showMessageDialog(null,"Event created\n");
								} catch (HeadlessException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (ParseException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							}
						
							else{
								JOptionPane.showMessageDialog(null, "Event exists", "Event Creation Fail", JOptionPane.WARNING_MESSAGE);

								}
							break;
						
						case 3:
							
							//while scheduled events exist invite every member who logs in
							boolean is_added=false;
							ArrayList<Object>mem_in_this_eve=new ArrayList<Object>();
							ArrayList<Object> events=new ArrayList<Object>();
							Member m=Member.getLoggedMem();
							ArrayList<Event> eve=new ArrayList<Event>();
							int i=0;
							
								
								//get all events to check if they have been invited to
								events=db.searchEvent();
								
								System.out.println(events.size());
								
								
								for( i=0;i<events.size();i+=2){
									
									
									
									Event anEvent=new Training();
									anEvent.setEventId((Integer)events.get(i));
									anEvent.setDescription(events.get(i+1).toString());
									eve.add(anEvent);
								
									//check if member already exists in event
									
									if(db.search_mbr_in_event(m.getId(), anEvent.getEventId())==null ){ 
										mem_in_this_eve=db.searchInvitation(anEvent.getEventId(), m.getId());
									
										if(mem_in_this_eve==null){//invite this mem
											int choice__=JOptionPane.showConfirmDialog(null, "Accept invitation to event "+anEvent.getDescription()+"?", "Invitation", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
											
											if(choice__==0)//if approved
											{
												
												is_added=db.add_mbr_in_event(m, anEvent);
												 db.addInvitations(anEvent, m);
												 
											}
											
											//show message if add success
											if (is_added)
												JOptionPane.showMessageDialog(null, "Event added for this user");
											}
										}
							
								
					}
								if(i==events.size())
									
									JOptionPane.showMessageDialog(null, "No pending invitations for this user", "Invitation", JOptionPane.INFORMATION_MESSAGE);
								
							break;
							
						case 4:
							
							break;
						
		
					}
				}
					
				catch(Exception exp){
					exp.printStackTrace();
					JOptionPane.showMessageDialog(null, "Enter All Fields Correctly");
				}
			}
		});
	}
}
