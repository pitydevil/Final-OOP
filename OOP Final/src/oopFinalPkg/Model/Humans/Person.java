package oopFinalPkg.Model.Humans;

import oopFinalPkg.Model.HelperItem;

public class Person extends HelperItem {
	
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAccountType() {
		return accountType;
	}
	public void setAccountType(boolean accountType) {
		this.accountType = accountType;
	}

	protected String nama;
	protected int id;
	protected String password;
	protected boolean accountType;
	protected int numberOfTransaction;
	protected String joinDate;
	protected String lastAccessDate;
	
	public Person(String nama, int id, String password, boolean accountType, int numberOfTransaction, String joinDate,
			String lastAccessDate) {
		super();
		this.nama = nama;
		this.id = id;
		this.password = password;
		this.accountType = accountType;
		this.numberOfTransaction = numberOfTransaction;
		this.joinDate = joinDate;
		this.lastAccessDate = lastAccessDate;
	}

	public int getNumberOfTransaction() {
		return numberOfTransaction;
	}

	public void setNumberOfTransaction(int numberOfTransaction) {
		this.numberOfTransaction = numberOfTransaction;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getLastAccessDate() {
		return lastAccessDate;
	}

	public void setLastAccessDate(String lastAccessDate) {
		this.lastAccessDate = lastAccessDate;
	}
	
}
