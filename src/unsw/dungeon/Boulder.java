package unsw.dungeon;

import java.util.ArrayList;

public class Boulder extends Entity implements Movable {

	public Boulder(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	// TODO
    @Override
	public boolean passable (Dungeon dungeon, Point pt) {
		boolean result = true;
		
		ArrayList <Entity> eList = dungeon.getEntity(pt);
		if (eList == null) return true;
		
		// if boulder false
		// if collectable false
		// if wall false
		// if movable false
		
		return result;
	}
    
    

    
    
	@Override
	public void interact(Player p, String direction) {
		// TODO Auto-generated method stub
		if (direction == "up") {
			moveUp();
		}
		
		if (direction == "down") {
			moveUp();
		}
		
		if (direction == "left") {
			moveUp();
		}
		
		if (direction == "right") {
			moveUp();
		}
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		
	}


	


}

