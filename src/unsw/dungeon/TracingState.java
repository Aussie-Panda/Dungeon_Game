package unsw.dungeon;

import java.util.ArrayList;

public class TracingState implements EnemyState {

    private Enemy enemy;

    public TracingState( Enemy enemy) {
        this.enemy = enemy;
    }


    @Override
    public String getState() {
        return "Tracing";
    }



	@Override
	public void controlMovement() {
		//Point playerPos = player.getPt();
        String xDir = enemy.getPlayerXDirection ();
        String yDir = enemy.getPlayerYDirection ();
        boolean xBlocked = enemy.isBlocked(xDir);
        boolean yBlocked = enemy.isBlocked(yDir);
        
        
        // Priority: not Block > xDir > yDir
        if (yBlocked && !xBlocked)  {
        	enemy.enemyMoveX(xDir);
        }
        
        else if (!yBlocked && xBlocked)  {
        	enemy.enemyMoveY(yDir);
        	
        } 
        
        else if (!yBlocked && !xBlocked) {
        	enemy.enemyMoveX(xDir);
        }
		
	}

}


