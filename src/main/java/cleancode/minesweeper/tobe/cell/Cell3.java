package cleancode.minesweeper.tobe.cell;


public interface Cell3 {
     static final String FLAG_SIGN = "⚑";
     static final String UNCHECKED_SIGN = "□";

    public abstract void turnOnLandMine();

    public abstract void updateNearbyLandMineCount(int count);

    public void flag();

    public boolean isChecked();

    public boolean isOpened();

    public abstract String getSign();

    public abstract boolean hasLandMineCount();

    public boolean isClosed();

    public abstract boolean isLandMine();

    public void open();
}
