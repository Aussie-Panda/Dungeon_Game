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
        this.state = new TracingState(this); // tracing state by default

    }

    /**
     * set the state of the enemy
     * @param state
     */
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
    	if (goal != null) {
    		goal.update(this);
    	}
        
    }

    @Override
    public void update(Subject s) {
        //convertState();
    	Player p = (Player) s;
    	if (p.getState().equals("invincible") || p.getState().equals("sword_invincible")) {
    		this.setState(new DodgingState(this));
    	} else {
    		this.setState(new TracingState(this));
    	}
    	
        state.controlMovement();
    }

    /**
     * kill the player or killed by the player
     */
    @Override
    public void interact(Player p, String dir) {
        // enemy die if player is invincible or holding sword
        if (!p.getState().equals("normal")){
            dungeon.removeEntity(this);
            getPt().setPt(new Point(-1, -1));
            p.consumeSword();
            notifyObserver();
            
            System.out.println("Slayyyy");
            
        } else if (p.getState().equals("normal")){
            dungeon.lose();
        }
    }

	@Override
    public void moveUp() {
		System.out.println("up");
	
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
		System.out.println("down");
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
		System.out.println("left");
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
		System.out.println("right");
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

    
    /**
     * check the instance on the pt is passable or not
     * @param pt
     * @return
     */
	public boolean passable (Point pt) {
		boolean result = true;
		
		ArrayList <Entity> eList = dungeon.getEntity(pt);
		if (eList.isEmpty()) return true;
		for (Entity e : eList) {
			result = result & e.passable(dungeon,pt);
		}
		return result;
	}

	//================functions for state===========
	
 
	/**
	 * get the player direction on the x axis
	 * @return player's direction in string format
	 */
    public String getPlayerXDirection () {
    	Point playerPos = ((Player) player).getPt();
    	Point enemyPos  = this.getPt();
    	String result = null;
    	if (playerPos.getX() < enemyPos.getX()) {
    		result = "left";
    	} 
    	
    	else if (playerPos.getX() > enemyPos.getX()) {
    		result = "right";
    	} 
    	
    	else {
    		result = "equal";
    	}
		return result;
    }

	/**
	 * get the player direction on the y axis
	 * @return player's direction in string format
	 */
    public String getPlayerYDirection () {
    	Point playerPos = ((Player) player).getPt();
    	Point enemyPos  = this.getPt();
    	String result = null;
    	if (playerPos.getY() < enemyPos.getY()) {
    		result = "up";
    	} 
    	
    	else if (playerPos.getY() > enemyPos.getY()) {
    		result = "down";
    	} 
    	
    	else {
    		result = "equal";
    	}
		return result;
    }
    
    /**
     * check if i'm block in certain direction
     * @param dir the direction you want to check
     * @return true if is blocked else false
     */
    public boolean isBlocked(String dir) {
    	boolean result = true;

    	Point target = null;
    	if (dir == "up") target = this.getPt().getUp();
    	if (dir == "down") target = this.getPt().getDown();
    	if (dir == "right") target = this.getPt().getRight();
    	if (dir == "left") target = this.getPt().getLeft();
  	
    	ArrayList <Entity> eList = dungeon.getEntity(target);
    	for (Entity e : eList) {
    		result = (result && (e.passable(dungeon, null)));
    	}
    	
    	return (!result);
    	
    }
    
    /**
     * enemy move along the y axis
     * @param dir the dirction you want to move
     */
    public void enemyMoveY (String dir) {
    	if (dir == "up") {
    		this.moveUp();
    	} else if (dir == "down") {
    		this.moveDown();
    	} else if (dir == "equal") {
    		enemyMoveX(getPlayerXDirection ());
    	}
    }
    
    /**
     * enemy move along the x axis
     * @param dir the dirction you want to move
     */
    public void enemyMoveX (String dir) {
    	if (dir == "left") {
    		this.moveLeft();
    	} else if (dir == "right") {
    		this.moveRight();
    	}  else if (dir == "equal") {
    		enemyMoveY(getPlayerYDirection ());
    	}
    }

	
	
	
	
	@Override
	public boolean passable(Dungeon d, Point pt) {
		// TODO Auto-generated method stub
		return false;
	}


	
}
