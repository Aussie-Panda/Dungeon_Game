package unsw.dungeon;

public class SwitchGoal implements Goal, Observer {

    private int switches = 0;
    private boolean status = false;

    public SwitchGoal(Dungeon dungeon) {
        for (Entity e : dungeon.getEntities()){
            if (e.getClass() == Switch.class) subscript(null);
        }
    }

    @Override
    public void subscript(Subject s) {
        switches++;
    }


    @Override
    public void update(Subject s) {
        if ((Switch.))
        if (switches > 0) switches--;
        if (switches == 0) status = true;
        else status = false;
    }

    @Override
    public boolean isComplete() {
        return status;
    }
}
