package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Point;

public class testCollectable {

	@Test
    void testPlayerPicpKeys(){
		System.out.println("Testing Player move left -----");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        
        Key k = new Key(dungeon, 0, 1, 0);
        dungeon.addEntity(k);
        Key k1 = new Key(dungeon, 1, 2, 0);
        dungeon.addEntity(k1);
        
        p.moveRight();
        

        assertEquals(p.getBackPack().contains(k1), false);
        assertEquals(p.getBackPack().contains(k), true);
        
        p.moveRight();
        assertEquals(p.getBackPack().contains(k1), false);
        

    }
}
