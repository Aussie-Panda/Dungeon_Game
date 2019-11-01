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
	
	
	public boolean passable (ArrayList <Entity> eList) {
		if (eList.isEmpty()) return true;
		for (Entity e : eList) {
			//System.out.println(e.getClass() == Wall.class);
			if (e.getClass() == Wall.class) {
				return false;
			} else if (e.getClass() == Boulder.class) {
				return ((Boulder) e).canPass();
			} else if (e.getClass() ==Door.class) {
				return ((Door) e).canPass();
			}
		}
		
		return true;
	}
	
	public ArrayList <Collectable> isCollectable (ArrayList <Entity> eList) {
		ArrayList <Collectable> result = new ArrayList <Collectable> ();
		for (Entity e : eList) {
			if (e instanceof Collectable) {
				result.add((Collectable) e);
			}
		}
		return result;
	}
	
	
	
	
	//=========== end of enviroment detection ===========
	
    
	//=========== player Movement ===========
	public void processMove (Point target, String direction) {
		ArrayList <Entity> eList = this.dungeon.getEntity(target);
		ArrayList <Collectable> cList = this.isCollectable(eList);
		boolean cond = (getY() < dungeon.getHeight() - 1) && (getY() > 0) &&
				(getX() > 0) && (getX() < dungeon.getWidth() - 1);
		
        if (cond && (passable(eList))) {
        	if (!cList.isEmpty()) {
        		for (Collectable c : cList) {
        			this.pickUp(c);
        		}
        	}
        	if (direction == "up") {
        		this.getPt().setUp();
        	}
            
        	if (direction == "down") {
        		this.getPt().setDown();
        	}
        	if (direction == "left") {
        		this.getPt().setLeft();
        	}
        	if (direction == "right") {
        		this.getPt().setRight();
        	}
        }
	}
	
	@Override
    public void moveUp() {
		Point target = getPt().getUp();
		processMove (target, "up");
		//if ((getY() > 0) && (passable(eList)))
    }

    @Override
    public void moveDown() {
    	Point target = getPt().getDown();
        //if (getY() < dungeon.getHeight() - 1)
		processMove (target, "down");
    }

    @Override
    public void moveLeft() {
    	Point target = getPt().getLeft();
    	processMove (target, "left");
    }

    @Override
    public void moveRight() {
    	Point target = getPt().getRight();
    	processMove (target, "right");
    }
    //=========== end of backPack functions ===========  
    
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


