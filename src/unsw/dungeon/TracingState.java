package unsw.dungeon;

public class TracingState implements EnemyState {

    private Player player;

    public TracingState(Player player) {
        this.player = player;
    }

    @Override
    public void convertState(Enemy e) {
        e.setState(new DodgingState(player));
    }


    @Override
    public String getState() {
        return "Tracing";
    }

}


public system(){
    Player player1 = new Player(new Point(1, 2));
}