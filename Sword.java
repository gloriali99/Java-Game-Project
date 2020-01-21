package unsw.dungeon;

public class Sword extends Entity {
	
	private int attacks; 
	private Dungeon dungeon;
	//private Player player = dungeon.getPlayer(); 	
	
	public Sword(Dungeon dungeon, int x, int y) {
		super(x,y);
		this.attacks = 5;
		this.dungeon = dungeon;
	}

	public int getAttacks() {
		return attacks;
	}

	public void setAttacks(int attacks) {
		this.attacks = attacks;
	}
	
	public void decrementAttacks() {
		this.attacks = this.attacks - 1;
		dungeon.setText(getAttacks()+" attacks left");
	}
	
	public void collectMe (Player p) {
		p.addCollecteable(this); 
		dungeon.removeEntity(this);
		dungeon.setText("Collected sword");
	}
	
}