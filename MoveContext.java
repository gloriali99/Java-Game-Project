package unsw.dungeon;

public class MoveContext {
	
	private Moveable move; 
	
	public MoveContext(Moveable move){
		this.move = move; 
	}
	
	public boolean executeMoveable(int x1, int y1, int x2, int y2) {
		return move.checkMoveable(x1, y1, x2, y2); 
	}
	
	public void executeMove(int x1, int y1, int x2, int y2) {
		move.move(x1, y1, x2, y2);
	}
	
}
