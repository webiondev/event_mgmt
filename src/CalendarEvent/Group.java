package CalendarEvent;


import java.util.HashSet;

import java.util.Set;


public class Group {

	private static Set<Group> groups=new HashSet<Group>();//holds all groups
	private String name;
	private int totalMembers;
	
	public static boolean addGroup(Group g){

		return groups.add(g);

	}
	public static int totalGroups(){
		return groups.size();
	}
	public void setGroup(String group){

		name=group;
	}

	//get group name
	public String getGroup(){

		return name;

	}

	//set members size of a group
	public void setMembers(){

		totalMembers=0;
	}

	public int getMembers(){

		return totalMembers;
	}
	
	//get all events for this group
	/*public List<Event> getE() {
		return e;
	}
	public void removeMember(Member m){
		Member mBr;
		Iterator<Member> itr = members.iterator();
		while (itr.hasNext()){
			mBr=itr.next();
			if (mBr.equals(m)){//if member exists in this group
				itr.remove();
			}
		}

	}
	
	public boolean addMember(Member m){
		return members.add(m);
	}


	public void addE(Event e) {
		this.e.add(e);
	}*/
	
	
}
