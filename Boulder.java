package unsw.dungeon;

public class Boulder extends Entity {

	private Dungeon dungeon;
	private boolean on_switch;
	private MoveContext strategy; 

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Boulder(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.strategy = new MoveContext(new BoulderMovement(this.dungeon,this)); 
    }
    

    public MoveContext getStrategy() {
		return strategy;
	}

	// checks if boulder is on switch
	public boolean switched_on(){
		return on_switch;
	}

	public void setOn_switch(boolean on_switch){
		this.on_switch = on_switch;
	}


}



// checks if boulder can be moved
/* public boolean checkMoveable(int x1, int y1, int x2, int y2) {
	
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
		this.on_switch = false;
		Switch s = (Switch) e1;
		s.switch_off();
		System.out.println("switching off");
	} 
	
	Entity e2 = dungeon.getEntity(x2, y2);
	if (e2 instanceof Switch) {
		this.on_switch = true;
		Switch s = (Switch) e2;
		s.switch_on();
		System.out.println("switched on");
		if(dungeon.checkSwitches()) {
			System.out.println("all switched on");
			if (dungeon.checkGoals(false)) {
    			System.out.println("DUNGEON COMPLETE");
    		}
		}
		
	}
	y().set(y2);
	x().set(x2);
}*/

