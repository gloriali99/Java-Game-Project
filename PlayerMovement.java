package unsw.dungeon;

import java.util.Timer;
import java.util.TimerTask;

public class PlayerMovement implements Moveable{
	
	private Dungeon dungeon;
	private Player player;
	
	public PlayerMovement(Dungeon dungeon, Player player){
		this.dungeon = dungeon; 
		this.player = player;
	}

	  // checks if player can move 
    public boolean checkMoveable(int x1, int y1, int x2, int y2) {
    	
    	//checks edge cases, doors, walls, exits
    	if (dungeon.checkUnmoveable(x1, y1)) {
    		return false;
    	} 
    	
    	//checks if there is a boulder and if it can be moved
    	if (dungeon.checkBoulder(x1, y1)) {
    		Boulder b = dungeon.getBoulder(x1, y1);
    		return b.getStrategy().executeMoveable(x1, y1, x2, y2);
    	}
    	return true;
    } 
    
    
    // moving the player
    public void move(int x1, int y1, int x2, int y2) {
    	dungeon.initialiseHound();
    	// push boulder
    	Boulder b = dungeon.getBoulder(x1, y1);
    	if (b != null) {
    		b.getStrategy().executeMove(x1, y1, x2, y2);
    	}
    	// move to other portal
    	Entity e = dungeon.getEntity(x1,y1);
    	if (e instanceof Portal) {
    		Portal p = ((Portal) e).findOtherPortal(dungeon); 
    		player.setY(p.getY());
    		player.setX(p.getX());
    	}else{
    		player.setX(x1);;
    		player.setY(y1);
    		player.collectEntity(x1,y1);
    	}
    	

    	if(this.dungeon.enemyExist() && player.getPotionTrigger() != 1){
    		player.getWhereAt().locationChanged(true,x1,y1);
    	}else if(player.getPotionTrigger() == 1){
    		player.getWhereAt().locationChanged(false,x1,y1);
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				public void run() {
					player.setPotionTrigger(0);
		        }
			};
			timer.schedule(task, 5000);
			
		}
    	
    	// fix this as well
    	if(dungeon.checkEnemy() == true){
    		if(dungeon.checkLife() == true) {
    			int attack = 0;
    			for(Entity j : player.getCollectables()){
    				if(j instanceof Sword) {
    					Sword s = (Sword)j;
    					attack = s.getAttacks();
    				}
        		}
    			dungeon.setText("Enemy dead, " + attack + " attacks left");
    			if (dungeon.checkGoals(false)) {
    				dungeon.setText("DUNGEON COMPLETE");
	    		}
    		}else{
    			dungeon.setLifeTrigger(1);
    			player.dead();
    			dungeon.setText("Player dead, restart or return to main menu");
    		}
    	}
    	
    	if(dungeon.checkHound() == true) {
    		dungeon.setLifeTrigger(1);
			player.dead();
			dungeon.setText("Player dead, restart or return to main menu");
    	}
    	
    	
    
    }

	
}
