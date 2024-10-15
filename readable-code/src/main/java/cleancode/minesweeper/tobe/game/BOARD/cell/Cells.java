package cleancode.minesweeper.tobe.game.BOARD.cell;

import java.util.Arrays;
import java.util.List;

public class Cells {
    private final List<Cell2> cells;
    

    public Cells(List<Cell2> cells) {
        this.cells = cells;
    }
    public static Cells of(List<Cell2> cells){
        return new Cells(cells);
    }

    public static Cells from(Cell2[][] cells) {
        List<Cell2> cellList = Arrays.stream(cells)
                .flatMap(Arrays::stream)
                        .toList();
        return of(cellList);
    }

    public boolean isAllChecked() {
        return cells.stream()
                .allMatch(Cell::isChecked);
    }

}
