
package tetris;

import java.awt.Color;
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
    private Color color;    
    
    
    public List<Cell> getCellsList() {
        return cellsList;
    }    

    /**
     * Инициализация с использованием двумерного массива с координатами клеток.
     * @param cellsMap относительные координаты клеток
     * @param x координаты фигуры по оси X
     * @param y координаты фигуры по оси Y
     * @param color
     */
    public Shape(List<Point> cellsMap, int x, int y, Color color) {        
        this.x = x;
        this.y = y;        
        this.cellsMap = cellsMap;
        this.color = color;
        
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
        return new Shape(newCellsMap, getX(), getY(), getColor());
    }

    /**
     * Устанавливает параметры клеток в соответствии с текущим состоянием группы (фигуры).
     */
    private void updateCellsParams() {
        for (int i = 0; i < getCellsAmmount(); i++) {
            Cell updatingCell = cellsList.get(i);
            updatingCell.setCoords(new Point(x + cellsMap.get(i).getX(), y + cellsMap.get(i).getY()));
            updatingCell.setColor(color);
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

    public Color getColor() {
        return color;
    }
        
    @Override
    public String toString() {
        String retStr = "Shape{";
        String cells = "";
        
        for (Cell cell : getCellsList()) {
            cells += cell.toString() + " : ";
        }
                        
        return retStr + cells + '}';
    }
        
}
