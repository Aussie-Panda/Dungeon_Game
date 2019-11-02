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
		boolean result = false;
		
		ArrayList <Entity> eList = dungeon.getEntity(pt);
		// if no object, througabe, switch
		if (eList.isEmpty()) return true;
		
		if (eList.size() == 1) {
			for (Entity e : eList) {
				if ((e instanceof Collectable) || 
				(e.getClass() == Switch.class)) {
					return true;
				}
			}
		}
		
		return result;
	}
    
    public Point setTarget(Point pt, String direction) {
    	Point target = new Point(-1, -1);
    	if (direction == "up") {
    		target = getPt().getUp();
    	}
    	
		if (direction == "down") {
			target = getPt().getDown();		
    	}
		
		if (direction == "left") {
			target = getPt().getLeft();
		}
		
		if (direction == "right") {
			target = getPt().getRight();
    	}
		
		return target;
    }

    
    
	@Override
	public void interact(Player p, String direction) {
		// TODO Auto-generated method stub
		
		Point target = setTarget(this.getPt(), direction);
		Switch dest = p.getDungeon().getSwitch(target);
		Switch curr = p.getDungeon().getSwitch(this.getPt());
		
		
		//if can move
		if (passable(p.getDungeon(),target)) {
			if (direction == "up") {	
				moveUp();
				//move player
				p.getPt().setUp();
			}
			
			if (direction == "down") {
				moveDown();
				p.getPt().setDown();
			}
			
			if (direction == "left") {
				moveLeft();
				p.getPt().setLeft();
			}
			
			if (direction == "right") {
				moveRight();
				p.getPt().setRight();
			}
		}
		
		
		//turn on
		if (dest != null) {
			dest.setState(1);
		}
		//turn off
		if (curr != null) {
			curr.setState(0);
		}
	}

	@Override
	public void moveUp() {
		// TODO Auto-generated method stub
		this.getPt().setUp();
		
	}

	@Override
	public void moveDown() {
		// TODO Auto-generated method stub
		this.getPt().setDown();
		
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		this.getPt().setLeft();
		
	}

	@Override
	public void moveRight() {
		// TODO Auto-generated method stub
		this.getPt().setRight();
		
	}


	


}

