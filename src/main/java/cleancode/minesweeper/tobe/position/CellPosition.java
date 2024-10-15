package cleancode.minesweeper.tobe.position;

import java.util.Objects;

public class CellPosition {
    private final int rowIndex;
    private final int colIndex;

    public CellPosition(int rowIndex, int colIndex) {
        if (rowIndex < 0 || colIndex < 0) {
            throw new IllegalArgumentException("올바르지 않은 좌표입니다");
        }
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public static CellPosition of(int rowIndex, int colIndex) {

        return new CellPosition(rowIndex, colIndex);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CellPosition that = (CellPosition) o;
        return rowIndex == that.rowIndex && colIndex == that.colIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, colIndex);
    }

    public boolean isRowIndexMoreThanOrEqual(int rowSize) {
        return rowSize >= rowIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public boolean isColIndexMoreThanOrEqual(int colSize) {
        return colSize >= colIndex;
    }

    public  boolean canCalculatePositionBy(RelativePosition relativePosition) {
        return rowIndex + relativePosition.getDeltaRow() >= 0
                && colIndex + relativePosition.getDeltaCol() >= 0;
    }
    public CellPosition calculatePositionBy(RelativePosition relativePosition) {
       if(canCalculatePositionBy(relativePosition)){
           return CellPosition.of(rowIndex + relativePosition.getDeltaRow(),
                   colIndex + relativePosition.getDeltaCol());
       }
        throw new IllegalArgumentException("잘못된 좌표입니다");
    }

    public boolean isRowIndexLessThan(int rowIndex) {
        return this.rowIndex < rowIndex;
    }
    public boolean isColIndexLessThan(int colIndex) {
        return this.colIndex < colIndex;
    }
}
