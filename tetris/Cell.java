/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

/**
 *
 * @author user
 */
public class Cell extends GridObject {    
    private int state;
    
    /**
     * Значения клетки в сетке
     */  
    public final int GRID_TAKEN = 1; //Клетка занята
    public final int GRID_BLANK = 0; //Клетка свободна

    public Cell(int x, int y) {
        super(x, y);
    }

    public int getState() {
        return state;
    }
        
}
