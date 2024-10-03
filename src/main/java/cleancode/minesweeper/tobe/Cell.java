package cleancode.minesweeper.tobe;



public class Cell {
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";
    private static final String EMPTY_SIGN = "■";

    private final String sign;
    private int nearbyLandCount;
    private boolean isLandMine;
    private boolean isFlagged;
    private boolean isOpened;

    private Cell(String sign, int nearbyLandCount,  boolean isLandMine, boolean isFlagged, boolean isOpened) {
        this.sign = sign;
        this.nearbyLandCount = nearbyLandCount;
        this.isLandMine = isLandMine;
        this.isFlagged = isFlagged;
        this.isOpened = isOpened;
    }

    public static Cell create() {
        return of("", 0, false);
    }

    public static Cell of(String sign, int nearbyLandCount,  boolean isLandMine ){
        return new Cell(sign, nearbyLandCount, isLandMine, false, false);

    }

    public static Cell  ofLandMine(){
        return of(LAND_MINE_SIGN, 0, false);
    }

    public static Cell  ofOpen(){
        return of(EMPTY_SIGN, 0, false);
    }

    public static Cell  ofClosed(){
        return of(UNCHECKED_SIGN, 0, false);
    }

    public static Cell ofNearByLandMineCount(int count) {
        return of(String.valueOf(count), 0, false);
    }




    public void turnOnLandMine(){
        this.isLandMine = true;
    }

    public void updateNearbyLandMineCount(int count) {
        this.nearbyLandCount = count;

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
    public String getSign(){
        if(isOpened){
            if(isLandMine){
                return LAND_MINE_SIGN;
            }
            if(hasLandMineCount()){
                return String.valueOf(nearbyLandCount);
            }
            return EMPTY_SIGN;
        }
            if(isFlagged){
                return FLAG_SIGN;
            }
        return UNCHECKED_SIGN;
    }

    boolean hasLandMineCount() {
        return this.nearbyLandCount != 0;
    }
    public boolean isClosed(){
        return !isOpened();
    }

    public boolean isLandMine() {
        return isLandMine;
    }
    public void open(){

    }
}
