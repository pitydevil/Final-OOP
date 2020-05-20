package oopFinalPkg.Model;

import java.util.ArrayList;
import java.util.List;

public class Search {

	private Item itemFound = null;
	
	public Search(ArrayList<Item> array, int searchID) {	
		int size = array.size();
		
		SearchThread thread1 = new SearchThread(array.subList(0, size/2), searchID);
		SearchThread thread2 = new SearchThread(array.subList(size/2, size), searchID);
	
		try {
			thread1.join();
			thread2.join();
		}
		catch (InterruptedException e) {
			System.out.println("Search thread interrupted!");
		}

		//Jika tidak ketemu
		if (thread1.getItemFound() == null && thread2.getItemFound() == null) {
			setItemFound(null);
		}
		else {
			itemFound = (thread1.getItemFound() != null) ? thread1.getItemFound() : thread2.getItemFound(); 
		}
		
	}

	public Item getItemFound() {
		return itemFound;
	}

	public void setItemFound(Item itemFound) {
		this.itemFound = itemFound;
	}
		

}
