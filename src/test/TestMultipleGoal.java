package test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMultipleGoal {
    @Test
    void testAndGoalWithoutExitFail() {
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        // set tressures
        dungeon.addEntity(new Treassure(dungeon, 1, 3));
        dungeon.addEntity(new Treassure(dungeon, 2, 3));
        dungeon.addEntity(new Treassure(dungeon, 3, 3));

        // set switch and boulder
        dungeon.addEntity(new Switch(0, 2));
        dungeon.addEntity((new Switch(2, 0)));
        dungeon.addEntity((new Boulder(1, 0)));
        dungeon.addEntity((new Boulder(0, 1)));

        Goals goals = new Goals(dungeon, "AND");
        SwitchGoal sg = new SwitchGoal(dungeon);
        TreassureGoal tg = new TreassureGoal(dungeon);
        goals.addSubgoals(sg);
        goals.addSubgoals(tg);
        goals.setMain();

        assertFalse(goals.isComplete());
    }

    @Test
    void testAndGoalWithoutExitSuccess() {
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        // set tressures
        dungeon.addEntity(new Treassure(dungeon, 0, 2));
        dungeon.addEntity(new Treassure(dungeon, 0, 3));
        dungeon.addEntity(new Treassure(dungeon, 1, 3));

        // set switch and boulder
        dungeon.addEntity(new Switch(2, 0));
        dungeon.addEntity((new Switch(2, 1)));
        dungeon.addEntity((new Boulder(1, 0)));
        dungeon.addEntity((new Boulder(1, 1)));

        Goals goals = new Goals(dungeon, "AND");
        SwitchGoal sg = new SwitchGoal(dungeon);
        TreassureGoal tg = new TreassureGoal(dungeon);
        goals.addSubgoals(sg);
        goals.addSubgoals(tg);
        goals.setMain();

        // toggle switches
        p.moveRight();  // toggle 1st switch
        p.moveLeft();
        p.moveDown();
        p.moveRight();  // toggle 2nd switch
        p.moveLeft();
        // pick up treasure
        p.moveDown();   // pick up 1st
        p.moveDown(); // pick up 2nd
        p.moveRight(); // pick up 3rd

        assertTrue(goals.isComplete());
    }

    @Test
    void testAndGoalWithExitFail() {
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        // set tressures and exit
        dungeon.addEntity(new Treassure(dungeon, 1, 2));
        dungeon.addEntity(new Treassure(dungeon, 2, 2));
        dungeon.addEntity(new Treassure(dungeon, 3, 2));
        Exit exit = new Exit(3, 3);
        dungeon.addEntity(exit);

        Goals goals = new Goals(dungeon, "AND");
        ExitGoal eg = new ExitGoal(dungeon);
        TreassureGoal tg = new TreassureGoal(dungeon);
        goals.addSubgoals(eg);
        goals.addSubgoals(tg);
        goals.setMain();

        // go to exit without picking up treasures
        p.moveDown();
        p.moveDown();
        p.moveDown();
        p.moveRight();
        p.moveRight();
        p.moveRight();

        assertFalse(goals.isComplete());

        // go picking treasure
        p.moveUp();
        p.moveRight();
        p.moveLeft();
        p.moveLeft();

        assertFalse(goals.isComplete());
    }

    @Test
    void testAndGoalWithExitSuccess() {
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        // set tressures and exit
        dungeon.addEntity(new Treassure(dungeon, 1, 2));
        dungeon.addEntity(new Treassure(dungeon, 2, 2));
        dungeon.addEntity(new Treassure(dungeon, 3, 2));
        Exit exit = new Exit(3, 3);
        dungeon.addEntity(exit);

        Goals goals = new Goals(dungeon, "AND");
        ExitGoal eg = new ExitGoal(dungeon);
        TreassureGoal tg = new TreassureGoal(dungeon);
        goals.addSubgoals(eg);
        goals.addSubgoals(tg);
        goals.setMain();

        // go to exit without picking up treasures
        p.moveDown();
        p.moveDown();
        p.moveDown();
        p.moveRight();
        p.moveRight();
        p.moveRight();

        assertFalse(goals.isComplete());

        // go picking treasure
        p.moveUp();
        p.moveRight();
        p.moveLeft();
        p.moveLeft();

        assertFalse(goals.isComplete());

        // go back to exit
        p.moveDown();
        p.moveRight();
        p.moveRight();

        assertTrue(goals.isComplete());
    }

    @Test
    void testOrGoalWithoutExitFail(){
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        // set tressures
        dungeon.addEntity(new Treassure(dungeon, 1, 3));
        dungeon.addEntity(new Treassure(dungeon, 2, 3));
        dungeon.addEntity(new Treassure(dungeon, 3, 3));

        // set switch and boulder
        dungeon.addEntity(new Switch(0, 2));
        dungeon.addEntity((new Switch(2, 0)));
        dungeon.addEntity((new Boulder(0, 1)));
        dungeon.addEntity((new Boulder(1, 0)));

        Goals goals = new Goals(dungeon, "OR");
        SwitchGoal sg = new SwitchGoal(dungeon);
        TreassureGoal tg = new TreassureGoal(dungeon);
        goals.addSubgoals(sg);
        goals.addSubgoals(tg);
        goals.setMain();

        assertFalse(goals.isComplete());
    }

    @Test
    void testOrGoalWithoutExitSuccess() {
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        // set tressures
        dungeon.addEntity(new Treassure(dungeon, 1, 3));
        dungeon.addEntity(new Treassure(dungeon, 2, 3));
        dungeon.addEntity(new Treassure(dungeon, 3, 3));

        // set switch and boulder
        dungeon.addEntity(new Switch(0, 3));
        dungeon.addEntity((new Switch(2, 0)));
        dungeon.addEntity((new Boulder(0, 2)));
        dungeon.addEntity((new Boulder(1, 0)));

        Goals goals = new Goals(dungeon, "OR");
        SwitchGoal sg = new SwitchGoal(dungeon);
        TreassureGoal tg = new TreassureGoal(dungeon);
        goals.addSubgoals(sg);
        goals.addSubgoals(tg);
        goals.setMain();

        // pick up all treasure
        p.moveDown();
        p.moveRight();
        p.moveDown();
        p.moveDown();
        p.moveRight();
        p.moveRight();

        assertTrue(goals.isComplete());
    }

    @Test
    void testOrGoalWithExitFail(){
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        // set tressures and exit
        dungeon.addEntity(new Treassure(dungeon, 1, 2));
        dungeon.addEntity(new Treassure(dungeon, 2, 2));
        dungeon.addEntity(new Treassure(dungeon, 3, 2));
        Exit exit = new Exit(3, 3);
        dungeon.addEntity(exit);

        Goals goals = new Goals(dungeon, "OR");
        ExitGoal eg = new ExitGoal(dungeon);
        TreassureGoal tg = new TreassureGoal(dungeon);
        goals.addSubgoals(eg);
        goals.addSubgoals(tg);
        goals.setMain();

        assertFalse(goals.isComplete());

    }

    @Test
    void testOrGoalWithExitSuccess1(){
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        // set tressures and exit
        dungeon.addEntity(new Treassure(dungeon, 1, 2));
        dungeon.addEntity(new Treassure(dungeon, 2, 2));
        dungeon.addEntity(new Treassure(dungeon, 3, 2));
        Exit exit = new Exit(3, 3);
        dungeon.addEntity(exit);

        Goals goals = new Goals(dungeon, "OR");
        ExitGoal eg = new ExitGoal(dungeon);
        TreassureGoal tg = new TreassureGoal(dungeon);
        goals.addSubgoals(eg);
        goals.addSubgoals(tg);
        goals.setMain();

        // go to exit only
        p.moveDown();
        p.moveDown();
        p.moveDown();
        p.moveRight();
        p.moveRight();
        p.moveRight();

        assertTrue(goals.isComplete());
    }

    @Test
    void testOrGoalWithExitSuccess2(){
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        // set tressures and exit
        dungeon.addEntity(new Treassure(dungeon, 1, 2));
        dungeon.addEntity(new Treassure(dungeon, 2, 2));
        dungeon.addEntity(new Treassure(dungeon, 3, 2));
        Exit exit = new Exit(3, 3);
        dungeon.addEntity(exit);

        Goals goals = new Goals(dungeon, "OR");
        ExitGoal eg = new ExitGoal(dungeon);
        TreassureGoal tg = new TreassureGoal(dungeon);
        goals.addSubgoals(eg);
        goals.addSubgoals(tg);
        goals.setMain();

        // pick up treasure only
        p.moveDown();
        p.moveDown();
        p.moveRight();
        p.moveRight();
        p.moveRight();

        assertTrue(goals.isComplete());
    }
}
