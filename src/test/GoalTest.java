package test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GoalTest {


    @Test
    void testSingleExitGoalFail(){
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        Exit exit = new Exit(2, 2);
        dungeon.addEntity(exit);
        ExitGoal goal = new ExitGoal(dungeon);
        goal.setMain();

        p.moveRight();
        p.moveDown();

        assertFalse(goal.isComplete());
    }

    @Test
    void testSingleExitGoalSuccess() {
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        Exit exit = new Exit(2, 2);
        dungeon.addEntity(exit);
        ExitGoal goal = new ExitGoal(dungeon);
        goal.setMain();

        p.moveRight();
        p.moveDown();
        p.moveRight();
        p.moveDown();

        assertTrue(goal.isComplete());
    }

    @Test
    void testSingleTreassureGoalFail(){
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        dungeon.addEntity(new Treassure(dungeon, 1, 0));
        dungeon.addEntity(new Treassure(dungeon, 2, 2));
        dungeon.addEntity(new Treassure(dungeon, 3, 4));
        TreassureGoal goal = new TreassureGoal(dungeon);

        p.moveRight();
        p.moveDown();

        assertFalse(goal.isComplete());
    }

    @Test
    void testSingleTreassureGoalSuccess() {
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        dungeon.addEntity(new Treassure(dungeon, 1, 0));
        dungeon.addEntity(new Treassure(dungeon, 2, 2));
        dungeon.addEntity(new Treassure(dungeon, 3, 4));
        TreassureGoal goal = new TreassureGoal(dungeon);

        p.moveRight();  // pick 1st treasure
        p.moveDown();
        p.moveDown();
        p.moveRight();  // pick 2nd treasure
        p.moveRight();
        p.moveDown();
        p.moveDown();   // pick 3rd treasure

        assertTrue(goal.isComplete());
    }
}
