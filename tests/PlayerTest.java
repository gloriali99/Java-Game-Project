package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Door;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Wall;

class PlayerTest {

	@Test
	void MovePlayer() {
		
		// setting up dungeon with walls, boulders and door
		Dungeon dungeon = new Dungeon(4, 4);
		for (int i = 0; i < 4; i++) {
			Wall wall = new Wall(0,i);
			dungeon.addEntity(wall);
		}
		for (int i = 0; i < 4; i++) {
			Wall wall = new Wall(3,i);
			dungeon.addEntity(wall);
		}
		
		dungeon.addEntity(new Door(1,0,0));
		
		dungeon.addEntity(new Boulder(dungeon, 2,0));
		
		dungeon.addEntity(new Wall(2,3));
		
		dungeon.addEntity(new Wall(1,3));
		
		Player player = new Player(dungeon, 1, 1);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		
		// moving player to each edge of dungeon and checking it doesn't go past a wall, boulder or unopened door
		player.moveRight();
		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);
		
		player.moveDown();
		player.moveDown();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 2);
		
		player.moveLeft();
		player.moveLeft();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 2);
		
		player.moveUp();
		player.moveUp();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 1);
		
		
	}
	
	@Test
	void EdgeCase() {
		
		// setting up dungeon with no entities on the edges
		Dungeon dungeon = new Dungeon(2, 2);	
		Player player = new Player(dungeon,0,0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
		player.moveRight();
		player.moveRight();
		player.moveDown();
		player.moveDown();
		player.moveLeft();
		player.moveLeft();
		player.moveUp();
		player.moveUp();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);
	}

}