package Snake.Representation;

import Interfaces.iState;
import Snake.enums.SnakeField;

public class SnakeState implements iState {
    private final boolean gameOver;
    private final int score;
    private final SnakeField[][] board;
    public SnakeState(boolean gameOver, int score, SnakeField[][] board) {
        this.gameOver = gameOver;
        this.score = score;
        this.board = board;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public SnakeField[][] getBoard() {
        return board;
    }

    @Override
    public boolean isWon() {
        return false;
    }
}
