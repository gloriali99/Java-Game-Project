package unsw.dungeon;

public class boulderGoal extends oneGoal implements Goal {

	private Dungeon dungeon;
	
	public boulderGoal(Dungeon dungeon) {
		super(dungeon);
		this.dungeon = dungeon;
	}

	@Override
	public boolean goalComplete(boolean nextToExit) {
		return dungeon.checkSwitches();
	}
	
	
	public String goalText() {
		return "Boulders ";
	}
	
	


}
