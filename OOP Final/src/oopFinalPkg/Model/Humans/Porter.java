package oopFinalPkg.Model.Humans;

public class Porter extends Admin implements PersonHelper{

	

	public Porter(String nama, int id, String password, boolean accountType, int numberOfTransaction, String joinDate,
			String lastAccessDate) {
		super(nama, id, password, accountType, numberOfTransaction, joinDate, lastAccessDate);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void printTransactionList(String nama) {
		// TODO Auto-generated method stub
		
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
		if(category.equals("Vegatable")) {
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
