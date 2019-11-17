package unsw.dungeon;

import java.util.ArrayList;

public class Goals implements Goal {

    private Dungeon dungeon;
    private String type;
    private boolean isMain = false;
    private ArrayList<Goal> subGoals = new ArrayList <Goal>();
    private Goals parent;
    private boolean hasExit = false;


    public Goals (Dungeon dungeon, String type) {
        this.dungeon = dungeon;
        this.type = type;
    }


    // will be called by subject (player) if player get to an exit
    @Override
    public boolean isComplete(){
        Boolean res = null;
        switch (type){
            case "OR":
                res = false;
                for (Goal g : subGoals){
                    res  = res || g.isComplete();
                }

                break;

            case "AND":
                res = true;
                for (Goal g : subGoals){
                    res = res && g.isComplete();
                }
                break;
        }

        return res;
    }

    @Override
    public boolean isMain() {
        return isMain;
    }

    /**
     * add Subgoals to subgoals list
     * @param g the goal to be added
     */
    public void addSubgoals(Goal g) {
        this.subGoals.add(g);
        g.setParent(this);
        if (g.getClass() == ExitGoal.class) hasExit = true;
    }
    
    
    /**
     * get the list of subgoals
     * @return subGoals Array list of subgoals
     */
    public ArrayList<Goal> getSubGoals() {
		return subGoals;
	}


    @Override
	public void setMain() {
        isMain = true;
        this.dungeon.setMainGoal(this);
    }
    
    /**
     * check if this all goals are completed, if yes, notify parent goal to check completeness
     */
    public void checkComplete() {
        if (isComplete() && isMain()) dungeon.win();
        else if (!isMain && parent != null) parent.checkComplete();
    }

    @Override
    public void setParent(Goals g) {
        parent = g;
    }
    
    @Override
    public String getName() {
    	return null;
    }
    

	@Override
	public int getNum() {
		return -1;
	}
	
	public String getType() {
		return type;
	}
}

