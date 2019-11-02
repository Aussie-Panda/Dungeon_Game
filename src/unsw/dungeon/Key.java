package unsw.dungeon;

import java.util.ArrayList;

public class Key extends Entity implements Consumable, Collectable {

    Dungeon dungeon;
    int id;

    public Key(Dungeon dungeon, int id, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;

    }

    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }
    
    @Override
    public void consume() {
    	//TODO
    	
    }

	@Override
	public void interact(Player p) {
		// TODO Auto-generated method stub
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

		}
		
	}

	
	
}
