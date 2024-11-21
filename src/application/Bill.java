package application;

import javafx.collections.ObservableList;

public class Bill {
	private int billId;
	private boolean status;
	private String paymentType;
	private double amount;
	private DBHandler db;
	
	Bill()
	{
		db= new DBHandler();
	}
	Bill(int id, double amt)
	{
		this.billId=id;
		this.amount=amt;
	}
	public ObservableList<Bill> loadBills(int id)
	{
		ObservableList<Bill> bill= db.loadBills(id);
		return bill;
	}
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public DBHandler getDb() {
		return db;
	}
	public void setDb(DBHandler db) {
		this.db = db;
	}

}
