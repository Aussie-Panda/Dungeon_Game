package unsw.dungeon;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 * @author Yanning Cao
 * @author Katrina Ding
 */
public class Player extends Entity implements Movable, Subject {

    private Dungeon dungeon;
    private int treasure;
    private boolean disable = false;
    private ArrayList <Observer> observers = new ArrayList <Observer>();
    final static int NORMAL = 0;
    final static int INVINCIBLE = 1;
    final static int SWORD = 2;
    final static int SWORD_INVINCIBLE = 3;
    protected IntegerProperty state = new SimpleIntegerProperty(NORMAL);
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
    

    /**
     * pick up all the collectable items
     * @param c
     */
    public void pickUp (Collectable c) {
    	c.collect(this);
    }
    
    /**
     * check if the player carries a sword
     * @return
     */
	public boolean hasSword () {
		//if has sword in the bag
		return (state.get() == SWORD || state.get() == SWORD_INVINCIBLE);
	}
	
	/**
	 * check if the player have the key with the  same id
	 * @param id the id of the door
	 * @return if has the key correspond to the door return true else false
	 */
	public boolean hasKey (int id) {
		//if has key in the bag
		for (Collectable c : backPack) {
			if (c.getClass() == Key.class && ((Key) c).getId() == id) return true;
		}
		return false;
	}
	
	/**
	 * check if the player carries a key
	 * @return	true if player has key else false
	 */
	public boolean hasKey () {
		//if has key in the bag
		for (Collectable c : backPack) {
			if (c.getClass() == Key.class) return true;
		}
		return false;
	}
	
	/**
	 * consume a key 
	 * @param id the key's id you wish to consume
	 */
	public void consumeKey(int id) {
		for (Collectable c : backPack) {
			if (c.getClass() == Key.class) {
				Key k = (Key)c;
				if (k.getId() == id) {
					k.consume(this);
					return;
				}
			}
		}
	}
	
	/**
	 * consume the sword
	 */
	public void consumeSword() {
		for (Collectable c : backPack) {
			if (c.getClass() == Sword.class) {
				((Sword) c).consume(this);
				return;
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
	
	/**
	 * check if a object on a specific point is consumable
	 * @param pt
	 * @return
	 */
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


	/**
	 * player moves up and interact with the object
	 */
	@Override
    public void moveUp() {
		if (disable == false) {
			if (getY() <= 0) return;
			if (!observers.isEmpty()) notifyObserver();

			Point target = getPt().getUp();
			ArrayList <Entity> eList = dungeon.getEntity(target);
	    	
			// if no entities at the point or the only entity is switch
			if (eList.isEmpty() || (eList.size() == 1 && eList.get(0).getClass() == Switch.class)) {
				// move up
				this.getPt().setUp();
			} else {
				for (Entity e : eList) {
					e.interact(this, "up");
				}
			}
		}
		
		
	}
	
	/**
	 * player moves down and interact with the object
	 */
    @Override
    public void moveDown() {
    	if (disable == false) {
    		if (getY() >= (dungeon.getHeight() - 1)) return;
        	if (!observers.isEmpty()) notifyObserver();
        	
        	Point target = getPt().getDown();
    		ArrayList <Entity> eList = dungeon.getEntity(target);
    		
    		// if no entities at the point or the only entity is switch
    		if (eList.isEmpty() || (eList.size() == 1 && eList.get(0).getClass() == Switch.class)) {
    			// move down
    			this.getPt().setDown();
    		} else {
    			for (Entity e : eList) {
    				e.interact(this, "down");
    			}
    		}
    	}
    	
    }

	/**
	 * player moves left and interact with the object
	 */
    @Override
    public void moveLeft() {
    	if (disable == false) {
    		if (getX() <= 0) return;
        	if (!observers.isEmpty()) notifyObserver();
        	
        	Point target = getPt().getLeft();
    		ArrayList <Entity> eList = dungeon.getEntity(target);
    		
    		// if no entities at the point or the only entity is switch
    		if (eList.isEmpty() || (eList.size() == 1 && eList.get(0).getClass() == Switch.class)) {
    			// move left
    			this.getPt().setLeft();
    		} else {
    			for (Entity e : eList) {
    				e.interact(this, "left");
    			}
    		}
    	}
    	

    }

	/**
	 * player moves right and interact with the object
	 */
    @Override
    public void moveRight() {
    	if (disable == false) {
    		if (getX() >= dungeon.getWidth() - 1) return;
        	if (!observers.isEmpty()) notifyObserver();
        	
        	Point target = getPt().getRight();
        	ArrayList <Entity> eList = dungeon.getEntity(target);
        	
        	// if no entities at the point or the only entity is switch
    		if (eList.isEmpty() || (eList.size() == 1 && eList.get(0).getClass() == Switch.class)) {
    			// move right
    			this.getPt().setRight();
    		} else {
    			for (Entity e : eList) {
    				e.interact(this, "right");
    			}
    		}
    	}
    	
    }
    //=========== end of Player movement functions ===========  
    
    //=========== getters and setters functions ===========

    /**
     * get the backpack
     * @return pleyer's backpack
     */
	public ArrayList<Collectable> getBackPack() {
		return backPack;
	}

	/**
	 * get the treasure
	 * @return
	 */
	public int getTreasure() {
		return treasure;
	}

	/**
	 * set the treasure
	 * @param treasure
	 */
	public void setTreasure(int treasure) {
		this.treasure = treasure;
	}

	/**
	 * set player's backpack
	 * @param backPack
	 */
	public void setBackPack(ArrayList<Collectable> backPack) {
		this.backPack = backPack;
	}
	


	/**
	 * get player's state
	 * @return the format will be in String
	 */
	public String getState() {
		String str = null;
		if (this.state.get() == NORMAL) {
			str = "normal";
		}
		else if (this.state.get() == INVINCIBLE) {
			str = "invincible";
		} 
		else if (this.state.get() == SWORD_INVINCIBLE) {
			str = "invincible";
		}
		else if (this.state.get() == SWORD) {
			str = "sword";
		}
		return str;
	}

	/**
	 * set player's statue using String
	 * @param state the string of the state
	 */
	public void setState(String state) {
		if (state.equals("normal")) {
			this.state.set(NORMAL);
		}
		
		else if (state.equals("invincible")) {
			this.state.set(INVINCIBLE);
		}
		
		else if (state.equals("sword_invincible")) {
			this.state.set(SWORD_INVINCIBLE);
		}
		
		else if (state.equals("sword")) {
			this.state.set(SWORD);
		}
		
		else {
			System.out.println("TYPO !!!!!!!!!!!!!!!!!!!!");
		}
	}
	
	/**
	 * set player's state to invincible
	 */
	public void beInvincible() {
		if (state.get() < SWORD_INVINCIBLE) state.set(state.get()+1);
		System.out.println("Become invincible!");
	}
	
	/**
	 * remove invincible state from the player
	 */
	public void removeInvincible() {
		if (state.get() > NORMAL) state.set(state.get()-1);
        System.out.println("Back to normal");
	}
	
	/**
	 * set the player state to sword
	 */
	public void swordState() {
		state.set(state.get()+2);
		System.out.println("Sword Picked!");
	}
	
	/**
	 * remove the sword state
	 * @param s
	 */
	public void removeSword(Sword s) {
		getBackPack().remove(s);
		state.set(state.get()-2);
		System.out.println("Sword is broken");
	}

	/**
	 * get the dungeon the player's in
	 * @return
	 */
	public Dungeon getDungeon() {
		return dungeon;
	}

	@Override
	public boolean passable(Dungeon d, Point pt) {
		return false;
	}

	/**
	 * check if the player's move ability has been disabled
	 * @return true is it is diabeld else false
	 */
	public boolean isDisable() {
		return disable;
	}

	/**
	 * set the disable 
	 * @param disable
	 */
	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	@Override
	public void interact(Player p, String direction) {
		// player cannot interact with itself
	}





}


