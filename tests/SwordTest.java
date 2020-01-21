package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;
import unsw.dungeon.Sword;

class SwordTest {
	
	
	@Test
	void testKillingWithSword() {
		Dungeon dungeon = new Dungeon (4,5); 
		Player player = new Player(dungeon,1,2); 
		Sword sword = new Sword(dungeon,2,3); 
		Enemy enemy = new Enemy(dungeon,3,4); 
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		dungeon.addEntity(sword);
		dungeon.addEntity(enemy);
		
		player.moveRight();
		player.moveRight();
		assertFalse(dungeon.checkEnemy()); 
		
		
	}
	 @Test 
	void testSwordDecrementation(){
		Dungeon dungeon = new Dungeon (4,5); 
		Player player = new Player(dungeon,1,3); 
		Sword sword = new Sword(dungeon,2,3); 
		Enemy enemy = new Enemy(dungeon,2,4); 
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		dungeon.addEntity(sword);
		dungeon.addEntity(enemy);
		
		//player should have picked up sword; 
		//enemy would have moved towards player and been killed 
		player.moveRight();
		assertEquals(sword.getAttacks(),5);
	}
	
	
}
