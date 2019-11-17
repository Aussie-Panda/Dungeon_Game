package unsw.dungeon;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Point {

    private IntegerProperty x, y;

    public Point(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
		
    }
    /**
     * create a point on the upward position
     * @return
     */
    public Point getUp(){
        return new Point(getX(), getY() - 1);
    }

    /**
     * create a point on the downward position
     * @return
     */
    public Point getDown(){
        return new Point(getX(), getY() + 1);
    }

    /**
     * create a point on the left position
     * @return
     */
    public Point getLeft(){
        return new Point(getX() - 1, getY());
    }

    /**
     * create a point on the right position
     * @return
     */
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

    /*
     * getX
     */
    public int getX() {
        return x().get();
    }

    /*
     * get Y coordinate
     */
    public int getY() {
        return y().get();
    }

    public IntegerProperty x() {
        return x;
    }

    public IntegerProperty y() {
        return y;
    }

    /**
     * set the point to up position
     */
    public void setUp() {
        y().set(getY() - 1);
    }

    /**
     * set the point to down position
     */
    public void setDown() {
        y().set(getY() + 1);
    }
    
    /**
     * set the point to left position
     */
    public void setLeft() {
        x().set(getX() - 1);
    }
    
    /**
     * set the point to right position
     */
    public void setRight() {
        x().set(getX() + 1);
    }
    
    /**
     * set point to a specific position
     * @param pt the location you wish to set
     */
    public void setPt(Point pt) {
    	x().set(pt.getX());
    	y().set(pt.getY());
    }


}
