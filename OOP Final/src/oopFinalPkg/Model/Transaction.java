package oopFinalPkg.Model;

public class Transaction {
	
	protected int transactionID;
	protected String transUsername;
	protected boolean accountType;
	protected String transDescription;
	protected String transDate;
	
	public Transaction(int transactionID, String transUsername, boolean accountType,
			String transDescription, String transDate) {
		super();
		this.transactionID = transactionID;
		this.transUsername = transUsername;
		this.accountType = accountType;
		this.transDescription = transDescription;
		this.transDate = transDate;
	}
	
	//Untuk ngeprint log transactionnya
	public void getTransactionInfo() {
		System.out.print("[TransactionID: " + transactionID + "]" + transDescription + " by " + transUsername);
		if (accountType == true) 
			System.out.print("(Admin)");
		else
			System.out.print("(Porter)");
		System.out.print(" on " + transDate + "\n");
	}

	//Getter dan setter utk seluruh atribut/fields nya
	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public String getTransUsername() {
		return transUsername;
	}

	public void setTransUsername(String transUsername) {
		this.transUsername = transUsername;
	}

	public boolean isAccountType() {
		return accountType;
	}

	public void setAccountType(boolean accountType) {
		this.accountType = accountType;
	}

	public String getTransDescription() {
		return transDescription;
	}

	public void setTransDescription(String transDescription) {
		this.transDescription = transDescription;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	
	
	
	

}
