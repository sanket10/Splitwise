package models;
import java.util.*;

public class User {
	private int userId;
	private Map<Integer, Double> friends;
	
	public User(int userId) {
		this.userId = userId;
		this.friends = new HashMap<Integer, Double>();
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Map<Integer, Double> getFriends() {
		return friends;
	}

	public void setFriends(Map<Integer, Double> friends) {
		this.friends = friends;
	}
}
