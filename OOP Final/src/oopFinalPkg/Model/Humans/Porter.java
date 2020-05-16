package oopFinalPkg.Model.Humans;

import java.util.ArrayList;

import oopFinalPkg.Model.Transaction;

public class Porter extends Admin implements PersonHelper{

	

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
	public void relocate(String nama) {
		// TODO Auto-generated method stub
		
	}
		
	  private String[] buahLokasi = {"Shelf A", "Shelf B", "Shelf X", "Shelf D", "Shelf I"};
	  private String[] cannedLokasi = {"Shelf C", "Shelf K", "Shelf E", "Shelf F", "Shelf L"};
	  private String[] chillerLokasi = {"Chiller A", "Chiller B", "Chiller C", "Chiller D"};
		
	@Override
	public String determineLocation(String category) {
		String location = "";
		if(category.equals("Vegetable")) {
			location = chillerLokasi[random.nextInt(2)] + random.nextInt(50);
		}else if (category.equals("Canned")) {
			location = cannedLokasi[random.nextInt(5)] + random.nextInt(100);
		}else if (category.equals("Frozen")) {
			location = chillerLokasi[random.nextInt(2)+2] + random.nextInt(50);
		}else if (category.equals("Fruit")) {
			location = buahLokasi[random.nextInt(5)] + random.nextInt(100);
		}
		return location;
	}

}
