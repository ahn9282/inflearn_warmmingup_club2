package cleancode.minesweeper.tobe.game.BOARD;


import cleancode.minesweeper.tobe.game.BOARD.cell.*;
import cleancode.minesweeper.tobe.game.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.position.CellPositions;
import cleancode.minesweeper.tobe.position.RelativePosition;

import java.util.Arrays;
import java.util.List;

public class GameBoard {
    private final Cell2[][] BOARD;
    private final int landMineCount;
    private GameStatus gameStatus;


    public GameBoard(GameLevel gameLevel) {
        BOARD = new Cell2[gameLevel.getRowSize()][gameLevel.getColSize()];
        landMineCount = gameLevel.getLandMineCount();
        initializeGameStatus();

    }


    public Cell2[][] getBOARD() {
        return BOARD;
    }

    public void initializeGame() {

        initializeGameStatus();
        CellPositions cellPositions = CellPositions.from(BOARD);

        intializeEmptyCells(cellPositions);

        List<CellPosition> landMinePositions = cellPositions.extractRandomPositions(landMineCount);
        intializeLandMineCells(landMinePositions);

        List<CellPosition> numberPositionCandidates = cellPositions.subtract(landMinePositions);
        initalizeNumberCells(numberPositionCandidates);

    }

    private void initializeGameStatus() {
        gameStatus = GameStatus.IN_PROGRESS;
    }

    private void initalizeNumberCells(List<CellPosition> numberPositionCandidates) {
        for (CellPosition candidatePosition : numberPositionCandidates) {
            int count = countNearByLandMines(candidatePosition);
            if (count != 0) {
                updateCellAt(candidatePosition, new NumberCell(count));

            }
        }
    }

    private void intializeLandMineCells(List<CellPosition> landMinePositions) {
        for (CellPosition landMinePosition : landMinePositions) {
            updateCellAt(landMinePosition,new LandMineCell());
        }
    }

    private void intializeEmptyCells(CellPositions cellPositions) {
        List<CellPosition> allPositions = cellPositions.getPositions();
        for (CellPosition allPosition : allPositions) {
            updateCellAt(allPosition, new EmptyCell());
        }
    }

    private void updateCellAt(CellPosition position, Cell2 cell) {
        BOARD[position.getRowIndex()][position.getColIndex()] = cell;
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

    public CellSnapshot getSign(CellPosition cellPosition) {
        Cell2 cell = findCell(cellPosition);
        return cell.getSnapshot();
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

    public boolean isAllCellChecked() {
        Cells cells = Cells.from(BOARD);
        return cells.isAllChecked();
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

    public CellSnapshot getSnapShot(CellPosition cellPosition) {
        Cell2 cell = findCell(cellPosition);
        return cell.getSnapshot();
    }

    public boolean isInProgress() {
        return gameStatus == GameStatus.IN_PROGRESS;
    }

    void checkIfGameIsOver() {
        boolean isAllOpened = isAllCellOpened();
        if (isAllOpened) {
            changeGameStatusToWin();
        }
    }

    private void changeGameStatusToWin() {
        gameStatus = GameStatus.WIN;
    }

    public void flagAt(CellPosition cellPosition) {
        flag(cellPosition);
        checkIfGameIsOver();
    }

    public void openAt(CellPosition cellInput) {

        if (isLandMineCellAt(cellInput)) {

            open(cellInput);
            changeGameStatusToLose();
            return;
        }
        openSurroundedCells(cellInput);

        checkIfGameIsOver();
    }


    private void changeGameStatusToLose() {
        gameStatus = GameStatus.LOSE;

    }

    public boolean isWinStatus(){
        return gameStatus == GameStatus.WIN;
    }

    public boolean isLoseStatus(){
        return gameStatus == GameStatus.LOSE;
    }
}

