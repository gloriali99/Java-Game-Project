package unsw.dungeon;

public class enemyGoal extends oneGoal implements Goal {

	private Dungeon dungeon;
	
	public enemyGoal(Dungeon dungeon) {
		super(dungeon);
		this.dungeon = dungeon;
	}

	@Override
	public boolean goalComplete(boolean nextToExit) {
		return !dungeon.enemyExist();
	}
	
	public String goalText() {
		return "Enemy ";
	}
	


}
