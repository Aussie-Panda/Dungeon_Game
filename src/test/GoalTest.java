package test;

import org.junit.jupiter.api.Test;
import unsw.dungeon.Dungeon;
import unsw.dungeon.Exit;
import unsw.dungeon.ExitGoal;
import unsw.dungeon.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoalTest {



    @Test
    void testSingleExitGoal(){
        Dungeon dungeon = new Dungeon(4, 4);
        Player p = new Player(dungeon, 0, 0);
        dungeon.addEntity(p);
        Exit exit = new Exit(2, 2);
        dungeon.addEntity(exit);
        ExitGoal goal = new ExitGoal(dungeon);
        goal.setMain();

        p.moveRight();
        p.moveUp();

        assertEquals(goal.isComplete(), false, "Status is: " + goal.isComplete());

    }

}
