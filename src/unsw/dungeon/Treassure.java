package unsw.dungeon;

public class Treassure extends Entity implements Collectable, Subject {

    Dungeon dungeon;
    private int numOftreasure;
    Observer goal;

    public Treassure(Dungeon dungeon, int x, int y){
        super(x, y);
        this.dungeon = dungeon;
        this.numOftreasure = 0;
    }

    @Override
    public boolean passable (Dungeon d, Point pt) {
    	return true;
    }
    
	@Override
	public void interact(Player p, String direction) {
		// TODO Auto-generated method stub
		collect(p);
		if (direction == "up") {
			p.getPt().setUp();
		}
		
		if (direction == "down") {
			p.getPt().setDown();
		}
		
		if (direction == "left") {
			p.getPt().setLeft();
		}
		
		if (direction == "right") {
			p.getPt().setRight();
		}
	}
	
    
	@Override
	public void collect(Player p) {

		p.setTreasure(p.getTreasure()+1);
		this.numOftreasure = p.getTreasure();
		// notify goal that this tressure has been picked
		notifyObserver();
		//remove from dungeon list
		dungeon.removeEntity(this);

	}

	@Override
	public void attachObserver(Observer o) {
    	goal = o;
	}

	@Override
	public void notifyObserver() {
		goal.update(this);
	}

	public int getNumOftreasure() {
		return numOftreasure;
	}

	public void setNumOftreasure(int numOftreasure) {
		this.numOftreasure = numOftreasure;
	}
}
