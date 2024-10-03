package cleancode.minesweeper.tobe;

import java.util.Arrays;
import java.util.Random;


public class GameBoard {
    private static final int LAND_MINE_COUNT = 10;
    private final Cell[][] BOARD;

    public GameBoard(int rowSize, int colSize) {
        BOARD = new Cell[rowSize][colSize];
    }

    public Cell[][] getBOARD() {
        return BOARD;
    }

    public void initializeGame() {
        int rowSize = getRowSize();
        int colSize = getColSize();

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                BOARD[row][col] = Cell.create();
            }
        }
        for (int i = 0; i < LAND_MINE_COUNT; i++) {
            int landMineCol = new Random().nextInt(colSize);
            int landMineRow = new Random().nextInt(rowSize);
            Cell landMineCell = findCell(landMineRow, landMineCol);
        }
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                int count = 0;
                if (isLandMineCell(row, col)) {
                    continue;
                }
                count = countNearByLandMines(row, col, count);
                findCell(row, col).updateNearbyLandMineCount(count);
            }
        }
    }



    private int countNearByLandMines(int row, int col, int count) {
        if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row - 1 >= 0 && col + 1 < col && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (col + 1 < col && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row + 1 < row && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row + 1 < row && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row + 1 < row && col + 1 < col && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        return count;
    }

    private boolean isLandMineCell(int selectedRowIndex, int selectedColIndex) {
        return findCell(selectedRowIndex,selectedColIndex ).isLandMine();
    }

    public int getRowSize(){
        return BOARD.length;
    }   public int getColSize(){
        return BOARD[0].length;
    }

    public String getSign(int row, int col) {
        Cell cell = findCell(row, col);
        return cell.getSign();
    }

    Cell findCell(int rowIndex, int colIndex) {
        return BOARD[rowIndex][colIndex];
    }

    public void openSurroundedCells(int row, int col) {

        if (row < 0 || row >= getRowSize() || col < 0 || col >= getColSize()) {
            return;
        }
        if (findCell(row,col).isOpened()) {
            return;
        }
        if (isLandMineCell(row, col)) {
            return;
        }

        open(row, col);

        if(BOARD[row][col].hasLandMineCount()){
            return;
        }
        openSurroundedCells(row - 1, col - 1);
        openSurroundedCells(row - 1, col);
        openSurroundedCells(row - 1, col + 1);
        openSurroundedCells(row, col - 1);
        openSurroundedCells(row, col + 1);
        openSurroundedCells(row + 1, col - 1);
        openSurroundedCells(row + 1, col);
        openSurroundedCells(row + 1, col + 1);
    }
    public void open(int rowIndex, int colIndex){
        Cell cell = findCell(rowIndex, colIndex);
        cell.open();
    }
    public  boolean isAllCellOpened() {

        return Arrays.stream(BOARD)
                .flatMap(Arrays::stream)
                .allMatch(Cell::isChecked);
    }
}
