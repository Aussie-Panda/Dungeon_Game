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
		ArrayList<Collectable> backPack = p.getBackPack();
		Dungeon dungeon = p.getDungeon();
		if (!hasSword(backPack)) {
			// set the player state to sword
			p.setState(1);
			// add to backpack
			backPack.add(this);
			//remove from dungeon list
			dungeon.removeEntity(this);

		}
		
		
	}
	public boolean hasSword (ArrayList<Collectable> backPack) {
		//if has key in the bag
		for (Collectable c : backPack) {
			if (c.getClass().isInstance(Sword.class)) return true;
		}
		return false;
	}

}
