package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public abstract class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private Point pt;

    
    public void interact (Player p) {
    	
    }
    
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

	public abstract void interact(Player p, String direction);
	
	
}
