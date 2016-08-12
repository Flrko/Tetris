/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class Shape {
    private List<Cell> cellsList;
    
    
    
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
        
}
