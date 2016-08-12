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
public abstract class GridObject {
    private int x;
    private int y;
    
    public GridObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GridObject(int[] coords) {
        
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
