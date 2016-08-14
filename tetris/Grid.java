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
 * @author One
 */
public class Grid {
    
    char [][] display;
    int[][] map;

    private final static char EMPTY_CELL = '░';
    private final static char TAKEN_CELL = '█';
    
    
    public Grid(int width, int height) {
        display = new char[height][width];
        map = new int[height][width];
        
        clearDisplay();
    }
    
    public final void clearDisplay() {        
        for (int i = 0; i < display.length; i++) {
            for (int j = 0; j < display[i].length; j++) {
                setDisplayChar(new Point(i, j), EMPTY_CELL);
            }            
        }
    }
    
    public void drowConsole() {
        for (char[] i : display) {
            for (char j : i) {
                System.out.print(j);
            }
            System.out.println();
        }
    }
    
    private void setDisplayChar(Point index, char c) {
        display[index.getY()][index.getX()] = c;
    }
        
    
    public void setTakenCells(List<Cell> cells) {
        for (Cell cell : cells) {
            setDisplayChar(cell.getPoint(), TAKEN_CELL);
        }
    }
}
