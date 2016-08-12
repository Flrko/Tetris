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
public class ShapesCreator {

    //private final Shape ZZShape = new Shape(new );

    private final static int[][] Z_SHAPE_CELLS = {{0,0} , {1,0} , {1,-1} , {2,-1}};
    private final static int[][] FLAT_SHAPE_CELLS = {{0,0} , {1,0} , {2,0} , {3,0}};
    private final static int[][] SQUARE_SHAPE_CELLS = {{0,0} , {1,0} , {0,-1} , {1,-1}};
    private final static int[][] FLAT_SHAPE_CELLS = {{0,0} , {0,1} , {0,2} , {0,3}};
            
    private List<Cell> getZZShapeCellsList() {
        List<Cell> cellList = new LinkedList<>();        
        cellList.add(new Cell(0, 0));
        cellList.add(new Cell(0, 1));
        cellList.add(new Cell(-1, 1));
        cellList.add(new Cell(-1, 2));
        return cellList;
    }
    
            
    public ShapesCreator() {
    }
    
    public Shape makeShape() {
        
    }
    
    
}
