package Chess.Representation;

import Chess.interfaces.iChessGame;
import Interfaces.*;
import Utils.Tuple;

import java.util.ArrayList;

public class ChessGame implements iGame2dPlus, iChessGame {
    private int[][] board;
    private boolean gameOver;
    private int score;
    private boolean won;
    private ArrayList<Tuple<Integer, Integer>> atRisk;

    public ChessGame() {
        startPosition();
        this.gameOver = false;
        this.score = 0;
    }

    @Override
    public iState2dPlus getState() {
        return new ChessState(this.gameOver, getScore(), this.board, this.won, this.atRisk);
    }

    @Override
    public void startPosition() {
        board = new int[8][8];
        Tuple<Integer, Integer> t;
        //---black back rank---------------------
        // black rook on white field
        t = convertStringToCoords(A8);
        board[t.getX()][t.getY()] = B_ROOK_WF;
        // black knight on black field
        t = convertStringToCoords(B8);
        board[t.getX()][t.getY()] = B_KNIGHT_BF;
        // black bishop on white field
        t = convertStringToCoords(C8);
        board[t.getX()][t.getY()] = B_BISHOP_WF;
        // white king
        t = convertStringToCoords(D8);
        board[t.getX()][t.getY()] = B_KING;
        // white queen
        t = convertStringToCoords(E8);
        board[t.getX()][t.getY()] = B_QUEEN;
        // black bishop on black field
        t = convertStringToCoords(F8);
        board[t.getX()][t.getY()] = B_BISHOP_BF;
        // black knight on white field
        t = convertStringToCoords(G8);
        board[t.getX()][t.getY()] = B_KNIGHT_WF;
        // black rook on black field
        t = convertStringToCoords(H8);
        board[t.getX()][t.getY()] = B_ROOK_BF;
        //---black front rank---------------------
        // black pawn a
        t = convertStringToCoords(A7);
        board[t.getX()][t.getY()] = B_PAWN_A;
        // black pawn b
        t = convertStringToCoords(B7);
        board[t.getX()][t.getY()] = B_PAWN_B;
        // black pawn c
        t = convertStringToCoords(C7);
        board[t.getX()][t.getY()] = B_PAWN_C;
        // black pawn d
        t = convertStringToCoords(D7);
        board[t.getX()][t.getY()] = B_PAWN_D;
        // black pawn e
        t = convertStringToCoords(E7);
        board[t.getX()][t.getY()] = B_PAWN_E;
        // black pawn f
        t = convertStringToCoords(F7);
        board[t.getX()][t.getY()] = B_PAWN_F;
        // black pawn g
        t = convertStringToCoords(G7);
        board[t.getX()][t.getY()] = B_PAWN_G;
        // black pawn h
        t = convertStringToCoords(H7);
        board[t.getX()][t.getY()] = B_PAWN_H;

        //---white back rank---------------------
        // white rook on white field
        t = convertStringToCoords(A1);
        board[t.getX()][t.getY()] = W_ROOK_BF;
        // white knight on black field
        t = convertStringToCoords(B1);
        board[t.getX()][t.getY()] = W_KNIGHT_WF;
        // white bishop on white field
        t = convertStringToCoords(C1);
        board[t.getX()][t.getY()] = W_BISHOP_BF;
        // white king
        t = convertStringToCoords(D1);
        board[t.getX()][t.getY()] = W_KING;
        // white queen
        t = convertStringToCoords(E1);
        board[t.getX()][t.getY()] = W_QUEEN;
        // white bishop on black field
        t = convertStringToCoords(F1);
        board[t.getX()][t.getY()] = W_BISHOP_WF;
        // white knight on white field
        t = convertStringToCoords(G1);
        board[t.getX()][t.getY()] = W_KNIGHT_BF;
        // white rook on black field
        t = convertStringToCoords(H1);
        board[t.getX()][t.getY()] = W_ROOK_WF;
        //---white front rank---------------------
        // white pawn a
        t = convertStringToCoords(A2);
        board[t.getX()][t.getY()] = W_PAWN_A;
        // white pawn b
        t = convertStringToCoords(B2);
        board[t.getX()][t.getY()] = W_PAWN_B;
        // white pawn c
        t = convertStringToCoords(C2);
        board[t.getX()][t.getY()] = W_PAWN_C;
        // white pawn d
        t = convertStringToCoords(D2);
        board[t.getX()][t.getY()] = W_PAWN_D;
        // white pawn e
        t = convertStringToCoords(E2);
        board[t.getX()][t.getY()] = W_PAWN_E;
        // white pawn f
        t = convertStringToCoords(F2);
        board[t.getX()][t.getY()] = W_PAWN_F;
        // white pawn g
        t = convertStringToCoords(G2);
        board[t.getX()][t.getY()] = W_PAWN_G;
        // white pawn h
        t = convertStringToCoords(H2);
        board[t.getX()][t.getY()] = W_PAWN_H;

        // REST EMOTY
        for(int x = 0; x < board.length; x++) {
            for(int y = 2; y < 6; y++) {
                board[x][y] = EMPTY;
            }
        }
    }

    @Override
    public Tuple<Integer, Integer> convertStringToCoords(String field) {
        char file = field.charAt(0);
        int rank = Integer.parseInt(field.charAt(1)+"");
        int x = -1, y = 8 - rank;
        switch (file) {
            case 'a':
                x = 0;
                break;
            case 'b':
                x = 1;
                break;
            case 'c':
                x = 2;
                break;
            case 'd':
                x = 3;
                break;
            case 'e':
                x = 4;
                break;
            case 'f':
                x = 5;
                break;
            case 'g':
                x = 6;
                break;
            case 'h':
                x = 7;
                break;
        }
        return new Tuple<>(x,y);
    }

    @Override
    public boolean move(String from, String to) {
        return false;
    }

    @Override
    public boolean isRisked(String field) {
        return false;
    }

    @Override
    public boolean movePawn(String from, String to) {
        return false;
    }

    @Override
    public ArrayList<String> getPawnMoves(String pawn) {
        return null;
    }

    @Override
    public boolean canCaptureEnPassant(String capturer, String capturee) {
        return false;
    }

    @Override
    public boolean canPromote(String pawn, int valueOfPiece) {
        return false;
    }

    @Override
    public boolean moveRook(String from, String to) {
        return false;
    }

    @Override
    public ArrayList<String> getRookMoves(String rook) {
        return null;
    }

    @Override
    public boolean moveKnight(String from, String to) {
        return false;
    }

    @Override
    public ArrayList<String> getKnightMoves(String knight) {
        return null;
    }

    @Override
    public boolean moveBishop(String from, String to) {
        return false;
    }

    @Override
    public ArrayList<String> getBishopMoves(String bishop) {
        return null;
    }

    @Override
    public boolean moveQueen(String from, String to) {
        return false;
    }

    @Override
    public ArrayList<String> getQueenMoves(String queen) {
        return null;
    }

    @Override
    public boolean moveKing(String from, String to) {
        return false;
    }

    @Override
    public boolean isCheck(int player) {
        return false;
    }

    @Override
    public ArrayList<String> getKingMoves(String king) {
        return null;
    }

    @Override
    public boolean isCheckmate(int player) {
        return false;
    }

    @Override
    public void log() {

    }

    @Override
    public ArrayList<String> getLog() {
        return null;
    }

    @Override
    public int getScore() {
        score = 0;
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board.length; y++) {
                score += board[x][y];
            }
        }
        return score;
    }

    public int[][] getBoard() {
        return board;
    }
}
