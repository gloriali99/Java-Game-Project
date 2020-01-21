package unsw.dungeon;


public class Potion extends Entity {
	
	private Dungeon dungeon;
//	private Player player; 
//	int counter = 5; 
	
	public Potion(Dungeon dungeon,int x, int y){
		super(x,y); 
		this.dungeon = dungeon; 
		//this.player = dungeon.getPlayer();
		
	}
	
//	//invoking potion 
//	public void invincible(){
//		// creating a timer; 
//		player.getWhereAt().notifyObserversRun(); ;
//		if(counter == 0) {
//			dungeon.setPotionTrigger(0);
//		}	
//	}
//
//	//invoking potion 
//	public void countDown(){
//		counter = counter - 1; 
//	}
//	
//
//	public void setCounter(int counter) {
//		this.counter = counter;
//	}
//	
//	public int returnCount(){
//		return this.counter;
//	}
	
	public void collectMe (Player p) {
		p.addCollecteable(this);
		dungeon.removeEntity(this);
		if(dungeon.enemyExist()) {
			p.setPotionTrigger(1);
		}
		dungeon.setText("Collected potion");
	}

		
	
}
		

