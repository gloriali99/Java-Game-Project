package unsw.dungeon;

public class EnemyMovement implements Moveable {
	
	private Dungeon dungeon; 
	private Enemy enemy; 
	
	public EnemyMovement(Dungeon dungeon, Enemy enemy) {
		this.dungeon = dungeon; 
		this.enemy = enemy; 
	}

	// Enemy checks if space can be moved into 
	public boolean checkMoveable(int x1, int y1, int x2, int y2){
		
		// checking if its a wall, exit or door
		if(dungeon.checkUnmoveable(x1,y1)){
			return false; 
		}
		// checking if space is a boulder
		if(dungeon.checkBoulder(x1, y1)) {
			return false; 
		}
		return true; 	
	}

	//moves to the square and checks if there is an entity there, portal will move the enemy 
	public void move(int x1, int y1, int x2, int y2) {
		Entity e = dungeon.getEntity(x1, y1); 
		if(e instanceof Portal){
			Portal p = ((Portal) e).findOtherPortal(dungeon); 
    		enemy.y().set(p.getY());
    		enemy.x().set(p.getX());
		}else { 
			enemy.x().set(x1);
			enemy.y().set(y1);
		}
	}
}
