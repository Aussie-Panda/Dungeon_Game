package test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


class TestDoorAndKey {

    @Test
    void testPlayerWithoutKey() {
        int idD = 0;
        int idK = 0;
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);

        Door d1 = new Door(dungeon, ++idD, 1,1);
        dungeon.addEntity(d1);
        Key k1 = new Key(dungeon, ++idK, 1, 2 );
        dungeon.addEntity(k1);

        // player not picking up the key
        p.moveDown();
        p.moveRight();

        assertEquals(p.getPt(), new Point(0, 1));

    }


    @Test
    void testPlayerWithKey() {
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);

        Door d1 = new Door(dungeon, 0, 1,1);
        dungeon.addEntity(d1);
        Key k1 = new Key(dungeon, 0, 1, 2 );
        dungeon.addEntity(k1);

        // player goes to pick up key
        p.moveDown();
        p.moveDown();
        p.moveRight();
        p.moveUp();

        // player should consume key and pass the door
        assertEquals(p.getPt(), new Point(1, 0));
        assertFalse(p.hasKey());

        // player pass door again without key
        p.moveDown();
        assertEquals(p.getPt(), new Point(1, 2));
    }
}
