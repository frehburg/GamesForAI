package Snake.Representation;

import Interfaces.iState;
import Snake.enums.SnakeField;

public class SnakeState implements iState {
    private final boolean gameOver;
    private final int score;
    private final SnakeField[][] board;
    private int[][] idBoard;
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
    public int[][] getIDBoard() {
        if(idBoard == null) {
            idBoard = new int[board.length][board.length];
            for(int x = 0; x < board.length; x++) {
                for(int y = 0; y < board.length; y++) {
                    if(board[x][y].getID() == SnakeField.EMPTY.getID()) {
                        if ((x + y & 1) == 1)
                            idBoard[x][y] = SnakeField.EMPTY.getID() - 1;
                        else
                            idBoard[x][y] = SnakeField.EMPTY.getID();
                    } else {
                        idBoard[x][y] = board[x][y].getID();
                    }
                }
            }
        }
        return idBoard;
    }

    @Override
    public boolean isWon() {
        return false;
    }
}
