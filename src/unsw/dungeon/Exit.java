package unsw.dungeon;

public class Exit extends Entity implements Subject{

	Observer goal;
	Goal otherGoal;


	public Exit(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }

	@Override
	public void attachObserver(Observer o) {
		this.goal = o;
	}

	@Override
	public void notifyObserver() {
		goal.update(this);
	}

	// if there's other goal, player must achieve them first
	public void setOtherGoal(Goal g) {
		otherGoal = g;
	}

	// if player wants to get to exit and all other goals are completed, notify ExitGoal
	@Override
	public void interact(Player p, String dir) {
		// if there's no other goal
		if (otherGoal == null) {
			notifyObserver();

		// if others goals are completed
		} else if (otherGoal.isComplete()) {
			notifyObserver();

		} else {
			System.out.println("Goals are not completed, cannot exit.");
		}

	}
}
