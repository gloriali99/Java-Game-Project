package unsw.dungeon;


public class oneGoal {
	
	private Dungeon dungeon;
	
	public oneGoal(Dungeon dungeon) {
		this.setDungeon(dungeon);
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}

}