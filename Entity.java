package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity{

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    protected IntegerProperty x, y;
    protected BooleanProperty onGrid;
	//private IntegerProperty y;

    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        this.onGrid = new SimpleBooleanProperty(true);    
    }
    

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }
    
    
    public BooleanProperty cellProperty() {
		return onGrid;
	}
    
    public void add() {
    	onGrid.setValue(true);
    }
    
    public void remove() {
    	onGrid.setValue(false);
    }

    public int getY() {
        return y().get();
    }

    public int getX() {
        return x().get();
    }
    
    public void collectMe(Player p) {
    	
    }


}
