package unsw.dungeon;

public class Hound extends Entity{
	
	private Dungeon dungeon; 
	private int moveLeft = 0; 
	
	public Hound(Dungeon dungeon, int x, int y){
		super(x,y); 
		this.dungeon = dungeon; 
	}

	public boolean checkProximity() {
		if(this.getX() == dungeon.getPlayer().getX() && this.getY() == dungeon.getPlayer().getY()) {
			return true; 
		}
		return false; 
	}
	
	public void move(){
		 
		if(moveLeft == 0 && this.getX() != dungeon.getWidth()) {
			this.x().set(getX()+1);
		}
		if(this.getX() == dungeon.getWidth()){
			moveLeft = 1; 
		}
		if (this.getX() == 0) {
			moveLeft = 0;
		}
		if(moveLeft == 1){
			this.x().set(getX()-1);
		}
		
		
	}
	
}
