package unsw.dungeon;

public class ExitGoal implements Goal, Observer{

    Subject exit;
    Boolean status = false;
    Dungeon dungeon;

    public ExitGoal (Dungeon dungeon){
        this.dungeon = dungeon;
    }


    @Override
    public void subscript(Subject s) {
        this.exit = s;
        s.attachObserver(this);
    }

    @Override
    public boolean isComplete() {
        return status;
    }

    @Override
    public void update(Subject s) {
        status =  true;//TODO
        dungeon.win();

    }
}
