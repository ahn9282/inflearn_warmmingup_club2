package cleancode.minesweeper.tobe.game.io.sign;


import cleancode.minesweeper.tobe.game.BOARD.cell.CellSnapshot;

public interface CellSignProvidable {

    String provide(CellSnapshot cellSnapshot);

    public boolean supports(CellSnapshot snapshot);
}
