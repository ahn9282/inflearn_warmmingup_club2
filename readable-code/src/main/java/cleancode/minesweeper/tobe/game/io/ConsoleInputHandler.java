package cleancode.minesweeper.tobe.game.io;

import cleancode.minesweeper.tobe.position.CellPosition;
import cleancode.minesweeper.tobe.user.UserAction;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler{

    private static final Scanner SCANNER = new Scanner(System.in);
    private final BoardIndexConverter boardIndexConverter = new BoardIndexConverter();

    public String getUserInput() {
        return SCANNER.nextLine();
    }

    @Override
    public UserAction getUserActionFromUser() {
        String userInput = SCANNER.nextLine();
        if ("1".equals(userInput)) {
            return UserAction.OPEN;
        } else if ("2".equals(userInput)) {
            return UserAction.FLAG;
        }
        return UserAction.UNKNOWN;
    }

    @Override
    public CellPosition getCellPositionFromUser() {
        String userInput = SCANNER.nextLine();
        int colIndex = boardIndexConverter.getSelectedColIndex(userInput);
        int rowIndex = boardIndexConverter.getSelectedRowIndex(userInput);
        return CellPosition.of(rowIndex, colIndex);
    }
}
