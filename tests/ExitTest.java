package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Exit;
import unsw.dungeon.Player;
import unsw.dungeon.exitGoal;

class ExitTest {

	@Test
	void test() {
		
		//setting up dungeon with an exit
		Dungeon dungeon = new Dungeon(2, 2);	
		dungeon.setGoals(new exitGoal(dungeon));
		Player player = new Player(dungeon,0,0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		dungeon.addEntity(new Exit(1,0));
		
		// moving to exit
		player.moveRight();
		assertTrue(dungeon.checkGoals(true));
		assertEquals(player.getX(),1);
		assertEquals(player.getY(),0);
	}

}
