package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.cell.Cell2;
import cleancode.minesweeper.tobe.cell.EmptyCell;
import cleancode.minesweeper.tobe.cell.LandMineCell;
import cleancode.minesweeper.tobe.cell.NumberCell;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.position.RelativePosition;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;


public class GameBoard {
    private final Cell2[][] BOARD;
    private final int landMineCount;


    public GameBoard(GameLevel gameLevel) {
        BOARD = new Cell2[gameLevel.getRowSize()][gameLevel.getColSize()];
        landMineCount = gameLevel.getLandMineCount();

    }

    private static boolean canMovePosition(CellPosition cellPosition, RelativePosition relativePosition) {
        return cellPosition.canCalculatePositionBy(relativePosition);

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
                CellPosition cellPosition = CellPosition.of(row, col);
                if (isLandMineCell(cellPosition)) {
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

    private int countNearByLandMines(CellPosition cellPosition) {
        int row = getRowSize();
        int col = getColSize();

        long count = calCalculateSurroundedPosition(cellPosition).stream()
                .filter(this::isLandMineCellAt)
                .count();

        return (int) count;
    }

    private List<CellPosition> calCalculateSurroundedPosition(CellPosition cellPosition) {
        return RelativePosition.SURROUNDED_POSITIONS.stream()
                .filter(cellPosition::canCalculatePositionBy)
                .map(cellPosition::calculatePositionBy)
                .filter(position -> position.isRowIndexLessThan(getRowSize()))
                .filter(position -> position.isColIndexLessThan(getColSize()))
                .toList();
    }

    private boolean isLandMineCell(CellPosition cellPosition) {
        return findCell(cellPosition).isLandMine();
    }

    public int getRowSize() {
        return BOARD.length;
    }

    public int getColSize() {
        return BOARD[0].length;
    }

    public String getSign(CellPosition cellPosition) {
        Cell2 cell = findCell(cellPosition);
        return cell.getSign();
    }

    Cell2 findCell(CellPosition cellPosition) {
        return BOARD[cellPosition.getRowIndex()][cellPosition.getColIndex()];
    }

    public void openSurroundedCells(CellPosition cellPosition) {

        if (cellPosition.isRowIndexMoreThanOrEqual(getRowSize())
                || cellPosition.isColIndexMoreThanOrEqual(getColSize())) return;

        if (isOpenedCell(cellPosition)) return;

        if (isLandMineCell(cellPosition)) return;

        open(cellPosition);

        if (doesCellHaveLandMineCount(cellPosition)) return;

        calCalculateSurroundedPosition(cellPosition)
                .forEach(this::openSurroundedCells);

    }

    public void open(CellPosition cellPosition) {
        Cell2 cell = findCell(cellPosition);
        cell.open();
    }

    public boolean isAllCellOpened() {

        return Arrays.stream(BOARD)
                .flatMap(Arrays::stream)
                .allMatch(Cell2::isChecked);
    }

    public boolean isInvalidCellPosition(CellPosition cellPosition) {
        int rowSize = getRowSize();
        int colSize = getColSize();

        return cellPosition.isRowIndexMoreThanOrEqual(rowSize) || cellPosition.isColIndexMoreThanOrEqual(colSize);
    }

    public void flag(CellPosition cellInput) {
        findCell(cellInput).flag();
    }

    public boolean isOpenedCell(CellPosition cellPosition) {
        return findCell(cellPosition).isOpened();
    }

    public boolean isLandMineCellAt(CellPosition cellPosition) {
        return findCell(cellPosition).isOpened();
    }

    public boolean doesCellHaveLandMineCount(CellPosition cellPosition) {
        return findCell(cellPosition).hasLandMineCount();
    }
}
