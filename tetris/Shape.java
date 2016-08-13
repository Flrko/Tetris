
package tetris;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class Shape {
    private List<Cell> cellsList;
    private int x;
    private int y;
        
    public Shape() {
    }
    
    public List<Cell> getCellsList() {
        return cellsList;
    }    
    
    /**
     * Инициализация с использованием списка клеток.
     * @param cellsList 
     */
    public Shape(List<Cell> cellsList) {
        this.cellsList = cellsList;
    }

    /**
     * Инициализация с использованием двумерного массива с координатами клеток.
     * @param coords 
     */
    public Shape(int[][] coords) {
        cellsList = new LinkedList<>();
        for (int[] coord : coords) {
            cellsList.add(new Cell(coord[0], coord[1]));
        }
    }
    
    /**
     * Поворот фигуры вправо.
     */
    public void rotateRight() {
        for (Cell cell : cellsList) {            
            int oldX = cell.getX();
            cell.setX(cell.getY());
            cell.setY(-oldX);
        }
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
