package cleancode.minesweeper.tobe.game.io.sign;

import cleancode.minesweeper.tobe.game.BOARD.cell.CellSnapshot;
import cleancode.minesweeper.tobe.game.BOARD.cell.cellsnapshotstatus.CellSnapShotStatus;

public class EmptyCellProvider implements CellSignProvidable {
    private static final String EMPTY_SIGN = "■";

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return EMPTY_SIGN;
    }

    @Override
    public boolean supports(CellSnapshot snapshot) {
        return snapshot.isSameStatus(CellSnapShotStatus.EMPTY);
    }
}
