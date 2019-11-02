package unsw.dungeon;

public class ExitGoal implements Goal, Observer{

    Subject exit;
    Boolean isMain = false;
    Boolean status = false;
    Dungeon dungeon;

    public ExitGoal (Dungeon dungeon){
        this.dungeon = dungeon;
        for (Entity e : dungeon.getEntities()){
            if (e.getClass() == Exit.class) {
                subscript((Subject) e);
            }
        }
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

    public void setMain() {
        isMain = true;
    }

    @Override
    public boolean isMain() {
        return isMain;
    }

}
