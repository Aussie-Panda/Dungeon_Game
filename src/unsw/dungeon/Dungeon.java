/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

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

	public List<Entity> getEntities() {
		return entities;
	}

	public void win(){
    	System.out.println("WIN!!");
	}

	public void lose() {
    	System.out.println("LOSE!!");
	}



}
