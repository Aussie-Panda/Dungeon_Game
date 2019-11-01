package unsw.dungeon;

public class Goals  {

    private Dungeon dungeon;
    private String type;
    private ArrayList <Goal> subGoals = new ArrayList <Goal>();


    public Goals (Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    @Override
    public boolean or(Goal g) {
        return isComplete() || g.isComplete();
    }

    @Override
    public boolean and(Goal g) {
        return isComplete() && g.isComplete();
    }

    // will be called by subject (player) if player get to an exit
    @Override
    public boolean isComplete(){
        Boolean res = true;
        for (Goal g : subGoals){
            res  = res && g.isComplete();
        }
        return res;
    }

    // notify dungeon that this goal is complete
    public void complete(){
        dungeon.win();
    }

    public void addSubgoals(ArrayList<Goal> subGoals) {
        this.subGoals.add(subGoals);
    }
}
