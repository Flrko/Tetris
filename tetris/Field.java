/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author user
 */
public class Field {
    
    private final int xLength;
    private final int yLength;
    
    
    /**
     * Массив, описывающий игровую сетку.
     * Индексы соответствуют координатам клетки.
     * Значения соответствуют состоянию клетки.
     */
    private int[][] grid;
    public int[][] getGrid() {
        return grid;
    }    

    /**
     * Значения клетки в сетке
     */    
    public final int GRID_TAKEN = 1; //Клетка занята
    public final int GRID_BLANK = 0; //Клетка свободна
    
    /**
     * Конструктор
     * @param xLength
     * @param yLength 
     */
    public Field(int xLength, int yLength) {
        this.xLength = xLength;
        this.yLength = yLength;
        
        grid = new int[this.xLength][this.yLength];
        Arrays.fill(grid, GRID_BLANK);
    }
    
    int setNewBlock(List<Cell> cells) {
        for (Cell cell : cells) {
            if (!checkPointOutOfBounds(point) || !checkPointCollision(point)) {
                getGrid()[point.getX()][point.getY()] = 1;
            }
        }
    }
    
    
    /**
     * Проверка точки на выход за границы сетки
     * @param checkingPoint
     * @return 
     */
    private boolean checkPointOutOfBounds(Cell checkingPoint) {
        int x = checkingPoint.getX();
        int y = checkingPoint.getY();
                
        return x > xLength || y > yLength || x < 0 || y < 0;
    }
    
    /**
     * Проверка 
     * @param checkingPoint
     * @return 
     */
    private boolean checkPointCollision(Cell checkingPoint) {
        int x = checkingPoint.getX();
        int y = checkingPoint.getY();
        
        return getGrid()[x][y] != 0;
    }
    
}
