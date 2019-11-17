package unsw.dungeon;

import java.util.ArrayList;

public class Key extends Entity implements Consumable, Collectable {

    private int id;

    public Key(Dungeon dungeon, int id, int x, int y) {
        super(x, y);
        this.id = id;

    }

    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }
    
    @Override
    public void consume(Player p) {
    	p.getBackPack().remove(this);
    	
    }

	@Override
	public void interact(Player p, String direction) {
		p.setPt(this.getPt());
		collect(p);
	}
	
	@Override
	public void collect(Player p) {
		// TODO Auto-generated method stub
		ArrayList<Collectable> backPack = p.getBackPack();
		Dungeon dungeon = p.getDungeon();
		if (!p.hasKey()) {
			// add to backpack
			backPack.add(this);
			//remove from dungeon list
			dungeon.removeEntity(this);
			getPt().setPt(new Point(-1, -1));

		}
		
	}

	/**
	 * get the id of the key
	 * @return the id of the key
	 */
	public int getId() {
		return id;
	}
}
