
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
    
    public void setColor(Color color) {
        this.color = color;
    }
    
    public Point getPoint() {
        return new Point(getX(), getY());
    }

    @Override
    public String toString() {
        //return "Cell{" + "state=" + state + ", GRID_TAKEN=" + GRID_TAKEN + ", GRID_BLANK=" + GRID_BLANK + ", color=" + color + '}';
        return "Cell{" + "x = " + getX() + " y = " + getY() + " color = " + color + '}';
    }       
}
