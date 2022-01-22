package games.Game2048.Model;

import games.Game2048.enums.Field2048;
import misc.Interfaces.iState;

public class State2048 implements iState {
    private final boolean gameOver;
    private final int score;
    private final Field2048[][] board;
    private int[][] idBoard;
    private final boolean won;

    public State2048(boolean gameOver, boolean won, int score, Field2048[][] board) {
        this.gameOver = gameOver;
        this.won = won;
        this.score = score;
        this.board = board;
    }

    @Override
    public boolean isGameOver() {
        return this.gameOver;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public Field2048[][] getBoard() {
        return this.board;
    }

    @Override
    public int[][] getIDBoard() {
        if(idBoard == null) {
            idBoard = new int[board.length][board.length];
            for(int x = 0; x < board.length; x++) {
                for(int y = 0; y < board.length; y++) {
                    idBoard[x][y] = board[x][y].getPower();
                }
            }
        }
        return idBoard;
    }

    @Override
    public boolean isWon() {
        return this.won;
    }
}
