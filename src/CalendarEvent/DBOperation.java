/**
 * The Member class implements all the functionalities for database
 *
 * @author  Mohammad Mushfequr Rahman
 * @version 3.0 
 */

package CalendarEvent;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import javax.swing.JOptionPane;

public class DBOperation {
	
	//attributes for dboperations
		private Connection connection;
		private PreparedStatement pstmt;
		private ResultSet rs;
		
		//constructor will load the driver for the database used
		public DBOperation()
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			connection = null;
		}
		public void makecon()
		{
			try {
				connection = DriverManager
						.getConnection("jdbc:mysql://localhost:3306/eventmanagement","root", "");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Unable to connect to DB Server", "Connection Fail", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		//search
		
		public ArrayList<String> searchUser(){
			
			String sql = "select id from user"; 
			
			try {
				pstmt = connection.prepareStatement(sql);
				  
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<String> list = new ArrayList<String>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getString("id"));
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
		}

		public ArrayList<String> searchUser(String id){
			
			String sql = "select * from user where id=?"; 
			
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  id);   
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<String> list = new ArrayList<String>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getString("id"));
						list.add(rs.getString("name"));
						list.add(rs.getString("pass"));
						list.add(rs.getString("position"));
						list.add(rs.getString("status_"));
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
		}
		public ArrayList<String> searchUserEx(String id, String pass){
			
			String sql = "select * from user where id=? and pass=?"; 
			
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  id);
				pstmt.setString(2,  pass);
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<String> list = new ArrayList<String>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getString("id"));
						list.add(rs.getString("name"));
						list.add(rs.getString("pass"));
						list.add(rs.getString("position"));
						list.add(rs.getString("status_"));
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
		}
		
		public ArrayList<Object> searchGroup(){
			
			String sql = "select * from group_"; 
			
			try {
				pstmt = connection.prepareStatement(sql);
				
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<Object> list = new ArrayList<Object>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getString("name"));
						list.add(rs.getInt("total_members"));
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
		}
		public ArrayList<Object> searchGroup(String namee){
			
			String sql = "select * from group_ where name=?"; 
			
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  namee);
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<Object> list = new ArrayList<Object>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getString("name"));
						list.add(rs.getInt("total_members"));
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
		}
		
		public ArrayList<Object> searchEvent(){
			
			String sql = "select id, description from event ";
			try {
				pstmt = connection.prepareStatement(sql);
				
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<Object> list = new ArrayList<Object>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getInt("id"));
						list.add(rs.getString("description"));
						
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
		}
		
		public ArrayList<Object> searchInvitation(int eveid,String memid){
			
			String sql = "select eveid, memid from invitations where eveid=? and memid=?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, eveid);
				pstmt.setString(2, memid);
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<Object> list = new ArrayList<Object>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getInt("eveid"));
						list.add(rs.getString("memid"));
						
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
		}
		
		public ArrayList<Object> searchEvent(int id){
			
			String sql = "select * from event where id=?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1,  id);
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<Object> list = new ArrayList<Object>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getInt("id"));
						list.add(rs.getString("description"));
						list.add(rs.getString("venue"));
						list.add(rs.getDate("date_"));
						list.add(rs.getTime("time_"));
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
		}
		
		public Event searchEventDate(int id, String d1, String d2){
			
			String sql = "select * from event where id=? and date_ between ? and ?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1,  id);
				pstmt.setDate(2,  Date.valueOf(d1));
				pstmt.setDate(3, Date.valueOf(d2));
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					
					Event e=new Training();
					while (rs.next()) //as long as there is value to retrieve
					{
						
						
						e.setEventId(rs.getInt("id"));
						e.setDescription(rs.getString("description"));
						e.setVenue(rs.getString("venue"));
						
						DateFormat df= new SimpleDateFormat("yyyy-MM-DD");
						DateFormat df_= new SimpleDateFormat("HH:mm");
						e.setDate((df.format(rs.getDate("date_"))));
						e.setTime(df_.format(rs.getTime("time_")));
						
						
					}
					
					return e;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		return null;
		}
		
		
		public ArrayList<String> search_mbr_in_grp(String memid){
			
			String sql = "select * from mbr_in_grp where memid=?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  memid);
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<String> list = new ArrayList<String>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getString("memid"));
						list.add(rs.getString("grpname"));
						
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			return null;
		}
		
		public ArrayList<String> search_mbr_in_a_grp(String memid, String grpname){
			
			String sql = "select * from mbr_in_grp where memid=? and grpname=?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  memid);
				pstmt.setString(2,  grpname);
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<String> list = new ArrayList<String>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getString("memid"));
						list.add(rs.getString("grpname"));
						
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			return null;
		}
		public ArrayList<Object> search_mbr_in_event(String memid){
			
			String sql = "select * from mbr_in_event where memid=?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  memid);
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<Object> list = new ArrayList<Object>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getString("memid"));
						list.add(rs.getInt("eventid"));
						
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			return null;
		}
		
		//search a member in an event
		public ArrayList<Object> search_mbr_in_event(String memid, int eveid){
			
			String sql = "select * from mbr_in_event where memid=? and eventid=?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  memid);
				pstmt.setInt(2,  eveid);
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<Object> list = new ArrayList<Object>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getString("memid"));
						list.add(rs.getInt("eventid"));
						
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			return null;
		}
		
		//search member in specific event
		
		
		public ArrayList<String> search_mbrs_in_event(int eventid){
			
			String sql = "select * from mbr_in_event where eventid=?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1,  eventid);
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<String> list = new ArrayList<String>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getString("memid"));
						
						
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			return null;
		}
		
		
		public ArrayList<Object> search_grp_in_event(String name){
			
			
			String sql = "select * from grp_in_event where grpname=?";
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  name);
				rs = pstmt.executeQuery();  //execute the query and get the result
				
				if (!rs.isBeforeFirst())
					return null;
				else 
				{  //if the record exist
					ArrayList<Object> list = new ArrayList<Object>(); //create new arraylist object
					while (rs.next()) //as long as there is value to retrieve
					{
						list.add(rs.getString("grpname"));
						list.add(rs.getInt("eventid"));
						
						
					}
					return list;
				}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			
			return null;
		}
		
		
		//add
		public boolean addUser(Object u, String status_){
			
			String sql1 = "insert into user(id,name,pass,position,status_) values (?,?,?,?,?) ";
			
			try {
				pstmt = connection.prepareStatement(sql1);
				
				//this two statement is to set the variable above ?
				if(u instanceof Member){
					
					System.out.println("MEMBER");
					
					pstmt.setString(1,  ((Member) u).getId());  //if the data type is int, use setInt() instead 
					pstmt.setString(2,((Member) u).getName());
					pstmt.setString(3, ((Member) u).getPassword());
					pstmt.setString(4, ((Member) u).getPos());
					pstmt.setString(5, status_);
					
			}
				else{
					
					pstmt.setString(1,  ((Admin) u).getId());  //if the data type is int, use setInt() instead 
					pstmt.setString(2,((Admin) u).getName());
					pstmt.setString(3, ((Admin) u).getPassword());
					pstmt.setString(4, ((Admin) u).getPos());
					pstmt.setString(5, status_);
					
					
				
				}
				
				
				int rowcount = pstmt.executeUpdate();  //to execute the sql statement
				if (rowcount > 0)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean addGroup(Group g){
			
			String sql = "insert into group_(name,total_members) values (?,?) ";
			
			try {
				pstmt = connection.prepareStatement(sql);
				//this two statement is to set the variable above ?
			
					pstmt.setString(1,  g.getGroup());  //if the data type is int, use setInt() instead 
					pstmt.setInt(2,g.getMembers());
					
					
			
				int rowcount = pstmt.executeUpdate();  //to execute the sql statement
				if (rowcount > 0)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean addEvent (Event e) throws ParseException{
			String sql = "insert into event(id,description,venue,date_,time_) values (?,?,?,?,?) ";
			
			try {
				pstmt = connection.prepareStatement(sql);
				//this two statement is to set the variable above ?
			
				pstmt.setInt(1,  e.getEventId());  
				pstmt.setString(2,e.getDescription());
				pstmt.setString(3, e.getVenue());
				
				
				pstmt.setDate(4, Date.valueOf(e.getDate()));
				java.util.Date d=null;
				d = new SimpleDateFormat("HH:mm").parse(e.getTime());
				pstmt.setTime(5, new Time(d.getTime()));
				
					
					
			
				int rowcount = pstmt.executeUpdate();  //to execute the sql statement
				if (rowcount > 0) 
					return true;
				else
					return false;
				
			} catch (SQLException exp) {
				// TODO Auto-generated catch block
				exp.printStackTrace();
				return false;
			}
		}
		
		public boolean addEventTrain (Training t){
			String sql = "insert into training(eventid,title,fee) values (?,?,?) ";
			
			try {
				pstmt = connection.prepareStatement(sql);
				//this two statement is to set the variable above ?
			
				pstmt.setInt(1,  t.getEventId());  //if the data type is int, use setInt() instead 
				pstmt.setString(2,t.getTitle());
				pstmt.setDouble(3, t.getFee());
				
					
			
				int rowcount = pstmt.executeUpdate();  //to execute the sql statement
				if (rowcount > 0)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException exp) {
				// TODO Auto-generated catch block
				exp.printStackTrace();
				return false;
			}
		}
		
		public boolean addEventMeet (Meeting m){
			String sql = "insert into meeting(eventid,status_,type) values (?,?,?) ";
			
			try {
				pstmt = connection.prepareStatement(sql);
				//this two statement is to set the variable above ?
			
				pstmt.setInt(1,  m.getEventId());  //if the data type is int, use setInt() instead 
				pstmt.setString(2,m.getStatus());
				pstmt.setString(3, m.getType());
				
					
			
				int rowcount = pstmt.executeUpdate();  //to execute the sql statement
				if (rowcount > 0)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException exp) {
				// TODO Auto-generated catch block
				exp.printStackTrace();
				return false;
			}
		}
		
		
		public boolean addInvitations(Event e, Member m){
			
			String sql = "insert into invitations(eveid,memid,invited) values (?,?,?) ";
			
			try {
				pstmt = connection.prepareStatement(sql);
				//this two statement is to set the variable above ?
			
					pstmt.setInt(1,  e.getEventId());  //if the data type is int, use setInt() instead 
					pstmt.setString(2,m.getId());
					pstmt.setString(3, "invited");
					
			
				int rowcount = pstmt.executeUpdate();  //to execute the sql statement
				if (rowcount > 0)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
				return false;
			}
		}
		
		public boolean add_mbr_in_grp(Member m, Group g){
			String sql = "insert into mbr_in_grp(memid,grpname) values (?,?) ";
			
			try {
				pstmt = connection.prepareStatement(sql);
				//this two statement is to set the variable above ?
			
				pstmt.setString(1,m.getId());
				pstmt.setString(2, g.getGroup());
				
					
					
			
				int rowcount = pstmt.executeUpdate();  //to execute the sql statement
				if (rowcount > 0)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException exp) {
				// TODO Auto-generated catch block
				exp.printStackTrace();
				return false;
			}
		}
		
		public boolean add_mbr_in_event(Member m, Event e){
			
			String sql = "insert into mbr_in_event(memid,eventid) values (?,?) ";
			
			try {
				pstmt = connection.prepareStatement(sql);
				//this two statement is to set the variable above ?
			
				pstmt.setString(1,m.getId());
				pstmt.setInt(2,e.getEventId());
				
					
					
			
				int rowcount = pstmt.executeUpdate();  //to execute the sql statement
				if (rowcount > 0)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException exp) {
				// TODO Auto-generated catch block
				exp.printStackTrace();
				return false;
			}
		}
		
		public boolean add_grp_in_event(Group g, Event e){
			String sql = "insert into grp_in_event(grpname,eventid) values (?,?) ";
			
			try {
				pstmt = connection.prepareStatement(sql);
				//this two statement is to set the variable above ?
			
				pstmt.setString(1,g.getGroup());
				pstmt.setInt(2,e.getEventId());
				
					
					
			
				int rowcount = pstmt.executeUpdate();  //to execute the sql statement
				if (rowcount > 0)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException exp) {
				// TODO Auto-generated catch block
				exp.printStackTrace();
				return false;
			}
			
		}
		//delete
		public boolean deleteUser(String id){
			String sql = "delete from user where id=?"; //create the sql string
			
			try {
				//creating a prepareStatement for the sql string above
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  id);
				
				int rowcount = pstmt.executeUpdate(); //to execute the statement
				
				if (rowcount == 1)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			
			
			return false;
		}
		
		
		public boolean deleteGroup(String name){
			
			String sql = "delete from group_ where name=?"; //create the sql string
			
			try {
				//creating a prepareStatement for the sql string above
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  name);
				
				int rowcount = pstmt.executeUpdate(); //to execute the statement
				
				if (rowcount == 1)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			
			
			return false;
		}
		
		public boolean deleteEvent(int id){
			String sql = "delete from event where id=?"; //create the sql string
			
			try {
				//creating a prepareStatement for the sql string above
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1,  id);
				
				int rowcount = pstmt.executeUpdate(); //to execute the statement
				
				if (rowcount == 1)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			
			
			return false;
		}
		
		public boolean delete_mbr_in_grp(String memid){
			
			String sql = "delete from mbr_in_grp where memid=?"; //create the sql string
			
			try {
				//creating a prepareStatement for the sql string above
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  memid);
				
				int rowcount = pstmt.executeUpdate(); //to execute the statement
				
				if (rowcount == 1)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			
			
			return false;
		}
		
		public boolean delete_mbr_in_event(String memid){
			String sql = "delete from mbr_in_event where memid=?"; //create the sql string
			
			try {
				//creating a prepareStatement for the sql string above
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  memid);
				
				int rowcount = pstmt.executeUpdate(); //to execute the statement
				
				if (rowcount == 1)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			
			
			return false;
		}
		public boolean delete_grp_in_event(String name){
			String sql = "delete from grp_in_event where grpname=?"; //create the sql string
			
			try {
				//creating a prepareStatement for the sql string above
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1,  name);
				
				int rowcount = pstmt.executeUpdate(); //to execute the statement
				
				if (rowcount == 1)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			
			
			return false;
		}
		//update
		
		public boolean updateMember(String name, int totalMember){
			String sql = "update group_ set total_members=? where name=?"; //create the sql string
			
			try {
				//creating a prepareStatement for the sql string above
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1,  totalMember);
				pstmt.setString(2, name);
				int rowcount = pstmt.executeUpdate(); //to execute the statement
				
				if (rowcount == 1)  //if the record exist
					return true;
				else
					return false;
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
			
			
			return false;
		}
		
		public void closeCon(){
			
			try {
				
				
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "DB connection failed!", "Connection Error", JOptionPane.ERROR_MESSAGE);
				
			}
			
		}
}
