package unsw.dungeon;

public class ExitGoal implements Goal, Observer{

    private Subject exit;
    private Boolean isMain = false;
    private Boolean status = false;
    private Dungeon dungeon;
    private Goals parent;


    public ExitGoal (Dungeon dungeon){
        this.dungeon = dungeon;
        for (Entity e : dungeon.getEntities()) {
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
        if (isMain())dungeon.win();
        else if (parent != null) {
            status = true;
            // if still some goals are not completed, set to false
            if (!parent.isComplete()) {
                System.out.println("You haven't completed other goals yet");
                status = false;
            }
            parent.checkComplete(this);
        }
    }

    public void setMain() {
        isMain = true;
    }

    @Override
    public boolean isMain() {
        return isMain;
    }

    @Override
    public void setParent(Goals g) {
        parent = g;

    }
}
