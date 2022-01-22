package games.Chess.Model;

import misc.Interfaces.iState;

public class LightweightChessState implements iState {
    private final boolean gameOver;
    private final int score;
    private final int[][] board;
    private final boolean won;

    public LightweightChessState(boolean gameOver, int score, int[][] board, boolean won) {
        this.gameOver = gameOver;
        this.score = score;
        //TODO: convert board to hash
        this.board = board;
        this.won = won;
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
    public Integer[][] getBoard() {
        return null;
    }

    @Override
    public int[][] getIDBoard() {
        return new int[0][];
    }

    @Override
    public boolean isWon() {
        return won;
    }
}
