package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Point;

public class TestPlayerMovement {

	@Test
    void testPlayerMoveLeft(){
		System.out.println("Testing Player move left -----");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 1, 0);
        dungeon.addEntity(p);

        p.moveLeft();
        Point correct = new Point(0,0);

        assertEquals(p.getPt().equals(correct), true);


    }
	
	@Test
    void testPlayerMoveRight(){
		System.out.println("Testing Player move right -----");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);

        p.moveRight();
        Point correct = new Point(1,0);

        assertEquals(p.getPt().equals(correct), true);

    }
	
	@Test
    void testPlayerMoveUp(){
		System.out.println("Testing Player move up -----");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 1, 1);
        dungeon.addEntity(p);

        p.moveUp();
        Point correct = new Point(1,0);

        assertEquals(p.getPt().equals(correct), true);

    }
	
	@Test
    void testPlayerMoveDown(){
		System.out.println("Testing Player move left -----");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);

        p.moveDown();
        Point correct = new Point(0,1);

        assertEquals(p.getPt().equals(correct), true);

    }
	
	
	
}
