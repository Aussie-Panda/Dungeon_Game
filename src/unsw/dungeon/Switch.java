package unsw.dungeon;

public class Switch extends Entity implements Subject {

	private Observer goal;
	final static int OFF = 0;
    final static int ON = 1;
    private int state = OFF;
    
	public Switch(int x, int y) {
		super(x, y);

	}
	
    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }
    
	/**
	 * check if the switch status is on
	 * @return
	 */
	public boolean isOn () {
		if (state == OFF) return false;
		return true;
	}

	@Override
	public void attachObserver(Observer o) {
		goal = o;
	}

	@Override
	public void notifyObserver() {
		goal.update(this);
	}

	/**
	 * get the switch state
	 * @return the state of the switch
	 */
	public int getState() {
		return state;
	}

	/**
	 * set the switch state
	 * @param state 
	 */
	public void setState(int state) {
		if (state != this.state) {
			this.state = state;
			if (goal != null) notifyObserver();
		}
		
	}

	@Override
	public void interact(Player p, String direction) {
		// switch is unable to be interacted by player
	}

}
