
package tetris;

/**
 *
 * @author user
 */
public abstract class GridObject {
    private int x;
    private int y;
    
    public GridObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GridObject(int[] coords) {
        
    }

    public GridObject() {
        this.x = 0;
        this.y = 0;
    }
    
    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
        
}
