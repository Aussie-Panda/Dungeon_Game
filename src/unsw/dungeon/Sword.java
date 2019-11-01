package unsw.dungeon;

import java.util.ArrayList;

public class Sword extends Entity implements Collectable {

	public Sword(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void collect(Player p) {
		//if sword is colleted switch player's state
		p.setState(1);
		
	}


}
