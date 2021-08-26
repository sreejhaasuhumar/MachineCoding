package entity;

public class FoodItems {
    Integer itemId;
    String itemName;
    Double itemPrice;
	public Integer getItemId() {
		return itemId;
	}
	public FoodItems(Integer itemId, String itemName, double i) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = i;
	}
	
	public FoodItems()
	{
		
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Double getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}
    
}
