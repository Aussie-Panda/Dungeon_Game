package unsw.dungeon;

import java.util.ArrayList;

public class Goals implements Goal {

    private Dungeon dungeon;
    private String type;
//    private boolean status = false;
    private ArrayList<Goal> subGoals = new ArrayList <Goal>();


    public Goals (Dungeon dungeon) {
        this.dungeon = dungeon;
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



    public void addSubgoals(Goal g) {
        this.subGoals.add(g);
    }
}

