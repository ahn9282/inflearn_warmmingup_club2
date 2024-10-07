package cleancode.minesweeper.tobe.cell;


public class EmptyCell extends Cell2 {

    private final CellState cellState = CellState.initialize();

    @Override
    public void turnOnLandMine() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");

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
        return false;
    }

    public CellSnapshot getSnapshot(){
        if(cellState.isOpened()){
            return CellSnapshot.ofEmpty();
        }
        if (cellState.isChecked()) {
            return CellSnapshot.ofFlag();
        }
        return CellSnapshot.ofUnChecked();
    }

}
