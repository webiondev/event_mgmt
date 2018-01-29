/**
 * The Admin class implements all the functionalities that an admin object and class should possess
 *
 * @author  Mohammad Mushfequr Rahman
 * @version 3.0 
 */
package CalendarEvent;

import java.util.ArrayList;

import java.util.List;




public class Admin extends User {

	
	private static List <Admin> admins=new ArrayList<Admin>();//holds all admins
	
	public Admin(String id, String name, String password) {
		super(id, name,password);

	}
	public Admin(){}
	
	
	public static void addAdmin(Admin a){
		admins.add(a);
	}
	
	public static Admin getAdmin (int indexMem ){
		
		
			return (admins.get(indexMem));
	}
	
	public static void removeAdm(int indexMem){
		
		admins.remove(indexMem);
	}
	
	public static int getAdmins(){
		
		return admins.size();
	}

	
}
