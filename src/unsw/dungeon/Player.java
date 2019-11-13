package unsw.dungeon;

import java.util.ArrayList;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity implements Movable, Subject {

    private Dungeon dungeon;
    private int treasure;
    private ArrayList <Observer> observers = new ArrayList <Observer>();
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
    
	
	
	public boolean hasKey (int id) {
		//if has key in the bag
		for (Collectable c : backPack) {
			if (c.getClass() == Key.class && ((Key) c).getId() == id) return true;
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
	
	public void consumeKey(int id) {
		// TODO Auto-generated method stub
		for (Collectable c : backPack) {
			if (c.getClass() == Key.class) {
				Key k = (Key)c;
				if (k.getId() == id) {
					backPack.remove(k);
					return;
				}
			}
		}
		
	}
	
	//=========== end of backPack functions ===========

	@Override
	public void attachObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObserver() {
		for (Observer o : observers){
			o.update(this);
		}
	}


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
		if (getY() <= 0) return;
		
		Point target = getPt().getUp();
		
		ArrayList <Entity> eList = dungeon.getEntity(target);
    	
		if (eList.isEmpty()) {
			// move up
			this.getPt().setUp();
		} else {
			for (Entity e : eList) {
				e.interact(this, "up");
			}
		}
		
	}
	

    @Override
    public void moveDown() {
    	if (getY() >= (dungeon.getHeight() - 1)) return;
    	
    	Point target = getPt().getDown();

		ArrayList <Entity> eList = dungeon.getEntity(target);
		
		if (eList.isEmpty()) {
			// move down
			this.getPt().setDown();
		} else {
			for (Entity e : eList) {
				e.interact(this, "down");
			}
		}
    }

    @Override
    public void moveLeft() {
    	if (getX() <= 0) return;
    	
    	Point target = getPt().getLeft();
    	
		ArrayList <Entity> eList = dungeon.getEntity(target);
		
		if (eList.isEmpty()) {
			// move left
			this.getPt().setLeft();
		} else {
			for (Entity e : eList) {
				e.interact(this, "left");
			}
		}

    }

    @Override
    public void moveRight() {
    	if (getX() >= dungeon.getWidth() - 1) return;
    	
    	Point target = getPt().getRight();
    	
    	ArrayList <Entity> eList = dungeon.getEntity(target);
		if (eList.isEmpty()) {
			// move right
			this.getPt().setRight();
		} else {
			for (Entity e : eList) {
				e.interact(this, "right");
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

	@Override
	public boolean passable(Dungeon d, Point pt) {
		return false;
	}

	@Override
	public void interact(Player p, String direction) {
		// player cannot interact with itself
	}





}


