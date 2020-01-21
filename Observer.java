package unsw.dungeon;

public interface Observer {
	//public void attack();
	//public void runAway(); 
	public void update(int x, int y, Boolean test); 
	public enemyAction getAction();
	
}
