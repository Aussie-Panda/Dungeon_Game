package unsw.dungeon;

public class Enemy extends Entity implements Movable, Observer, Subject {

    private Dungeon dungeon;
    private EnemyState state;
    private Subject player;
    private Observer goal;

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
    public void attachObserver(Observer o) {
        goal = o;
    }

    @Override
    public void notifyObserver() {
        goal.update(this);
    }

    @Override
    public void update(Subject s) {
        convertState();
    }

    @Override
    public void interact(Player p) {
        // enemy die if player is invincible
        if (p.getState().equals("invincible")){
            dungeon.removeEntity(this);
            notifyObserver();
        } else {
            dungeon.lose();
        }
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
