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
    final static int NORMAL = 0;
    final static int INVINCIBLE = 1;
    private int state = NORMAL;
    
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
    	c.collect(this);
    }
    
	public boolean hasSword () {
		//if has key in the bag
		for (Collectable c : backPack) {
			if (c.getClass().isInstance(Sword.class)) return true;
		}
		return false;
	}
    
	
	
	public boolean hasKey () {
		//if has key in the bag
		for (Collectable c : backPack) {
			if (c.getClass().isInstance(Key.class)) return true;
		}
		return false;
	}
//=========== end of backPack functions ===========    
    
    
//



	@Override
    public void moveUp() {
		Point target = getPt().getUp();
        if (getY() > 0)
            this.getPt().setUp();
    }

    @Override
    public void moveDown() {
    	Point target = getPt().getDown();
        if (getY() < dungeon.getHeight() - 1)
        	this.getPt().setDown();
    }

    @Override
    public void moveLeft() {
    	Point target = getPt().getLeft();
        if (getX() > 0)
        	this.getPt().setLeft();
    }

    @Override
    public void moveRight() {
    	Point target = getPt().getRight();
        if (getX() < dungeon.getWidth() - 1)
        	this.getPt().setRight();
    }
    
    //=========== getters and setters functions ===========

	public ArrayList<Collectable> getBackPack() {
		return backPack;
	}

	public int getTreasure() {
		return treasure;
	}

	public void setTreasure(int treasure) {
		this.treasure = treasure;
	}

	public void setBackPack(ArrayList<Collectable> backPack) {
		this.backPack = backPack;
	}

	public String getState() {
		String str = null;
		if (this.state == NORMAL) {
			str = "normal";
		}
		else if (this.state == INVINCIBLE) {
			str = "invincible";
		} 
		return str;
	}

	public void setState(String state) {
		if (state.equals("normal")) {
			this.state = NORMAL;
		}
		
		else if (state.equals("invincible")) {
			this.state = INVINCIBLE;
		}
		
		else {
			System.out.println("TYPO !!!!!!!!!!!!!!!!!!!!");
		}
	}

	public Dungeon getDungeon() {
		return dungeon;
	}


}


