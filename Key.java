package unsw.dungeon;

public class Key extends Entity {

	private int ID; 
	private Dungeon dungeon;
	
	public Key(Dungeon dungeon, int x, int y, int id){
		super(x,y); 
		this.ID = id; 
		this.dungeon = dungeon;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public void collectMe (Player p) {
		if (!p.hasKey()) {
			p.addCollecteable(this); 
    		dungeon.removeEntity(this);
    		dungeon.setText("Collected key");
		} else {
			dungeon.setText("Already have a key, cannot collect");
		}
	}

	
	
}