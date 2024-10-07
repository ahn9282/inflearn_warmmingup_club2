package cleancode.minesweeper.tobe.game.gamelevel;

import cleancode.minesweeper.tobe.game.gamelevel.GameLevel;

public class Advanced implements GameLevel {
    @Override
    public int getRowSize() {
        return 20;
    }

    @Override
    public int getColSize() {
        return 24;
    }

    @Override
    public int getLandMineCount() {
        return 99;
    }
}
