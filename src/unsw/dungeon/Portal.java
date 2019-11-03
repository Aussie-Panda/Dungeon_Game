package unsw.dungeon;

import java.util.ArrayList;

public class Portal extends Entity implements Throughable {

    int id;
    boolean isA = true;
    Dungeon dungeon;

    public Portal (Dungeon dungeon, int id, boolean isA, int x, int y){
        super(x, y);
        this.dungeon = dungeon;
        this.id = id;
        this.isA = isA;

    }

    // find valid Direction clock-wisely from a portal
    private Point validDirection(Portal por){
        ArrayList <Point> dir = new ArrayList<Point>();
        dir.add(por.getPt().getUp());
        dir.add(por.getPt().getRight());
        dir.add(por.getPt().getDown());
        dir.add(por.getPt().getLeft());
        for (Point pt : dir){
            ArrayList<Entity> eList = dungeon.getEntity(pt);
            if (pt.getY() < dungeon.getHeight()
                    && pt.getY() >= 0
                    && pt.getX() < dungeon.getWidth()
                    && pt.getX() >= 0
                    && passable(dungeon, pt)){
                    return pt;
                }
        }
        return null;
    }

    @Override
    public void through(Movable p, String dir) {
        // get the corresponding portal
        Portal B  = dungeon.getPortal(id, !isA);
        Point pt = validDirection(B);
        
        
        if (pt != null) {
        	
        	Collectable c = dungeon.getCollectable(pt);
        	if (c != null && (p.getClass() == Player.class)) {
            	((Entity) c).interact((Player) p, dir);
            	return;
            }
        	
        	((Entity) p).setPt(pt);
        }
        

    }

    @Override
    public void interact(Player p, String direction) {
        through(p, direction);
    }

    public int getId() {
        return id;
    }

    public boolean isA() {
        return isA;
    }
    
    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }

}
