package games.Chess.Model;

import games.Chess.exceptions.NoPieceInFieldException;
import games.Chess.exceptions.NoSuchPieceException;
import games.Chess.interfaces.iChessGame;
import misc.Interfaces.iState2dPlus;
import misc.Utils.ArrayUtils;
import misc.Utils.Tuple;

import java.util.ArrayList;

public class ChessGame implements iChessGame {
    private int[][] board;
    private int[][] prevBoard;
    private int[][] prev2Board;
    private boolean gameOver;
    private int score;
    private boolean won;
    private ArrayList<Tuple<Integer, Integer>> atRisk;
    private ArrayList<String> moveLog;
    private int turn;

    public ChessGame() {
        startPosition();
        this.gameOver = false;
        this.score = getScore();
        this.turn = WHITE;
    }

    @Override
    public iState2dPlus getState() {
        return new ChessState(this.gameOver, getScore(), this.board, this.won, this.atRisk);
    }

    @Override
    public void startPosition() {
        moveLog = new ArrayList<>();
        board = new int[8][8];
        Tuple<Integer, Integer> t;
        //---black back rank---------------------
        // black rook on white field
        t = iChessGame.convertStringToCoords(A8);
        board[t.getX()][t.getY()] = B_ROOK_WF;
        // black knight on black field
        t = iChessGame.convertStringToCoords(B8);
        board[t.getX()][t.getY()] = B_KNIGHT_BF;
        // black bishop on white field
        t = iChessGame.convertStringToCoords(C8);
        board[t.getX()][t.getY()] = B_BISHOP_WF;
        // white king
        t = iChessGame.convertStringToCoords(D8);
        board[t.getX()][t.getY()] = B_KING;
        // white queen
        t = iChessGame.convertStringToCoords(E8);
        board[t.getX()][t.getY()] = B_QUEEN;
        // black bishop on black field
        t = iChessGame.convertStringToCoords(F8);
        board[t.getX()][t.getY()] = B_BISHOP_BF;
        // black knight on white field
        t = iChessGame.convertStringToCoords(G8);
        board[t.getX()][t.getY()] = B_KNIGHT_WF;
        // black rook on black field
        t = iChessGame.convertStringToCoords(H8);
        board[t.getX()][t.getY()] = B_ROOK_BF;
        //---black front rank---------------------
        // black pawn a
        t = iChessGame.convertStringToCoords(A7);
        board[t.getX()][t.getY()] = B_PAWN_A;
        // black pawn b
        t = iChessGame.convertStringToCoords(B7);
        board[t.getX()][t.getY()] = B_PAWN_B;
        // black pawn c
        t = iChessGame.convertStringToCoords(C7);
        board[t.getX()][t.getY()] = B_PAWN_C;
        // black pawn d
        t = iChessGame.convertStringToCoords(D7);
        board[t.getX()][t.getY()] = B_PAWN_D;
        // black pawn e
        t = iChessGame.convertStringToCoords(E7);
        board[t.getX()][t.getY()] = B_PAWN_E;
        // black pawn f
        t = iChessGame.convertStringToCoords(F7);
        board[t.getX()][t.getY()] = B_PAWN_F;
        // black pawn g
        t = iChessGame.convertStringToCoords(G7);
        board[t.getX()][t.getY()] = B_PAWN_G;
        // black pawn h
        t = iChessGame.convertStringToCoords(H7);
        board[t.getX()][t.getY()] = B_PAWN_H;

        //---white back rank---------------------
        // white rook on white field
        t = iChessGame.convertStringToCoords(A1);
        board[t.getX()][t.getY()] = W_ROOK_BF;
        // white knight on black field
        t = iChessGame.convertStringToCoords(B1);
        board[t.getX()][t.getY()] = W_KNIGHT_WF;
        // white bishop on white field
        t = iChessGame.convertStringToCoords(C1);
        board[t.getX()][t.getY()] = W_BISHOP_BF;
        // white king
        t = iChessGame.convertStringToCoords(D1);
        board[t.getX()][t.getY()] = W_KING;
        // white queen
        t = iChessGame.convertStringToCoords(E1);
        board[t.getX()][t.getY()] = W_QUEEN;
        // white bishop on black field
        t = iChessGame.convertStringToCoords(F1);
        board[t.getX()][t.getY()] = W_BISHOP_WF;
        // white knight on white field
        t = iChessGame.convertStringToCoords(G1);
        board[t.getX()][t.getY()] = W_KNIGHT_BF;
        // white rook on black field
        t = iChessGame.convertStringToCoords(H1);
        board[t.getX()][t.getY()] = W_ROOK_WF;
        //---white front rank---------------------
        // white pawn a
        t = iChessGame.convertStringToCoords(A2);
        board[t.getX()][t.getY()] = W_PAWN_A;
        // white pawn b
        t = iChessGame.convertStringToCoords(B2);
        board[t.getX()][t.getY()] = W_PAWN_B;
        // white pawn c
        t = iChessGame.convertStringToCoords(C2);
        board[t.getX()][t.getY()] = W_PAWN_C;
        // white pawn d
        t = iChessGame.convertStringToCoords(D2);
        board[t.getX()][t.getY()] = W_PAWN_D;
        // white pawn e
        t = iChessGame.convertStringToCoords(E2);
        board[t.getX()][t.getY()] = W_PAWN_E;
        // white pawn f
        t = iChessGame.convertStringToCoords(F2);
        board[t.getX()][t.getY()] = W_PAWN_F;
        // white pawn g
        t = iChessGame.convertStringToCoords(G2);
        board[t.getX()][t.getY()] = W_PAWN_G;
        // white pawn h
        t = iChessGame.convertStringToCoords(H2);
        board[t.getX()][t.getY()] = W_PAWN_H;

        // REST EMOTY
        for(int x = 0; x < board.length; x++) {
            for(int y = 2; y < 6; y++) {
                board[x][y] = EMPTY;
            }
        }

        prevBoard = ArrayUtils.copyArray(board);
        prev2Board = ArrayUtils.copyArray(board);
    }

    @Override
    public boolean move(String from, String to){
        System.out.println(turn);
        Tuple<Integer,Integer> fromCoords = iChessGame.convertStringToCoords(from),
                toCoords = iChessGame.convertStringToCoords(to);
        int piece = board[fromCoords.getX()][fromCoords.getY()];
        if(piece == EMPTY)
            return false;
        int color = iChessGame.getColor(piece);
        if(color == turn) {//it is the right turn
            switch(piece) {
                case W_PAWN_A:
                case W_PAWN_B:
                case W_PAWN_C:
                case W_PAWN_D:
                case W_PAWN_E:
                case W_PAWN_F:
                case W_PAWN_G:
                case W_PAWN_H:
                case B_PAWN_A:
                case B_PAWN_B:
                case B_PAWN_C:
                case B_PAWN_D:
                case B_PAWN_E:
                case B_PAWN_F:
                case B_PAWN_G:
                case B_PAWN_H:
                    return movePawn(fromCoords, toCoords);
                case W_ROOK_BF:
                case W_ROOK_WF:
                case B_ROOK_BF:
                case B_ROOK_WF:
                    return moveRook(fromCoords, toCoords);
                case W_KNIGHT_BF:
                case W_KNIGHT_WF:
                case B_KNIGHT_BF:
                case B_KNIGHT_WF:
                    return moveKnight(fromCoords, toCoords);
                case W_BISHOP_BF:
                case W_BISHOP_WF:
                case B_BISHOP_BF:
                case B_BISHOP_WF:
                    return moveBishop(fromCoords, toCoords);
                case W_KING:
                case B_KING:
                    return moveKing(fromCoords, toCoords);
                case W_QUEEN:
                case B_QUEEN:
                    return moveQueen(fromCoords, toCoords);
            }
        }
        return false;
    }

    @Override
    public boolean isRisked(String field) {
        return false;
    }

    //------------------------PAWN-----------------------------------
    @Override
    public boolean movePawn(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords) {
        try {
            ArrayList<Tuple<Integer,Integer>> moves = getPawnMoves(fromCoords);
            for(Tuple<Integer, Integer> t : moves) {
                //the requested move is a valid move
                if(t.getX() == toCoords.getX() && t.getY() == toCoords.getY()) {
                    //then actually move
                    prev2Board = ArrayUtils.copyArray(prevBoard);
                    prevBoard = ArrayUtils.copyArray(board);
                    String piece = iChessGame.convertPieceIDString(board[fromCoords.getX()][fromCoords.getY()]);
                    String fieldTo = iChessGame.convertCoordsToString(toCoords);
                    log("Moved " + piece + " from "
                            + iChessGame.convertCoordsToString(fromCoords) + " to " + fieldTo + "("+
                            iChessGame.convertPieceIDString(board[toCoords.getX()][toCoords.getY()])+")");
                    if(canPromote(toCoords)) {
                        board[toCoords.getX()][toCoords.getY()] = turn/Math.abs(turn) * iChessGame.W_QUEEN;
                        log("Promoted "+ piece + " on " + fieldTo);
                    } else {
                        board[toCoords.getX()][toCoords.getY()] = board[fromCoords.getX()][fromCoords.getY()];
                    }
                    board[fromCoords.getX()][fromCoords.getY()] = EMPTY;

                    //change turn
                    changeTurn();

                    return true;
                }
                //else nothing happens
            }
        } catch (NoPieceInFieldException | NoSuchPieceException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Tuple<Integer, Integer>> getPawnMoves(Tuple<Integer,Integer> pawn) throws NoPieceInFieldException {
        //expects that the right player is at turn and that there is a pawn in the field
        ArrayList<Tuple<Integer,Integer>> moves = new ArrayList<>();
        int x = pawn.getX(), y = pawn.getY();
        int piece = board[pawn.getX()][pawn.getY()];
        int color = iChessGame.getColor(piece);
        if(color == WHITE) {
            if(y == 6) {//still in start position
                Tuple<Integer, Integer> nextField = new Tuple<>(x,5), nextNextField = new Tuple<>(x, 4);
                //check if can go two moves
                if(isEmpty(nextField)) {
                    moves.add(nextField);
                    if(isEmpty(nextNextField)) {
                        moves.add(nextNextField);
                    }
                }
            } else if(y > 0){
                Tuple<Integer, Integer> nextField = new Tuple<>(x,y - 1);
                if(isEmpty(nextField)) {
                    moves.add(nextField);
                }
            }
            //capturing
            if(x > 0 && y > 0) {
                Tuple<Integer, Integer> topLeft = new Tuple<>(x - 1,y - 1);
                if(board[topLeft.getX()][topLeft.getY()] != EMPTY) {
                    //can capture top left
                    moves.add(topLeft);
                }
                Tuple<Integer, Integer> left = new Tuple<>(x - 1,y);
                //capture enPassant
                if(canCaptureEnPassant(pawn, left)) {
                    moves.add(left);
                }
            }
            if(x < 7 && y > 0) {
                Tuple<Integer, Integer> topRight = new Tuple<>(x + 1,y - 1);
                if(board[topRight.getX()][topRight.getY()] != EMPTY) {
                    //can capture top left
                    moves.add(topRight);
                }
                Tuple<Integer, Integer> right = new Tuple<>(x + 1,y);
                //capture enPassant
                if(canCaptureEnPassant(pawn, right)) {
                    moves.add(right);
                }
            }
        } else if(color == BLACK) {
            if(y == 1) {//still in start position
                Tuple<Integer, Integer> nextField = new Tuple<>(x,2), nextNextField = new Tuple<>(x, 3);
                //check if can go two moves
                if(isEmpty(nextField)) {
                    moves.add(nextField);
                    if(isEmpty(nextNextField)) {
                        moves.add(nextNextField);
                    }
                }
            } else if(y < 7){
                Tuple<Integer, Integer> nextField = new Tuple<>(x,y + 1);
                if(isEmpty(nextField)) {
                    moves.add(nextField);
                }
            }
            //capturing
            if(x > 0 && y < 7) {
                Tuple<Integer, Integer> bottomLeft = new Tuple<>(x - 1,y + 1);
                if(board[bottomLeft.getX()][bottomLeft.getY()] != EMPTY) {
                    //can capture top left
                    moves.add(bottomLeft);
                }
                Tuple<Integer, Integer> left = new Tuple<>(x - 1,y);
                //capture enPassant
                if(canCaptureEnPassant(pawn, left)) {
                    moves.add(left);
                }
            }
            if(x < 7 && y < 7) {
                Tuple<Integer, Integer> bottomRight = new Tuple<>(x + 1,y + 1);
                if(board[bottomRight.getX()][bottomRight.getY()] != EMPTY) {
                    //can capture top left
                    moves.add(bottomRight);
                }
                Tuple<Integer, Integer> right = new Tuple<>(x + 1,y);
                //capture enPassant
                if(canCaptureEnPassant(pawn, right)) {
                    moves.add(right);
                }
            }
        }
        return moves;
    }

    @Override
    public boolean canCaptureEnPassant(Tuple<Integer, Integer> capturer, Tuple<Integer, Integer> capturee) {
        int capturerPiece = board[capturer.getX()][capturer.getY()],
        captureePiece = board[capturee.getX()][capturee.getY()];
        int color = iChessGame.getColor(capturerPiece);
        int x = capturer.getX(), y = capturer.getY();
        try {
            if(iChessGame.convertPieceIDString(captureePiece).contains("pawn")) {
                if(color == WHITE) {
                    //capturing
                    if(x > 0 && y == 3) {
                        Tuple<Integer, Integer> topLeft = new Tuple<>(x - 1,y - 2);
                        if(prevBoard[topLeft.getX()][topLeft.getY()] == captureePiece) {
                            //can capture top left
                            return true;
                        }
                    }
                    if(x < 7 && y == 3) {
                        Tuple<Integer, Integer> topRight = new Tuple<>(x + 1,y - 2);
                        if(prevBoard[topRight.getX()][topRight.getY()] == captureePiece) {
                            //can capture top left
                            return true;
                        }
                    }
                } else if(color == BLACK) {
                    //capturing
                    if(x > 0 && y == 4) {
                        Tuple<Integer, Integer> bottomLeft = new Tuple<>(x - 1,y + 2);
                        if(prevBoard[bottomLeft.getX()][bottomLeft.getY()] == captureePiece) {
                            //can capture top left
                            return true;
                        }
                    }
                    if(x < 7 && y == 4) {
                        Tuple<Integer, Integer> bottomRight = new Tuple<>(x + 1,y + 2);
                        if(prevBoard[bottomRight.getX()][bottomRight.getY()] == captureePiece) {
                            //can capture top left
                            return true;
                        }
                    }
                }
            }
        } catch (NoSuchPieceException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean canPromote(Tuple<Integer,Integer> pawn) {
        if(turn == WHITE) {
            return pawn.getY() == 0;
        } else if(turn == BLACK) {
            return pawn.getY() == 7;
        }
        return false;
    }
    //------------------------ROOK-----------------------------------
    @Override
    public boolean moveRook(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords) {
        try {
            ArrayList<Tuple<Integer,Integer>> moves = getRookMoves(fromCoords);
            for(Tuple<Integer, Integer> t : moves) {
                //the requested move is a valid move
                if(t.getX() == toCoords.getX() && t.getY() == toCoords.getY()) {
                    //then actually move
                    prev2Board = ArrayUtils.copyArray(prevBoard);
                    prevBoard = ArrayUtils.copyArray(board);
                    log("Moved " + iChessGame.convertPieceIDString(board[fromCoords.getX()][fromCoords.getY()]) + " from "
                            + iChessGame.convertCoordsToString(fromCoords) + " to " + iChessGame.convertCoordsToString(toCoords) + "("+
                            iChessGame.convertPieceIDString(board[toCoords.getX()][toCoords.getY()])+")");
                    board[toCoords.getX()][toCoords.getY()] = board[fromCoords.getX()][fromCoords.getY()];
                    board[fromCoords.getX()][fromCoords.getY()] = EMPTY;

                    //change turn
                    changeTurn();

                    return true;
                }
                //else nothing happens
            }
        } catch (NoSuchPieceException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Tuple<Integer, Integer>> getRookMoves(Tuple<Integer,Integer> rook) {
        ArrayList<Tuple<Integer,Integer>> moves = new ArrayList<>();
        //rook can move down the file or down the rank
        //up
        Tuple<Integer, Integer> t = rook.clone();
        while (t.getY() > 0) {
            t = new Tuple<>(t.getX(), t.getY() - 1);
            if(!isEmpty(t)) {
                //found a piece of the same color, therefore cannot move here or further
                if(iChessGame.getColor(board[t.getX()][t.getY()]) != turn) {//if we found a piece of the opposing player
                    moves.add(t);
                }
                break;
            }
            moves.add(t);
        }
        //down
        t = rook.clone();
        while (t.getY() < 7) {
            t = new Tuple<>(t.getX(), t.getY() + 1);
            if(!isEmpty(t)) {
                //found a piece of the same color, therefore cannot move here or further
                if(iChessGame.getColor(board[t.getX()][t.getY()]) != turn) {//if we found a piece of the opposing player
                    moves.add(t);
                }
                break;
            }

            moves.add(t);
        }
        //left
        t = rook.clone();
        while (t.getX() > 0) {
            t = new Tuple<>(t.getX() - 1, t.getY());
            if(!isEmpty(t)) {
                //found a piece of the same color, therefore cannot move here or further
                if(iChessGame.getColor(board[t.getX()][t.getY()]) != turn) {//if we found a piece of the opposing player
                    moves.add(t);
                }
                break;
            }
            moves.add(t);
        }
        //right
        t = rook.clone();
        while (t.getX() < 7) {
            t = new Tuple<>(t.getX() + 1, t.getY());
            if(!isEmpty(t)) {
                //found a piece of the same color, therefore cannot move here or further
                if(iChessGame.getColor(board[t.getX()][t.getY()]) != turn) {//if we found a piece of the opposing player
                    moves.add(t);
                }
                break;
            }
            moves.add(t);
        }
        return moves;
    }
    //------------------------KNIGHT-----------------------------------
    @Override
    public boolean moveKnight(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords) {
        try {
            ArrayList<Tuple<Integer,Integer>> moves = getKnightMoves(fromCoords);
            for(Tuple<Integer, Integer> t : moves) {
                //the requested move is a valid move
                if(t.getX() == toCoords.getX() && t.getY() == toCoords.getY()) {
                    //then actually move
                    prev2Board = ArrayUtils.copyArray(prevBoard);
                    prevBoard = ArrayUtils.copyArray(board);
                    log("Moved " + iChessGame.convertPieceIDString(board[fromCoords.getX()][fromCoords.getY()]) + " from "
                            + iChessGame.convertCoordsToString(fromCoords) + " to " + iChessGame.convertCoordsToString(toCoords) + "("+
                            iChessGame.convertPieceIDString(board[toCoords.getX()][toCoords.getY()])+")");
                    board[toCoords.getX()][toCoords.getY()] = board[fromCoords.getX()][fromCoords.getY()];
                    board[fromCoords.getX()][fromCoords.getY()] = EMPTY;

                    //change turn
                    changeTurn();

                    return true;
                }
                //else nothing happens
            }
        } catch (NoSuchPieceException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Tuple<Integer, Integer>> getKnightMoves(Tuple<Integer,Integer> knight) {
        ArrayList<Tuple<Integer,Integer>> moves = new ArrayList<>(), possibleMoves = new ArrayList<>();
        possibleMoves.add(new Tuple<>(knight.getX() - 1, knight.getY() - 2));//top left
        possibleMoves.add(new Tuple<>(knight.getX() + 1, knight.getY() - 2));//top right
        possibleMoves.add(new Tuple<>(knight.getX() - 2, knight.getY() - 1));//left top
        possibleMoves.add(new Tuple<>(knight.getX() - 2, knight.getY() + 1));//left bottom
        possibleMoves.add(new Tuple<>(knight.getX() + 2, knight.getY() - 1));//right top
        possibleMoves.add(new Tuple<>(knight.getX() + 2, knight.getY() + 1));//right bottom
        possibleMoves.add(new Tuple<>(knight.getX() - 1, knight.getY() + 2));//bottom left
        possibleMoves.add(new Tuple<>(knight.getX() + 1, knight.getY() + 2));//bottom right
        for(Tuple<Integer,Integer> t : possibleMoves) {
            if(inBounds(t)) {
                moves.add(t);
            }
        }
        return moves;
    }
    //------------------------BISHOP-----------------------------------
    @Override
    public boolean moveBishop(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords) {
        return false;
    }

    @Override
    public ArrayList<Tuple<Integer, Integer>> getBishopMoves(Tuple<Integer,Integer> bishop) {
        return null;
    }
    //------------------------QUEEN-----------------------------------
    @Override
    public boolean moveQueen(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords) {
        try {
            ArrayList<Tuple<Integer,Integer>> moves = getQueenMoves(fromCoords);
            for(Tuple<Integer, Integer> t : moves) {
                //the requested move is a valid move
                if(t.getX() == toCoords.getX() && t.getY() == toCoords.getY()) {
                    //then actually move
                    prev2Board = ArrayUtils.copyArray(prevBoard);
                    prevBoard = ArrayUtils.copyArray(board);
                    log("Moved " + iChessGame.convertPieceIDString(board[fromCoords.getX()][fromCoords.getY()]) + " from "
                            + iChessGame.convertCoordsToString(fromCoords) + " to " + iChessGame.convertCoordsToString(toCoords) + "("+
                            iChessGame.convertPieceIDString(board[toCoords.getX()][toCoords.getY()])+")");
                    board[toCoords.getX()][toCoords.getY()] = board[fromCoords.getX()][fromCoords.getY()];
                    board[fromCoords.getX()][fromCoords.getY()] = EMPTY;

                    //change turn
                    changeTurn();

                    return true;
                }
                //else nothing happens
            }
        } catch (NoSuchPieceException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Tuple<Integer, Integer>> getQueenMoves(Tuple<Integer,Integer> queen) {
        ArrayList<Tuple<Integer,Integer>> moves = new ArrayList<>();
        moves.addAll(getRookMoves(queen));
        //moves.addAll(getBishopMoves(queen));//TODO
        return moves;
    }
    //------------------------KING-----------------------------------
    @Override
    public boolean moveKing(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords) {
        return false;
    }

    @Override
    public boolean isCheck(int player) {
        return false;
    }

    @Override
    public ArrayList<Tuple<Integer, Integer>> getKingMoves(Tuple<Integer,Integer> king) {
        return null;
    }

    @Override
    public boolean isCheckmate(int player) {
        return false;
    }

    @Override
    public boolean inBounds(Tuple<Integer, Integer> field) {
        return field.getX() >= 0 && field.getX() <= 7 && field.getY() >= 0 && field.getY() <= 7;
    }
    //------------------------REST-----------------------------------

    public boolean isEmpty(Tuple<Integer, Integer> field) {
        return board[field.getX()][field.getY()] == EMPTY;
    }

    @Override
    public void changeTurn() {
        if(turn == WHITE) {
            turn = BLACK;
        }else if(turn == BLACK) {
            turn = WHITE;
        }
    }
    @Override
    public void log(String s) {
        System.out.println(s);
        moveLog.add(s);
    }

    @Override
    public ArrayList<String> getLog() {
        return moveLog;
    }

    @Override
    public int getScore() {
        score = 0;
        for (int[] ints : board) {
            for (int y = 0; y < board.length; y++) {
                try {
                    score += iChessGame.convertPieceIDValue(ints[y]);
                } catch (NoSuchPieceException e) {
                    e.printStackTrace();
                }
            }
        }
        return score;
    }

    public int[][] getBoard() {
        return board;
    }
}