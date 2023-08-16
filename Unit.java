package inventoryManagementSystem;

import java.util.UUID;

class Unit{
	private String serialNumber;
	private final String _id;
	private float cost;
	private ItemConditions condition;
	private Location location;
	private Product product;
	
	Unit(Product product, Location location, ItemConditions condition, float cost){
		this._id = UUID.randomUUID().toString();
		this.location = location;
		this.product = product;
		this.cost = cost;
		this.condition = condition;
		
		this.serialNumber = "";
		
		product.addUnit(this);
		location.addUnit(this);
	}
	
	//If the optional serial number is provided
	Unit(Product product, Location location, ItemConditions condition, float cost,  String serialNumber){
		this(product, location, condition, cost);
		this.serialNumber = serialNumber;
	}
	
	public Product getProduct() {
		return this.product;
	}
	
	public String getSerialNumber() {
		return this.serialNumber;
	}
	
	public String getId() {
		return this._id;
	}
	
	public float getCost() {
		return this.cost;
	}
	
	public ItemConditions getCondition() {
		return this.condition;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	//Serial Numbers cannot be changed once set
	public void addSerialNumber(String serialNumber) {
		if(this.serialNumber.equals("")) this.serialNumber = serialNumber;
	}
	
	public void setCost(float cost) {
		if(cost > -1) this.cost = cost;
	}
	
	public void setCondition(ItemConditions condition) {
		this.condition = condition;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	//destroy = remove from record in Product HashMap and Location HashMap
	public void destroy() {
		this.product.removeUnit(this._id);
		this.location.removeUnit(this._id);
	}
	
	//TODO Fix alignment
	public String toString() {
		return String.format("%-20s", product.getSku())
		+ String.format("%-15s", condition) 
		+ String.format("%-10s", location)
		+ "$" + String.format("%-15s", String.valueOf(cost))
		+ "S/N: " + String.format("%-40s", serialNumber.equals("") ? "N/A" : serialNumber);
	}
}