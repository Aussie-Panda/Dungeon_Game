package unsw.dungeon;

public class ExitGoal extends GoalObject implements Observer{

    Subject exit;

    public ExitGoal (Dungeon dungeon){
        super(dungeon);
    }



    @Override
    public void subscript(Subject s) {
        this.exit = s;
    }
}
