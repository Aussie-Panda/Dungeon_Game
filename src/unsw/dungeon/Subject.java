package unsw.dungeon;

public interface Subject {
	/**
	 * sttach the obsever to observe
	 * @param o
	 */
    public void attachObserver(Observer o);
    /**
     * notify the obsever of the changes
     */
    public void notifyObserver();
}
