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
            if (eList.isEmpty()) {
                if (pt.getY() < dungeon.getHeight()
                        && pt.getY() >= 0
                        && pt.getX() < dungeon.getWidth()
                        && pt.getX() >= 0){
                    return pt;
                }
            } else {


            }
        }

            if ((getY() < dungeon.getHeight() - 1) && (passable(target))) {
                for (Entity e : eList) {
                    if (!(e instanceof Floor)) {
                        e.interact(this, "down");
                    }

                }
            }
        }
    }

    @Override
    public void through(Movable p) {
        // get the corresponding portal
        Portal B  = dungeon.getPortal(id, !isA);

        ((Entity) p).setPt(B.getPt());
    }

    @Override
    public void interact(Player p, String direction) {
        through(p);
    }

    public int getId() {
        return id;
    }

    public boolean isA() {
        return isA;
    }

}
