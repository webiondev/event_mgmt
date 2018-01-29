/**
 * The Member class implements all the functionalities for an abstract user
 *
 * @author  Mohammad Mushfequr Rahman
 * @version 2.0 
 */
package CalendarEvent;

public abstract class User {




	private String name;
	private String userID;
	private String password;
	private String position;

	public User(String id, String name, String password) {
		this.name=name.toLowerCase();
		userID=id;
		this.password=password;
	}
	public User(){}

	public void setPos(String pos){
		position=pos.toLowerCase();
	}
	public String getPos(){
		return position;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setId(String Id) {
		userID=Id;
	}
	public String getId() {
		return userID;
	}
	public void setPassword(String pass) {
		password=pass;
	}
	public String getPassword() {
		return password;
	}
	//abstract public String register(String a);

}
