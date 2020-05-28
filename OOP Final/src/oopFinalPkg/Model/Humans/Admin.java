package oopFinalPkg.Model.Humans;

import java.util.ArrayList;
import oopFinalPkg.Model.Transaction;

public class Admin extends Person implements PersonHelper{

	
	public Admin(String nama, int id, String password, boolean accountType, int numberOfTransaction, String joinDate,
			String lastAccessDate) {
		super(nama, id, password, accountType, numberOfTransaction, joinDate, lastAccessDate);
		// TODO Auto-generated constructor stub
	}
	
	// random.nextInt hanya untuk membuat nilai shelf acak, jadi jangan terlalu dipikirin,
	// untuk chillerLokasi, untuk frozen hanya bisa mengambil chiller c - d, sedangkan canned bisa dari a -b
    private String[] buahLokasi = {"Shelf A", "Shelf B", "Shelf X", "Shelf D", "Shelf I"};
    private String[] cannedLokasi = {"Shelf C", "Shelf K", "Shelf E", "Shelf F", "Shelf L"};
	private String[] chillerLokasi = {"Chiller A", "Chiller B", "Chiller C", "Chiller D"};
    

	public String determineLocation(String category) {
		String location = "";
		if(category.equals("Vegetable")) {
			location = chillerLokasi[random.nextInt(2)] + random.nextInt(100);
		}else if (category.equals("Canned")) {
			location = cannedLokasi[random.nextInt(5)] + random.nextInt(100);
		}else if (category.equals("Frozen")) {
			location = chillerLokasi[random.nextInt(2)+2] + random.nextInt(100);
		}else if (category.equals("Fruit")) {
			location = buahLokasi[random.nextInt(5)] + random.nextInt(100);
		}
		return location;
	}

	@Override
	public void printTransactionList(String nama, ArrayList<Transaction> transactionArray) {
		for (Transaction currTransactionItem : transactionArray) {
			currTransactionItem.getTransactionInfo();
		}
	}
	
	
}
