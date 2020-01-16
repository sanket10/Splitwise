package controllers;
import stores.*;

public class GroupController {
	private GroupRecords groupRecords;
	
	GroupController() {
		this.groupRecords = GroupRecords.getInstant();
	}
	
	public void addUserToGroup(String input) {
		String inputs[] = this.provideInputArgument(input);
		
		if (inputs.length == 2) {
			try {
				int groupId = Integer.parseInt(inputs[0]);
				int userId = Integer.parseInt(inputs[1]);

				this.groupRecords.addUserToGroup(groupId, userId);
			} catch(Exception e) {
				System.out.println("Input format is not valid " + e.getMessage());
			}
		} else {
			System.out.println("No A valid input for Add User to Group, INPUT: " + input);
		}
		
	}
	
	public void removeUserFromGroup(String input) {
		String inputs[] = this.provideInputArgument(input);
		if (inputs.length == 2) {
			try {
				int groupId = Integer.parseInt(inputs[0]);
				int userId = Integer.parseInt(inputs[1]);

				this.groupRecords.removeUserFromGroup(groupId, userId);
			} catch(Exception e) {
				System.out.println("Input format is not valid " + e.getMessage());
			}
		} else {
			System.out.println("No A valid input for Remove User to Group, INPUT: " + input);
		}
	}
	
	public void addGroupTransaction(String input) {
		String inputs[] = this.provideInputArgument(input);
		if (inputs.length == 3) {
			try {
				int groupId = Integer.parseInt(inputs[0]);
				int userId = Integer.parseInt(inputs[1]);
				double amount = Double.parseDouble(inputs[2]);

				this.groupRecords.addGroupTransaction(groupId, userId, amount);
			} catch(Exception e) {
				System.out.println("Input format is not valid " + e.getMessage());
			}
		} else {
			System.out.println("No A valid input for Add Group Transaction, INPUT: " + input);
		}
	}
	
	public void showGroupDetail(String input) {
		String inputs[] = this.provideInputArgument(input);
		if (inputs.length == 1) {
			try {
				int groupId = Integer.parseInt(inputs[0]);
				
				System.out.println("Group Detail - GroupID : " + groupId);
				System.out.print("Group Users : ");
				for (Integer userId : this.groupRecords.getGroupDetail(groupId)) {
					System.out.print(userId + " ");
				}
				System.out.println();
			} catch(Exception e) {
				System.out.println("Input format is not valid " + e.getMessage());
			}
		} else {
			System.out.println("No A valid input to Group Details, INPUT: " + input);
		}
	}
	
	private String[] provideInputArgument(String input) {
		return input.trim().split(" ");
	}
}
