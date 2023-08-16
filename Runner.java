package inventoryManagementSystem;

public class Runner {

	public static void main(String[] args) {		
		demo();
		
		ViewProductsGUI VPG = new ViewProductsGUI();
		VPG.setVisible(true);
	}
	
	//Provides a handful of locations, products, and units for testing
	private static void demo() {
		InventoryManager IM = InventoryManager.getInstance();
		
		IM.createLocation("A1");
		IM.createLocation("A2");
		IM.createLocation("G1");
		
		IM.createProduct("XB950N1", "Sony XB950N1 Bluetooth Headphones", 149);
		IM.createProduct("WH-1000XM3", "Sony WH-1000XM3 Bluetooth Noise Cancelling Headphones", 199);
		IM.createProduct("M1PRO16", "16\" M1 Pro Macbook", 1399);
		
		new Unit(IM.getProduct("XB950N1"), IM.getLocation("A1"), ItemConditions.NEW, 140);
		new Unit(IM.getProduct("XB950N1"), IM.getLocation("A1"), ItemConditions.NEW, 140);
		new Unit(IM.getProduct("XB950N1"), IM.getLocation("A1"), ItemConditions.NEW, 159);
		new Unit(IM.getProduct("XB950N1"), IM.getLocation("A1"), ItemConditions.NEW, 149);
		
		new Unit(IM.getProduct("M1PRO16"), IM.getLocation("A2"), ItemConditions.REFURBISHED, 1199, "1000990122");
	}

}
