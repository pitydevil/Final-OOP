package oopFinalPkg.Model;

public class Item {

	
	public String getExpired() {
		return expired;
	}
	public void setExpired(String expired) {
		this.expired = expired;
	}

	public String getType() {
		return type;
	}
	public void setType(String tyoe) {
		this.type = tyoe;
	}
	protected String expired;
	protected int idItem;
	protected int quantity;
	protected int harga;
	protected String lokasi;
	
	public Item(String expired, int idItem, int quantity, int harga, String lokasi, String nama, String type) {
		super();
		this.expired = expired;
		this.idItem = idItem;
		this.quantity = quantity;
		this.harga = harga;
		this.lokasi = lokasi;
		this.nama = nama;
		this.type = type;
	}
	public String getLokasi() {
		return lokasi;
	}
	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}
	public int getHarga() {
		return harga;
	}
	public void setHarga(int harga) {
		this.harga = harga;
	}
	protected String nama;
	protected String type;

	public int getIdItem() {
		return idItem;
	}
	public void setIdItem(int idItem) {
		this.idItem = idItem;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
}
