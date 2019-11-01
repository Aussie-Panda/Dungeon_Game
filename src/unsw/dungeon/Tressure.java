package unsw.dungeon;

public class Tressure extends Entity implements Collectable {

    Dungeon dungeon;

    public Tressure (Dungeon dungeon, int x, int y){
        super(x, y);
        this.dungeon = dungeon;
    }

    @Override
    public void collect() {

    }
}
