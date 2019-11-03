package test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWallBlock {

	@Test
    void testWallBlockPlayer(){
		System.out.println("Testing Wall Block Player from different direction mult"
				+ "le times-----");
        Dungeon dungeon = new Dungeon(3, 3);
        Player p = new Player(dungeon, 1, 1);
        dungeon.addEntity(p);
        Wall w = new Wall(1,0);
        Wall w1 = new Wall(0,1);
        Wall w2 = new Wall(1,2);
        Wall w3 = new Wall(2,1);
        
        dungeon.addEntity(w);
        dungeon.addEntity(w1);
        dungeon.addEntity(w2);
        dungeon.addEntity(w3);
        
        p.moveLeft();
        p.moveRight();
        p.moveUp();
        p.moveDown();
        p.moveLeft();
        p.moveRight();
        p.moveUp();
        p.moveDown();
        Point correct = new Point(1,1);

        assertEquals(p.getPt().equals(correct), true);
	}
	
	
	@Test
    void testWallBlockBoulder(){
		System.out.println("Testing Wall Block Boulder");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        
        Boulder b = new Boulder(0, 1);
        dungeon.addEntity(b);


        Wall w1 = new Wall(0,2);        
        dungeon.addEntity(w1);

        p.moveDown();
        Point correct = new Point(0,1);

        assertEquals(b.getPt().equals(correct), true);
	}
	
	@Test
    void testWallBlockBoulderAfterMove(){
		System.out.println("Testing Wall Block Boulder after moving the boulder");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        
        Boulder b = new Boulder(0, 1);
        dungeon.addEntity(b);


        Wall w1 = new Wall(0,3);        
        dungeon.addEntity(w1);

        p.moveDown();
        p.moveDown();
        p.moveDown();
        Point correct = new Point(0,2);

        assertEquals(b.getPt().equals(correct), true);
	}
	
	
	
	@Test
    void testWallBlockEnemy(){
		System.out.println("Testing Wall Block Enemy");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        Enemy e = new Enemy(dungeon, 0, 1);
        dungeon.addEntity(p);
        dungeon.addEntity(e);


        Wall w1 = new Wall(0,2);        
        dungeon.addEntity(w1);

        e.moveDown();
        Point correct = new Point(0,1);

        assertEquals(e.getPt().equals(correct), true);
	}
	
	
	
}