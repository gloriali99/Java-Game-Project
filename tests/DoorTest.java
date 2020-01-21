package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;

class DoorTest {

	@Test
	void test() {
		
		// setting up dungeon with two keys and two doors
		Dungeon dungeon = new Dungeon(3, 3);	
		Player player = new Player(dungeon,0,0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);

		Key k1 = new Key(dungeon,1,0,0);
		Key k2 = new Key(dungeon,1,1,1);
		dungeon.addEntity(k1);
		dungeon.addEntity(k2);
		
		Door d1 = new Door(2,0,1);
		Door d2 = new Door(2,2,0);
		dungeon.addEntity(d1);
		dungeon.addEntity(d2);
		
		//collecting 1st key
		player.moveRight();
		assertTrue(player.hasKey());
		assertTrue(player.checkKey(0));
		
		//cannot open door as key and door ids do not match
		player.moveRight();
		assertFalse(d1.isOpen());
		assertTrue(player.hasKey());
		
		//cannot collect 2nd key as player already has a key
		player.moveDown();
		assertFalse(player.checkKey(1));

		// player opens the correct door
		player.moveDown();
		player.moveRight();
		assertTrue(d2.isOpen());
		assertFalse(player.hasKey());
		
		// player collects the 2nd key
		player.moveUp();
		assertTrue(player.hasKey());
		assertTrue(player.checkKey(1));
		
		// player opens the 2nd door
		player.moveUp();
		player.moveRight();
		assertTrue(d1.isOpen());
		assertFalse(player.hasKey());
		
		
	}

}
