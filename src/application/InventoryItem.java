package application;

import javafx.collections.ObservableList;

public class InventoryItem {
	
	private int invID;
	private int quantity; 
	private String name;
	private String category;
	private DBHandler db;
	InventoryItem()
	{
		db= new DBHandler();
	}
	InventoryItem(int id, int q, String n, String c)
	{
		this.invID=id;
		this.quantity=q;
		this.name=n;
		this.category=c;
	}
	public  ObservableList<InventoryItem> displayCurrentInventory()
	{
		
		ObservableList<InventoryItem> list= db.displayCurrentInventory();
		return list;
	}
	

	public boolean addInventoryItem(int amt, String type, String name)
	{
		boolean check = db.addInventoryItem(amt, type, name);
		return check;
	}
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getInvID() {
		return invID;
	}
	public void setInvID(int invID) {
		this.invID = invID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public ObservableList<String> loadItemNames()
	{
		ObservableList<String> list= db.loadItemNames();
		return list;
	}
	public boolean updateInventoryItem(int amt, String type, String name)
	{
		boolean check= db.updateInventoryItem( amt,  type, name);
		return check;
	}
	
}