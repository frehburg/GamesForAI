package Snake.Representation;

import Snake.enums.SnakeField;

public class SnakeState {
    private final boolean gameOver;
    private final int score;
    private final SnakeField[][] board;
    public SnakeState(boolean gameOver, int score, SnakeField[][] board) {
        this.gameOver = gameOver;
        this.score = score;
        this.board = board;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public int getScore() {
        return score;
    }

    public SnakeField[][] getBoard() {
        return board;
    }
}
