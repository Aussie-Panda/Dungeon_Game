package unsw.dungeon;

public interface Goal {
	
	/**
	 * Check if the current Goal is completed
	 * @return status indicating if the coal is completed
	 */
    public boolean isComplete();
    
    /**
     * indicating if the current Goal is the top-level goal (i.e. the goal need to complete to win)
     * @return boolean value indicating if it's the main goal
     */
    public boolean isMain();
    
    /**
     * Set the parent of the goal if the goal is inherited in another goal (i.e multi-goal)
     * @param g the multi-goal that wants to set as parent
     */
    public void setParent(Goals g);
    
    /**
     * set the current goal as the main goal
     */
    public void setMain();
    
    /** 
     * get the name of this goal, will be display on the user interface
     * @return the name of the goal
     */
    public String getName();
    
    /** 
     * get the number of incomplete items, will be displayed on the user interface
     * @return the number of incomplete items.
     */
    public int getNum();

}
