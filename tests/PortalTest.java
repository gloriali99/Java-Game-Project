package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Portal;
import unsw.dungeon.Wall;

class PortalTest {

	@Test
	void test() {
		
		// setting up dungeon with portals
		Dungeon dungeon = new Dungeon(6, 6);
		dungeon.addEntity(new Portal(1,0,1));
		dungeon.addEntity(new Portal(5,3,1));
		dungeon.addEntity(new Wall(4,3));
			
		Player player = new Player(dungeon, 0, 0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
		// move to portal and check new x, y coords
		player.moveRight();
		assertEquals(player.getX(),5);
		assertEquals(player.getY(),3);
		
		// edge cases
		player.moveRight();
		player.moveLeft();
		player.moveDown();
		assertEquals(player.getX(),5);
		assertEquals(player.getY(),4);
		
		// move back to first portal and check new x, y coords
		player.moveUp();
		assertEquals(player.getX(),1);
		assertEquals(player.getY(),0);
		
		player.moveLeft();
		assertEquals(player.getX(),0);
		assertEquals(player.getY(),0);
	}

}
