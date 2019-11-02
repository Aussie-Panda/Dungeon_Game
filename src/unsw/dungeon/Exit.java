package unsw.dungeon;

public class Exit extends Entity implements Subject{

	Observer goal;

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
	public void removeObserver(Observer o) {
		this.goal = null;
	}
}
