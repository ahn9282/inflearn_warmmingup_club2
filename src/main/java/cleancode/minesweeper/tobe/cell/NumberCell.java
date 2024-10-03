package cleancode.minesweeper.tobe.cell;

public class NumberCell extends Cell2 {
    private final int nearbyLandCount;

    public NumberCell(int nearbyLandCount) {
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
    public String getSign() {
        if (isOpened) {
            return String.valueOf(nearbyLandCount);
        }
        if(isFlagged){
            return FLAG_SIGN;
        }
        return UNCHECKED_SIGN;
    }

    @Override
    public boolean hasLandMineCount() {
        return true;
    }

    @Override
    public boolean isLandMine() {
        return false;
    }
}
