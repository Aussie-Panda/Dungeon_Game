package unsw.dungeon;

public interface Consumable {
	/**
	 * allows player to consume the object
	 * @param p the player who wants to consume the object
	 */
    public void consume(Player p);

}
