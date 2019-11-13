package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Door extends Entity implements Throughable {

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

        switch (dir){
            case "up":
                ((Entity) m).setPt(getPt().getUp());
                break;

            case "down":
                ((Entity) m).setPt(getPt().getDown());
                break;

            case "left":
                ((Entity) m).setPt(getPt().getLeft());
                break;

            case "right":
                ((Entity) m).setPt(getPt().getRight());
                break;
        }
		
	}


    @Override
    public void interact(Player p, String direction) {
        // if door is closed and player has the key
        if (!passable(null, null) && p.hasKey(id)){
            canPass.set(true);
            p.consumeKey(id);
            through(p, direction);

        // if door is opened
        } else if (passable(null, null)) {
            through(p, direction);
        }

        // else do nothing

    }

}
