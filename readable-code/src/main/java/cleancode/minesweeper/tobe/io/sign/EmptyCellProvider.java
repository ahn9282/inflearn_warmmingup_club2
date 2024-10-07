package cleancode.minesweeper.tobe.io.sign;

import cleancode.minesweeper.tobe.cell.CellSnapshot;
import cleancode.minesweeper.tobe.cell.cellsnapshotstatus.CellSnapShotStatus;

public class EmptyCellProvider implements CellSignProvidable{
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
