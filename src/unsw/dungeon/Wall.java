package unsw.dungeon;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(x, y);
    }

	@Override
	public boolean passable(Dungeon d, Point pt) {
		return false;
	}

	@Override
	public void interact(Player p, String direction) {
		// wall is unable to interact
	}
    
    
    
}
