package unsw.dungeon;

import java.util.ArrayList;

public class Sword extends Entity implements Collectable, Consumable {
	
	private int durability = 5;
	
	public Sword(int x, int y) {
		super(x, y);
	}


    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }
	
	@Override
	public void interact(Player p, String direction) {
		p.setPt(this.getPt());
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
			p.swordState();
			//remove from dungeon list and grid pane
			dungeon.removeEntity(this);
			this.setPt(new Point(-1, -1));
		}
	}

	/**
	 * consume the sword if sword has no durability
	 */
	@Override
	public void consume(Player p) {
		if (p.getState().equals("sword")) {
			durability--;
			System.out.println("Sword durability: " + durability);
			if (durability == 0) {
				p.removeSword(this);
			}
		}
		// TODO: nned test
	}


}
