package inventoryManagementSystem;
import java.util.HashMap;

public class Product{
	private String sku, name;
	private float cost;
	private HashMap<String, Unit> unitsInStock;
	//TODO finding Units in a data file (HashMap and unique IDs already implemented)
	
	Product(String sku, String name, float cost){
		this.sku = sku;
		this.name = name;
		this.unitsInStock = new HashMap<String, Unit>();
		this.cost = cost;
	}
	
	public String getSku() {
		return this.sku;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getCost() {
		return this.cost;
	}
	
	public Unit getUnit(String id) {
		return unitsInStock.get(id);
	}
	
	public Unit[] getUnitsInStock(){
		return unitsInStock.values().toArray(Unit[]::new);
	}
	
	//Authenticated = checked that the SKU is not already in use
	public void setAuthenticatedSKU(String sku) {
		this.sku = sku;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	public void addUnit(Unit u) {
		this.unitsInStock.put(u.getId(), u);
	}
	
	public void removeUnit(String id) {
		this.unitsInStock.remove(id);
	}
	
	//TODO fix alignment
	public String toString() {
		return String.format("%-25s", sku) 
		+ "$" + String.format("%-15s", String.valueOf(cost))
		+ "In Stock:" + String.format("%-5s", String.valueOf(unitsInStock.size()))
		+ String.format("%-40s", name);
	}
}