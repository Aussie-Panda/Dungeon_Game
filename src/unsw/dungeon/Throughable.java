package unsw.dungeon;

public interface Throughable {
	
	/**
	 * allows movable object to move through the door
	 * @param m movable object that wants to throught the throughable
	 * @param dir the diection that is heading
	 */
	public void through(Movable m, String dir);
	
}
