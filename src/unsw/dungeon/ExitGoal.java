package unsw.dungeon;

public class ExitGoal implements Goal, Observer{
	
	private String name = "Get to Exit";
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
    	
        status =  true;
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
        dungeon.updateGoal();
    }

    public void setMain() {
        isMain = true;
        this.dungeon.setMainGoal(this);
    }

    @Override
    public boolean isMain() {
        return isMain;
    }

    @Override
    public void setParent(Goals g) {
        parent = g;

    }
    
    @Override
    public String getName() {
    	return name;
    }


	@Override
	public int getNum() {
		return (status == true)? 0:1;
	}
}
