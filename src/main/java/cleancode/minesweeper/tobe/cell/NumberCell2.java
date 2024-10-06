package cleancode.minesweeper.tobe.cell;

public class NumberCell2 implements Cell3 {
    private final int nearbyLandCount;
    private final CellState cellState = CellState.initialize();

    public NumberCell2(int nearbyLandCount) {
        this.nearbyLandCount = nearbyLandCount;
    }

    @Override
    public void turnOnLandMine() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");

    }

    @Override
    public void updateNearbyLandMineCount(int count) {

    }

    @Override
    public void flag() {
        cellState.flag();
    }

    @Override
    public boolean isChecked() {
        return cellState.isChecked();
    }

    @Override
    public boolean isOpened() {
        return cellState.isOpened();
    }


    @Override
    public String getSign() {
        if (isOpened()) {
            return String.valueOf(nearbyLandCount);
        }
        if(isChecked()){
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
    }

    @Override
    public boolean hasLandMineCount() {
        return true;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isLandMine() {
        return false;
    }

    @Override
    public void open() {
cellState.open();
    }
}
