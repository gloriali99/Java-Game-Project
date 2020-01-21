package unsw.dungeon;

public class exitGoal extends oneGoal implements Goal {

	private Dungeon dungeon;
	
	public exitGoal(Dungeon dungeon) {
		super(dungeon);
		this.dungeon = dungeon;
	}

	@Override
	public boolean goalComplete(boolean nextToExit) {
		return nextToExit;
	}
	
	public String goalText() {
		return "Exit ";
	}
	
	
	

}
