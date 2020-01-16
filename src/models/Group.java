package models;
import java.util.*;

public class Group {
	private int groupId;
	private Set<Integer> users;
	
	public Group(int groupId) {
		this.groupId = groupId;
		this.users = new HashSet<Integer>();
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Set<Integer> getUsers() {
		return users;
	}

	public void setUsers(Set<Integer> users) {
		this.users = users;
	}
}
