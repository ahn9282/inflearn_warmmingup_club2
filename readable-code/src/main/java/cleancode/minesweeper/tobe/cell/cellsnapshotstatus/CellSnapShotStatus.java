package cleancode.minesweeper.tobe.cell.cellsnapshotstatus;

public enum CellSnapShotStatus {
    EMPTY("빈 셀"),
    LAND_MINE("지뢰"),
    FLAG("깃발"),
    NUMBER("숫자"),
    UNCHECKED("확인 전");


    private final String description;

    CellSnapShotStatus(String description) {
        this.description = description;
    }
}
