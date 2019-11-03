package unsw.dungeon;

import java.util.ArrayList;

public class Goals implements Goal {

    private Dungeon dungeon;
    private String type;
    private boolean isMain = false;
//    private boolean status = false;
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

    public void addSubgoals(Goal g) {
        this.subGoals.add(g);
        g.setParent(this);
        if (g.getClass() == ExitGoal.class) hasExit = true;
    }

    public void setMain() {
        isMain = true;
    }

    public void checkComplete(Goal g) {
        if (isComplete() && isMain()) dungeon.win();
        else if (!isMain && parent != null) parent.checkComplete(this);
    }

    @Override
    public void setParent(Goals g) {
        parent = g;
    }
}

