package controllers;
import stores.*;
import java.util.*;

public class UserController {
	private UserRecord userRecord;
	
	UserController() {
		this.userRecord = UserRecord.getInstant();
	}
	
	public void createUser(String input) {
		String inputs[] = this.provideInputArgument(input);
		if (inputs.length == 2) {
			try {
				int userId = Integer.parseInt(inputs[1]);

				if (!this.userRecord.createUser(userId)) {
					System.out.println("User already created");
				}
			} catch(Exception e) {
				System.out.println("Input format is not valid " + e.getMessage());
			}
		} else {
			System.out.println("No A valid input for Create User, INPUT: " + input);
		}
	}
	
	public void addUserToUserTransaction(String input) {
		String inputs[] = this.provideInputArgument(input);
		if (inputs.length == 4) {
			try {
				int giverId = Integer.parseInt(inputs[1]);
				int takerId = Integer.parseInt(inputs[2]);
				double amount = Double.parseDouble(inputs[3]);

				if (!this.userRecord.addTransaction(giverId, takerId, amount)) {
					System.out.println("User doesn't exist");
				}
				
			} catch(Exception e) {
				System.out.println("Input format is not valid " + e.getMessage());
			}
		} else {
			System.out.println("No A valid input for addUserToUserTransaction, INPUT: " + input);
		}
	}
	
	public void showUserDetail(String input) {
		String inputs[] = this.provideInputArgument(input);
		if (inputs.length == 2) {
			try {
				int userId = Integer.parseInt(inputs[1]);
				
				System.out.println("User Detail - UserId : " + userId);
				Map<Integer, Double> userDetails = this.userRecord.getUserRecord(userId);
				for(Integer tUserId : userDetails.keySet()) {
					System.out.println("UserId : " + tUserId + "    Balance : " + userDetails.get(tUserId));
				}
			} catch(Exception e) {
				System.out.println("Input format is not valid " + e.getMessage());
			}
		} else {
			System.out.println("No A valid input for showUserDetail, INPUT: " + input);
		}
	}

	private String[] provideInputArgument(String input) {
		return input.trim().split(" ");
	}
}
