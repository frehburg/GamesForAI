package Chess.Representation;

import Chess.interfaces.iChessGame;
import Interfaces.iState;
import Interfaces.iState2dPlus;
import Utils.Tuple;

import java.util.ArrayList;

/**
 * This class is optimized for drawing
 */
public class ChessState implements iState2dPlus {
    private final boolean gameOver;
    private final int score;
    private final int[][] board;
    private final boolean won;
    private final ArrayList<Tuple<Integer, Integer>> atRisk;

    public ChessState(boolean gameOver, int score, int[][] board, boolean won, ArrayList<Tuple<Integer, Integer>> atRisk) {
        this.gameOver = gameOver;
        this.score = score;
        this.board = board;
        this.won = won;
        this.atRisk = atRisk;
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
        Integer[][] b = new Integer[board.length][board.length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board.length; j++) {
                b[i][j] = board[i][j];
            }
        }
        return b;
    }

    @Override
    public int[][][] getIDBoard() {
        //TODO: switch depending on the player, use rotate method from 2048
        //put rim
        int[][][] idBoard = new int[2][10][10];
        idBoard[1][0][0] = iChessGame.RIM_TOP_LEFT;
        idBoard[1][0][9] = iChessGame.RIM_BOTTOM_LEFT;
        idBoard[1][9][0] = iChessGame.RIM_TOP_RIGHT;
        idBoard[1][9][9] = iChessGame.RIM_BOTTOM_RIGHT;
        for(int x = 1; x < 9; x++) {
            idBoard[1][x][0] = iChessGame.RIM_TOP;
            idBoard[1][x][9] = iChessGame.RIM_BOTTOM;
        }
        for(int y = 1; y < 9; y++) {
            idBoard[1][0][y] = iChessGame.RIM_LEFT;
            idBoard[1][9][y] = iChessGame.RIM_RIGHT;
        }

        //put pieces
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
                idBoard[1][x + 1][y + 1] = board[x][y];
            }
        }
        //---------------------------------------------------------------
        //TODO: Add at risk
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 10; y++) {
                if(((x+y) & 1) == 1) {
                    idBoard[0][x][y] = iChessGame.BLACK;
                } else {
                    idBoard[0][x][y] = iChessGame.WHITE;
                }
            }
        }
        return idBoard;
    }

    @Override
    public boolean isWon() {
        return won;
    }

    public ArrayList<Tuple<Integer, Integer>> getAtRisk() {
        return atRisk;
    }
}
