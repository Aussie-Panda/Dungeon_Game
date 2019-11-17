/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 * @author Yanning Cao
 * @author Katrina Ding
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Goal mainGoal;
    protected IntegerProperty winStatus = new SimpleIntegerProperty(0);
    protected BooleanProperty goalChangedFlag = new SimpleBooleanProperty(false);
//    private Goal mainGoal; // store the first processed goal
    // need a status indicating winning
    
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
    }
    /**
     * get the dungeon width
     * @return the width of the dungeons
     */
    public int getWidth() {
        return width;
    }

    /**
     * get dungeon height 
     * @return the height of the dungeon
     */
    public int getHeight() {
        return height;
    }

    /**
     * get the player who is inside the dungeon
     * @return player inside the dungeon
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * set the player to a player object
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * add entity into the entityList
     * @param entity an enitity that wish to be add into the dungeon
     */
    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity.getClass() == Player.class) setPlayer((Player) entity);
    }
    
    /**
     * remove the entity from the entityList
     * @param entity an enitity that wish to be removed from the dungeon
     */
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
    

    /**
     * find a movable object on a specific location 
     * @param pt the location that you want check
     * @return if found return the object else return null
     */
	public Movable  getMovable (Point pt) {
    	
		Movable  result = null;
    	for (Entity e : entities) {
    		if (e.getPt().equals(pt)) {
    			if (e instanceof Movable ) {
	    			result = (Movable ) e;
	    		}
    		}
    	}
    	return result;
    }
    /**
     * find a collectable object on a specific location 
     * @param pt the location that you want check
     * @return if found return the object else return null
     */
	public Collectable getCollectable (Point pt) {
	    	
    	Collectable result = null;
    	for (Entity e : entities) {
    		if (e.getPt().equals(pt)) {
    			if (e instanceof Collectable) {
	    			result = (Collectable) e;
	    		}
    		}
    	}
    	return result;
    }
	   
    /**
     * find a wall object on a specific location 
     * @param pt the location that you want check
     * @return if found return the object else return null
     */
	public Wall getWall (Point pt) {
		Wall result = null;
		for (Entity e : entities) {
			if (e.getPt().equals(pt)) {
				if (e.getClass() == Wall.class) {
					result = (Wall) e;
				}
			}	
		}
		return result;
	}
    /**
     * find a boulder object on a specific location 
     * @param pt the location that you want check
     * @return if found return the object else return null
     */
	public Boulder getBoulder (Point pt) {
		Boulder result = null;
		for (Entity e : entities) {
			if (e.getPt().equals(pt)) {
				if (e.getClass() == Boulder.class) {
					result = (Boulder) e;
				}
			}	
		}
		return result;
	}
	
    /**
     * find a switch object on a specific location 
     * @param pt the location that you want check
     * @return if found return the object else return null
     */
	public Switch getSwitch (Point pt) {
		Switch result = null;
		for (Entity e : entities) {
			if (e.getPt().equals(pt)) {
				if (e.getClass() == Switch.class) {
					result = (Switch) e;
				}
			}	
		}
		return result;
	}
	
    /**
     * find a portal object on a specific location 
     * @param pt the location that you want check
     * @return if found return the object else return null
     */
	public Portal getPortal (int id, boolean isA) {
		for (Entity e: entities){
			if (e.getClass() == Portal.class && ((Portal) e).getId() == id && ((Portal) e).isA() != isA){
				return (Portal) e;
			}
		}
		return null;
	}
	
    /**
     * find a throughable object on a specific location 
     * @param pt the location that you want check
     * @return if found return the object else return null
     */
	public Throughable getThroughable (Point pt) {
    	
		Throughable result = null;
    	for (Entity e : entities) {
    		if (e.getPt().equals(pt)) {
    			if (e instanceof Throughable) {
	    			result = (Throughable) e;
	    		}
    		}
    	}
    	return result;
    }
	
    /**
     * find a player object on a specific location 
     * @param pt the location that you want check
     * @return if found return the object else return null
     */
	public Player getPlayer (Point pt) {
		Player result = null;
		for (Entity e : entities) {
			if (e.getPt().equals(pt)) {
				if (e.getClass() == Player.class) {
					result = (Player) e;
				}
			}	
		}
		return result;
	}
	
    /**
     * find all entities on a specific location 
     * @param pt the location that you want check
     * @return an entityList contains all of the entities on the pt location
     */
	public ArrayList <Entity> getEntity (Point pt) {
		
		ArrayList <Entity> result = new ArrayList <Entity> ();
		for (Entity e : entities) {
			if (e.getPt().equals(pt)) {
				result.add(e);
			}
		}
		return result;
	}
	

	/**
	 * get all the boulder object in the entity list
	 * @return an boulderList contains all of the boulders in the entities
	 */
	public ArrayList <Boulder> getBoulderList () {
		ArrayList <Boulder> result = new ArrayList <Boulder> ();
		for (Entity e : this.entities) {
			if (e.getClass() == Boulder.class) {
				result.add((Boulder) e);
			}
		}
		
		return result;
	}
	
	/**
	 * get all the switch objects in the entities
	 * @return an SwitchList contains all of the switches in the entities
	 */
	public ArrayList <Switch> getSwitchList () {
		ArrayList <Switch> result = new ArrayList <Switch> ();
		for (Entity e : this.entities) {
			if (e.getClass() == Switch.class) {
				result.add((Switch) e);
			}
		}
		
		return result;
	}
	
	/**
	 * check if any boulder is spawn on the switch when dungeon is created
	 * if there is , set the switch state to on
	 */
	public void checkSpawnOnSwitch () {
		ArrayList <Boulder> boulders = getBoulderList ();
		ArrayList <Switch> switches = getSwitchList ();
		for (Boulder b : boulders) {
			
			for (Switch s : switches) {
				if (s.getPt().equals(b.getPt())) {
					s.setState(1);
				}
			}
		}
		
		
		
	}
	
	/**
	 * get the entities list
	 * @return eneities 
	 */
	public List<Entity> getEntities() {
		return entities;
	}

	/**
	 * set the win status to win and print win
	 * disable the player movement
	 */
	public void win(){
		winStatus.set(1);
    	System.out.println("WIN!!");
    	player.setDisable(true);
	}

	/**
	 * set the lose status to lose and print lose
	 * disable the player movement
	 * remove the player from the dungeon
	 */
	public void lose() {
		winStatus.set(-1);
		player.setDisable(true);
		player.setPt(new Point(-1, -1));
		
    	System.out.println("LOSE!!");
	}
	
	/**
	 * get the mainGoal
	 * @return a maingoal
	 */
	public Goal getMainGoal() {
		return this.mainGoal;
	}
	/**
	 * set a main goal
	 * @param g the goal you want to set
	 */
	public void setMainGoal(Goal g) {
		this.mainGoal = g;
	}
	
	/**
	 * allows the controller can track if the goal has changer
	 * @return booleanpropery
	 */
	public BooleanProperty getGoalChangedFlag() {
		return goalChangedFlag;
	}
	
	/**
	 * allows the controller can track if player has win the game
	 * @return if win or not
	 */
	public IntegerProperty getWinStatus() {
		return winStatus;
	}

	/**
	 * indicates if any changes has occur in the goal status 
	 */
	public void updateGoal() {
		this.goalChangedFlag.set(goalChangedFlag.get() ^ true);
	}



}
