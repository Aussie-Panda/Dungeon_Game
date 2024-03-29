package test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestSingleGoal {

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
        dungeon.addEntity(new Treassure(dungeon, 3, 3));
        TreassureGoal goal = new TreassureGoal(dungeon);
        goal.setMain();

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
        dungeon.addEntity(new Treassure(dungeon, 3, 3));
        TreassureGoal goal = new TreassureGoal(dungeon);
        goal.setMain();

        p.moveRight();  // pick 1st treasure
        p.moveDown();
        p.moveDown();
        p.moveRight();  // pick 2nd treasure
        p.moveRight();
        p.moveDown();   // pick 3rd treasure

        assertTrue(goal.isComplete());
    }

    @Test
    void testSingleEnemyGoalFail(){
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        Enemy e1 = new Enemy(dungeon, 2, 2);
        dungeon.addEntity(e1);
        e1.subscript(p);
        Enemy e2 = new Enemy(dungeon, 3, 3);
        dungeon.addEntity(e2);
        e2.subscript(p);
        EnemyGoal goal = new EnemyGoal(dungeon);
        goal.setMain();


        assertFalse(goal.isComplete());
    }

    @Test
    void testSingleEnemyGoalSuccess(){
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        Enemy e1 = new Enemy(dungeon, 0, 1);
        dungeon.addEntity(e1);
        e1.subscript(p);
        Enemy e2 = new Enemy(dungeon, 3, 3);
        dungeon.addEntity(e2);
        e2.subscript(p);
        EnemyGoal goal = new EnemyGoal(dungeon);
        goal.setMain();

        p.setState("invincible");
        //e1.interact(p, null);
        e1.moveUp();
        e2.interact(p, null);

        assertTrue(goal.isComplete());
    }

    @Test
    void testSingleSwitchGoalFail(){
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        dungeon.addEntity(new Switch(3, 0));
        dungeon.addEntity((new Boulder(2, 0)));
        SwitchGoal goal = new SwitchGoal(dungeon);
        goal.setMain();

        p.moveRight();

        assertFalse(goal.isComplete());
    }

    @Test
    void testSingleSwitchGoalSuccess() {
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        dungeon.addEntity(new Switch(0, 2));
        dungeon.addEntity((new Switch(2, 0)));
        dungeon.addEntity((new Boulder(1, 0)));
        dungeon.addEntity((new Boulder(0, 1)));
        SwitchGoal goal = new SwitchGoal(dungeon);
        goal.setMain();

        p.moveRight(); // push first boulder to the first switch
        p.moveLeft();
        p.moveDown(); // push second boulder o the second switch

        assertTrue(goal.isComplete());

    }



}
