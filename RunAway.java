package unsw.dungeon;

public class RunAway implements enemyAction {

	private Enemy e;
	private MoveContext strategy;
	private int pX;
	private int pY;
	
	public RunAway(Enemy e, MoveContext s, int pX, int pY) {
		this.e = e;
		this.strategy = s;
		this.pX = pX;
		this.pY = pY;
	}

	@Override
	public void action() {
		if(pX - this.e.getX() == 0 ){ 
			if(pY < this.e.getY()){
				if (strategy.executeMoveable(e.getX(), e.getY()+1, 0, 0)){
					if(strategy.executeMoveable(e.getX(), e.getY()+1, 0, 0)) {
					strategy.executeMove(e.getX(), e.getY()+2, 0, 0);
					}
				}
			}else if(pY > this.e.getY()) {
				if (strategy.executeMoveable(e.getX(), e.getY()-1, 0, 0)){
					if(strategy.executeMoveable(e.getX(), e.getY()-1, 0, 0)) {
						strategy.executeMove(e.getX(), e.getY()-1, 0, 0);
						}
				}
			}
		}else if(pY - this.e.getY() == 0 ){ 
			if(pX < this.e.getX()){
				if (strategy.executeMoveable(e.getX()+1, e.getY(), 0, 0)){
						strategy.executeMove(e.getX()+1, e.getY(), 0, 0);
				}
			}else if(pX > this.e.getX()) {
				if (strategy.executeMoveable(e.getX()-1, e.getY(), 0, 0)){
						strategy.executeMove(e.getX()-1, e.getY(), 0, 0);
				}
			}
		}else {
			if(pY - this.e.getY() > 0 ){
				if(strategy.executeMoveable(e.getX(), e.getY()-1,0,0)) {
					strategy.executeMove(e.getX(), e.getY()-1,0,0);
				}else if(pX < this.e.getX()){
					if(strategy.executeMoveable(e.getX()+1, e.getY(),0,0)) {
						strategy.executeMove(e.getX()+1, e.getY(),0,0); 
					}
				}else if(pX > this.e.getX()) {
					if(strategy.executeMoveable(e.getX()-1, e.getY(),0,0)){
						strategy.executeMove(e.getX()-1, e.getY(),0,0); 
					}
				}
			//strategy.executeMove NORTH
			}else if(pY - this.e.getY() < 0 ){
				if(strategy.executeMoveable(e.getX(), e.getY()+1,0,0)) {
					strategy.executeMove(e.getX(), e.getY()+1, 0, 0);
				}else if(pX < this.e.getX()) {
					if(strategy.executeMoveable(e.getX()+1, e.getY(), 0, 0)){
						strategy.executeMove(e.getX()+1, e.getY(), 0, 0);
					}
				}else if(pX > this.e.getX()) {
					if (strategy.executeMoveable(e.getX()-1, e.getY(), 0, 0)){
						strategy.executeMove(e.getX()-1, e.getY(), 0, 0);
					}
				}
				
			}else if(pX - this.e.getX() > 0 ){
				//strategy.executeMove WEST 
				if(strategy.executeMoveable(e.getX()-1, e.getY(),0,0)){
					strategy.executeMove(e.getX()-1, e.getY(), 0, 0);
				}else if(pY < this.e.getY()){
					if (strategy.executeMoveable(e.getX(), e.getY()+1, 0, 0)){
						strategy.executeMove(e.getX(), e.getY()+1, 0, 0);
					}
				}else if(pY > this.e.getY()){
					if(strategy.executeMoveable(e.getX(), e.getY()-1, 0, 0)){
						strategy.executeMove(e.getX(), e.getY()-1, 0, 0);
					}
				}
			}else if (pX - this.e.getX() < 0 ){
				//strategy.executeMove EAST
				if(strategy.executeMoveable(e.getX()+1, e.getY(),0,0)) {
					strategy.executeMove(e.getX()+1, e.getY(), 0, 0);
				}else if(pY < this.e.getY()){
					if (strategy.executeMoveable(e.getX(), e.getY()+1, 0, 0)) {
						strategy.executeMove(e.getX(), e.getY()+1, 0, 0);
					}	
				}else if(pY < this.e.getY()){
					if(strategy.executeMoveable(e.getX(), e.getY()-1, 0, 0)) {
						strategy.executeMove(e.getX(), e.getY()-1, 0, 0);
					}
				}
			} 
		}
		
	}

}
