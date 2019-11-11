package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import unsw.dungeon.Dungeon;
import unsw.dungeon.Key;
import unsw.dungeon.Player;
import unsw.dungeon.Point;
import unsw.dungeon.Potion;
import unsw.dungeon.Treassure;

public class TestCollectable {

	@Test
    void testPlayerPickKeys(){
		System.out.println("Testing Player pick keys -----");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        
        Key k = new Key(dungeon, 0, 1, 0);
        dungeon.addEntity(k);
        Key k1 = new Key(dungeon, 1, 2, 0);
        dungeon.addEntity(k1);
        
        p.moveRight();
        Point correct = new Point (1,0);
        assertEquals(p.getPt().equals(correct), true);
        assertEquals(p.getBackPack().contains(k1), false);
        assertEquals(p.getBackPack().contains(k), true);
        
        p.moveRight();
        //fail becuz already has one key
        assertEquals(p.getBackPack().contains(k1), false);
        
    }
	
	@Test
	void testPlayerPicpKeysAfterConsumeKeys(){
		System.out.println("Testing Player pick keys consume key pick"
				+ "another key -----");
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
        p.consumeKey(0);
        p.moveRight();

        assertEquals(p.getBackPack().contains(k1), true);

    }
	
	@Test
    void testPlayerPickTreasures(){
		System.out.println("Testing Player pick Treasures -----");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        
        Treassure t = new Treassure(dungeon, 1, 0);
        dungeon.addEntity(t);
        Treassure t1 = new Treassure(dungeon, 2, 0);
        dungeon.addEntity(t1);
        
        p.moveRight();
        assertEquals(p.getTreasure() == 1, true);
        p.moveRight();
        assertEquals(p.getTreasure() == 2, true);

    }
	
	@Test
    void testPlayerPickSword(){
		System.out.println("Testing Player pick Swords -----");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        
        Treassure t = new Treassure(dungeon, 1, 0);
        dungeon.addEntity(t);
        Treassure t1 = new Treassure(dungeon, 2, 0);
        dungeon.addEntity(t1);
        
        p.moveRight();
        assertEquals(p.getTreasure() == 1, true);
        p.moveRight();
        assertEquals(p.getTreasure() == 2, true);

    }
	
	@Test
    void testPlayerPickPotion(){
		System.out.println("Testing Player pick potion -----");
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        
        Potion po = new Potion(1, 0);
        dungeon.addEntity(po);
        p.moveRight();
        assertEquals(p.getState() == "normal", true);


    }
	
	
	
}
