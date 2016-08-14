
package tetris;

import java.awt.Color;

/**
 *
 * @author user
 */
public class Cell extends GridObject {    
    private int state;
    
    /**
     * «начени¤ клетки в сетке
     */  
    public final int GRID_TAKEN = 1; // летка зан¤та
    public final int GRID_BLANK = 0; // летка свободна

    private Color color;

    public Color getColor() {
        return color;
    }
        
    public Cell(int x, int y) {
        super(x, y);
    }
    
    public Cell() {
        super();
    }

    public int getState() {
        return state;
    }
    
    public void setCoords(Point point) {
        setX(point.getX());
        setY(point.getY());
    }
    
    public Point getPoint() {
        return new Point(getX(), getY());
    }
        
}
