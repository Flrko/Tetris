
package tetris;

import java.awt.Color;

/**
 * Клетка
 * @author user
 */
public class Cell extends GridObject {    
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
        return "Cell{" + "x = " + getX() + " y = " + getY() + " color = " + color + '}';
    }       
}
