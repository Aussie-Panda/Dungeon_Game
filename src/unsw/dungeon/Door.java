package unsw.dungeon;

public class Door extends Entity implements Throughable {

    Dungeon dungeon;
    int id;
    Key key;

    public Door (Dungeon dungeon, int id, int x, int y, Key key){
        super(x, y);
        this.dungeon = dungeon;
        this.id = id;
        
    }

	// TODO
	public boolean canPass() {
		return false;
	}

	@Override
	public void through() {
		// TODO Auto-generated method stub
		
	}
}
