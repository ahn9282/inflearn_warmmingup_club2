package cleancode.minesweeper.tobe.io.sign;

import cleancode.minesweeper.tobe.cell.CellSnapshot;
import cleancode.minesweeper.tobe.cell.cellsnapshotstatus.CellSnapShotStatus;

public class FlagCellProvider implements CellSignProvidable{
    private static final String FLAG_SIGN = "⚑";

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return FLAG_SIGN;
    }

    @Override
    public boolean supports(CellSnapshot snapshot) {
        return snapshot.isSameStatus(CellSnapShotStatus.FLAG);    }
}
