package cleancode.minesweeper.tobe.game.BOARD.cell;

import cleancode.minesweeper.tobe.game.BOARD.cell.Cell2;

public class NumberCell extends Cell2 {
    private final int nearbyLandCount;

    public NumberCell(int nearbyLandCount) {
        this.nearbyLandCount = nearbyLandCount;
    }
    private final CellState cellState = CellState.initialize();


    @Override
    public void turnOnLandMine() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");

    }

    @Override
    public void updateNearbyLandMineCount(int count) {

    }




    @Override
    public boolean hasLandMineCount() {
        return true;
    }

    @Override
    public boolean isLandMine() {
        return false;
    }
    public CellSnapshot getSnapshot(){
        if(cellState.isOpened()){
            return CellSnapshot.ofNumber(nearbyLandCount);
        }
        if (cellState.isChecked()) {
            return CellSnapshot.ofFlag();
        }
        return CellSnapshot.ofUnChecked();
    }
}
