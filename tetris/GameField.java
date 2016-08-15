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
    private final static Point spawnPoint = new Point(FIELD_WIDTH / 2, 0);
    private List<Cell> grid;
    private Shape fallingShape;
    
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
        System.out.println("Created grid with size = " + grid.size());
        //~~~
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
        //~~
        System.out.printf("Getting cell width coords (%d , %d)\n", point.getX(), point.getY());
        //~~~        
        return grid.get(point.getY() * FIELD_WIDTH + point.getX());
    }
    
    private void drawShape(Graphics g, Shape shape) {
        for (Cell cell : shape.getCellsList()) {
            drawCell(g, cell.getPoint(), cell.getColor());
        }
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
    
    /**
     * Спавнит новую фигуру
     * @param shape 
     */
    private void spawnShape(Shape shape) {
        shape.setCoords(spawnPoint);
        fallingShape = shape;
    }
    
    /**
     * Фиксирует группу клеток (фигуру) в сетке
     * @param shape 
     */
    public void fixShape(Shape shape) {
        //~~
        System.out.println("Adding shape " + shape + " ...");
        //~~~
        
        List<Cell> cells = shape.getCellsList();
        for (Cell cell : cells) {            
            if (!checkOutOfField(cell)) {
                addCell(cell);
                //~~
                System.out.println("Cell " + cell + " added!");
                //~~~
            }
        }
    }

    /**
     * Проверка фигуры на выход за границы поля.
     * @param shape
     * @return true, если хотябы одна ячейка фигуры находится вне поля
     */
    private boolean checkOutOfField(Shape shape) {
        for (Cell cell : shape.getCellsList()) {
            if (checkOutOfField(cell)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Проверка ячейки на выход за границы поля
     * @param cell
     * @return true, если точка находится за границей поля
     */
    private boolean checkOutOfField(Cell cell) {
        return !(cell.getX() < FIELD_WIDTH && cell.getX() > 0 && cell.getY() > 0 && cell.getY() < FIELD_HEIGHT);
    }
    
    /**
     * Проверка совпадения координат клеток фигуры с уже существующими в сетке
     * @param shape
     * @return true, если есть совпадение
     */
    private boolean checkCollision(Shape shape) {
        for (Cell cell : shape.getCellsList()) {
            if (checkCollision(cell)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Проверка совпадения координат клетки с уже существующими в сетке.
     * @param cell
     * @return true, если есть совпадение
     */
    private boolean checkCollision(Cell cell) {
        return cellAt(cell.getPoint()) != null;
    }
    
    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension size = getSize();                                               //размер окна
        int boardTop = (int) size.getHeight() - FIELD_HEIGHT * cellHeight();      //
        
        for (Cell cell : grid) {
            if (cell != null) {
                drawCell(g, cell.getPoint(), cell.getColor());
            }
        }

        if (fallingShape != null) {
            drawShape(g, fallingShape);
        }
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
        
    
    private void moveFallingShape(int xShift, int yShift) {
        Shape testShape = fallingShape.copy();
        
        //~~
        System.out.println("old shape: " + fallingShape);
        System.out.println("new shape: " + testShape);
        //~~~
        
        testShape.shiftCoords(xShift, yShift);
        if ( !checkCollision(testShape) && !checkOutOfField(testShape) ) {
            //~~
            System.out.println("YO!");
            //~~~
            fallingShape = testShape;
        }
    }
    
    public void test() {
        ShapesCreator creator = new ShapesCreator();
        Shape newShape = creator.makeShape(ShapeSetup.STICK_SHAPE, 5, 10, null);
        fixShape(newShape);
        
        spawnShape(creator.makeShape(ShapeSetup.CRANE_SHAPE, 0, 0, null));
        
        repaint();
    }
    
    public void moveFallingShapeDown() {
        moveFallingShape(0, 1);
        repaint();
    }
    
    public void moveFallingShapeRight() {
        moveFallingShape(1, 0);
        repaint();
    }
    
    public void moveFallingShapeLeft() {
        moveFallingShape(-1, 0);
        repaint();
    }
    
    
    public void rotateFallingShape() {
        Shape rotatedShape = fallingShape.rotateLeft();
        if (!checkOutOfField(rotatedShape)) {
            fallingShape = rotatedShape;
        }
        repaint();
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
            .addGap(0, 413, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
