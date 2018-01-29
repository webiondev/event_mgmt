package CalendarEvent;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class SearchFrame extends JFrame {

	private JFrame frame;
	private static SearchFrame sF;
	private JTextField textFieldSearch;
	private JButton btnSearch = new JButton("Search");
	
	private DBOperation db=new DBOperation();
	
	int searchOption=0;

	/**
	 * Launch the application.
	 */
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sF = new SearchFrame();
					sF.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SearchFrame() {
		
		init();
	}
	
	public void init() {
		frame = new JFrame("Search Portal");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		db.makecon();
		
		
		
		JPanel panelSearch = new JPanel();
		panelSearch.setBounds(119, 6, 207, 146);
		frame.getContentPane().add(panelSearch);
		panelSearch.setLayout(null);
		
		JPanel panelRes = new JPanel();
		panelRes.setBounds(145, 164, 150, 108);
		frame.getContentPane().add(panelRes);
		panelRes.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
	

		JButton btnSearchByEvent = new JButton("Search By Event ID");
		btnSearchByEvent.setBounds(25, 88, 147, 29);
		panelSearch.add(btnSearchByEvent);
		
		JButton btnSearchByUser = new JButton("Search By User ID");
		btnSearchByUser.setBounds(17, 6, 155, 29);
		panelSearch.add(btnSearchByUser);
		
		JButton btnSearchByGroup = new JButton("Search By Group Name");
		btnSearchByGroup.setBounds(6, 47, 185, 29);
		panelSearch.add(btnSearchByGroup);
		
		JButton btnToLogIn = new JButton("To LogIn");
		
		btnToLogIn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// if admin logs in get admin frame
				
				mainWindow.main(null);;
				SearchFrame.sF.setVisible(false);
				db.closeCon();
				
			}
		});
		
		btnToLogIn.setBounds(54, 111, 92, 29);
		panelSearch.add(btnToLogIn);
		
		btnSearchByUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				textFieldSearch=new JTextField("Enter User ID");
				
				
				panelRes.removeAll();
				panelRes.add(textFieldSearch);
				panelRes.add(btnSearch);
				panelRes.revalidate();
				panelRes.repaint();
				
				searchOption=1;
			}
		});
		
		btnSearchByGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				textFieldSearch=new JTextField("Enter Group Name");
				
				
				panelRes.removeAll();
				panelRes.add(textFieldSearch);
				panelRes.add(btnSearch);
				panelRes.revalidate();
				panelRes.repaint();
				
				searchOption=2;
			}
		});
		
		btnSearchByEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				textFieldSearch=new JTextField("Enter Event ID");
				
				
				panelRes.removeAll();
				panelRes.add(textFieldSearch);
				panelRes.add(btnSearch);
				panelRes.revalidate();
				panelRes.repaint();
				
				searchOption=3;
			}
		});
			
		btnSearch.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				switch(searchOption){
					
				case 1:
						ArrayList<String> foundInfo=new ArrayList<String>();//hold user info
						foundInfo=db.searchUser(textFieldSearch.getText());
						if(foundInfo!=null){//if user exists
							JOptionPane.showMessageDialog(null, foundInfo.get(0)+"\n"+foundInfo.get(1)+"\n"+foundInfo.get(3)+"\n"+foundInfo.get(4)+"\n\n", "User Info", JOptionPane.PLAIN_MESSAGE);
						}
						else
							JOptionPane.showMessageDialog(null, "User not found", "User Search Fail", JOptionPane.ERROR_MESSAGE);
						break;
					
					
				case 2:
					ArrayList<Object> foundG=new ArrayList<Object>();//hold user info
					foundG=db.searchGroup(textFieldSearch.getText());
					if(foundG!=null){//if user exists
						JOptionPane.showMessageDialog(null, foundG.get(0)+"\n"+foundG.get(1)+"\n\n", "Group Info", JOptionPane.PLAIN_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "Group not found", "Group Search Fail", JOptionPane.ERROR_MESSAGE);
					break;
					
				
				case 3:
					
					
					
					ArrayList<Object> foundEve=new ArrayList<Object>();//hold user info
					foundEve=db.searchEvent(Integer.parseInt(textFieldSearch.getText()));
					if(foundEve!=null){//if user exists
						JOptionPane.showMessageDialog(null, foundEve.get(0)+"\n"+foundEve.get(1)+"\n"+foundEve.get(3)+"\n"+foundEve.get(4)+"\n\n", "Event Info", JOptionPane.PLAIN_MESSAGE);
					}
					else
						JOptionPane.showMessageDialog(null, "Event not found", "Event Search Fail", JOptionPane.ERROR_MESSAGE);
					break;
			}
		
		
		}//endSwitch
			
			
		});
	
	}
}
