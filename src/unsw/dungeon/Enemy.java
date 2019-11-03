package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends Entity implements Movable, Observer, Subject {

    private Dungeon dungeon;
    private EnemyState state;
    private Subject player;
    private Observer goal;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        subscript((Subject) dungeon.getPlayer());
        this.state = new TracingState((Player) this.player); // tracing state by default


    }


    public void convertState() {
        this.state.convertState(this);
    }

    public void setState(EnemyState state) {
        this.state = state;
    }

    @Override
    public void subscript(Subject s) {
        this.player = s;
        s.attachObserver(this);
    }

    @Override
    public void attachObserver(Observer o) {
        goal = o;
    }

    @Override
    public void notifyObserver() {
        goal.update(this);
    }

    @Override
    public void update(Subject s) {
        convertState();
    }

    @Override
    public void interact(Player p, String dir) {
        // enemy die if player is invincible or holding sword
        if (p.getState().equals("invincible") || p.hasSword()){
            dungeon.removeEntity(this);
            notifyObserver();
        } else if (p.getState().equals("normal")){
            dungeon.lose();
        }
    }

    @Override
    public void moveUp() {
        if (getY() > 0) getPt().setUp();

    }

    @Override
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) getPt().setDown();

    }

    @Override
    public void moveLeft() {
        if (getX() > 0) getPt().setLeft();
    }

    @Override
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) getPt().setRight();
    }
    
    
    
	public boolean passable (Point pt) {
		boolean result = true;
		
		ArrayList <Entity> eList = dungeon.getEntity(pt);
		if (eList.isEmpty()) return true;
		for (Entity e : eList) {
			result = result & e.passable(dungeon,pt);
		}
		return result;
	}

}
