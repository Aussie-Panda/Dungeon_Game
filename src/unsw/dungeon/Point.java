package unsw.dungeon;

public class Point {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point getUp(){
        return new Point(x, y+1);
    }

    public Point getDown(){
        return new Point(x, y-1);
    }

    public Point getLeft(){
        return new Point(x-1, y);
    }

    public Point getRight(){
        return new Point(x+1, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;

        } else if (obj.getClass() != this.getClass()){
            return false;

        } else if (((Point) obj).getX() != this.x && ((Point) obj).getY() != this.y){
            return false;
        }

        return true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setUp() {
        this.y++;
    }

    public void setDown() {
        this.y--;
    }

    public void setLeft() {
        this.x--;
    }

    public void setRight() {
        this.x++;
    }

}
