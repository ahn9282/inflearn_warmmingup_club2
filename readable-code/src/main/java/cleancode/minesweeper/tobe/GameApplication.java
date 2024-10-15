package cleancode.minesweeper.tobe;


import cleancode.minesweeper.tobe.game.MineSweeper;
import cleancode.minesweeper.tobe.game.config.GameConfig;
import cleancode.minesweeper.tobe.game.gamelevel.GameLevel;
import cleancode.minesweeper.tobe.game.gamelevel.Middle;
import cleancode.minesweeper.tobe.game.gamelevel.VeryBeginner;
import cleancode.minesweeper.tobe.game.io.ConsoleInputHandler;
import cleancode.minesweeper.tobe.game.io.ConsoleOutputHandler;
import cleancode.minesweeper.tobe.game.io.InputHandler;
import cleancode.minesweeper.tobe.game.io.OutputHandler;

public class GameApplication {

    public static void main(String[] args) {
        GameLevel gameLevel = new VeryBeginner();
        InputHandler inputHandler = new ConsoleInputHandler();
        OutputHandler outputHandler = new ConsoleOutputHandler();
        GameConfig gameConfig = new GameConfig(gameLevel, inputHandler, outputHandler);

        MineSweeper mineSweeper = new MineSweeper(gameConfig);
        mineSweeper.run();
    }

}
