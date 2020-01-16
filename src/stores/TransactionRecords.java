package stores;
import java.util.*;

public class TransactionRecords {
	private Map<Integer, Transaction> transactions;
	private int transactionId;
	private GroupRecords groupRecords;
	private UserRecord userRecords;
	
	private static TransactionRecords transactionRecordInstance = new TransactionRecords();
	
	private TransactionRecords() {
		this.transactionId = 1;
		this.groupRecords = GroupRecords.getInstant();
		this.userRecords = UserRecord.getInstant();
		this.transactions = new HashMap<Integer, Transaction>();
	}
	
	public static TransactionRecords getIntant() {
		return TransactionRecords.transactionRecordInstance;
	}
	
	public boolean createGroup(int groupId) {
		TransactionData td = new CreateGroupData(groupId);
		this.transactions.put(this.transactionId++, new Transaction(TransactionType.CreateGroup, td));
		return this.groupRecords.createGroup(groupId);
	}
	
	public boolean createUser(int userId) {
		TransactionData td = new CreateUserData(userId);
		this.transactions.put(this.transactionId++, new Transaction(TransactionType.CreateUser, td));
		return this.userRecords.createUser(userId);
	}
	
	public boolean addTransaction(int giverId, int takenId, double amount) {
		TransactionData td = new ADDTransactionData(giverId, takenId, amount);
		this.transactions.put(this.transactionId++, new Transaction(TransactionType.ADDTransaction, td));
		return this.userRecords.addTransaction(giverId, takenId, amount);
	}
	
	public boolean addGroupTransaction(int groupId, int userId, double amount) {
		TransactionData td = new ADDGroupTransactionData(groupId, userId, amount);
		this.transactions.put(this.transactionId++, new Transaction(TransactionType.ADDGroupTransaction, td));
		return this.groupRecords.addGroupTransaction(groupId, userId, amount);
	}
	
	public Map<Integer, Double> showUserDetail(int userId) {
		TransactionData td = new ShowUserDetailData(userId);
		this.transactions.put(this.transactionId++, new Transaction(TransactionType.ShowUserDetail, td));
		return this.userRecords.getUserRecord(userId);
	}
	
	public Set<Integer> showGroupDetail(int groupId) {
		TransactionData td = new ShowGroupDetailData(groupId);
		this.transactions.put(this.transactionId++, new Transaction(TransactionType.ShowGroupDetail, td));
		return this.groupRecords.getGroupDetail(groupId);
	}
	
	public boolean addUserToGroup(int groupId, int userId) {
		TransactionData td = new AddUserToGroupData(groupId, userId);
		this.transactions.put(this.transactionId++, new Transaction(TransactionType.AddUserToGroup, td));
		return this.groupRecords.addUserToGroup(groupId, userId);
	}
}

enum TransactionType {
	CreateGroup,
	CreateUser,
	ADDTransaction,
	ADDGroupTransaction,
	ShowUserDetail,
	ShowGroupDetail,
	AddUserToGroup
}

class Transaction {
	TransactionType transactionType;
	TransactionData transactionData;
	
	Transaction(TransactionType tt, TransactionData td) {
		this.transactionData = td;
		this.transactionType = tt;
	}
}

interface TransactionData {
}

class CreateGroupData implements TransactionData {
	int groupId;
	public CreateGroupData(int groupId) {
		super();
		this.groupId = groupId;
	}

	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}

class CreateUserData implements TransactionData {
	int userId;
	
	public CreateUserData(int userId) {
		super();
		this.userId = userId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}

class ADDTransactionData implements TransactionData {
	int giverUserId;
	int takenUserId;
	double amount;
	
	
	public ADDTransactionData(int giverUserId, int takenUserId, double amount) {
		super();
		this.giverUserId = giverUserId;
		this.takenUserId = takenUserId;
		this.amount = amount;
	}
	public int getGiverUserId() {
		return giverUserId;
	}
	public void setGiverUserId(int giverUserId) {
		this.giverUserId = giverUserId;
	}
	public int getTakenUserId() {
		return takenUserId;
	}
	public void setTakenUserId(int takenUserId) {
		this.takenUserId = takenUserId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}

class ADDGroupTransactionData implements TransactionData {
	int groupId;
	int userId;
	double amount;
	
	
	public ADDGroupTransactionData(int groupId, int userId, double amount) {
		super();
		this.groupId = groupId;
		this.userId = userId;
		this.amount = amount;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}

class ShowUserDetailData implements TransactionData {
	int userId;

	public ShowUserDetailData(int userId) {
		super();
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}

class ShowGroupDetailData implements TransactionData {
	int groupId;

	
	public ShowGroupDetailData(int groupId) {
		super();
		this.groupId = groupId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}

class AddUserToGroupData implements TransactionData {
	int groupId;
	int userId;
	
	public AddUserToGroupData(int groupId, int userId) {
		super();
		this.groupId = groupId;
		this.userId = userId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}