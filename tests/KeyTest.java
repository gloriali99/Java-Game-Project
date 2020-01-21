package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

class KeyTest {

	@Test
	void test() {
		
		// setting up dungeon with two keys
		Dungeon dungeon = new Dungeon(2, 2);	
		Player player = new Player(dungeon,0,0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
		// adding entities
		Key k1 = new Key(dungeon,1,0,0);
		Key k2 = new Key(dungeon,1,1,1);
		dungeon.addEntity(k1);
		dungeon.addEntity(k2);
		
		//collecting first key
		player.moveRight();
		assertTrue(player.hasKey());
		assertTrue(player.checkKey(0));
		
		// attempt to collect 2nd key, should fail as player can only collect one key
		player.moveDown();
		assertTrue(player.checkKey(0));
		assertFalse(player.checkKey(1));
	}

}
