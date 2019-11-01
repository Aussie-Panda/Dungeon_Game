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
    public void consume() {

    }

	@Override
	public void collect(Player p) {
		// TODO Auto-generated method stub
		ArrayList<Collectable> backPack = p.getBackPack();
		if (!hasKey(backPack)) {
			backPack.add(this);
		}
		
	}
	
	
	public boolean hasKey (ArrayList<Collectable> backPack) {
		//if has key in the bag
		for (Collectable c : backPack) {
			if (c.getClass().isInstance(Key.class)) return true;
		}
		return false;
	}
	
	
}
