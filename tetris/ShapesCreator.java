
package tetris;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class ShapesCreator {
        
    public Shape makeShape(ShapeSetup setup) {
        return new Shape(setup.coordinates);
    }

    public ShapesCreator() {
    }    
}
