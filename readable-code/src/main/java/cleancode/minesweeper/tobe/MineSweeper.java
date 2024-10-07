package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.config.GameConfig;
import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.cell.LandMineCell;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.InputHandler;
import cleancode.minesweeper.tobe.io.OutputHandler;
import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.user.UserAction;

import java.util.Scanner;


public class MineSweeper implements GameInitializable, GameRunnable {
    public static final int BOARD_ROW_SIZE = 10;
    public static final int BOARD_CELL_SIZE = 18;
    public static final int LAND_MINE_COUNT = 10;
    private static  GameBoard gameBoard;
    private static final Integer[][] NEARBY_LAND_MINE_COUNTS = new Integer[BOARD_ROW_SIZE][BOARD_CELL_SIZE];
    private static final boolean[][] LAND_MINES = new boolean[BOARD_ROW_SIZE][BOARD_CELL_SIZE];
    private static final Scanner SCANNER = new Scanner(System.in);
    private  final InputHandler inputHandler;
    private  final OutputHandler outputHandler;
    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배
    private static final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();


    public MineSweeper(GameConfig gameConfig) {
        gameBoard = new GameBoard(gameConfig.getGameLevel());
        this.inputHandler = gameConfig.getInputHandler();
        this.outputHandler = gameConfig.getOutputHandler();
    }



    private static void changeGameStatusToLose() {
        gameStatus = -1;
    }

    private static boolean doesUserChooseToOpenCell(UserAction userAction) {
        return userAction == UserAction.OPEN;
    }

    private static boolean doesUserChooseToPlantFlag(UserAction userAction) {
        return userAction == UserAction.FLAG;
    }

    @Override
    public void initialize() {
        outputHandler.showGameStartComments();

        gameBoard.initializeGame();
    }

    @Override
    public void run() {


        while (true) {
            try {
                outputHandler.showBoard(gameBoard);

                if (doesUserWinGame()) {
                    outputHandler.printGameWinningComment();
                    break;
                }
                if (doesUserLoseGame()) {
                    outputHandler.printGameLosingComment();
                    break;
                }
                CellPosition cellInput = getCellInputFromUser(SCANNER);
                UserAction userActionInput = getUserActionInputFromUser(SCANNER);
                actOnCell(cellInput, userActionInput);

            } catch (AppException | GameException e) {
                outputHandler.printExceptionMessage(e);
            } catch (Exception e) {
                outputHandler.printSimpleMessage(e.getMessage());
            }
        }
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



    private void actOnCell(CellPosition cellInput, UserAction userAction) {

        int selectedRowIndex = cellInput.getRowIndex();
        int selectedColIndex = cellInput.getColIndex();

        if (doesUserChooseToPlantFlag(userAction)) {

            gameBoard.flag(cellInput);
            checkIfGameIsOver();

        } else if (doesUserChooseToOpenCell(userAction)) {

            if (LAND_MINES[selectedRowIndex][selectedColIndex]) {

                LandMineCell landMineCell = new LandMineCell();
                landMineCell.turnOnLandMine();
                gameBoard.findCell(cellInput).turnOnLandMine();
                changeGameStatusToLose();
                return;
            }
            gameBoard.openSurroundedCells(cellInput);

            checkIfGameIsOver();
            return;
        }
        throw new AppException("잘못된 번호를 선택하셨습니다.");
    }

    private UserAction getUserActionInputFromUser(Scanner scanner) {
        outputHandler.printCommentForUserAction();
        return inputHandler.getUserActionFromUser();
    }

    private CellPosition getCellInputFromUser(Scanner scanner) {
        outputHandler.printCommentForSelectingCell();
        CellPosition cellPosition = inputHandler.getCellPositionFromUser();
        if (gameBoard.isInvalidCellPosition(cellPosition)) {
            throw new GameException("잘못된 좌표를 선택하셨습니다.");
        }

        return cellPosition;
    }

    private static boolean doesUserLoseGame() {
        return gameStatus == -1;
    }

    private static boolean doesUserWinGame() {
        return gameStatus == 1;
    }

    private static void checkIfGameIsOver() {
        boolean isAllOpened = gameBoard.isAllCellOpened();
        if (isAllOpened) {
            changeGameStatusToWin();
        }
    }

    private static void changeGameStatusToWin() {
        gameStatus = 1;
    }

}



