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
 *
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        if (entity.getClass() == Player.class) setPlayer((Player) entity);
    }
    
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
    
    // if find a movable return else, null
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

	public Portal getPortal (int id, boolean isA) {
		for (Entity e: entities){
			if (e.getClass() == Portal.class && ((Portal) e).getId() == id && ((Portal) e).isA() != isA){
				return (Portal) e;
			}
		}
		return null;
	}
	

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
	
	
	public ArrayList <Entity> getEntity (Point pt) {
		
		ArrayList <Entity> result = new ArrayList <Entity> ();
		for (Entity e : entities) {
			if (e.getPt().equals(pt)) {
				result.add(e);
			}
		}
		return result;
	}
	
	// get all the boulder object in the entity list
	public ArrayList <Boulder> getBoulderList () {
		ArrayList <Boulder> result = new ArrayList <Boulder> ();
		for (Entity e : this.entities) {
			if (e.getClass() == Boulder.class) {
				result.add((Boulder) e);
			}
		}
		
		return result;
	}
	
	// get all the switch object in the entity list
	public ArrayList <Switch> getSwitchList () {
		ArrayList <Switch> result = new ArrayList <Switch> ();
		for (Entity e : this.entities) {
			if (e.getClass() == Switch.class) {
				result.add((Switch) e);
			}
		}
		
		return result;
	}
	
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
	

	public List<Entity> getEntities() {
		return entities;
	}

	public void win(){
		winStatus.set(1);
    	System.out.println("WIN!!");
	}

	public void lose() {
		winStatus.set(-1);
    	System.out.println("LOSE!!");
	}
	
	public Goal getMainGoal() {
		return this.mainGoal;
	}
	
	public void setMainGoal(Goal g) {
		this.mainGoal = g;
	}
	
	
	public BooleanProperty getGoalChangedFlag() {
		return goalChangedFlag;
	}
	
	public IntegerProperty getWinStatus() {
		return winStatus;
	}

	public void updateGoal() {
		this.goalChangedFlag.set(goalChangedFlag.get() ^ true);
	}



}
