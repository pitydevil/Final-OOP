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
}
