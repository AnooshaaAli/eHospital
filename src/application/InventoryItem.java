package application;

public class InventoryItem {
	
	private int invID;
	private int quantity; 
	private String name;
	private String category;
	
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
	
}