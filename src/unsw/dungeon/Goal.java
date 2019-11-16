package unsw.dungeon;

public interface Goal {

    public boolean isComplete();
    public boolean isMain();
    public void setParent(Goals g);
    public void setMain();
    public String getName();
    public int getNum();

}
