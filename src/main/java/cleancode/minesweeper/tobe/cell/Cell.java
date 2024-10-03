package cleancode.minesweeper.tobe.cell;


public class Cell {
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";
    private static final String EMPTY_SIGN = "■";

    private int nearbyLandCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;

    private Cell(int nearbyLandCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.nearbyLandCount = nearbyLandCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static Cell create() {
        return of(0, false, false, false);
    }

    public static Cell of(int nearbyLandCount, boolean isLandMine, boolean isFlagged, boolean isOpened) {
        return new Cell(nearbyLandCount, isLandMine, isFlagged, isOpened);

    }
    public void updateNearbyLandMineCount(int count){
        this.nearbyLandCount = count;}


    public static Cell ofNearByLandMineCount(int count) {
        return create();
    }


    public void turnOnLandMine() {
        this.isLandMine = true;
    }



    public void flag() {
        this.isFlagged = true;
    }

    public boolean isChecked() {
        return this.isFlagged || isOpened;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public String getSign() {
        if (isOpened) {
            if (isLandMine) {
                return LAND_MINE_SIGN;
            }
            if (hasLandMineCount()) {
                return String.valueOf(nearbyLandCount);
            }
            return EMPTY_SIGN;
        }
        if (isFlagged) {
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
    }

    public boolean hasLandMineCount() {
        return this.nearbyLandCount != 0;
    }

    public boolean isClosed() {
        return !isOpened();
    }

    public boolean isLandMine() {
        return isLandMine;
    }

    public void open() {
        this.isOpened = true;
    }
}
