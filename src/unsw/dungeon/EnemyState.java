package unsw.dungeon;

public interface EnemyState {

	/**
	 * get the state of the enemy
	 * @return the state of the enemy
	 */
    public String getState();
    /**
     * control the enemy's movement
     */
    public void controlMovement();
    
}
