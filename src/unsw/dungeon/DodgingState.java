package unsw.dungeon;

public class DodgingState implements EnemyState {


    private Enemy enemy;

    public DodgingState(Enemy enemy) {
        this.enemy = enemy;
    }


    @Override
    public String getState() {
        return "Dodging";
    }
    
    /**
     * reverse the dirction
     * @param dir the direction needs to be reversed
     * @return
     */
    public String reverseDir (String dir) {
    	if (dir == "up") return "down";
    	if (dir == "down") return "up";
    	if (dir == "right") return "left";
    	if (dir == "left") return "right";
    	if (dir == "equal") return "equal";
		return null;
    }
    
    /**
     * enemy move along the y axis
     * @param dir the dirction you want to move
     */
    public void enemyMoveY (String dir) {
    	if (dir == "up") {
    		enemy.moveUp();
    	} else if (dir == "down") {
    		enemy.moveDown();
    	}
    }
    
    /**
     * enemy move along the x axis
     * @param dir the direction you want to move
     */
    public void enemyMoveX (String dir) {
    	if (dir == "left") {
    		enemy.moveLeft();
    	} else if (dir == "right") {
    		enemy.moveRight();
    	}
    }

    
    
	@Override
	public void controlMovement() {
		//Point playerPos = player.getPt();
		//xDir and yDir is suppose to go dir
        String xDir = reverseDir(enemy.getPlayerXDirection ());
        String yDir = reverseDir(enemy.getPlayerYDirection ());
        boolean xBlocked = enemy.isBlocked(xDir);
        boolean yBlocked = enemy.isBlocked(yDir);
        boolean xReversed = enemy.isBlocked(reverseDir(xDir));
        boolean yReversed = enemy.isBlocked(reverseDir(yDir));
        
        
        // pro ai
        if (xDir.equals("equal")) {
        	//System.out.println("x equals");
        	if (enemy.isBlocked("left")) {
        		//System.out.println("move right");
        		enemyMoveX("right");
        	} else {
        		enemyMoveX("left");
        	}
        } else if (yDir.equals("equal")) {
        	if (enemy.isBlocked("up")) {
        		enemyMoveY("down");
        	} else {
        		enemyMoveY("up");
        	}
        }
        // Priority: not Block > xDir > yDir
        else if (xDir.contentEquals("equal")) {

        }
        if (yBlocked && !xBlocked)  {
        	enemyMoveX(xDir);
        }
        
        else if (!yBlocked && xBlocked)  {
        	enemyMoveY(yDir);
        	
        } 
        
        else if (!yBlocked && !xBlocked) {
        	enemyMoveX(xDir);
        }
        
        else if (yBlocked && xBlocked) {
        	if (!xReversed) {
        		enemyMoveX(reverseDir(xDir));
        	} else {
        		enemyMoveY(reverseDir(yDir));
        	}
        	
        }
 
	}
}
