/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author One
 */
public class GameField extends javax.swing.JPanel implements ActionListener {

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    
    private final static int FIELD_WIDTH = 6;
    private final static int FIELD_HEIGHT = 20;
    private final static Point spawnPoint = new Point(FIELD_WIDTH / 2, 1);
    private List<Cell> grid;
    private Shape fallingShape;
    private ShapesCreator shapesCreator;
    private int score = 0;
    private JLabel scoreBar;
    private Timer timer;
    private boolean isPaused;
    
    /**
     * Creates new form GameField
     * @param parent
     */
    public GameField(TetrisFrame parent) {
                
        initComponents();
        
        setFocusable(true);
        
        int gridLength = FIELD_WIDTH * FIELD_HEIGHT;
        grid = new ArrayList<>(gridLength);
        for (int i = 0; i < gridLength; i++) {
            grid.add(null);
        }        
        scoreBar = parent.getScoreBar();
        addKeyListener(new TetrisKeyAdapter());
        shapesCreator = new ShapesCreator();
        isPaused = false;
        timer = new Timer(400, this);
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
    
    private void addCell(Cell cell) {      
        grid.set(pointToIndex(cell.getPoint()), cell);
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
        if (!checkOutOfField(point)) {
            return grid.get(point.getY() * FIELD_WIDTH + point.getX());
        }
        return null;
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
        repaint();
    }
    
    private void spawnRandomShape() {        
        spawnShape(shapesCreator.makeRandomShape());
    }
    
    /**
     * Фиксирует группу клеток (фигуру) в сетке
     * @param shape 
     */
    public void fixShape(Shape shape) {        
        List<Cell> cells = shape.getCellsList();
        for (Cell cell : cells) {            
            if (!checkOutOfField(cell)) {
                addCell(cell);
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
     * @param gridObject
     * @return true, если точка находится за границей поля
     */
    private boolean checkOutOfField(GridObject gridObject) {
        return !(gridObject.getX() < FIELD_WIDTH && gridObject.getX() >= 0 && gridObject.getY() > 0 && gridObject.getY() < FIELD_HEIGHT);
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
    
    /**
     * Проверка линий в указанном промежутке на заполненность
     * @param minY
     * @param maxY 
     */
    private void checkFullLines (int minY, int maxY) {
        //~~
        System.out.println("Checking lines from " + minY + " to " + maxY);
        //~~~
        for (int y = minY; y <= maxY; y++) {
            if (checkLineFilling(y)) {
                //~~
                System.out.println("Removing line with y = " + y );
                //~~~
                for (int x = 0; x < FIELD_WIDTH; x++) {
                    removeCell(new Point(x, y));
                }
                coverGap(y);
                updateScore(1);
            }
        }
        repaint();
    }
    
    
    
    /**
     * Удаляет точку в сетке
     * @param point 
     */
    private void removeCell(Point point) {
        grid.set(pointToIndex(point), null);
    }
    
    /**
     * Проверка линии на заполненность
     * @param y местоположение линиии по оси Y
     * @return true, если линия заполнена
     */
    private boolean checkLineFilling(int y) {
        for (int x = 0; x < FIELD_WIDTH; x++) {
            if (cellAt(new Point(x, y)) == null) {
                return false;
            }
        }
        return true;
    }
    
//    private void removeLine(int y) {
//        for (int x = 0; x < FIELD_WIDTH; x++) {
//            grid.set(pointToIndex(new Point(x, y)), null);
//        }
//    }
        
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
        
    /**
     * Семещает координаты падающей фигуры
     * @param xShift смещение по X
     * @param yShift смещение по Y
     * @return true, если перемещение удалось
     */
    private boolean moveFallingShape(int xShift, int yShift) {
        Shape testShape = fallingShape.copy();                
        testShape.shiftCoords(xShift, yShift);
        if ( !checkCollision(testShape) && !checkOutOfField(testShape) ) {            
            fallingShape = testShape;
            repaint();
            return true;
        }
        return false;
    }
    
    /**
     * Запуск игры
     */
    public void start() {        
        timer.start();
        spawnRandomShape();
    }
    
    /**
     * Функция управления паузой
     */
    private void pause() {
        if (isPaused) {
            timer.start();
            isPaused = false;
            return;
        }
        
        timer.stop();
        isPaused = true;
    }
    
    /**
     * Заполняет разрыв
     * @param gapY местоположение разрыва по Y
     */
    private void coverGap(int gapY) {
        //~~
        System.out.printf("Covering gap in %d ...\n", gapY);
        //~~~
        
        for (int y = gapY - 1; y > 0; y--) {                    //идем по всем линиям выше пустой линии (gap)
            for (int x = 0; x < FIELD_WIDTH; x++) {             //идем по всем клеткам на линии, получая при этом координаты всех точек над разрывом
                Cell shiftingCell = cellAt(new Point(x, y));    //берем клетку над гапом                
                if (shiftingCell != null) {                     //если она заполнена то ...                    
                    shiftingCell.setY(y + 1);                   //опускаем ее вниз на 1 клетку (y++)
                    addCell(shiftingCell);                      //добавляем заново, но с новыми координатами
                    removeCell(new Point(x, y));                //очищаем старое место
                }
            }
        }
    }
    
    /**
     * Метод управления перемещением падающей фигуры
     * @param action - команда
     * @return true, если перемещение удалось
     */
    public boolean moveFallingShape(int action) {
        switch (action) {
            case DOWN : 
                return GameField.this.moveFallingShape(0, 1);
            case LEFT : 
                return GameField.this.moveFallingShape(-1, 0);
            case RIGHT : 
                return GameField.this.moveFallingShape(1, 0);
            case UP : 
                return GameField.this.moveFallingShape(0, -1);
        }
        return false;
    }
    
    /**
     * 
     */
    public void rotateFallingShape() {
        Shape rotatedShape = fallingShape.rotateLeft();
        if (!checkOutOfField(rotatedShape) && !checkCollision(rotatedShape)) {
            fallingShape = rotatedShape;
        }
        repaint();
    }
    
    private void fallShape() {
        if (!moveFallingShape(DOWN)) {
            fixShape(fallingShape);
            checkFullLines(fallingShape.minY(), fallingShape.maxY());
            spawnRandomShape();
        }
    }
    
    private void updateScore (int value) {
        score += value;
        scoreBar.setText(String.valueOf(score));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fallShape();
    }
    
    class TetrisKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent ke) {
//            if (!isStarted || curPiece.getShape() == Tetrominoes.NoShape) {
//                return;
//            }

            int keyCode = ke.getKeyCode();

            if (keyCode == 'p' || keyCode == 'P') {
                pause();
            }

            if (isPaused) {
                return;
            }

            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    moveFallingShape(LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    moveFallingShape(RIGHT);
                    break;
                case KeyEvent.VK_DOWN:
                    fallShape();
                    break;
                case KeyEvent.VK_UP:
                    rotateFallingShape();
                    break;
                case KeyEvent.VK_SPACE:
                    //dropDown();
                    break;
                case 'd':
                case 'D':
                    //oneLineDown();
                    break;
            }

        }
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
            .addGap(0, 240, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
