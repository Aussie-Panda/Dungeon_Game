package unsw.dungeon;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Point {

    private IntegerProperty x, y;

    public Point(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
		
    }

    public Point getUp(){
        return new Point(getX(), getY() - 1);
    }

    public Point getDown(){
        return new Point(getX(), getY() + 1);
    }

    public Point getLeft(){
        return new Point(getX() - 1, getY());
    }

    public Point getRight(){
        return new Point(getX() + 1, getY());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;

        } else if (obj.getClass() != this.getClass()){
            return false;

        } else if (((Point) obj).getX() != this.getX() || ((Point) obj).getY() != this.getY()){
            return false;
        }

        return true;
    }

    public int getX() {
        return x().get();
    }

    public int getY() {
        return y().get();
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    public void setUp() {
        y().set(getY() - 1);
    }

    public void setDown() {
        y().set(getY() + 1);
    }

    public void setLeft() {
        x().set(getX() - 1);
    }

    public void setRight() {
        x().set(getX() + 1);
    }
    
    public void setPt(Point pt) {
    	x().set(pt.getX());
    	y().set(pt.getY());
    }


}
