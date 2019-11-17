package unsw.dungeon;

public interface Floor {
	/**
	 * change the floor object state
	 * @param state the state of the switch
	 */
	public void trigger(int state);
}
