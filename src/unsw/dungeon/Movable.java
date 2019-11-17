package unsw.dungeon;

public interface Movable {

	/**move the movable object upward
	 */
    public void moveUp();
    /**
     * move the movable object downward
     */
    public void moveDown();
    /**
     * move the movable object leftward
     */
    public void moveLeft();
    /**
     * move the movable object right
     */
    public void moveRight();
}
