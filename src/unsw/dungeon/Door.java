package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity implements Throughable {

	/**
	 * A Door class which allows player to interact with Door
	 * and can triggers switch with boulder
	 * @author Yanning Cao
	 * @author Katrina Ding
	 */

    private Dungeon dungeon;
    private int id;
    protected BooleanProperty canPass = new SimpleBooleanProperty(false);

    public Door (Dungeon dungeon, int id, int x, int y){
        super(x, y);
        this.dungeon = dungeon;
        this.id = id;
        
    }

    @Override
    public boolean passable(Dungeon d, Point pt) {
        return canPass.get();
    }

	// pass through the player to a point
	@Override
	public void through(Movable m, String dir) {
		Point dest = this.getPt();
        
    	((Entity) m).setPt(dest);
    	Collectable c = dungeon.getCollectable(dest);
    	if (c != null && (m.getClass() == Player.class)) {
        	((Entity) c).interact((Player) m, dir);
        	return;
        }
        	
        
		
	}


    @Override
    public void interact(Player p, String direction) {
        // if door is closed and player has the key
        if (!passable(null, null) && p.hasKey(id)){
            canPass.set(true);
            p.consumeKey(id);
            //through(p, direction);

        // if door is opened
        } else if (passable(null, null)) {
            through(p, direction);
        }

        // else do nothing

    }
    /**
     * get the door id
     * @return the id of the door
     */
    public int getId() {
    	return id;
    }

}
