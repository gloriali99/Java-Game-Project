package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity {
	
	private int ID; 
	//private boolean open; 
	private BooleanProperty open;
	
	public Door(int x, int y, int id) {
		super(x,y);
		this.ID = id; 
		this.open = new SimpleBooleanProperty(false);
	}
	

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isOpen() {
		return open.getValue();
	}
	
	public void open() {
		this.open.set(true);
	}

	
	public BooleanProperty openProperty() {
		return open;
	}
	
	
}
