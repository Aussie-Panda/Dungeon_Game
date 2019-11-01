package unsw.dungeon;

public class DodgingState implements EnemyState {

    private Player player;

    public DodgingState(Player player) {
        this.player = player;
    }

    @Override
    public void convertState(Enemy e) {
        e.setState(new TracingState(player));
    }

    @Override
    public String getState() {
        return "Dodging";
    }

}
