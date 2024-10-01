package cleancode.minesweeper.tobe;

public class Cell {
    private static final String FLAG_SIGN = "⚑";
    private static final String LAND_MINE_SIGN = "☼";
    private static final String UNCHECKED_SIGN = "□";
    private static final String EMPTY_SIGN = "■";

    private final String sign;
    private int nearbyLandCount;
    private boolean isLandMine;

    private Cell(String sign, int nearbyLandCount,  boolean isLandMine) {
        this.sign = sign;
        this.nearbyLandCount = nearbyLandCount;
        this.isLandMine = isLandMine;
    }

    public static Cell of(String sign, int nearbyLandCount,  boolean isLandMine){
        return new Cell(sign, nearbyLandCount, isLandMine);

    }
    public static Cell  ofFlag(){
        return of(FLAG_SIGN,0, false);
    }
    public static Cell  ofLandMine(){
        return of(LAND_MINE_SIGN, 0, false);
    }
    public static Cell  ofClosed(){
        return of(UNCHECKED_SIGN, 0, false);
    }
    public static Cell  ofOpen(){
        return of(EMPTY_SIGN, 0, false);
    }

    public static Cell ofNearByLandMineCount(int count) {
        return of(String.valueOf(count), 0, false);
    }

    public boolean equalSign(String closedSign) {
        return sign.equals(closedSign);
    }

    public String getSign() {
        return sign;
    }

    public boolean doesNotEqualSign( ) {
        return !equalSign(UNCHECKED_SIGN);
    }
    public boolean isClosed(){
        return UNCHECKED_SIGN.equals(this.sign);
    }
}
