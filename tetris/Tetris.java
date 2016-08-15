
package tetris;

/**
 *
 * @author user
 */
public class Tetris {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //ShapesCreator creator = new ShapesCreator();
        //drow();
        startTetris();
    }
    
    public static void drow() {
//        Grid g = new Grid(20, 20);
//        ShapesCreator creator = new ShapesCreator();
//        Shape sh = creator.makeShape(ShapeSetup.Z_SHAPE, 10, 10);
//        g.setTakenCells(sh.getCellsList());
//        g.drowConsole();
    }
    
    public static void startTetris() {
        TetrisFrame tf = new TetrisFrame();
        tf.setVisible(true);
    }
    
}
