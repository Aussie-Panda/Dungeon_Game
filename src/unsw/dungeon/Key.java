package unsw.dungeon;

public class Key extends Entity implements Consumable, Collectable {

    Dungeon dungeon;
    int id;

    public Key(Dungeon dungeon, int id, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;

    }

    @Override
    public void collect() {

    }

    @Override
    public void consume() {

    }
}
