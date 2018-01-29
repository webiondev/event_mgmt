/**
 * The Member class implements Main Wndow for Log in and new user
 *
 * @author  Mohammad Mushfequr Rahman
 * @version 3.0 
 */

package CalendarEvent;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class mainWindow extends JFrame {
	
	//declare fields
	private JFrame frame;
	private static mainWindow window;
	
	private JTextField nameField;
	private JTextField nameFieldName;
	private JPasswordField passField;
	private JTextField desiredRole;
	
	
	String id, pass;
	int i=0;//lopping
	Member m=new Member();
	Admin a=new Admin();
	boolean correct=false;
	private ArrayList<String> userr=new ArrayList<String>();
	private DBOperation db=new DBOperation();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 window = new mainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public mainWindow(){
		
		init();
	}
	
	public void init(){
		
		//set frame
		frame = new JFrame("Event Management Portal");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//set panel 1
		JPanel panelLog = new JPanel();
		panelLog.setBounds(103, 6, 198, 81);
		frame.getContentPane().add(panelLog);
		panelLog.setLayout(null);
		
		//set panel 2
		JPanel panelInfo = new JPanel();
		panelInfo.setBounds(113, 99, 188, 173);
		frame.getContentPane().add(panelInfo);
		
		//initialise
		JButton btnLogIn = new JButton("Log In");
		JButton btnNewUser = new JButton("New User");
		JButton ok=new JButton("OK");
		JButton apply=new JButton("Apply");
		
		
		//when user clicks log in button
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					nameField=new JTextField("Enter User ID");
					
					passField=new JPasswordField("Enter User Password");
					
					//reset view
					panelInfo.removeAll();
					panelInfo.add(nameField);
					panelInfo.add(passField);
					panelInfo.add(ok);
					panelInfo.revalidate();
					panelInfo.repaint();
					
					db.makecon();//make connection with database
					
										
						
		
			}
		});
		
		//when user clicks new user button
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//initialise fields
				nameField=new JTextField("Enter User ID");
				nameFieldName=new JTextField("Enter Name");
				passField=new JPasswordField("Enter User Password");
				desiredRole=new JTextField("Enter Desired Role");
				
				//reset view
				panelInfo.removeAll();
				
				panelInfo.add(nameField);
				panelInfo.add(nameFieldName);
				panelInfo.add(passField);
				panelInfo.add(desiredRole);
				panelInfo.add(apply);
				panelInfo.revalidate();
				panelInfo.repaint();
							
			}
		});
		
		
		
			//when user clicks ok button
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				try{	
					
					
					//if log in frame
					id=nameField.getText();
					pass=String.valueOf((passField.getPassword()));
					
					
					userr=db.searchUserEx(id, pass);
					
					if(userr!=null){
					
						//if member assign to member object values
						if(userr.get(3).equals("member")){
			
							m.setId(userr.get(0));
							m.setName(userr.get(1));
							m.setPassword(userr.get(2));
							m.setPos(userr.get(3));
							m.setStatus(userr.get(4));
							correct=true;
							
							Member.add_newLogin(m);
							
							db.closeCon();
							
							window.frame.setVisible(false);
							MemberFrame.run();
							
							
			
						}
			
			
						else{
							//assign to admin object
							a.setId(userr.get(0));
							a.setName(userr.get(1));
							a.setPassword(userr.get(2));
							a.setPos(userr.get(3));
							correct=true;
							
							
							db.closeCon();
							
							window.frame.setVisible(false);
							AdminFrame.run();
							
			
						}}
					

					else if(correct==false){
						JOptionPane.showMessageDialog(null, "Wrong username/password", "Error Login", JOptionPane.WARNING_MESSAGE);
					
						i++;}
				if(i==3){//if three tries fail
					
					window.frame.setVisible(false);//hide main window
					db.closeCon();
					System.exit(0);
					
						
					}
					
				}
				
				
				catch(Exception exp){
					
					
						JOptionPane.showMessageDialog(null, "user or password not entered!", "Error Log In", JOptionPane.ERROR_MESSAGE);
				}
				
				}
			});
			
			
	
	
		//new user apply button	
		apply.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try{	
						
						//if member
						
						if(desiredRole.getText().toLowerCase().equals("member"))
						{
							//add member to volatile data structure
							Member m=new Member(nameField.getText(), nameFieldName.getText(), String.valueOf(passField.getPassword()));
							m.setPos("member");
							Member.addMember(m);
							JOptionPane.showMessageDialog(null, "new User Registration Pending", "Self Registration", JOptionPane.PLAIN_MESSAGE);
							
							
						}
						//if admin do same
						else if (desiredRole.getText().toLowerCase().equals("admin")){
							Admin a=new Admin(nameField.getText(), nameFieldName.getText(), String.valueOf(passField.getPassword()));
							a.setPos("admin");
							Admin.addAdmin(a);
							JOptionPane.showMessageDialog(null, "new User Registration Pending", "Self Registration", JOptionPane.PLAIN_MESSAGE);
							
							
						}
						
					}
						
					
				
				catch(Exception exp){
					//exp.printStackTrace();
					
						JOptionPane.showMessageDialog(null, "Enter All Fields!", "Error New User Application", JOptionPane.ERROR_MESSAGE);
				}
					
			
				}
			});
	
		btnLogIn.setBounds(64, 21, 83, 29);
		panelLog.add(btnLogIn);
		
		
		btnNewUser.setBounds(54, 46, 103, 29);
		panelLog.add(btnNewUser);
		
		
		
		
		
		
	}
}
