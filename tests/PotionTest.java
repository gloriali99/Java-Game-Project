package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;
import unsw.dungeon.Potion;

class PotionTest {


	@Test
	void testTriggerPotion() {
		Dungeon dungeon = new Dungeon (4,5); 
		Player player = new Player(dungeon,2,3); 
		Potion potion = new Potion(dungeon,3,3);
		Enemy enemy = new Enemy(dungeon,1,2);
		dungeon.addEntity(enemy);
		dungeon.addEntity(player);
		player.initialise(enemy);
		dungeon.setPlayer(player);
		dungeon.addEntity(potion);
		
		player.moveRight(); 
		assertEquals(player.getPotionTrigger(),1);
		
	}

}
