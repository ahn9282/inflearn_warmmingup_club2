package cleancode.minesweeper.tobe;


import cleancode.minesweeper.tobe.gamelevel.Beginner;
import cleancode.minesweeper.tobe.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.gamelevel.Middle;
import cleancode.minesweeper.tobe.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.io.ConsoleOutputHandler;

public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new Middle();
        MineSweeper mineSweeper = new MineSweeper(gameLevel, new ConsoleInputHandler(), new ConsoleOutputHandler());
        mineSweeper.run();
    }

}
