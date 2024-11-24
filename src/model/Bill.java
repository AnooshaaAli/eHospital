package model;

import application.DBHandler;
import javafx.collections.ObservableList;

public class Bill {
	private int billId;
	private boolean status;
	private String paymentType;
	private double amount;
	private DBHandler dbhandler;
	
	Bill() 
	{
		dbhandler = new DBHandler();
	}
	
	public Bill(int id, double amt)
	{
		this.billId=id;
		this.amount=amt;
	}
	
	public Bill(int bid, double payment, String paymentType, boolean status) {
	    this.billId = bid;
	    this.amount = payment;
	    this.paymentType = paymentType;
	    this.status = status;
	    dbhandler = new DBHandler();
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

	public DBHandler getDbhandler() {
		return dbhandler;
	}

	public void setDbhandler(DBHandler dbhandler) {
		this.dbhandler = dbhandler;
	}
	
	// ---------------------------------------------- GET BILLS LIST OF A PATIENT ------------------------------------------------ //
	
	public ObservableList<Bill> loadBills(int recId)
	{
		ObservableList<Bill> list = dbhandler.loadBills(recId);
		return list;
	}
	
	// ---------------------------------------------- GET BILLS LIST OF A PATIENT ------------------------------------------------ //
	
	public ObservableList<Bill> getBills(int pid)
	{
		ObservableList<Bill> list = dbhandler.getBills(pid);
		return list;
	}
	
	// ------------------------------------------------------ ADD BILL ---------------------------------------------------------- //

	public void addBill(int recId, double payment, String type) {
		dbhandler.addBill(recId, payment, type);
	}

	// ------------------------------------------------------ UPDATE BILL STATUS ------------------------------------------------- //
	
	public boolean payByCash(int billId)
	{
		boolean check= dbhandler.payByCash(billId);
		return check;
	}
	
	public boolean payByCard(int billId)
	{
		boolean check= dbhandler.payByCard(billId);
		return check;
	}
}