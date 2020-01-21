package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Entity;
import unsw.dungeon.Player;
import unsw.dungeon.Treasure;
import unsw.dungeon.treasureGoal;

class TreasureTest {

	@Test
	void test() {
		
		// setting up dungeon with treasure to collect
		Dungeon dungeon = new Dungeon(2, 2);	
		dungeon.setGoals(new treasureGoal(dungeon));
		
		Player player = new Player(dungeon,0,0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
		// adding treasure
		for (int i = 0; i < 2; i++) {
			Treasure t = new Treasure(dungeon,1,i);
			dungeon.addEntity(t);
		}
		assertEquals(dungeon.getNumTreasure(),2);
		assertEquals(dungeon.getNumTreasure(),2);
		
		//collecting first treasure
		//checking to make sure not all treasure has been collected
		player.moveRight();
		assertFalse(dungeon.checkTreasure());
		assertFalse(dungeon.checkGoals(false));
		
		// collecting second treasure
		// checking that all treasure has been collected and in the hands of the player
		player.moveDown();
		assertTrue(dungeon.checkTreasure());
		assertTrue(dungeon.checkGoals(false));
		for (Entity e: player.getCollectables()) {
			assertTrue(e instanceof Treasure);
		}
		for (Entity e: dungeon.getEntityList()) {
			assertFalse(e instanceof Treasure);
		}
	}

}
