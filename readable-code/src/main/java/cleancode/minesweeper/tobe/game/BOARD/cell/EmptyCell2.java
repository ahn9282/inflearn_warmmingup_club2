package cleancode.minesweeper.tobe.game.BOARD.cell;

public class EmptyCell2 implements cleancode.minesweeper.tobe.BOARD.cell.Cell3 {
    private static final String EMPTY_SIGN = "■";

    private final cleancode.minesweeper.tobe.BOARD.cell.CellState cellState = cleancode.minesweeper.tobe.BOARD.cell.CellState.initialize();

    @Override
    public void turnOnLandMine() {
        throw new UnsupportedOperationException("지원하지 않는 기능입니다.");

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
        return false;
    }

    @Override
    public boolean isOpened() {
        return cellState.isOpened();
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
        return false;
    }

    @Override
    public void open() {
        cellState.open();

    }

    @Override
    public String getSign() {
        if (cellState.isOpened()) {
            return EMPTY_SIGN;
        }
        if (cellState.isChecked()) {
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
    }

}
