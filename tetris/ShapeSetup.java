/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

/**
 * Сетапы фигур.
 * @author One
 */
public enum ShapeSetup {
    Z_SHAPE (new int[][] {{-1,1} , {0,1} , {0,0} , {1,0}}),     
    S_SHAPE (new int[][] {{-1,0} , {0,0} , {0,1} , {1,1}}),      //S
    STICK_SHAPE (new int[][] {{-2,0} , {-1,0} , {0,0} , {1,0}}), //Палка
    SQUARE_SHAPE (new int[][] {{0,1} , {1,1} , {0,0} , {1,0}}),  //Квадрат
    CRANE_SHAPE (new int[][] {{1,1} , {0,1} , {0,0} , {0,-1}}),  //Г
    POINT_SHAPE (new int[][] {{1,1}});  
    public int[][] coordinates;
    
    private ShapeSetup (int[][] coordinates) {
        this.coordinates = coordinates;
    }
}
