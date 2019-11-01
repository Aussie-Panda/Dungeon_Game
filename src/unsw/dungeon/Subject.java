package unsw.dungeon;

public interface Subject {
    void attachObserver(Observer o);
    void removeObserver(Observer o);
}
