package oopFinalPkg.Model;

import java.util.List;

public class SearchThread implements Runnable {

	private List<Item> array;
	private int searchID;
	private Item itemFound = null;
	private Thread objectThread;
	
	public SearchThread(List<Item> array, int searchID) {
		this.array = array;
		this.searchID = searchID;
		
		objectThread = new Thread(this);
		objectThread.start();
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
	
	public Thread getThread() {
		return objectThread;
	}

	public Item getItemFound() {
		return itemFound;
	}

	public void setItemFound(Item itemFound) {
		this.itemFound = itemFound;
	}
}
