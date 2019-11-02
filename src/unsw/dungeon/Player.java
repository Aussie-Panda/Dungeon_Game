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
			if (c.getClass() == Sword.class) return true;
		}
		return false;
	}
    
	
	
	public boolean hasKey () {
		//if has key in the bag
		for (Collectable c : backPack) {
			if (c.getClass() == Key.class) return true;
		}
		return false;
	}
	//=========== end of backPack functions ===========    
    
	
	//=========== enviroment detection ===========
	
	
	public boolean passable (Point pt) {
		boolean result = true;
		
		ArrayList <Entity> eList = dungeon.getEntity(pt);
		if (eList.isEmpty()) return true;
		for (Entity e : eList) {
			result = result & e.passable(dungeon,pt);
		}
		return result;
	}
	
	
	//=========== end of enviroment detection ===========
	
    
	//=========== player Movement ===========
	
	

	
	@Override
    public void moveUp() {
	
		Point target = getPt().getUp();
		
		ArrayList <Entity> eList = dungeon.getEntity(target);
		if (eList.isEmpty()) {
			// move up
			this.getPt().setUp();
		} else {
			if ((getY() > 0) && (passable(target))) {
				for (Entity e : eList) {
					e.interact(this, "up");
				}
			}
		}
		
	}
	

    @Override
    public void moveDown() {

    	Point target = getPt().getDown();
    	
    	
		ArrayList <Entity> eList = dungeon.getEntity(target);
		if (eList.isEmpty()) {
			// move down
			this.getPt().setDown();
		} else {
			if ((getY() < dungeon.getHeight() - 1) && (passable(target))) {
				for (Entity e : eList) {
					e.interact(this, "down");
				}
			}
		}
    }

    @Override
    public void moveLeft() {
    	Point target = getPt().getLeft();
    	// interact boulder
    	
		ArrayList <Entity> eList = dungeon.getEntity(target);
		if (eList.isEmpty()) {
			// move left
			this.getPt().setLeft();
		} else {
			if ((getX() > 0) && (passable(target))) {
				for (Entity e : eList) {
					e.interact(this, "left");
				}
			}
		}

    }

    @Override
    public void moveRight() {
    	Point target = getPt().getRight();
    	
    	ArrayList <Entity> eList = dungeon.getEntity(target);
		if (eList.isEmpty()) {
			// move right
			this.getPt().setRight();
		} else {
			if ((getX() < dungeon.getWidth() - 1) && (passable(target))) {
				for (Entity e : eList) {
					e.interact(this, "right");
				}
			}
		}
    	
    	
    	
    	
    	if ((getX() < dungeon.getWidth() - 1) && (passable(target))) {
			if (dungeon.getBoulder(target) != null) {
				Boulder b = dungeon.getBoulder(target);
				b.interact(this, "right");
				return;
			}
			
			// move Right
			this.getPt().setRight();
			
			//collect
			if (dungeon.getCollectable(this.getPt()) != null) {
				Collectable c = dungeon.getCollectable(this.getPt());
				c.collect(this);
			}
		}
    }
    //=========== end of Player movement functions ===========  
    
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


