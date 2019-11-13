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

    public String reverseDir (String dir) {
    	if (dir == "up") return "down";
    	if (dir == "down") return "up";
    	if (dir == "right") return "left";
    	if (dir == "left") return "right";
    	if (dir == "equal") return "equal";
		return null;
    }
    
    
    public void enemyMoveY (String dir) {
    	if (dir == "up") {
    		enemy.moveUp();
    	} else if (dir == "down") {
    		enemy.moveDown();
    	} else if (dir == "equal") {
    		System.out.println("y equal move x");
    		enemyMoveX(reverseDir(enemy.getPlayerXDirection ()));
    	}
    }
    
    public void enemyMoveX (String dir) {
    	if (dir == "left") {
    		enemy.moveLeft();
    	} else if (dir == "right") {
    		enemy.moveRight();
    	}  else if (dir == "equal") {
    		System.out.println("x equals move y");
    		enemyMoveY(reverseDir(enemy.getPlayerYDirection ()));
    	}
    }

    
    
	@Override
	public void controlMovement() {
		//Point playerPos = player.getPt();
        String xDir = reverseDir(enemy.getPlayerXDirection ());
        String yDir = reverseDir(enemy.getPlayerYDirection ());
        boolean xBlocked = enemy.isBlocked(xDir);
        boolean yBlocked = enemy.isBlocked(yDir);
        boolean xReversed = enemy.isBlocked(reverseDir(xDir));
        boolean yReversed = enemy.isBlocked(reverseDir(yDir));
        
        
        // Priority: not Block > xDir > yDir
        if (xDir.contentEquals("equal")) {
        	if (yBlocked) {
        		//TODO
        	}
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
        else if (xBlocked && yBlocked) {
        	
        	 if (yReversed && !xReversed)  {
             	enemyMoveX(reverseDir(xDir));
             }
        	 else if (!yReversed && xReversed)  {
             	enemyMoveY(reverseDir(yDir));
             	
             } 
             
             else if (!yReversed && !xReversed) {
             	enemyMoveX(reverseDir(xDir));
             }
        	 
        }
	}
}
