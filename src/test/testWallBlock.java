package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Point;
import unsw.dungeon.Wall;

public class testWallBlock {

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
}