package inventoryManagementSystem;

//enum for the three potential conditions an item can have, 
//as well as finding the condition based on text input
public enum ItemConditions{NEW, USED, REFURBISHED;
	public static ItemConditions fromString(String s) {
		s = s.toUpperCase();
	    for(ItemConditions condition:values())
	         if (condition.name().equals(s)) 
	        	 return condition;      
	    return ItemConditions.NEW;
	}
}