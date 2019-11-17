package unsw.dungeon;

public interface Observer {
	/**
	 * subscribe the subject
	 * @param s the subject you want to subscribe
	 */
    public void subscript(Subject s);
    /**
     * update the subject status 
     * @param s the subject you have subscribed
     */
    public void update(Subject s);
}
