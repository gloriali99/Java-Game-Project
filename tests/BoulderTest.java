package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Switch;
import unsw.dungeon.Treasure;
import unsw.dungeon.Wall;
import unsw.dungeon.boulderGoal;

class BoulderTest {
	
	@Test
	void MoveBoulder() {
		
		//setting up dungeon with boulders
		Dungeon dungeon = new Dungeon(3, 4);
		dungeon.addEntity(new Boulder(dungeon, 2,1));
		dungeon.addEntity(new Wall(1,2));
		dungeon.addEntity(new Treasure(dungeon,0,3));
		
		Boulder b1 = new Boulder(dungeon, 1,0);
		dungeon.addEntity(b1);
		
		Boulder b2 = new Boulder(dungeon, 0,2);
		dungeon.addEntity(b2);
		
		Boulder b3 = new Boulder(dungeon, 1,1);
		dungeon.addEntity(b3);
		
		Player player = new Player(dungeon, 0, 0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
		//player moves boulder over an empty spot
		player.moveRight();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(b1.getX(), 2);
		assertEquals(b1.getY(), 0);
		
		//checking edge case
		player.moveRight();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(b1.getX(), 2);
		assertEquals(b1.getY(), 0);
		
		//player cannot move boulder past a wall
		player.moveDown();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
		assertEquals(b3.getX(), 1);
		assertEquals(b3.getY(), 1);
		
		// player cannot move boulder over an entity/another boulder
		player.moveLeft();
		player.moveDown();
		player.moveRight();
		player.moveDown();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 1);
		assertEquals(b2.getX(), 0);
		assertEquals(b2.getY(), 2);
		
	}

	@Test
	void MoveBoulderOntoSwitch() {
		
		//setting up dungeon with boulders
		Dungeon dungeon = new Dungeon(6, 3);
		dungeon.setGoals(new boulderGoal(dungeon));
		
		Boulder b1 = new Boulder(dungeon, 4,2);
		dungeon.addEntity(b1);
		
		Boulder b2 = new Boulder(dungeon, 3,1);
		dungeon.addEntity(b2);
		
		// Adding treasure onto a switch
		Switch s1 = new Switch(5,2);
		dungeon.addEntity(s1);
		dungeon.addEntity(new Treasure(dungeon,5,2));
		
		// Adding empty switch
		Switch s2 = new Switch(3,0);
		dungeon.addEntity(s2);
		
		Player player = new Player(dungeon, 3, 2);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
		
		// player should not be able to push boulder b1 over a switch with an entity on it
		player.moveRight();
		assertEquals(player.getX(), 3);
		assertEquals(player.getY(), 2);
		assertEquals(b1.getX(), 4);
		assertEquals(b1.getY(), 2);
		assertEquals(s1.onSwitch(), false);
		
		// player moves boulder b2 onto switch
		player.moveUp();
		assertEquals(player.getX(), 3);
		assertEquals(player.getY(), 1);
		assertEquals(b2.getX(), 3);
		assertEquals(b2.getY(), 0);
		assertEquals(s2.onSwitch(), true);
		assertFalse(dungeon.checkGoals(false));
		
		// player collects treasure off from switch
		player.moveRight();
		player.moveRight();
		player.moveDown();
		assertEquals(player.getX(), 5);
		assertEquals(player.getY(), 2);
		assertTrue(dungeon.checkTreasure());


		// players moves boulder b1 onto now empty switch
		player.moveUp();
		player.moveLeft();
		player.moveLeft();
		player.moveDown();
		player.moveRight();
		assertEquals(player.getX(), 4);
		assertEquals(player.getY(), 2);
		assertEquals(b1.getX(), 5);
		assertEquals(b1.getY(), 2);
		assertEquals(s1.onSwitch(), true);
		assertTrue(dungeon.checkSwitches());
		assertTrue(dungeon.checkGoals(false));
					
	}
	
	

}
