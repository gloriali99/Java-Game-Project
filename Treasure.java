package unsw.dungeon;

public class Treasure extends Entity{

	private boolean collected;
	private Dungeon dungeon;
	
	public Treasure(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
		
	}

	public boolean isCollected() {
		return collected;
	}

	public void collect() {
		this.collected = true;
	}
	
	public void collectMe (Player p) {
		p.addTreasure();
		p.addCollecteable(this); 
		dungeon.removeEntity(this);
		dungeon.setText("Collected treasure");
		if(dungeon.checkTreasure()) {
			dungeon.setText("All treasure collected!");
		}
		if (dungeon.checkGoals(false)) {
			dungeon.setText("DUNGEON COMPLETE");
		}
	}


}