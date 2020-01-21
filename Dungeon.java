/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

	private int width, height;
    private List<Entity> entities;
    private Entity[][] unmoveable_array;
    private Player player; 
    private int numTreasure;
    private int numSwitches;
	private Goal goals;
	private int lifeTrigger = 0; 
	private StringProperty text;
	

	public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<Entity>();
        this.unmoveable_array = new Entity[width][height];
        this.numTreasure = 0;
        this.numSwitches = 0; 
        this.text = new SimpleStringProperty();
        this.initialiseHound();
  
    }
    
	public void setGoals(Goal goals) {
		this.goals = goals;
	}
	
	public Goal getGoals() {
		return this.goals;
	}
	
	public boolean checkGoals(boolean nextToExit) {
		return goals.goalComplete(nextToExit);
	}
	
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

	public int getLifeTrigger() {
		return lifeTrigger;
	}

	public void setLifeTrigger(int lifeTrigger) {
		this.lifeTrigger = lifeTrigger;
	}
	
	  // keeps track of the number of treasure in the dungeon
    public void addTreasure() {
    	numTreasure++;
    }
    
    // keeps track of the number of switches in the dungeon
    public void addSwitches() {
    	numSwitches++;
    }
       
    public int getNumTreasure() {
    	return numTreasure;
    }
    
    public int getNumSwitches() {
    	return numSwitches;
    }
    
    
    
    // checks if all the treasure in the dungeon are collected
    public boolean checkTreasure() {
    	if (numTreasure == player.getNumTreasure()) {
    		return true;
    	}
    	return false;
    }

    
    public void addEntity(Entity entity) {
        entities.add(entity);
        entity.add();
        if (entity instanceof Treasure) {
        	numTreasure++;
        } else if (entity instanceof Switch) {
        	numSwitches++;;
        } else if (entity instanceof Wall || entity instanceof Exit || entity instanceof Door) {
        	storeUnmoveable(entity);
        }
    }
    
    public List<Entity>  getEntityList() {
    	return this.entities;
    }
    
    public void removeEntity(Entity entity) {
    	entities.remove(entity);
    	entity.remove();
    }

	// gets the entity given x and y coordinates
    // returns null if there are no entities
    public Entity getEntity(int x, int y) {
    	for (Entity e: this.entities) {
    		if (e == null) {
    			break;
    		}
    		if (e.getX() == x && e.getY() == y) {
    			return e;
    		}
    	} 
    	return null;
    }
    
 
    // gets the entity given x and y coordinates
    // checks if an entity exists given x and y coordinates
    public boolean checkEntity(int x, int y) {
    	for (Entity e: this.entities) {
    		if (e == null) {
    			break;
    		}
    		if (e.getX() ==  x && e.getY() == y) {
    			return true;
    		}
    	} 
    	return false;
    }
    

    // gets the boulder given x and y coordinates
    public Boulder getBoulder(int x, int y) {
    	for (Entity e: this.entities) {
    		if (e == null) {
    			break;
    		}
    		if (e.getX() ==  x && e.getY() == y) {
    			if (e instanceof Boulder) {
    				return (Boulder) e;
    			}
    		}
    	} 
    	return null;
    }
    
    //checks if there is a boulder given x and y coordinastes
    public boolean checkBoulder(int x, int y) {
    	int i = 0;
    	for (Entity e: this.entities) {
    		if (e == null) {
    			break;
    		}
    		if (e.getX() ==  x && e.getY() == y) {
    			if (e instanceof Boulder) {
    				i = 1;
    				break;
    			}
    		}
    	} 
    	if (i == 1) {
    		return true;
    	}
    	return false;
    }
    
    // array that stores walls, doors and exits
    public void storeUnmoveable(Entity e) {
    	this.unmoveable_array[e.getX()][e.getY()] = e; 
    }
    
    // checks if there is a wall, door, exit or if player is on the edge, given x and y coordinates
    public boolean checkUnmoveable(int x, int y) {
    	if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
    		return true;
    	}
    	if (unmoveable_array[x][y] != null) {
    		return true;
    	}
    	return false;
    }
    
    // check if there is an enemy
	public boolean enemyExist(){
    	for(Entity e : this.entities){
    		if(e instanceof Enemy){
    			return true; 
    		}
    	}
    	return false; 
    }
   

    public boolean playerExist(){
		if(entities.contains(player)){
			return true; 
		}
		return false; 
	}
    
  

    // checks if all floor switches have boulders on them
    public boolean checkSwitches(){
    	int count = 0;
    	for (Entity e: entities) {
    		if (e == null) {
    			break;
    		}
    		if (e instanceof Switch) {
    			Switch s = (Switch) e;
    			if (s.onSwitch()) {
    				count++;
    			}	
    		}
    	}
    	if (count == numSwitches) {
    		return true;
    	}
    	return false;
    }
    
    public boolean checkEnemy(){
    	for(Entity e : entities){
    		if(e instanceof Enemy){
    			if(((Enemy) e).checkProximity() == true) {
    				return true; 
    			}
    		}
    	}
    	return false; 
    }
    
    public boolean checkHound(){
    	for(Entity e : entities){
    		if(e instanceof Hound){
    			if(((Hound) e).checkProximity() == true) {
    				return true; 
    			}
    		}
    	}
    	return false; 
    }
    
    
    
    public boolean checkLife(){
    	for(Entity e : entities){
	    	if(e instanceof Enemy){
	    			//enemy killed 
	    			if(((Enemy) e).checkSword() == true) {
	    				entities.remove(e);
	    				return true;
	    			}
	    		}
	    	}
    	return false; 
    }

	public StringProperty textProperty() {
		return text;
	}

	public void setText(String text) {
		this.text.setValue(text);
	}
    
    
	//initialise the hound
	public void initialiseHound(){
		for(Entity e : entities){
    		if(e instanceof Hound){
				((Hound) e).move();
    		}
		}
	}


}

