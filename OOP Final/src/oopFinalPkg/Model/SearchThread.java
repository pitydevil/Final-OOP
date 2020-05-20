package oopFinalPkg.Model;

import java.util.List;

public class SearchThread extends Thread {

	private List<Item> array;
	private int searchID;
	private Item itemFound = null;
	
	public SearchThread(List<Item> array, int searchID) {
		this.array = array;
		this.searchID = searchID;
		start();
	}

	@Override
	public void run() {
		for (Item item : array) {
			if (item.getIdItem() == searchID) {
				setItemFound(item);
				break;
			}
		}
	}

	public Item getItemFound() {
		return itemFound;
	}

	public void setItemFound(Item itemFound) {
		this.itemFound = itemFound;
	}
	
	

	

}
