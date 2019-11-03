package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Enemy;
import unsw.dungeon.Player;
import unsw.dungeon.Point;
import unsw.dungeon.Portal;
import unsw.dungeon.Treassure;

public class TestPortal {

	
	@Test
    void testPlayerEnterPortal(){
		System.out.println("Testing Player enter portal -----");
        Dungeon dungeon = new Dungeon(3, 3);
        Player p = new Player(dungeon, 1, 1);
        dungeon.addEntity(p);
        Portal one = new Portal(dungeon, 0, true, 1, 0);
        dungeon.addEntity(one);
        Portal two = new Portal(dungeon, 0, false, 2, 1);
        dungeon.addEntity(two);
        p.moveUp();
        Point correct = new Point(2,0);
        assertEquals(p.getPt().equals(correct), true);
        
        p.moveDown();
        assertEquals(p.getPt().equals(correct), true);

    }
	
	@Test
	void testBoulderCanNotEnterPortal(){
		System.out.println("Testing Boulder can't enter portal -----");
        Dungeon dungeon = new Dungeon(3, 3);
        Player p = new Player(dungeon, 1, 2);
        dungeon.addEntity(p);
        
        Boulder b = new Boulder(1, 1);
        dungeon.addEntity(b);
        
        Portal one = new Portal(dungeon, 0, true, 1, 0);
        dungeon.addEntity(one);
        Portal two = new Portal(dungeon, 0, false, 2, 1);
        dungeon.addEntity(two);
        p.moveUp();
        Point correct = new Point(1,1);
        assertEquals(b.getPt().equals(correct), true);


    }
	
	@Test
    void testEnemyEnterPortal(){
		System.out.println("Testing Enemy enter portal -----");
        Dungeon dungeon = new Dungeon(3, 3);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        Portal one = new Portal(dungeon, 0, true, 1, 0);
        dungeon.addEntity(one);
        Portal two = new Portal(dungeon, 0, false, 2, 1);
        dungeon.addEntity(two);
        
        Enemy e = new Enemy(dungeon, 1, 1);
        dungeon.addEntity(e);
        e.moveUp();
        
        Point correct = new Point(2,0);
        assertEquals(e.getPt().equals(correct), true);
        
        e.moveDown();
        assertEquals(e.getPt().equals(correct), true);

    }
	
	@Test
    void testPlayerEnterPortalAndCollect(){
		System.out.println("Testing Player enter portal -----");
        Dungeon dungeon = new Dungeon(3, 3);
        Player p = new Player(dungeon, 1, 1);
        dungeon.addEntity(p);
        Portal one = new Portal(dungeon, 0, true, 1, 0);
        dungeon.addEntity(one);
        Portal two = new Portal(dungeon, 0, false, 2, 1);
        dungeon.addEntity(two);
        
        Treassure t = new Treassure (dungeon, 2, 0);
        dungeon.addEntity(t);
        
        p.moveUp();
        
        Point correct = new Point(2,0);
        assertEquals(p.getPt().equals(correct), true);
        assertEquals(p.getTreasure() == 1, true);
        
        
    }
	
}
