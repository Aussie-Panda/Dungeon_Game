package unsw.dungeon;

public class Door extends Entity implements Throughable {

    Dungeon dungeon;
    int id;
    boolean canPass = false;

    public Door (Dungeon dungeon, int id, int x, int y){
        super(x, y);
        this.dungeon = dungeon;
        this.id = id;
        
    }

    @Override
    public boolean passable(Dungeon d, Point pt) {
        return canPass;
    }

    public boolean canPass() {
		return canPass;
	}

	// pass through the player to a point
	@Override
	public void through(Player p, String dir) {
        switch (dir){
            case "up":
                p.setPt(getPt().getUp());
                break;

            case "down":
                p.setPt(getPt().getDown());
                break;

            case "left":
                p.setPt(getPt().getLeft());
                break;

            case "right":
                p.setPt(getPt().getRight());
                break;
        }
		
	}


    @Override
    public void interact(Player p, String direction) {
        // if door is closed and player has the key
        if (!canPass && p.hasKey(id)){
            canPass = true;
            open();
            through(p, direction);

        // if door is opened
        } else if (canPass) {
            through(p, direction);
        }

        // else do nothing

    }

    // change UI
    private void open() {

    }
}
