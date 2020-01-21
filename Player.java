package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

	
    private Dungeon dungeon;
    private PlayerLocation whereAt = new PlayerLocation();
    List<Entity> collectables; 
    private IntegerProperty numTreasure;
    private MoveContext strategy; 
    private IntegerProperty numCollected;
    private BooleanProperty alive;
	private int potionTrigger = 0;
	private BooleanProperty hasPotion;
    
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
 
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.collectables = new ArrayList<Entity>();
        this.numTreasure = new SimpleIntegerProperty(0);
        this.numCollected = new SimpleIntegerProperty(0);
        this.strategy = new MoveContext(new PlayerMovement(this.dungeon, this)); 
        this.alive = new SimpleBooleanProperty(true);
        this.hasPotion = new SimpleBooleanProperty(false);
    }
    
    public void moveRight() {
    	if (strategy.executeMoveable(getX()+1, getY(), getX()+2, getY())) {
    		strategy.executeMove(getX()+1, getY(), getX()+2, getY());
    	} else {
    		open(getX()+1, getY());
    	}
    }
    
    public void moveLeft() {
    	if (strategy.executeMoveable(getX()-1, getY(), getX()-2, getY())) {
    		strategy.executeMove(getX()-1, getY(), getX()-2, getY());
    	} else {
    		open(getX()-1, getY());
    	}	
    }
    
    public void moveUp() {
    	if (strategy.executeMoveable(getX(), getY()-1, getX(), getY()-2)) {
    		strategy.executeMove(getX(), getY()-1, getX(), getY()-2);
    	} else {
    		open(getX(), getY()-1);
    	}
    	
    }
    
    public void moveDown() {
    	if (strategy.executeMoveable(getX(), getY()+1, getX(), getY()+2)) {
    		strategy.executeMove(getX(), getY()+1, getX(), getY()+2);
    	} else {
    		open(getX(), getY()+1);
    	}
    	
    }
    

    public void removeCollecteable(Entity e) {
    	collectables.remove(e);
    }
    
    public void addCollecteable(Entity e) {
    	collectables.add(e);
    	addCollected();
    }
    
    // returns the number of treasure the player has
    public int getNumTreasure() {
    	return numTreasure.getValue();
    }
    
    public int getNumCollected() {
    	return numCollected.getValue();
    }

	public List<Entity> getCollectables() {
		return collectables;
	}

	public void setCollectables(List<Entity> collectables) {
		this.collectables = collectables;
	}

	// if there is a collectable entity, collect and add to entity list 
    public void collectEntity(int x, int y){
		for (Entity e: dungeon.getEntityList()) {
			if (e == null) {
				break;
			}
			if (e.getX() == x && e.getY() == y) {
				if (e instanceof Key || e instanceof Treasure || e instanceof Potion || e instanceof Sword) {
					e.collectMe(this);
					break;
				}

			}
		}
    }
    

    // opens door or exits dungeon
    public void open(int x, int y){
    	Entity e = dungeon.getEntity(x, y);
    	if (e instanceof Door) {
    		Door d = (Door) e;
    		if (!d.isOpen()) {
    			if (checkKey(d.getID())) {
        			d.open();
        			removeKey(d.getID());
        			dungeon.setText("Door opened, key discarded");
    			} else {
    				dungeon.setText("Door cannot be opened");
    			}
    		} 
    	} else if (e instanceof Exit) {
    		if (dungeon.checkGoals(true)) {
    			dungeon.setText("All goals completed, exiting game");
    			strategy.executeMove(x,y,0,0);
    			if (dungeon.checkGoals(true)) {
    				dungeon.setText("DUNGEON COMPLETE");
	    		}
    			//dungeon.removeEntity(this);
    		} else {
    			dungeon.setText("Not all goals completed, cannot exit dungeon");
    		}		
    	}
    	
    }
    

    
    // removes key from players collection
    public void removeKey(int id) {
    	for (Entity e: collectables) {
    		if (e == null ) {
    			break;
    		}
    		if (e instanceof Key) {
    			Key k = (Key) e;
    			if (k.getID() == id) {
    				removeCollecteable(e);
    				break;
    			}
    		}
    	}
    }
    
    // checks if player has key given its id
    public boolean checkKey(int id) {
    	for (Entity e: collectables) {
    		if (e == null ) {
    			break;
    		}
    		if (e instanceof Key) {
    			Key k = (Key) e;
    			if (k.getID() == id) {
    				return true;
    			}
    		}
    	}
    	return false;  	
    }
    
    //checks if player has key
    public boolean hasKey() {
    	for (Entity e: collectables) {
    		if (e == null ) {
    			break;
    		}
    		if (e instanceof Key) {
    			return true;
    		}
    	}
    	return false;  	
    }
    
    
    // PlayerLocation
    public  void  initialise(Enemy enemy){
    	whereAt.addObserver((Observer)enemy);
    	
    }
    
	public PlayerLocation getWhereAt() {
		return whereAt;
	}

	public void setWhereAt(PlayerLocation whereAt) {
		this.whereAt = whereAt;
	}
	
	public void addTreasure() {
		this.numTreasure.setValue(this.getNumTreasure()+1);
	}
	
	public void addCollected() {
		this.numCollected.setValue(this.getNumCollected()+1);
	}


	public void setY(int value){
		this.y().set(value);
	}
	
	public void setX(int value){
		this.x().set(value);
	}
	
	public IntegerProperty treasureProperty() {
		return numTreasure;
	}
	
	public IntegerProperty collectedProperty() {
		return numCollected;
	}
	
	public BooleanProperty aliveProperty() {
		return alive;
	}
	
	public void dead() {
		alive.setValue(false);
	}
	
    public void setPotionTrigger(int potionTrigger) {
		this.potionTrigger = potionTrigger;
		if (potionTrigger == 1) {
			this.hasPotion.setValue(true);
		} else {
			this.hasPotion.setValue(false);
		}
	}
    
    public BooleanProperty potionProperty() {
    	return hasPotion;
    }

    
    public int getPotionTrigger() {
		return potionTrigger;
	}

	
 }

