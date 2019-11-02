package unsw.dungeon;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Potion extends Entity implements Consumable, Collectable {

	public Potion(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void collect(Player p) {
		//if sword is colleted switch player's state
		ArrayList<Collectable> backPack = p.getBackPack();
		Dungeon dungeon = p.getDungeon();
		if (p.getState() != "invincible") {
			p.setState("invincible");
			//remove from dungeon list
			dungeon.removeEntity(this);
			// set timer for 5 sec	    
			Timer timer = new Timer();
	        timer.schedule(new potionTimer(), 0, 1000);
	        p.setState("normal");
		    
			

		}
		
		
	}

	@Override
	public void consume() {
		// TODO Auto-generated method stub

	}
	
    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }
    
    
    
	@Override
	public void interact(Player p, String direction) {
		// TODO Auto-generated method stub
		collect(p);
		if (direction == "up") {
			p.getPt().setUp();
		}
		
		if (direction == "down") {
			p.getPt().setDown();
		}
		
		if (direction == "left") {
			p.getPt().setLeft();
		}
		
		if (direction == "right") {
			p.getPt().setRight();
		}
	}
	
	

}


