package unsw.dungeon;

public class SwitchGoal implements Goal, Observer {

    private int switches = 0;
    private Dungeon dungeon;
    private boolean status = false;
    private boolean isMain = false;
    private Goals parent;

    public SwitchGoal(Dungeon dungeon) {
        for (Entity e : dungeon.getEntities()){
            if (e.getClass() == Switch.class) subscript((Subject) e);
            System.out.println("subscripting" + switches); 
        }
        this.dungeon = dungeon;
    }

    @Override
    public void subscript(Subject s) {
        switches++;
        s.attachObserver(this);
    }


    @Override
    public void update(Subject s) {
    	System.out.println(switches);
        // if the subject is triggered, decrease number of untriggered switch
        if (((Switch) s).isOn()) {
            if (switches > 0) switches--;
        // if the subject is untriggered, increase num of untriggered switches
        } else if (!((Switch) s).isOn()) {
            switches ++;
        }

        if (switches == 0) {
            status = true;
            if (isMain) dungeon.win();
            else if (parent != null) parent.checkComplete(this);
        } else {
        	status = false;
        }
    }

    @Override
    public boolean isComplete() {
        return status;
    }

    @Override
    public boolean isMain() {
        return isMain;
    }

    @Override
    public void setMain() {
        isMain = true;
    }

    @Override
    public void setParent(Goals g){
        parent = g;
    }
}
