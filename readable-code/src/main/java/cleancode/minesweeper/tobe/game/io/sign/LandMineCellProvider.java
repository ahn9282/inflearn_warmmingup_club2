package cleancode.minesweeper.tobe.game.io.sign;

import cleancode.minesweeper.tobe.game.BOARD.cell.CellSnapshot;
import cleancode.minesweeper.tobe.game.BOARD.cell.cellsnapshotstatus.CellSnapShotStatus;

public class LandMineCellProvider implements CellSignProvidable {
    private static final String LAND_MINE_SIGN = "☼";

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return LAND_MINE_SIGN;
    }

    @Override
    public boolean supports(CellSnapshot snapshot) {
        return snapshot.isSameStatus(CellSnapShotStatus.LAND_MINE);    }
}
