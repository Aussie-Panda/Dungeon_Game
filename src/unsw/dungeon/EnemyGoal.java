package unsw.dungeon;

public class EnemyGoal implements Goal, Observer {
	
	private Dungeon dungeon;
    private int enemies = 0;
    private boolean isMain = false;
    private boolean status = false;
    private Goals parent;

    public EnemyGoal(Dungeon dungeon) {
        for (Entity e : dungeon.getEntities()){
            if (e.getClass() == Enemy.class) subscript((Subject) e);
        }
        this.dungeon = dungeon;
        
    }

    @Override
    public void subscript(Subject s) {
        enemies++;
        s.attachObserver(this);
    }


    @Override
    public void update(Subject s) {
        if (enemies > 0) enemies--;
        if (enemies == 0) {
        	status = true;
        	if (isMain()) dungeon.win();
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
