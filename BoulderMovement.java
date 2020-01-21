package unsw.dungeon;

public class BoulderMovement implements Moveable{
	
	private Dungeon dungeon; 
	private Boulder boulder; 
	
	
	public BoulderMovement(Dungeon dungeon, Boulder boulder){
		this.dungeon = dungeon; 
		this.boulder = boulder; 
	}

	 // checks if boulder can be moved
    public boolean checkMoveable(int x1, int y1, int x2, int y2) {
    	
    	//checks edge cases, doors, walls, exits
    	if (dungeon.checkUnmoveable(x2, y2)) {
    		return false;
    	}
    	
    	//checks if there are corresponding boulders
    	if (dungeon.checkBoulder(x2, y2)) {
    		return false;
    	}
    	
    	// checks it there is an entity that is not a switch
    	int i = 0;
    	for (Entity e: dungeon.getEntityList()) {
    		if (e.getX() == x2 && e.getY() == y2) {
    			if (!(e instanceof Switch)) {
    				i = 1;
    			}
    		}
    	}
    	if (i != 1 ) {
    		move(x1, y1, x2, y2);
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    
    // moves the boulder
    public void move(int x1, int y1, int x2, int y2) {
    	Entity e1 = dungeon.getEntity(x1, y1);
		if (e1 instanceof Switch) {
			boulder.setOn_switch(false); 
			Switch s = (Switch) e1;
			s.switch_off();
			dungeon.setText("Switching off");
		} 
		
		Entity e2 = dungeon.getEntity(x2, y2);
		if (e2 instanceof Switch) {
			boulder.setOn_switch(true);
			Switch s = (Switch) e2;
			s.switch_on();
			dungeon.setText("Switched on");
			if(dungeon.checkSwitches()) {
				dungeon.setText("All switched on!");
    			if (dungeon.checkGoals(false)) {
    				dungeon.setText("DUNGEON COMPLETE");
        		}
    		}
			
		}
    	boulder.y().set(y2);
		boulder.x().set(x2);
    }
	
}
