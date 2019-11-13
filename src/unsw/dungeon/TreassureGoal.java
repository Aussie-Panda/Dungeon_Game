package unsw.dungeon;

public class TreassureGoal implements Goal, Observer  {

    private int treassures = 0;
    private boolean status = false;
    private boolean isMain = false;
    private Dungeon dungeon;
    private Goals parent;

    public TreassureGoal(Dungeon dungeon) {
        for (Entity e : dungeon.getEntities()){
            if (e.getClass() == Treassure.class) subscript((Subject) e);
        }
        this.dungeon = dungeon;
    }

    @Override
    public void subscript(Subject s) {
        treassures++;
        s.attachObserver(this);
    }


    @Override
    public void update(Subject s) {
        if (treassures > 0) treassures--;
        System.out.println("Treasure picked! Remaining treasure: " + treassures);
        if (treassures == 0) {
            status = true;
            if (isMain) dungeon.win();
            else if (parent != null) parent.checkComplete(this);
        }
        else status = false;
    }

    @Override
    public boolean isComplete() {
        return status;
    }

    @Override
    public boolean isMain() {
        return isMain;
    }

    public void setMain() {
        isMain = true;
    }

    @Override
    public void setParent(Goals g) {
        parent = g;
    }
}
