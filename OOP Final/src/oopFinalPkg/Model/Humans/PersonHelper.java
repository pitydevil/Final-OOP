package oopFinalPkg.Model.Humans;

import java.util.ArrayList;

import oopFinalPkg.Model.Transaction;

public interface PersonHelper {
	public abstract void printTransactionList(String nama, ArrayList<Transaction> transactionArray);
	public abstract void relocate(String nama);
	public abstract String determineLocation (String category);
}
