
package tetris;

import java.util.LinkedList;
import java.util.List;

/**
 * Группа клеток (фигура)
 * @author user
 */
public class Shape {
    private List<Cell> cellsList;
    private List<Point> cellsMap;
    
    private int x = 0;
    private int y = 0;

    public List<Cell> getCellsList() {
        return cellsList;
    }    

    /**
     * Инициализация с использованием двумерного массива с координатами клеток.
     * @param cellsMap относительные координаты клеток
     * @param x координаты фигуры по оси X
     * @param y координаты фигуры по оси Y
     */
    public Shape(List<Point> cellsMap, int x, int y) {        
        this.x = x;
        this.y = y;        
        this.cellsMap = cellsMap;        
        
        cellsList = new LinkedList<>();
        for (int i = 0; i < cellsMap.size(); i++) {
            cellsList.add(new Cell());
        }
        
        updateCellsParams();        
    }
        
    /**
     * Поворот фигуры вправо
     * @return фигура, повернутая вправо
     */
    public Shape rotateRight() {
        List<Point> newCellsMap = new LinkedList<>();
        for (int i = 0; i < getCellsAmmount(); i++) {
            newCellsMap.add(i, new Point(cellsMap.get(i).getY(), -cellsMap.get(i).getX()));
        }        
        return new Shape(newCellsMap, getX(), getY());
    }

    /**
     * Устанавливает параметры клеток в соответствии с текущим состоянием группы (фигуры).
     */
    private void updateCellsParams() {
        for (int i = 0; i < getCellsAmmount(); i++) {
            cellsList.get(i).setCoords(new Point(x + cellsMap.get(i).getX(), y + cellsMap.get(i).getY()));            
        }
    }
    
    /**
     * Возвращает количество клеток в группе
     * @return количество клеток
     */
    public int getCellsAmmount() {
        return cellsMap.size();
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
}
