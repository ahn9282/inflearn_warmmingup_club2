package cleancode.minesweeper.tobe.game.io.sign;

import cleancode.minesweeper.tobe.game.BOARD.cell.CellSnapshot;
import cleancode.minesweeper.tobe.game.BOARD.cell.cellsnapshotstatus.CellSnapShotStatus;

public class NumberCellProvider implements CellSignProvidable {

    @Override
    public String provide(CellSnapshot cellSnapshot) {
        return String.valueOf(cellSnapshot.getNearbyLandMineCount());
    }

    @Override
    public boolean supports(CellSnapshot snapshot) {
        return snapshot.isSameStatus(CellSnapShotStatus.NUMBER);    }
}
