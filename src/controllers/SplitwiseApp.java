package controllers;

import io.InputProcessor;

public class SplitwiseApp {
	
	public static void main(String args[]) {
		UserController userController = new UserController();
		GroupController groupController = new GroupController();
		
		InputProcessor inputProcessor = InputProcessor.getInstance();
		inputProcessor.addHandler("CREATE_USER", userController::createUser);
		inputProcessor.addHandler("ADD_USER_TO_GROUP", groupController::addUserToGroup);
		inputProcessor.addHandler("ADD_PERSONAL_EXPENSE", userController::addUserToUserTransaction);
		inputProcessor.addHandler("ADD_GROUP_EXPENSE", groupController::addGroupTransaction);
		inputProcessor.addHandler("SHOW_USER_EXPENSE", userController::showUserDetail);
		inputProcessor.addHandler("SHOW_GROUP_DETAILS", groupController::showGroupDetail);
		inputProcessor.addHandler("REMOVE_USER_TO_GROUP", groupController::removeUserFromGroup);
		inputProcessor.addHandler("CREATE_GROUP", groupController::createGroup);
		
		inputProcessor.listen();
	}
	
	

}
