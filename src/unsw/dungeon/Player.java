package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Movable {

    private Dungeon dungeon;
    private int treasure;
    
    // a back pack contains multiple collectable items
    private ArrayList<Collectable> backPack;
    
    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.backPack = new ArrayList<Collectable>();
        this.treasure = 0;
    }

//=========== backPack functions ===========
    
    // pick up all the collectable items
    public void pickUp (Collectable c) {
    	
    	if (c.getClass() == null ) {
    		System.out.println("Can't pick up not a collectable object");
    	} 
    	
    	if (addToBag (c) == true) return;
    	
    	else if (c.getClass().isInstance(Tressure.class)) {
    		this.treasure++;
    	}
    }
    

    /**
     * A helper to the pickUp function use to check if 
     * exits a collectable in the bag.
     * @param c
     * @return if can successfully add to bag return true
     * else false
     */
    public boolean addToBag (Collectable c) {
    	int flag = 0;
    	if (c.getClass().isInstance(Key.class)) {
    		c = (Key) c;
    		flag = 1;
    	}
    	
    	if (c.getClass().isInstance(Sword.class)) {
    		c = (Sword) c;
    		flag = 1;
    	}
    	
    	// if it can be pick up to bag and bag doesn't 
    	// contains the item, add to bag retrun success
    	if (!backPack.contains(c) && (flag == 1)) {
			backPack.add(c);
			return true;
		}
    	return false;
    }
    
 //=========== end of backPack functions ===========    
    
    





	@Override
    public void moveUp() {
        if (getY() > 0)
            y().set(getY() - 1);
    }

    @Override
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    @Override
    public void moveLeft() {
        if (getX() > 0)
            x().set(getX() - 1);
    }

    @Override
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
    
    //=========== getters and setters functions ===========

	public ArrayList<Collectable> getBackPack() {
		return backPack;
	}
}


