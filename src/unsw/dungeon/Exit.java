package unsw.dungeon;

public class Exit extends Entity {

	public Exit(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public boolean passable (Point pt) {
    	return true;
    }

}
