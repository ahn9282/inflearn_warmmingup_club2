package cleancode.minesweeper.tobe.game.io;


import cleancode.minesweeper.tobe.game.BOARD.GameBoard;
import cleancode.minesweeper.tobe.game.BOARD.cell.CellSnapshot;
import cleancode.minesweeper.tobe.game.io.sign.CellSignFinder;
import cleancode.minesweeper.tobe.game.io.sign.CellSignProvider;
import cleancode.minesweeper.tobe.position.CellPosition;

import java.util.List;
import java.util.stream.IntStream;

public class ConsoleOutputHandler implements OutputHandler {

    private final CellSignFinder cellSignFinder = new CellSignFinder();

    public void showGameStartComments() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println("지뢰찾기 게임 시작!");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public void showBoard(GameBoard board) {

        String joiningAlphabets = generateColAlphabets(board);
        System.out.println("    " + joiningAlphabets);

        for (int row = 0; row < board.getRowSize(); row++) {

            System.out.printf("%d  ", row + 1);

            for (int col = 0; col < board.getColSize(); col++) {

                CellPosition cellPosition = CellPosition.of(row, col);
               CellSnapshot snapshot = board.getSnapShot(cellPosition);
                String cellSign = CellSignProvider.findCellSignFrom(snapshot);

                System.out.print(cellSign + " ");
            }
            System.out.println();
        }
    }

    private String decideCellSignFrom(CellSnapshot snapshot) {

        return cellSignFinder.findCellSignFrom(snapshot);
    }

    public String generateColAlphabets(GameBoard board) {
        List<String> alphabets = IntStream.range(0, board.getColSize())
                .mapToObj(index -> (char) ('a' + index))
                .map(c -> c.toString())
                .toList();

        String joiningAlphabets = String.join(" ", alphabets);
        return joiningAlphabets;
    }

    public void printGameWinningComment() {
        System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");
    }

    public void printGameLosingComment() {

        System.out.println("지뢰를 밟았습니다. GAME OVER!");
    }

    public void printCommentForSelectingCell() {
        System.out.println();
        System.out.println("선택할 좌표를 입력하세요. (예: a1)");

    }

    public void printCommentForUserAction() {

        System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");

    }

    public void printExceptionMessage(RuntimeException e) {
        System.out.println(e.getMessage());
    }

    public void printSimpleMessage(String message) {
        System.out.println(message);
    }
}
