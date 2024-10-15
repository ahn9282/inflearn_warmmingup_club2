package cleancode.minesweeper.tobe.game.BOARD.cell;

import cleancode.minesweeper.tobe.game.BOARD.cell.Cell2;

public class LandMineCell extends Cell2 {

    private final CellState cellState = CellState.initialize();

    @Override
    public void turnOnLandMine() {

    }

    @Override
    public void updateNearbyLandMineCount(int count) {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
    }



    @Override
    public boolean hasLandMineCount() {
        return false;
    }

    @Override
    public boolean isLandMine() {
        return true;
    }
    public CellSnapshot getSnapshot(){
        if(cellState.isOpened()){
            return CellSnapshot.ofLandMine();
        }
        if (cellState.isChecked()) {
            return CellSnapshot.ofFlag();
        }
        return CellSnapshot.ofUnChecked();
    }
}
