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
            System.out.println("Slayyyy");
        } else if (p.getState().equals("normal")){
            dungeon.lose();
        }
    }

	@Override
    public void moveUp() {
	
		Point target = getPt().getUp();
		Throughable t = dungeon.getThroughable(target);
		if (getY() <= 0) {
			return;
		}
		ArrayList <Entity> eList = dungeon.getEntity(target);
		if (eList.isEmpty()) {
			// move up
			this.getPt().setUp();
		} else {
			if (passable(target))  {
				if (t != null) {
					t.through(this, "Up");
					return;
				}
				this.getPt().setUp();
			} else if ((dungeon.getPlayer(target) != null)) {
				this.interact((Player) this.player, null);
			}
		}
	}
		
	@Override
    public void moveDown() {
		//
		Point target = getPt().getDown();
		Throughable t = dungeon.getThroughable(target);
		if (getY() >= dungeon.getHeight() - 1) {
			return;
		}
		ArrayList <Entity> eList = dungeon.getEntity(target);
		if (eList.isEmpty()) {
			//
			this.getPt().setDown();
		} else {
			if (passable(target))  {
				if (t != null) {
					t.through(this, "down");
					return;
				}
				this.getPt().setDown();
			} else if ((dungeon.getPlayer(target) != null)) {
				this.interact((Player) this.player, null);
			}
		}
	}

	@Override
    public void moveLeft() {
		//
		Point target = getPt().getLeft();
		Throughable t = dungeon.getThroughable(target);
		if ((getX() <= 0)) {
			return;
		}
		ArrayList <Entity> eList = dungeon.getEntity(target);
		if (eList.isEmpty()) {
			//
			this.getPt().setLeft();
		} else {
			if (passable(target))  {
				if (t != null) {
					t.through(this, "left");
					return;
				}
				this.getPt().setLeft();
			} else if ((dungeon.getPlayer(target) != null)) {
				this.interact((Player) this.player, null);
			}
		}
	}

	@Override
    public void moveRight() {
		//
		Point target = getPt().getRight();
		Throughable t = dungeon.getThroughable(target);
		
		
		if (getX() >= dungeon.getWidth() - 1) {
			return;
		}
		ArrayList <Entity> eList = dungeon.getEntity(target);
		if (eList.isEmpty()) {
			//
			this.getPt().setRight();
		} else {
			if (passable(target))  {
				if (t != null) {
					t.through(this, "right");
					return;
				}
				this.getPt().setRight();
			} else if ((dungeon.getPlayer(target) != null)) {
				this.interact((Player) this.player, null);
			}
		}
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


	@Override
	public boolean passable(Dungeon d, Point pt) {
		// TODO Auto-generated method stub
		return false;
	}

}
