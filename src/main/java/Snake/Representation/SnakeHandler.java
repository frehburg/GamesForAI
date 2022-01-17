package Snake.Representation;

import Snake.GUI.SnakeGUI;
import Snake.enums.SnakeBoardSize;
import Snake.enums.SnakeSpeed;

import javax.swing.*;
import java.util.ArrayList;

public class SnakeHandler {
    private ArrayList<Integer> highscores;
    private SnakeGUI gui;
    private SnakePlayer p;
    public SnakeHandler() {
        highscores = new ArrayList<>();
        p = new SnakePlayer();
        SnakeGame game = new SnakeGame(SnakeBoardSize.STANDARD, SnakeSpeed.MEDIUM);
        SnakeGUI gui = new SnakeGUI(p, this, game);

        double cur = System.currentTimeMillis();
        double delta = 500;
        while(!game.getState().isGameOver()) {
            while(System.currentTimeMillis() - cur < delta) {
                //System.out.println(System.currentTimeMillis() - cur);
            }
            cur = System.currentTimeMillis();
            game.updateBoard();
            gui.render(game.getState());
        }
    }

    public void newGame(SnakeBoardSize snakeBoardSize, SnakeSpeed snakeSpeed) {
        SnakeGame game = new SnakeGame(snakeBoardSize, snakeSpeed);
        p.setGame(game);
        gui.initPlay(game);
        double cur = System.currentTimeMillis();
        double delta = 500;
        while(!game.getState().isGameOver()) {
            while(System.currentTimeMillis() - cur < delta) {
                //System.out.println(System.currentTimeMillis() - cur);
            }
            cur = System.currentTimeMillis();
            game.updateBoard();
            gui.render(game.getState());
        }
        addToHighScores(game.getState());
    }

    public void addToHighScores(SnakeState state) {

    }

    public void setGUI(SnakeGUI gui) {
        this.gui = gui;
    }

}
