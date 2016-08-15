
package tetris;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class ShapesCreator {
        
    public Shape makeShape(ShapeSetup setup, int x, int y, Color color) {
        List<Point> cellsMap = new LinkedList<>();
        for (int[] i : setup.coordinates) {
            cellsMap.add(new Point(i[0], i[1]));
        }
        
        Color setColor = color;
        if (color == null) {
            setColor = defaultColor();
        }
        
        return new Shape(cellsMap, x, y, setColor);
    }

      
    private Color defaultColor() {
        return new Color(50, 150, 40);
    }
    
    public ShapesCreator() {
    }    
}
