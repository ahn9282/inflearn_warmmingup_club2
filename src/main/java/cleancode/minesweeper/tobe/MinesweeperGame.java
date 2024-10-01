package cleancode.minesweeper.tobe;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MinesweeperGame {

    public static final int BOARD_ROW_SIZE = 8;
    public static final int BOARD_CELL_SIZE = 10;
    public static final int LAND_MINE_COUNT = 10;
    public static final String FLAG_SIGN = "⚑";
    public static final String LAND_MINE_SIGN = "☼";
    public static final String CLOSED_CELL_SIGN = "□";
    public static final String OPENED_CELL_SIGN = "■";
    private static final String[][] BOARD = new String[BOARD_ROW_SIZE][BOARD_CELL_SIZE];
    private static final Integer[][] LAND_MINE_COUNTS = new Integer[BOARD_ROW_SIZE][BOARD_CELL_SIZE];
    private static final boolean[][] LAND_MINES = new boolean[BOARD_ROW_SIZE][BOARD_CELL_SIZE];
    private static int gameStatus = 0; // 0: 게임 중, 1: 승리, -1: 패배

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        showGameStartCOmments();
        initalozeGame();
        while (true) {
            try{
            showBoard();
            if (doesUserWinGame()) {
                System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");
                break;
            }
            if (doesUserLoseGame()) {
                System.out.println("지뢰를 밟았습니다. GAME OVER!");
                break;
            }
            String cellInput = getCellInputFromUser(SCANNER);
            String userActionInput = getUserActionInputFromUser(SCANNER);
            actOnCell(cellInput, userActionInput);

            }catch(AppException e){
                System.out.println(e.getMessage());
            }catch(Exception e){
                System.out.println("프로그램에 문제가 생겼습니다.");
                e.printStackTrace();
            }
        }
    }

    private static void actOnCell(String cellInput, String userActionInput) {
        int selectedColIndex = getSelectedColIndex(cellInput);
        int selectedRowIndex = getSelectedRowIndex(cellInput);
        if (userActionInput.equals("2")) {
            BOARD[selectedRowIndex][selectedColIndex] = FLAG_SIGN;
            checkIfGameIsOver();
        } else if (userActionInput.equals("1")) {
            if (LAND_MINES[selectedRowIndex][selectedColIndex]) {
                BOARD[selectedRowIndex][selectedColIndex] = LAND_MINE_SIGN;
                gameStatus = -1;
                return;
            } else {
                open(selectedRowIndex, selectedColIndex);
            }
            checkIfGameIsOver();
        } else {
            System.out.println("잘못된 번호를 선택하셨습니다.");
        }
    }

    private static int getSelectedRowIndex(String cellInput) {
        char cellInputRow = cellInput.charAt(1);
        return convertRowFrom(cellInputRow);
    }
    private static int convertRowFrom(char cellInputRow) {

        int rowIndex = Character.getNumericValue(cellInputRow) - 1;
        if(rowIndex > BOARD_ROW_SIZE){
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
        System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
        String userActionInput = scanner.nextLine();
        return userActionInput;
    }

    private static String getCellInputFromUser(Scanner scanner) {
        System.out.println();
        System.out.println("선택할 좌표를 입력하세요. (예: a1)");
        String cellInput = scanner.nextLine();
        return cellInput;
    }

    private static boolean doesUserLoseGame() {
        return gameStatus == -1;
    }

    private static boolean doesUserWinGame() {
        return gameStatus == 1;
    }

    private static void checkIfGameIsOver() {
        boolean isAllOpened = isAllCellOpened2();
        if (isAllOpened) {
            changeGameStatusToWin();
        }
    }

    private static void changeGameStatusToWin() {
        gameStatus = 1;
    }

    private static boolean isAllCellOpened() {
        boolean isAllOpened = true;
        for (int i = 0; i < BOARD_ROW_SIZE; i++) {
            for (int j = 0; j < BOARD_CELL_SIZE; j++) {
                if (BOARD[i][j].equals(CLOSED_CELL_SIGN)) {
                    isAllOpened = false;
                }
            }
        }
        return isAllOpened;
    }
    private static boolean isAllCellOpened2() {
        return Arrays.stream(BOARD)
                .flatMap(Arrays::stream)
                .noneMatch(cell -> cell.equals(CLOSED_CELL_SIGN));
    }

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

    private static void showBoard() {
        System.out.println("   a b c d e f g h i j");
        for (int i = 0; i < 8; i++) {
            System.out.printf("%d  ", i + 1);
            for (int j = 0; j < 10; j++) {
                System.out.print(BOARD[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void initalozeGame() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                BOARD[i][j] = CLOSED_CELL_SIGN;
            }
        }
        for (int i = 0; i < 10; i++) {
            int col = new Random().nextInt(10);
            int row = new Random().nextInt(8);
            LAND_MINES[row][col] = true;
        }
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 10; col++) {
                int count = 0;
                if (!LAND_MINES[row][col]) {
                    if (row - 1 >= 0 && col - 1 >= 0 && LAND_MINES[row - 1][col - 1]) {
                        count++;
                    }
                    if (row - 1 >= 0 && LAND_MINES[row - 1][col]) {
                        count++;
                    }
                    if (row - 1 >= 0 && col + 1 < 10 && LAND_MINES[row - 1][col + 1]) {
                        count++;
                    }
                    if (col - 1 >= 0 && LAND_MINES[row][col - 1]) {
                        count++;
                    }
                    if (col + 1 < 10 && LAND_MINES[row][col + 1]) {
                        count++;
                    }
                    if (row + 1 < 8 && col - 1 >= 0 && LAND_MINES[row + 1][col - 1]) {
                        count++;
                    }
                    if (row + 1 < 8 && LAND_MINES[row + 1][col]) {
                        count++;
                    }
                    if (row + 1 < 8 && col + 1 < 10 && LAND_MINES[row + 1][col + 1]) {
                        count++;
                    }
                    LAND_MINE_COUNTS[row][col] = count;
                    continue;
                }
                LAND_MINE_COUNTS[row][col] = 0;
            }
        }
    }

    private static void showGameStartCOmments() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    private static void open(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 10) {
            return;
        }
        if (!BOARD[row][col].equals(CLOSED_CELL_SIGN)) {
            return;
        }
        if (LAND_MINES[row][col]) {
            return;
        }
        if (LAND_MINE_COUNTS[row][col] != 0) {
            BOARD[row][col] = String.valueOf(LAND_MINE_COUNTS[row][col]);
            return;
        } else {
            BOARD[row][col] = OPENED_CELL_SIGN;
        }
        open(row - 1, col - 1);
        open(row - 1, col);
        open(row - 1, col + 1);
        open(row, col - 1);
        open(row, col + 1);
        open(row + 1, col - 1);
        open(row + 1, col);
        open(row + 1, col + 1);
    }

}
