package inventoryManagementSystem;

import java.util.HashMap;
import java.util.ArrayList;


//Uses singleton design pattern. There is one inventory manager that tracks products and locations
public class InventoryManager {
	private HashMap<String, Product> products;
	private HashMap<String, Location> locations;
	
	private static InventoryManager singletonInstance = null;
	
	private InventoryManager() {
		products = new HashMap<String, Product>();
		locations = new HashMap<String, Location>();
	}
	
	public static InventoryManager getInstance() {
		if(singletonInstance == null) singletonInstance = new InventoryManager();
		
		return singletonInstance;
	}
	
	public Product getProduct(String sku) {
		if(products.containsKey(sku.toUpperCase())) return products.get(sku.toUpperCase());
		else return null;
	}
	
	public Product[] getProducts() {
		return products.values().toArray(Product[]::new);
	}
	
	//filter is checked against product SKU and product name
	public Product[] getProducts(String filter) {
		filter = filter.toUpperCase();
		ArrayList<Product> filteredProducts = new ArrayList<Product>();
		
		for(Product p : products.values()) {
			if(p.getSku().contains(filter) || p.getName().toUpperCase().contains(filter)) filteredProducts.add(p);
		}
		
		return filteredProducts.toArray(Product[]::new);
	}
	
	public Location getLocation(String code) {
		if(locations.containsKey(code)) return locations.get(code);
		else return null;
	}
	
	public Location[] getAllLocations() {
		return locations.values().toArray(Location[]::new);
	}
	
	//returns true if product was created, false if product was not (i.e., SKU is not unique)
	public boolean createProduct(String sku, String name, float cost) {
		sku = sku.toUpperCase();
		
		if(products.containsKey(sku)) return false;
		
		Product p = new Product(sku, name, cost);
		products.put(sku, p);
		return true;
	}
	
	//returns true if location was created, false if location was not (i.e., Code is not unique)
	public boolean createLocation(String code) {
		code = code.toUpperCase();
		
		if(locations.containsKey(code)) return false;
		
		Location l = new Location(code);
		locations.put(code, l);
		return true;
	}
	
	public void removeProduct(String sku) {
		sku = sku.toUpperCase();
		Product p = products.get(sku);
		
		for(Unit u : p.getUnitsInStock()) {
			u.destroy();
		}
		
		products.remove(sku);
	}
	
	public void removeLocation(String code) {
		locations.remove(code);
	}
	
	public void moveUnit(Unit u, Location l) {
		u.getLocation().removeUnit(u.getId());
		u.setLocation(l);
		l.addUnit(u);
	}
}
