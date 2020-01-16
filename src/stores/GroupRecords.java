package stores;
import java.util.*;
import models.*;

public class GroupRecords {
	private Map<Integer, Group> groups;
	private UserRecord userRecord;
	private static GroupRecords groupRecordsInstant = new GroupRecords();

	private GroupRecords() {
		this.userRecord = UserRecord.getInstant();
		this.groups = new HashMap<Integer, Group>();
	}
	
	public static GroupRecords getInstant() {
		return GroupRecords.groupRecordsInstant;
	}
	
	public boolean createGroup(int groupId) {
		if (this.groups.containsKey(groupId)) {
			return false;
		}
		this.groups.put(groupId, new Group(groupId));
		return true;
	}
	
	public boolean addUserToGroup(int groupId, int userId) {
		if (this.groups.containsKey(groupId)) {
			Set<Integer> tGroupUsers = this.groups.get(groupId).getUsers();
			if (tGroupUsers.contains(userId)) {
				System.out.println("User already part of group");
				return false;
			}
			tGroupUsers.add(userId);
			this.groups.get(groupId).setUsers(tGroupUsers);
			System.out.println("User Added");
			return true;
		}
		System.out.println("Group doesn't Exist");
		return false;
	}
	
	public boolean removeUserFromGroup(int groupId, int userId) {
		if (this.groups.containsKey(groupId)) {
			Set<Integer> tGroupUsers = this.groups.get(groupId).getUsers();
			if (!tGroupUsers.contains(userId)) {
				System.out.println("User is not a part of group");
				return false;
			}
			tGroupUsers.remove(userId);
			this.groups.get(groupId).setUsers(tGroupUsers);
			System.out.println("User Removed");
			return true;
		}
		System.out.println("Group doesn't Exist");		
		return false;
	}
	
	public boolean addGroupTransaction(int groupId, int userId, double amount) {
		if (this.groups.containsKey(groupId)) {
			Set<Integer> tGroupUsers = this.groups.get(groupId).getUsers();
			if (tGroupUsers.contains(userId)) {
				double phShare = amount/tGroupUsers.size();
				for (int gUserId: tGroupUsers) {
					if (gUserId != userId) {
						this.userRecord.addTransaction(userId, gUserId, phShare);
					}
				}
				return true;
			}
			System.out.println("User is not a part of given group");		
			return false;
		}
		System.out.println("Group doesn't Exist");		
		return false;
	}
	
	public Set<Integer> getGroupDetail(int groupId) {
		if (this.groups.containsKey(groupId)) {
			return this.groups.get(groupId).getUsers();
		}
		System.out.println("Group doesn't Exist");		
		return null;
	}
}
