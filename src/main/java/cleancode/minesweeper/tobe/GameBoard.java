package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.Cell2;
import cleancode.minesweeper.tobe.cell.EmptyCell;
import cleancode.minesweeper.tobe.cell.LandMineCell;
import cleancode.minesweeper.tobe.cell.NumberCell;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;

import java.util.Arrays;
import java.util.Random;


public class GameBoard {
    private final Cell2[][] BOARD;
    private final int landMineCount;


    public GameBoard(GameLevel gameLevel) {
        BOARD = new Cell2[gameLevel.getRowSize()][gameLevel.getColSize()];
        landMineCount = gameLevel.getLandMineCount();

    }

    public Cell2[][] getBOARD() {
        return BOARD;
    }

    public void initializeGame() {
        int rowSize = getRowSize();
        int colSize = getColSize();

        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                BOARD[row][col] = new EmptyCell();
            }
        }
        for (int i = 0; i < landMineCount; i++) {
            int landMineCol = new Random().nextInt(colSize);
            int landMineRow = new Random().nextInt(rowSize);
            BOARD[landMineRow][landMineCol] = new LandMineCell();
        }
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (isLandMineCell(row, col)) {
                    continue;
                }
                int count = countNearByLandMines(row, col);
                BOARD[row][col] = new NumberCell(count);
            }
        }
    }

    public void temp(Cell2 cell) {
        if (cell instanceof NumberCell) {
            cell.updateNearbyLandMineCount(0);

        }
    }


    private int countNearByLandMines(int row, int col) {
        int count = 0;
        if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row - 1 >= 0 && col - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (row - 1 >= 0 && col + 1 < col && isLandMineCell(row - 1, col - 1)) {
            count++;
        }
        if (col - 1 >= 0 && row - 1 >= 0 && isLandMineCell(row - 1, col - 1)) {
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
        return findCell(selectedRowIndex, selectedColIndex).isLandMine();
    }

    public int getRowSize() {
        return BOARD.length;
    }

    public int getColSize() {
        return BOARD[0].length;
    }

    public String getSign(int row, int col) {
        Cell2 cell = findCell(row, col);
        return cell.getSign();
    }

    Cell2 findCell(int rowIndex, int colIndex) {
        return BOARD[rowIndex][colIndex];
    }

    public void openSurroundedCells(int row, int col) {

        if (row < 0 || row >= getRowSize() || col < 0 || col >= getColSize()) {
            return;
        }
        if (findCell(row, col).isOpened()) {
            return;
        }
        if (isLandMineCell(row, col)) {
            return;
        }

        open(row, col);

        if (BOARD[row][col].hasLandMineCount()) {
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

    public void open(int rowIndex, int colIndex) {
        Cell2 cell = findCell(rowIndex, colIndex);
        cell.open();
    }

    public boolean isAllCellOpened() {

        return Arrays.stream(BOARD)
                .flatMap(Arrays::stream)
                .allMatch(Cell2::isChecked);
    }
}
