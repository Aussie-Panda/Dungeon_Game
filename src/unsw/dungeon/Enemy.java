package unsw.dungeon;

public class Enemy extends Entity implements Movable, Observer {

    private Dungeon dungeon;
    private EnemyState state;
    private Subject player;

    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        subscript((Subject) dungeon.getPlayer());
        this.state = new TracingState((Player) this.player); // tracing state by default

    }


    public void convertState() {
        this.state.convertState(this);
    }

    public void setState(EnemyState state) {
        this.state = state;
    }

    @Override
    public void subscript(Subject s) {
        this.player = s;
        s.attachObserver(this);
    }

    @Override
    public void update() {
        convertState();
    }

    @Override
    public void moveUp() {
        if (getY() > 0) getPt().setUp();

    }

    @Override
    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1) getPt().setDown();

    }

    @Override
    public void moveLeft() {
        if (getX() > 0) getPt().setLeft();
    }

    @Override
    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1) getPt().setRight();
    }

}
