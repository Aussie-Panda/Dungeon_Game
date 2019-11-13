package unsw.dungeon;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Potion extends Entity implements Consumable, Collectable {
	Timer timer = new Timer();
	
	
	public Potion(int x, int y) {
		super(x, y);
	}

	@Override
	public void collect(Player p) {
		Dungeon dungeon = p.getDungeon();
		if (p.getState() != "invincible") {
			p.setState("invincible");
			System.out.println("Become invincible!");
			//remove from dungeon list
			dungeon.removeEntity(this); 
			consume(p);
		}
	}
	

	@Override
	public void consume(Player p) {
		// set timmer task
		TimerTask task = new TimerTask() {
			public void run() {
	            p.setState("normal"); 
	            System.out.println("Back to normal");
	            timer.cancel();
	        }
		};
		getPt().setPt(new Point(-1, -1));
		timer.schedule(task, new Date(new Date().getTime() + 5000));

	}
	
    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }
    
    
	@Override
	public void interact(Player p, String direction) {
		// TODO Auto-generated method stub
		
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
		collect(p);
	}
	
	

}


