package unsw.dungeon;

public class Door extends Entity {

    Dungeon dungeon;
    int id;

    public Door (Dungeon dungeon, int id, int x, int y){
        super(x, y);
        this.dungeon = dungeon;
        this.id = id;
    }

}
