package unsw.dungeon.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Exit;
import unsw.dungeon.Goal;
import unsw.dungeon.Player;
import unsw.dungeon.Switch;
import unsw.dungeon.Treasure;
import unsw.dungeon.andGoal;
import unsw.dungeon.boulderGoal;
import unsw.dungeon.exitGoal;
import unsw.dungeon.orGoal;
import unsw.dungeon.treasureGoal;

class GoalTest {

	@Test
	void TreasureANDBoulder() {
		
		//setting up dungeon with goals BOULDERS AND TREASURE
		Dungeon dungeon = new Dungeon(3, 2);	
		
		Goal g1 = new boulderGoal(dungeon);
		Goal g2 = new treasureGoal(dungeon);
		andGoal allGoals = new andGoal();
		allGoals.addGoal(g1);
		allGoals.addGoal(g2);
		dungeon.setGoals(allGoals);

		// adding entities
		dungeon.addEntity(new Treasure(dungeon,0,1));
		dungeon.addEntity(new Boulder(dungeon, 1,0));
		dungeon.addEntity(new Switch(2,0));
		
		
		Player player = new Player(dungeon,0,0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
		// collect treasure, goals should not yet be complete
		player.moveDown();
		assertFalse(dungeon.checkGoals(false));

		// move boulder onto switch, goals should now be complete
		player.moveUp();
		player.moveRight();
		assertTrue(dungeon.checkGoals(false));

	}
	
	@Test
	void TreasureORBoulder () {
		
		// setting up dungeon with goals BOULDER OR TREASURE
		Dungeon dungeon = new Dungeon(3, 2);	
		Goal g1 = new boulderGoal(dungeon);
		Goal g2 = new treasureGoal(dungeon);
		orGoal allGoals = new orGoal();
		allGoals.addGoal(g1);
		allGoals.addGoal(g2);
		dungeon.setGoals(allGoals);

		// adding entities
		dungeon.addEntity(new Treasure(dungeon,0,1));
		dungeon.addEntity(new Boulder(dungeon, 1,0));
		dungeon.addEntity(new Switch(2,0));
		
		Player player = new Player(dungeon,0,0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
		// move down to collect treasure, goals should be complete
		player.moveDown();
		assertTrue(dungeon.checkGoals(false));

	}
	
	@Test
	void ExitANDTreasure() {
		
		// setting up dungeon with goals EXIT AND TREASURE
		Dungeon dungeon = new Dungeon(2, 2);	
		Goal g1 = new exitGoal(dungeon);
		Goal g2 = new treasureGoal(dungeon);
		andGoal allGoals = new andGoal();
		allGoals.addGoal(g1);
		allGoals.addGoal(g2);
		dungeon.setGoals(allGoals);

		// adding entities
		dungeon.addEntity(new Exit(0,1));
		dungeon.addEntity(new Treasure(dungeon,1,0));
		
		Player player = new Player(dungeon,0,0);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		
		//move down to exit, should fail as treasure is not collected
		player.moveDown();
		assertFalse(dungeon.checkGoals(true));

		// collect treasure then exit successfully
		player.moveRight();
		player.moveDown();
		player.moveLeft();
		assertTrue(dungeon.checkGoals(true));
	}
	
	

}
