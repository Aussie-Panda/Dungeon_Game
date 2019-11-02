package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Boulder;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Player;
import unsw.dungeon.Point;
import unsw.dungeon.Switch;
import unsw.dungeon.Wall;

public class testBoulder {

	@Test
    void testBoulderMove(){
		System.out.println("Testing player moves boulder");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        
        Boulder b = new Boulder(0, 1);
        dungeon.addEntity(b);


        p.moveDown();
        Point correct = new Point(0,2);

        assertEquals(b.getPt().equals(correct), true);
	}
	
	@Test
    void testBoulderTriggerSwitch(){
		System.out.println("Testing player trigger switch");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        
        Boulder b = new Boulder(0, 1);
        dungeon.addEntity(b);
        
        Switch s = new Switch (0,2);
        dungeon.addEntity(s);


        p.moveDown();
        Point correct = new Point(0,2);

        assertEquals(b.getPt().equals(correct), true);
        assertEquals(s.getState()==1, true);
        
        p.moveDown();
        assertEquals(s.getState()==0, true);
        
	}
	
	
	
}
