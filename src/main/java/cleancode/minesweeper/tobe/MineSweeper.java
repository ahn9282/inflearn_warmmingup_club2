package cleancode.minesweeper.tobe;

import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;

import java.util.Arrays;
import java.util.Scanner;


public class MineSweeper {
    public static final int BOARD_ROW_SIZE = 8;
    public static final int BOARD_CELL_SIZE = 10;
    public static final int LAND_MINE_COUNT = 10;
    private static final GameBoard gameBoard = new GameBoard(BOARD_ROW_SIZE, BOARD_CELL_SIZE);
    private static final Integer[][] NEARBY_LAND_MINE_COUNTS = new Integer[BOARD_ROW_SIZE][BOARD_CELL_SIZE];
    private static final boolean[][] LAND_MINES = new boolean[BOARD_ROW_SIZE][BOARD_CELL_SIZE];
    private static final Scanner SCANNER = new Scanner(System.in);
    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    private static final ConsoleInputHandler consoleInputHandler = new ConsoleInputHandler();
    private static final ConsoleOutputHandler consoleOutputHandler = new ConsoleOutputHandler();


    public void run() {

        consoleOutputHandler.showGameStartComments();

        gameBoard.initializeGame();
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

            } catch (AppException e) {
              consoleOutputHandler.printExceptionMessage(e);
            } catch (Exception e) {
                consoleOutputHandler.printSimpleMessage(e.getMessage());
            }
        }
    }

    private  void actOnCell(String cellInput, String userActionInput) {
        int selectedColIndex = getSelectedColIndex(cellInput);
        int selectedRowIndex = getSelectedRowIndex(cellInput);

        if (doesUserChooseToPlantFlag(userActionInput)) {

            gameBoard.findCell(selectedRowIndex,selectedColIndex).flag();
            checkIfGameIsOver();

        } else if (doesUserChooseToOpenCell(userActionInput)) {

            if (LAND_MINES[selectedRowIndex][selectedColIndex]) {

                gameBoard.getBOARD()[selectedRowIndex][selectedColIndex] = Cell.ofLandMine();
                changeGameStatusToLose();
                return;
            }
            gameBoard.openSurroundedCells(selectedRowIndex, selectedColIndex);

            checkIfGameIsOver();
            return;
        }
        throw new AppException("잘못된 번호를 선택하셨습니다.");
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

    private static int getSelectedRowIndex(String cellInput) {
        char cellInputRow = cellInput.charAt(1);
        return convertRowFrom(cellInputRow);
    }

    private static int convertRowFrom(char cellInputRow) {

        int rowIndex = Character.getNumericValue(cellInputRow) - 1;
        if (rowIndex > BOARD_ROW_SIZE) {
            throw new AppException("잘못된 입력입니다.");
        }
        return rowIndex;
    }

    private static int getSelectedColIndex(String cellInput) {
        char cellInputCol = cellInput.charAt(0);
        int selectedColIndex = convertCellFrom(cellInputCol);
        return selectedColIndex;
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


    private static int convertCellFrom(char c) {
        switch (c) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            case 'i':
                return 8;
            case 'j':
                return 9;
            default:
                throw new AppException("잘못된 입력입니다.");
        }
    }

}

