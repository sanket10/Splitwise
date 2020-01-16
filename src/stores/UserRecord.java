package stores;
import models.*;
import java.util.*;

public class UserRecord {
	private Map<Integer, User> users;
	private static UserRecord userRecordInstant = new UserRecord();
	
	private UserRecord() {
		this.users = new HashMap<Integer, User>();
	}
	
	public static UserRecord getInstant() {
		return UserRecord.userRecordInstant;
	}
	
	public boolean createUser(int userId) {
		if (this.users.containsKey(userId)) {
			return false;
		}
		this.users.put(userId, new User(userId));
		return true;	
	}
	
	// +ive = user has to take , -ive = user has to give
	public boolean addTransaction(int giverId, int takerId, double amount) {
		if (!this.users.containsKey(giverId) || !this.users.containsKey(takerId)) {
			return false;
		}
		if (!this.users.get(giverId).getFriends().containsKey(takerId)) {
			this.addTwoFriend(giverId, takerId);
		}
		Map<Integer,Double> tmp = this.users.get(giverId).getFriends();
		tmp.put(takerId, tmp.get(takerId) + amount);
		tmp = this.users.get(takerId).getFriends();
		tmp.put(giverId, tmp.get(giverId)-amount);
		return true;
	}
	
	public Map<Integer, Double> getUserRecord(int userId) {
		if (this.users.containsKey(userId)) {
			return this.users.get(userId).getFriends();
		}
		return null;
	}
	
	private boolean addTwoFriend(int userId1, int userId2) {
		if (this.users.get(userId1).getFriends().containsKey(userId2)) {
			return true;
		}
		Map<Integer, Double> tmp = this.users.get(userId1).getFriends();
		tmp.put(userId2, 0.0);
		this.users.get(userId1).setFriends(tmp);
		tmp = this.users.get(userId2).getFriends();
		tmp.put(userId1, 0.0);
		this.users.get(userId2).setFriends(tmp);
		return true;
	}
}
