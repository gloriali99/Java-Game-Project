package unsw.dungeon;


public interface Moveable{
	
	// can move into next slot 
	public boolean checkMoveable(int x1, int y1, int x2, int y2);
	// move the next position 
	public void move(int x1, int y1, int x2, int y2);
	
}