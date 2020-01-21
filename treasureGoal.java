package unsw.dungeon;

public class treasureGoal extends oneGoal implements Goal {

	private Dungeon dungeon;
	
	public treasureGoal(Dungeon dungeon) {
		super(dungeon);
		this.dungeon = dungeon;
	}

	@Override
	public boolean goalComplete(boolean nextToExit) {
		return dungeon.checkTreasure();
	}
	
	
	public String goalText() {
		return "Enemy ";
	}
	
}
