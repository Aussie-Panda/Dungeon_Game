package unsw.dungeon;

public class EnemyGoal implements Goal, Observer {

    private int enemies = 0;
    private boolean status = false;

    public EnemyGoal(Dungeon dungeon) {
        for (Entity e : dungeon.getEntities()){
            if (e.getClass() == Enemy.class) subscript(null);
        }
    }

    @Override
    public void subscript(Subject s) {
        enemies++;
    }


    @Override
    public void update() {
        if (enemies > 0) enemies--;
        if (enemies == 0) status = true;
        else status = false;
    }

    @Override
    public boolean isComplete() {
        return status;
    }
}
