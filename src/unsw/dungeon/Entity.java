package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 * @author Yanning Cao
 * @author Katrina Ding
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private Point pt;

    /**
     * allows player to interact with the object
     * @param p
     */
    public void interact (Player p) {
    	
    }
    
    /**
     * state if the entity is passable
     * @param d the dungeon that the entity is locate
     * @param pt the location of the entity
     * @return true is can be passed false if not
     */
    public abstract boolean passable(Dungeon d, Point pt);
    
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        pt = new Point(x, y);
    }

    public IntegerProperty x() {
        return pt.x();
    }

    public IntegerProperty y() {
        return pt.y();
    }

    public int getY() {
        return pt.getY();
    }

    public int getX() {
        return pt.getX();
    }

	public Point getPt() {
		return pt;
	}

	public void setPt(Point pt) {
		this.pt.setPt(pt);
	}

	/**
	 * allows player to interact with the object
     * @param p
	 */
	public abstract void interact(Player p, String direction);
	
	
}
