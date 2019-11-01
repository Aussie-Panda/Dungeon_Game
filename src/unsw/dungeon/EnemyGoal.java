package unsw.dungeon;

public class EnemyGoal implements Goal, Observer {

    private Subject subject;

    public EnemyGoal(Subject s) {
        this.subject = s;
    }

    @Override
    public void subscript(Subject s) {

    }

    @Override
    public void update() {

    }

    @Override
    public boolean isComplete() {
        return false;
    }
}
