package unsw.dungeon;


public class Enemy extends Entity implements Observer {
	
	private Dungeon dungeon; 
	private int pX;
	private int pY;
	private MoveContext strategy; 
	private enemyAction movement;

	public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.strategy = new MoveContext(new EnemyMovement(dungeon,this)); 
    }
	
	public void update(int x, int y, Boolean test) {
		this.pX = x; 
		this.pY = y; 
		if (test == true) {
			this.movement = new Attack(this, strategy, pX, pY);
		} else {
			this.movement = new RunAway(this, strategy, pX, pY);
		}
	}

	
	// check if player has sword, if yes kill the enemy 
	public boolean checkSword(){
		if(checkProximity() == true) {
			for(Entity e : dungeon.getPlayer().getCollectables()) {
				if(e instanceof Sword){
					//enemy dead - minus the attacks in sword.
					((Sword) e).decrementAttacks();
					dungeon.removeEntity(this);
					return true; 
				}
			}
		}
		return false; 
	}
	
	//check if the player and 
	public boolean checkProximity() {
		if(this.getX() == dungeon.getPlayer().getX() && this.getY() == dungeon.getPlayer().getY()) {
			return true; 
		}
		return false; 
	}
	
	public Enemy getEnemy(){
		return this;
	}

	public int getpX() {
		return pX;
	}

	public void setpX(int pX) {
		this.pX = pX;
	}

	public int getpY() {
		return pY;
	}

	public void setpY(int pY) {
		this.pY = pY;
	}
	
	public enemyAction getAction() {
		return movement;
	}
	
//	public void attack(){
//	
//	if(pX - this.getX() == 0 ){ 
//		if(pY < this.getY()){
//			if (strategy.executeMoveable(getX(), getY()-1, 0, 0)){
//				if(strategy.executeMoveable(getX(), getY()-1, 0, 0)) {
//					strategy.executeMove(getX(), getY()-1, 0, 0);
//				}
//			}
//		}else if(pY > this.getY()) {
//			if (strategy.executeMoveable(getX(), getY()+1, 0, 0)){
//				if(strategy.executeMoveable(getX(), getY()+1, 0, 0)) {
//					strategy.executeMove(getX(), getY()+1, 0, 0);
//					}
//			}
//		}
//	}else if(pY - this.getY() == 0 ){ 
//		if(pX < this.getX()){
//			if (strategy.executeMoveable(getX()-1, getY(), 0, 0)){
//				if(strategy.executeMoveable(getX()-1, getY(), 0, 0)) {
//					strategy.executeMove(getX()-1, getY(), 0, 0);
//				}
//			}
//		}else if(pX > this.getX()) {
//			if (strategy.executeMoveable(getX()+1, getY(), 0, 0)){
//				if(strategy.executeMoveable(getX()+1, getY(), 0, 0)) {
//					strategy.executeMove(getX()+1, getY(), 0, 0);
//				}
//			}
//		}
//	}else {
//		if(pY - this.getY() > 0 ){
//			if(strategy.executeMoveable(getX(), getY()+1,0,0)) {
//				strategy.executeMove(getX(), getY()+1,0,0);
//			}else if(pX < this.getX()){
//				if(strategy.executeMoveable(getX()-1, getY(),0,0)) {
//					strategy.executeMove(getX()-1, getY(),0,0); 
//				}
//			}else if(pX > this.getX()) {
//				if(strategy.executeMoveable(getX()+1, getY(),0,0)){
//					strategy.executeMove(getX()+1, getY(),0,0); 
//				}
//			}
//		//strategy.executeMove NORTH
//		}else if(pY - this.getY() < 0 ){
//			if(strategy.executeMoveable(getX(), getY()-1,0,0)) {
//				strategy.executeMove(getX(), getY()-1, 0, 0);
//			}else if(pX < this.getX()) {
//				if(strategy.executeMoveable(getX()-1, getY(), 0, 0)){
//					strategy.executeMove(getX()-1, getY(), 0, 0);
//				}
//			}else if(pX > this.getX()) {
//				if (strategy.executeMoveable(getX()+1, getY(), 0, 0)){
//					strategy.executeMove(getX()+1, getY(), 0, 0);
//				}
//			}
//			
//		}else if(pX - this.getX() > 0 ){
//			//strategy.executeMove WEST 
//			if(strategy.executeMoveable(getX()+1, getY(),0,0)){
//				strategy.executeMove(getX()+1, getY(), 0, 0);
//			}else if(pY < this.getY()){
//				if (strategy.executeMoveable(getX(), getY()-1, 0, 0)){
//					strategy.executeMove(getX(), getY()-1, 0, 0);
//				}
//			}else if(pY > this.getY()){
//				if(strategy.executeMoveable(getX(), getY()+1, 0, 0)){
//					strategy.executeMove(getX(), getY()+1, 0, 0);
//				}
//			}
//		}else if (pX - this.getX() < 0 ){
//			//strategy.executeMove EAST
//			if(strategy.executeMoveable(getX()-1, getY(),0,0)) {
//				strategy.executeMove(getX()-1, getY(), 0, 0);
//			}else if(pY < this.getY()){
//				if (strategy.executeMoveable(getX(), getY()-1, 0, 0)) {
//					strategy.executeMove(getX(), getY()-1, 0, 0);
//				}	
//			}else if(pY < this.getY()){
//				if(strategy.executeMoveable(getX(), getY()+1, 0, 0)) {
//					strategy.executeMove(getX(), getY()+1, 0, 0);
//				}
//			}
//		} 
//	}
//
//}
//
//public void runAway() {
//	if(pX - this.getX() == 0 ){ 
//		if(pY < this.getY()){
//			if (strategy.executeMoveable(getX(), getY()+1, 0, 0)){
//				if(strategy.executeMoveable(getX(), getY()+1, 0, 0)) {
//				strategy.executeMove(getX(), getY()+2, 0, 0);
//				}
//			}
//		}else if(pY > this.getY()) {
//			if (strategy.executeMoveable(getX(), getY()-1, 0, 0)){
//				if(strategy.executeMoveable(getX(), getY()-1, 0, 0)) {
//					strategy.executeMove(getX(), getY()-1, 0, 0);
//					}
//			}
//		}
//	}else if(pY - this.getY() == 0 ){ 
//		if(pX < this.getX()){
//			if (strategy.executeMoveable(getX()+1, getY(), 0, 0)){
//					strategy.executeMove(getX()+1, getY(), 0, 0);
//			}
//		}else if(pX > this.getX()) {
//			if (strategy.executeMoveable(getX()-1, getY(), 0, 0)){
//					strategy.executeMove(getX()-1, getY(), 0, 0);
//			}
//		}
//	}else {
//		if(pY - this.getY() > 0 ){
//			if(strategy.executeMoveable(getX(), getY()-1,0,0)) {
//				strategy.executeMove(getX(), getY()-1,0,0);
//			}else if(pX < this.getX()){
//				if(strategy.executeMoveable(getX()+1, getY(),0,0)) {
//					strategy.executeMove(getX()+1, getY(),0,0); 
//				}
//			}else if(pX > this.getX()) {
//				if(strategy.executeMoveable(getX()-1, getY(),0,0)){
//					strategy.executeMove(getX()-1, getY(),0,0); 
//				}
//			}
//		//strategy.executeMove NORTH
//		}else if(pY - this.getY() < 0 ){
//			if(strategy.executeMoveable(getX(), getY()+1,0,0)) {
//				strategy.executeMove(getX(), getY()+1, 0, 0);
//			}else if(pX < this.getX()) {
//				if(strategy.executeMoveable(getX()+1, getY(), 0, 0)){
//					strategy.executeMove(getX()+1, getY(), 0, 0);
//				}
//			}else if(pX > this.getX()) {
//				if (strategy.executeMoveable(getX()-1, getY(), 0, 0)){
//					strategy.executeMove(getX()-1, getY(), 0, 0);
//				}
//			}
//			
//		}else if(pX - this.getX() > 0 ){
//			//strategy.executeMove WEST 
//			if(strategy.executeMoveable(getX()-1, getY(),0,0)){
//				strategy.executeMove(getX()-1, getY(), 0, 0);
//			}else if(pY < this.getY()){
//				if (strategy.executeMoveable(getX(), getY()+1, 0, 0)){
//					strategy.executeMove(getX(), getY()+1, 0, 0);
//				}
//			}else if(pY > this.getY()){
//				if(strategy.executeMoveable(getX(), getY()-1, 0, 0)){
//					strategy.executeMove(getX(), getY()-1, 0, 0);
//				}
//			}
//		}else if (pX - this.getX() < 0 ){
//			//strategy.executeMove EAST
//			if(strategy.executeMoveable(getX()+1, getY(),0,0)) {
//				strategy.executeMove(getX()+1, getY(), 0, 0);
//			}else if(pY < this.getY()){
//				if (strategy.executeMoveable(getX(), getY()+1, 0, 0)) {
//					strategy.executeMove(getX(), getY()+1, 0, 0);
//				}	
//			}else if(pY < this.getY()){
//				if(strategy.executeMoveable(getX(), getY()-1, 0, 0)) {
//					strategy.executeMove(getX(), getY()-1, 0, 0);
//				}
//			}
//		} 
//	}
//}
	
	
}





