package oopFinalPkg.Model.Humans;

import java.util.ArrayList;

import oopFinalPkg.Model.Transaction;

public class Porter extends Person implements PersonHelper{

	

	public Porter(String nama, int id, String password, boolean accountType, int numberOfTransaction, String joinDate,
			String lastAccessDate) {
		super(nama, id, password, accountType, numberOfTransaction, joinDate, lastAccessDate);
		// TODO Auto-generated constructor stub
	}

	
	// Looping untuk seluruh objek transaction di dalam array
	// Untuk porter, hanya print yang usernamenya sama seperti si Porter
	@Override
	public void printTransactionList(String nama, ArrayList<Transaction> transactionArray) {
		for (Transaction currTransactionItem : transactionArray) {
			if (currTransactionItem.getTransUsername().equals(nama)) {
				currTransactionItem.getTransactionInfo();
			}
		}
	}


	@Override
	public boolean changePassword(String oldPass, String newPass) {
		boolean state = false;
		if(porterArray.get(crrnUserIndex).getPassword().equals(oldPass)==true) {
			porterArray.set(crrnUserIndex, new Porter(crrnUsername, crrnUserID, newPass, isCurrAdmin, porterArray.get(crrnUserIndex).getNumberOfTransaction(), porterArray.get(crrnUserIndex).getJoinDate(), 
					porterArray.get(crrnUserIndex).getLastAccessDate()));
			state = true;
		}else {
			state = false;
		}
		return false;
	}


	
}
