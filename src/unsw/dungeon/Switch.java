package unsw.dungeon;

public class Switch extends Entity  implements Floor, Subject{

	private Observer goal;

	public Switch(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	final static int OFF = 0;
    final static int ON = 1;
    private int state = OFF;
	
    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }
    
	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		

	}
	
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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

}
