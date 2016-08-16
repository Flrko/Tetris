
package tetris;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author user
 */
public class ShapesCreator {
    
    private Random rand;
    private List<Color> colors;
    
    public ShapesCreator() {
        rand = new Random();        
        colors = new LinkedList<>();
        
        initColorsList();
    }
    
    /**
     * Инициализирует список доступных цветов
     */
    private void initColorsList() {        
        colors.add(Color.BLUE);
        colors.add(Color.GREEN);
        colors.add(Color.ORANGE);
        colors.add(Color.PINK);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.GRAY);        
    }
    
    public Shape makeShape(ShapeSetup setup, int x, int y, Color color) {        
        return new Shape(generateCellsMap(setup), x, y, color == null ? defaultColor() : color);
    }

    public Shape makeRandomShape() {
        return makeShape(randomShapeSetup(), 0, 0, randomColor());
    }
      
    private Color randomColor() {
        return colors.get(randomIndex(colors.size()));
    }
    
    private ShapeSetup randomShapeSetup() {
        ShapeSetup[] setups = ShapeSetup.values();        
        return setups[randomIndex(setups.length)];
    }
    
    private List<Point> generateCellsMap(ShapeSetup setup) {
        List<Point> cellsMap = new LinkedList<>();
        for (int[] i : setup.coordinates) {
            cellsMap.add(new Point(i[0], i[1]));
        }
        return cellsMap;
    }
    
    /**
     * Генерирует случайное положительное число от 0 до limit
     * @param limit
     * @return 
     */
    private int randomIndex (int limit) {
        return Math.abs(rand.nextInt() % limit);
    }
    
    private Color defaultColor() {
        return new Color(50, 150, 40);
    }    
}
