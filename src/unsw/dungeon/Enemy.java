package unsw.dungeon;

public class Enemy extends Entity implements Movable {

    Dungeon dungeon;
    Player player;
    EnemyState state;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
        this.state = new TracingState(this.player); // tracing state by default
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }

    public void setState(EnemyState state) {
        this.state = state;
    }
}
