package cleancode.minesweeper.tobe.BOARD.cell;

public interface Cell3 {

     static final String FLAG_SIGN = "⚑";
     static final String UNCHECKED_SIGN = "□";

     void turnOnLandMine();

     void updateNearbyLandMineCount(int count);

     void flag();

     boolean isChecked();

     boolean isOpened();

     boolean hasLandMineCount();

     boolean isClosed();

     boolean isLandMine();

     void open();

     String getSign();
}
