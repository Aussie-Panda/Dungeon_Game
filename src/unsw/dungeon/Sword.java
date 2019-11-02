package unsw.dungeon;

import java.util.ArrayList;

public class Sword extends Entity implements Collectable {

	public Sword(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}


    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }
	
	@Override
	public void interact(Player p) {
		// TODO Auto-generated method stub
		collect(p);
	}



	@Override
	public void collect(Player p) {
		//if sword is colleted switch player's state
		ArrayList<Collectable> backPack = p.getBackPack();
		Dungeon dungeon = p.getDungeon();
		if (!p.hasSword()) {
			// add to backpack
			backPack.add(this);
			//remove from dungeon list
			dungeon.removeEntity(this);

		}
		
		
	}


}
