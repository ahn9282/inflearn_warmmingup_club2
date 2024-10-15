package cleancode.minesweeper.tobe.game;

import cleancode.minesweeper.tobe.exception.AppException;
import cleancode.minesweeper.tobe.exception.GameException;
import cleancode.minesweeper.tobe.game.BOARD.GameBoard;
import cleancode.minesweeper.tobe.game.config.GameConfig;
import cleancode.minesweeper.tobe.game.io.BoardIndexConverter;
import cleancode.minesweeper.tobe.game.io.InputHandler;
import cleancode.minesweeper.tobe.game.io.OutputHandler;
import cleancode.minesweeper.tobe.position.CellPosition;

import java.util.Scanner;


public class MineSweeper implements GameInitializable, GameRunnable {
    public static final int BOARD_ROW_SIZE = 10;
    public static final int BOARD_CELL_SIZE = 18;
    public static final int LAND_MINE_COUNT = 10;
    private static final Integer[][] NEARBY_LAND_MINE_COUNTS = new Integer[BOARD_ROW_SIZE][BOARD_CELL_SIZE];
    private static final boolean[][] LAND_MINES = new boolean[BOARD_ROW_SIZE][BOARD_CELL_SIZE];
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();
    private static GameBoard gameBoard;
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;


    public MineSweeper(GameConfig gameConfig) {
        gameBoard = new GameBoard(gameConfig.getGameLevel());
        this.inputHandler = gameConfig.getInputHandler();
        this.outputHandler = gameConfig.getOutputHandler();
    }

    @Override
    public void initialize() {
        outputHandler.showGameStartComments();

        gameBoard.initializeGame();
    }

    @Override
    public void run() {

        while (gameBoard.isInProgress()) {
            try {
                outputHandler.showBoard(gameBoard);


                CellPosition cellInput = getCellInputFromUser(SCANNER);
                cleancode.minesweeper.tobe.user.UserAction userActionInput = getUserActionInputFromUser(SCANNER);
                actOnCell(cellInput, userActionInput);

            } catch (AppException | GameException e) {
                outputHandler.printExceptionMessage(e);
            } catch (Exception e) {
                outputHandler.printSimpleMessage(e.getMessage());
            }

            outputHandler.showBoard(gameBoard);

            if (gameBoard.isWinStatus()) {
                outputHandler.printGameWinningComment();
                break;
            }
            if (gameBoard.isLoseStatus()) {
                outputHandler.printGameLosingComment();
                break;
            }
        }
    }
    private CellPosition getCellInputFromUser(Scanner scanner) {
        outputHandler.printCommentForSelectingCell();
        CellPosition cellPosition = inputHandler.getCellPositionFromUser();
        if (gameBoard.isInvalidCellPosition(cellPosition)) {
            throw new GameException("잘못된 좌표를 선택하셨습니다.");
        }

        return cellPosition;
    }
    private cleancode.minesweeper.tobe.user.UserAction getUserActionInputFromUser(Scanner scanner) {
        outputHandler.printCommentForUserAction();
        return inputHandler.getUserActionFromUser();
    }


    //    private static boolean isAllCellOpened() {
//        boolean isAllOpened = true;
//        for (int i = 0; i < BOARD_ROW_SIZE; i++) {
//            for (int j = 0; j < BOARD_CELL_SIZE; j++) {
//                if (BOARD[i][j].equals(CLOSED_CELL_SIGN)) {
//                    isAllOpened = false;
//                }
//            }
//        }
//        return isAllOpened;
//    }


    private void actOnCell(CellPosition cellInput, cleancode.minesweeper.tobe.user.UserAction userAction) {

        if (doesUserChooseToPlantFlag(userAction)) {

            gameBoard.flagAt(cellInput);
            return;
        }
        if (doesUserChooseToOpenCell(userAction)) {
            gameBoard.openAt(cellInput);

            return;
        }
        throw new AppException("잘못된 번호를 선택하셨습니다.");
    }
    private static boolean doesUserChooseToOpenCell(cleancode.minesweeper.tobe.user.UserAction userAction) {
        return userAction == cleancode.minesweeper.tobe.user.UserAction.OPEN;
    }

    private static boolean doesUserChooseToPlantFlag(cleancode.minesweeper.tobe.user.UserAction userAction) {
        return userAction == cleancode.minesweeper.tobe.user.UserAction.FLAG;
    }

}



