package unsw.dungeon.tests;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;

class EnemyTest {

	@Test
	void testCheckEnemyExists(){

		Dungeon dungeon = new Dungeon(8, 8);	
		Player player = new Player(dungeon,4,4);
		Enemy enemy = new Enemy(dungeon, 1, 3);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		dungeon.addEntity(enemy);
		dungeon.setPlayer(player);
		
		assertTrue(dungeon.enemyExist()); 
	}
	
	
	
	@Test
	void testMovementUpdate(){
		
		Dungeon dungeon = new Dungeon(8, 8);	
		Player player = new Player(dungeon,4,4);
		Enemy enemy = new Enemy(dungeon, 1, 3);
		dungeon.setPlayer(player);
		dungeon.addEntity(player);
		dungeon.addEntity(enemy);
		player.initialise(enemy);
		dungeon.setPlayer(player);
		
		//check if enemy exists 
		//check if player is updating enemy on their location
		player.moveRight();
		assertEquals(enemy.getpX(),player.getX());
		player.moveLeft();
		assertEquals(enemy.getpX(), player.getX()); 
		player.moveUp();
		assertEquals(enemy.getpY(),player.getY()); 
		player.moveDown();
		assertEquals(enemy.getpY(), player.getY()); 
		
	}
	
	
}
