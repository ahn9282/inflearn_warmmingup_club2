package cleancode.minesweeper.tobe.cell;

public class LandMineCell2 implements Cell3 {

    private static final String LAND_MINE_SIGN = "☼";

    private final CellState cellState = CellState.initialize();

    @Override
    public void turnOnLandMine() {

    }

    @Override
    public void updateNearbyLandMineCount(int count) {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");
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
            return LAND_MINE_SIGN;
        }
        if(isChecked()){
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
    }

    @Override
    public boolean hasLandMineCount() {
        return false;
    }

    @Override
    public boolean isClosed() {
        return false;
    }

    @Override
    public boolean isLandMine() {
        return true;
    }

    @Override
    public void open() {
        cellState.open();
    }
}
