package inventoryManagementSystem;

import java.util.HashMap;

public class Location {
	private String code;
	private HashMap<String, Unit> unitsInLocation;
	
	Location(String code){
		this.code = code;
		unitsInLocation = new HashMap<String, Unit>();
	}
	
	public String getCode() {
		return this.code;
	}
	
	public Unit getUnit(String id) {
		return unitsInLocation.get(id);
	}

	public Unit[] getUnitsInLocation(){
		return unitsInLocation.values().toArray(Unit[]::new);
	}
	
	//Authenticated = checked that the code is not already in use
	public void setAuthenticatedCode(String code) {
		this.code = code;
	}
	
	public void addUnit(Unit u) {
		this.unitsInLocation.put(u.getId(), u);
	}
	
	public void removeUnit(String id) {
		this.unitsInLocation.remove(id);
	}
	
	public String toString() {
		return this.code;
	}
}