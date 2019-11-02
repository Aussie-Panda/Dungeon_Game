package unsw.dungeon;

public class Tressure extends Entity implements Collectable {

    Dungeon dungeon;
    private int numOftreasure;

    public Tressure (Dungeon dungeon, int x, int y){
        super(x, y);
        this.dungeon = dungeon;
        this.numOftreasure = 0;
    }

    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }
    
	@Override
	public void interact(Player p) {
		// TODO Auto-generated method stub
		collect(p);
	}
    
	@Override
	public void collect(Player p) {

		p.setTreasure(p.getTreasure()+1);
		this.numOftreasure = p.getTreasure();
		//remove from dungeon list
		dungeon.removeEntity(this);

	}

	
	
	
	public int getNumOftreasure() {
		return numOftreasure;
	}

	public void setNumOftreasure(int numOftreasure) {
		this.numOftreasure = numOftreasure;
	}
}
