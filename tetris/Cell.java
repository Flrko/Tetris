
package tetris;

/**
 *
 * @author user
 */
public class Cell extends GridObject {    
    private int state;
    
    /**
     * «начени¤ клетки в сетке
     */  
    public final int GRID_TAKEN = 1; // летка зан¤та
    public final int GRID_BLANK = 0; // летка свободна

    public Cell(int x, int y) {
        super(x, y);
    }

    public int getState() {
        return state;
    }
        
}
