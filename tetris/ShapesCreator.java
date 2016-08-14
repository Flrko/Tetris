
package tetris;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class ShapesCreator {
        
    public Shape makeShape(ShapeSetup setup, int x, int y) {
        List<Point> cellsMap = new LinkedList<>();
        for (int[] i : setup.coordinates) {
            cellsMap.add(new Point(i[0], i[1]));
        }
        return new Shape(cellsMap, x, y);
    }

    public ShapesCreator() {
    }    
}
