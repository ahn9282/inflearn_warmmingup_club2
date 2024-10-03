package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.game.GameInitializable;
import cleancode.minesweeper.tobe.game.GameRunnable;
import cleancode.minesweeper.tobe.cell.LandMineCell;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;

import java.util.Scanner;


public class MineSweeper implements GameInitializable, GameRunnable {
    public static final int BOARD_ROW_SIZE = 10;
    public static final int BOARD_CELL_SIZE = 18;
    public static final int LAND_MINE_COUNT = 10;
    private static  GameBoard gameBoard;
    private static final Integer[][] NEARBY_LAND_MINE_COUNTS = new Integer[BOARD_ROW_SIZE][BOARD_CELL_SIZE];
    private static final boolean[][] LAND_MINES = new boolean[BOARD_ROW_SIZE][BOARD_CELL_SIZE];
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private static final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();
    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배
    private static final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();


    public MineSweeper(GameLevel level) {
        gameBoard = new GameBoard(level);
    }

    private static void changeGameStatusToLose() {
        gameStatus = -1;
    }

    private static boolean doesUserChooseToOpenCell(String userActionInput) {
        return userActionInput.equals("1");
    }

    private static boolean doesUserChooseToPlantFlag(String userActionInput) {
        return userActionInput.equals("2");
    }

    @Override
    public void initialize() {
        consoleOutputHandler.showGameStartComments();

        gameBoard.initializeGame();
    }

    @Override
    public void run() {


        while (true) {
            try {
                consoleOutputHandler.showBoard(gameBoard);

                if (doesUserWinGame()) {
                    consoleOutputHandler.printGameWinningComment();
                    break;
                }
                if (doesUserLoseGame()) {
                    consoleOutputHandler.printGameLosingComment();
                    break;
                }
                String cellInput = getCellInputFromUser(SCANNER);
                String userActionInput = getUserActionInputFromUser(SCANNER);
                actOnCell(cellInput, userActionInput);

            } catch (AppException | GameException e) {
                consoleOutputHandler.printExceptionMessage(e);
            } catch (Exception e) {
                consoleOutputHandler.printSimpleMessage(e.getMessage());
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



    private void actOnCell(String cellInput, String userActionInput) {
        int selectedColIndex = boardIndexConverter.getSelectedColIndex(cellInput, gameBoard.getColSize());
        int selectedRowIndex = boardIndexConverter.getSelectedRowIndex(cellInput, gameBoard.getRowSize());

        if (doesUserChooseToPlantFlag(userActionInput)) {

            gameBoard.findCell(selectedRowIndex, selectedColIndex).flag();
            checkIfGameIsOver();

        } else if (doesUserChooseToOpenCell(userActionInput)) {

            if (LAND_MINES[selectedRowIndex][selectedColIndex]) {

                LandMineCell landMineCell = new LandMineCell();
                landMineCell.turnOnLandMine();
                gameBoard.findCell(selectedRowIndex, selectedColIndex).turnOnLandMine();
                changeGameStatusToLose();
                return;
            }
            gameBoard.openSurroundedCells(selectedRowIndex, selectedColIndex);

            checkIfGameIsOver();
            return;
        }
        throw new AppException("잘못된 번호를 선택하셨습니다.");
    }

    private static String getUserActionInputFromUser(Scanner scanner) {
        consoleOutputHandler.printCommentForUserAction();
        return consoleInputHandler.getUserInput();
    }

    private static String getCellInputFromUser(Scanner scanner) {
        consoleOutputHandler.printCommentForSelectingCell();

        return consoleInputHandler.getUserInput();
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



