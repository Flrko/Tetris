/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author One
 */
public class GameField extends javax.swing.JPanel {

    private final static int FIELD_WIDTH = 20;
    private final static int FIELD_HEIGHT = 20;
    
    private List<Cell> grid;
//    private List<Cell> getGrid() {
//        return grid;
//    }
    
    private Shape droppingShape;
    
    /**
     * Creates new form GameField
     */
    public GameField() {
        
        
        initComponents();
        
        setFocusable(true);
        
        int gridLength = FIELD_WIDTH * FIELD_HEIGHT;
        grid = new ArrayList<>(gridLength);
        for (int i = 0; i < gridLength; i++) {
            grid.add(null);
        }
                
        //~~
        System.out.println("grid size = " + grid.size());
        //~~//~~
        
        test();
    }

    /**
     * Добавление ячейки в сетку
     * @param cells 
     */
    private void addCells(List<Cell> cells) {
        for (Cell cell : cells) {
            grid.add(pointToIndex(cell.getPoint()), cell);
        }
    }
    
    private void addCell(Cell addCell) {
        
        //~~
        System.out.println("Adding new cell to " + pointToIndex(addCell.getPoint()) + " index");
        //~~~
        
        grid.set(pointToIndex(addCell.getPoint()), addCell);
    }
    
    /**
     * Определяет индекс в коллекции, соответствующий координатам клетки .
     * @param point координаты клетки
     * @return 
     */
    private int pointToIndex(Point point) {        
        return point.getY() * FIELD_WIDTH + point.getX();
    }
    
    
    private Cell cellAt(Point point) {
        return grid.get(point.getY() * FIELD_WIDTH + point.getX());
    }
    
    /**
     * Рисует клетку
     * @param g
     * @param x
     * @param y
     * @param color 
     */
    private void drawCell(Graphics g, Point point, Color color) {        
        int x = point.getX() * cellWidth();
        int y = point.getY() * cellHeight();        
        g.setColor(color);
        g.fillRect(x + 1, y + 1, cellWidth() - 2, cellHeight() - 2);
        g.setColor(color.brighter());
        g.drawLine(x, y + cellHeight() - 1, x, y);
        g.drawLine(x, y, x + cellWidth() - 1, y);
        g.setColor(color.darker());
        g.drawLine(x + 1, y + cellHeight() - 1, x + cellWidth() - 1, y + cellHeight() - 1);
        g.drawLine(x + cellWidth() - 1, y + cellHeight() - 1, x + cellWidth() - 1, y + 1);
    }
    
    public void addShape(Shape shape) {
        
        //~~
        System.out.println(shape);        
        //~~~
        
        List<Cell> cells = shape.getCellsList();
        for (Cell cell : cells) {
            
            //~~
            System.out.println("x!");
            //~~~
            
            if (!checkOutOfField(cell)) {
                
                //~~
                System.out.println("y!");
                //~~~
                
                addCell(cell);
            }
        }
    }
    
    
    private boolean checkOutOfField(Cell cell) {
        return !(cell.getX() < FIELD_WIDTH && cell.getX() > 0 && cell.getY() > 0 && cell.getY() < FIELD_HEIGHT);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension size = getSize();                                               //размер окна
        int boardTop = (int) size.getHeight() - FIELD_HEIGHT * cellHeight();      //

//        for (int i = 0; i < FIELD_HEIGHT; i++) {
//            for (int j = 0; j < FIELD_WIDTH; ++j) {
//                //Shape shape = shapeAt(j, FIELD_HEIGHT - i - 1);
//                Cell cell = cellAt(null);
//                
//                if (shape != Tetrominoes.NoShape) {
//                    drawSquare(g, j * cellWidth(), boardTop + i * cellHeight(), shape);
//                }
//            }
//        }
        
        for (Cell cell : grid) {
            if (cell != null) {
                //~~
                System.out.println("Drawing cell " + cell);
                //~~~

                drawCell(g, cell.getPoint(), cell.getColor());
            }
        }

//        if (curPiece.getShape() != Tetrominoes.NoShape) {
//            for (int i = 0; i < 4; ++i) {
//                int x = curX + curPiece.x(i);
//                int y = curY - curPiece.y(i);
//                drawSquare(g, x * cellWidth(), boardTop + (FIELD_HEIGHT - y - 1) * cellHeight(), curPiece.getShape());
//            }
//        }
    }
    
    /**
     * Ширина изображения ячейки, в соответствии с шириной окна
     * @return 
     */
    public int cellWidth() {
        return (int) getSize().getWidth() / FIELD_WIDTH;
    }
 
    /**
     * Высота изображения ячейки, в соответствии с шириной окна
     * @return 
     */
    public int cellHeight() {
        return (int) getSize().getHeight() / FIELD_HEIGHT;
    }
    
    
    public void test() {
        ShapesCreator creator = new ShapesCreator();
        Shape newShape = creator.makeShape(ShapeSetup.STICK_SHAPE, 5, 10, null);
        addShape(newShape);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
