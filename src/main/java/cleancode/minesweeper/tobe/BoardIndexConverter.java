package cleancode.minesweeper.tobe;

public class BoardIndexConverter {

    private static final char BASE_CHAR_FOR_COL = 'a';

    public static int getSelectedRowIndex(String cellInput, int rowSize) {
        String cellInputRow = cellInput.substring(1);
        return convertRowFrom(cellInputRow, rowSize);
    }

    public static int getSelectedColIndex(String cellInput, int colSize) {
        char cellInputCol = cellInput.charAt(0);
        int selectedColIndex = convertColFrom(cellInputCol, colSize);
        return selectedColIndex;
    }

    public static int convertRowFrom(String cellInputRow, int rowSize) {

        int rowIndex = Integer.parseInt(cellInputRow) - 1;
        if (rowIndex > rowSize) {
            throw new GameException("잘못된 입력입니다.");
        }
        return rowIndex;
    }

    public static int convertColFrom(char c, int colSize) {
        int colIndex = c - 'a';
        if (colIndex < 0) {
            throw new GameException("잘못된 입력입니다.");
        }
        return colIndex;
    }

}
