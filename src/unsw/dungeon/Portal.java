package unsw.dungeon;

import java.util.ArrayList;

public class Portal extends Entity implements Throughable {

    private int id;
    private boolean isA = true;
    private Dungeon dungeon;

    public Portal (Dungeon dungeon, int id, boolean isA, int x, int y){
        super(x, y);
        this.dungeon = dungeon;
        this.id = id;
        this.isA = isA;

    }

    
    /**
     * find valid landing Direction clock-wisely from a portal (clock-wise)
     * @param por the portal you want to check
     * @return the first found valid point
     */
    private Point validDirection(Portal por){
        ArrayList <Point> dir = new ArrayList<Point>();
        if(por  == null) System.out.println("portal is null");
        dir.add(por.getPt().getUp());
        dir.add(por.getPt().getRight());
        dir.add(por.getPt().getDown());
        dir.add(por.getPt().getLeft());
        
        ArrayList <Entity> eList;
        boolean result;
        for (Point pt : dir){
        	result = true;
            if (pt.getY() < dungeon.getHeight()
                    && pt.getY() >= 0
                    && pt.getX() < dungeon.getWidth()
                    && pt.getX() >= 0){
            	eList = dungeon.getEntity(pt);
            	for (Entity e : eList) result = result && e.passable(dungeon, pt);
                if (result) return pt;
            }
        }
        return null;
    } 

    @Override
    public void through(Movable p, String dir) {
        // get the corresponding portal
        Portal B = dungeon.getPortal(id, isA);
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

    /**
     * get the id of the portal
     * @return the id of the portal
     */
    public int getId() {
        return id;
    }

    /**
     * check if the portal is entrance or exit
     * @return if it is entrance or exit
     */
    public boolean isA() {
        return isA;
    }
    
    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }

}
