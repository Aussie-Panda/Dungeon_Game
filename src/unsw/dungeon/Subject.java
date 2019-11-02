package unsw.dungeon;

public interface Subject {
    public void attachObserver(Observer o);
    public void notifyObserver();
}
