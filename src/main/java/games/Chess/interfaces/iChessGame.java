package games.Chess.interfaces;

import games.Chess.exceptions.NoPieceInFieldException;
import games.Chess.exceptions.NoSuchPieceException;
import misc.Interfaces.iGame2dPlus;
import misc.Utils.Tuple;

import java.util.ArrayList;

public interface iChessGame extends iGame2dPlus {
    //FIELDS
    String
            A1 = "a1", A2 = "a2", A3 = "a3", A4 = "a4", A5 = "a5", A6 = "a6", A7 = "a7", A8= "a8",
            B1 = "b1", B2 = "b2", B3 = "b3", B4 = "b4", B5 = "b5", B6 = "b6", B7 = "b7", B8= "b8",
            C1 = "c1", C2 = "c2", C3 = "c3", C4 = "c4", C5 = "c5", C6 = "c6", C7 = "c7", C8= "c8",
            D1 = "d1", D2 = "d2", D3 = "d3", D4 = "d4", D5 = "d5", D6 = "d6", D7 = "d7", D8= "d8",
            E1 = "e1", E2 = "e2", E3 = "e3", E4 = "e4", E5 = "e5", E6 = "e6", E7 = "e7", E8= "e8",
            F1 = "f1", F2 = "f2", F3 = "f3", F4 = "f4", F5 = "f5", F6 = "f6", F7 = "f7", F8= "f8",
            G1 = "g1", G2 = "g2", G3 = "g3", G4 = "g4", G5 = "g5", G6 = "g6", G7 = "g7", G8= "g8",
            H1 = "h1", H2 = "h2", H3 = "h3", H4 = "h4", H5 = "h5", H6 = "h6", H7 = "h7", H8= "h8";
    //PLAYERS
    int WHITE = 100, BLACK = -100;
    //PIECES
    int EMPTY = 0;
    int W_ROOK_BF = 1, W_KNIGHT_WF = 2, W_BISHOP_BF = 3, W_KING = 4, W_QUEEN = 5,
            W_BISHOP_WF = 6, W_KNIGHT_BF = 7, W_ROOK_WF = 8, W_PAWN_A = 9, W_PAWN_B = 10,
            W_PAWN_C = 11, W_PAWN_D = 12, W_PAWN_E = 13, W_PAWN_F = 14, W_PAWN_G = 15,
            W_PAWN_H = 16;
    int B_ROOK_BF = -1, B_KNIGHT_WF = -2, B_BISHOP_BF = -3, B_KING = -4, B_QUEEN = -5,
            B_BISHOP_WF = -6, B_KNIGHT_BF = -7, B_ROOK_WF = -8, B_PAWN_A = -9, B_PAWN_B = -10,
            B_PAWN_C = -11, B_PAWN_D = -12, B_PAWN_E = -13, B_PAWN_F = -14, B_PAWN_G = -15,
            B_PAWN_H = -16;
    //VALUE OF PIECES
    int PAWN_VALUE = 1, KNIGHT_VALUE = 3, BISHOP_VALUE = 3, ROOK_VALUE = 5, QUEEN_VALUE = 9, KING_VALUE = 1000;
    //RISK
    int WHITE_RISK = 101, BLACK_RISK = -101;
    //RIM
    int RIM_TOP_LEFT = 1000, RIM_TOP = 1001, RIM_TOP_RIGHT = 1002,
        RIM_LEFT = 1003,                        RIM_RIGHT = 1004,
        RIM_BOTTOM_LEFT = 1005, RIM_BOTTOM = 1006, RIM_BOTTOM_RIGHT = 1007;

    /**
     * Put all pieces to their starting positions
     */
    void startPosition();

    public static Tuple<Integer, Integer> convertStringToCoords(String field) {
        char file = field.charAt(0);
        int rank = Integer.parseInt(field.charAt(1)+"");
        int x = -1, y = 8 - rank;
        switch (file) {
            case 'a' -> x = 0;
            case 'b' -> x = 1;
            case 'c' -> x = 2;
            case 'd' -> x = 3;
            case 'e' -> x = 4;
            case 'f' -> x = 5;
            case 'g' -> x = 6;
            case 'h' -> x = 7;
        }
        return new Tuple<>(x,y);
    }

    static String convertCoordsToString(Tuple<Integer, Integer> field) {
        String f = "";
        switch (field.getX()) {
            case 0 -> f += 'a';
            case 1 -> f += 'b';
            case 2 -> f += 'c';
            case 3 -> f += 'd';
            case 4 -> f += 'e';
            case 5 -> f += 'f';
            case 6 -> f += 'g';
            case 7 -> f += 'h';
        }
        int rank = -field.getY() + 8;
        f += rank;
        return f;
    }

    static String convertPieceIDString(int piece) throws NoSuchPieceException {
        switch(piece) {
            case W_PAWN_A:
            case W_PAWN_B:
            case W_PAWN_C:
            case W_PAWN_D:
            case W_PAWN_E:
            case W_PAWN_F:
            case W_PAWN_G:
            case W_PAWN_H:
                return "white pawn";
            case B_PAWN_A:
            case B_PAWN_B:
            case B_PAWN_C:
            case B_PAWN_D:
            case B_PAWN_E:
            case B_PAWN_F:
            case B_PAWN_G:
            case B_PAWN_H:
                return "black pawn";
            case W_ROOK_BF:
            case W_ROOK_WF:
                return "white rook";
            case B_ROOK_BF:
            case B_ROOK_WF:
                return "black rook";
            case W_KNIGHT_BF:
            case W_KNIGHT_WF:
                return "white knight";
            case B_KNIGHT_BF:
            case B_KNIGHT_WF:
                return "black knight";
            case W_BISHOP_BF:
            case W_BISHOP_WF:
                return "white bishop";
            case B_BISHOP_BF:
            case B_BISHOP_WF:
                return "black bishop";
            case W_KING:
                return "white king";
            case B_KING:
                return "black king";
            case W_QUEEN:
                return "white queen";
            case B_QUEEN:
                return "black queen";
            case EMPTY:
                return "empty";
        }
        throw new NoSuchPieceException(piece);
    }
    static int convertPieceIDValue(int piece) throws NoSuchPieceException {
        return switch (piece) {
            case W_PAWN_A, W_PAWN_B, W_PAWN_C, W_PAWN_D, W_PAWN_E, W_PAWN_F, W_PAWN_G, W_PAWN_H -> PAWN_VALUE;
            case B_PAWN_A, B_PAWN_B, B_PAWN_C, B_PAWN_D, B_PAWN_E, B_PAWN_F, B_PAWN_G, B_PAWN_H -> -PAWN_VALUE;
            case W_ROOK_BF, W_ROOK_WF -> ROOK_VALUE;
            case B_ROOK_BF, B_ROOK_WF -> -ROOK_VALUE;
            case W_KNIGHT_BF, W_KNIGHT_WF -> KNIGHT_VALUE;
            case B_KNIGHT_BF, B_KNIGHT_WF -> -KNIGHT_VALUE;
            case W_BISHOP_BF, W_BISHOP_WF -> BISHOP_VALUE;
            case B_BISHOP_BF, B_BISHOP_WF -> -BISHOP_VALUE;
            case W_QUEEN -> QUEEN_VALUE;
            case B_QUEEN -> -QUEEN_VALUE;
            case W_KING -> KING_VALUE;
            case B_KING -> -KING_VALUE;
            default -> 0;
        };
    }

    static int getColor(int piece) {
        int abs = Math.abs(piece);
        return piece/abs == 1 ? WHITE : BLACK;
    }

    void changeTurn();

    /**
     * If the piece on the field from can be moved to the field to, then it is moved
     * and true is returned,
     * Else nothing happens and false is returned.
     * @param from Chess field
     * @param to Chess field
     * @return whether the move was successful
     */
    boolean move(String from, String to) throws NoPieceInFieldException;

    /**
     * Returns true, if the piece in field is risked,
     * returns false if the piece is not risked.
     * Returns false if there is no piece in field.
     * @param field Chess field with piece in it
     * @return
     */
    boolean isRisked(String field);

    //------------------------PAWN-----------------------------------
    /**
     * If the pawn on the field from can be moved to the field to, then it is moved
     * and true is returned,
     * Else nothing happens and false is returned.
     * Only works if there is a pawn on from.
     * @param fromCoords Chess field with pawn
     * @param toCoords Chess field
     * @return whether the move was successful
     */
    boolean movePawn(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords);

    /**
     * Returns a list of the fields the pawn can possibly go to.
     * @param pawn Chess field with pawn
     * @return a list of fields the pawn in pawn can move to
     */
    ArrayList<Tuple<Integer, Integer>> getPawnMoves(Tuple<Integer,Integer> pawn) throws NoPieceInFieldException;

    /**
     * Returns true if the pawn on capturer can capture the pawn on capturee.
     * @param capturer Chess field with pawn
     * @param capturee Chess field with an enemy pawn
     * @return Whether capturer can capture capturee en passant
     */
    boolean canCaptureEnPassant(Tuple<Integer, Integer> capturer, Tuple<Integer, Integer> capturee);

    /**
     * Returns true if the pawn has reached the last file
     * @param pawn Chess field with pawn
     * @return Whether the pawn in pawn can be promoted
     */
    boolean canPromote(Tuple<Integer, Integer> pawn);

    //---------------------------ROOK------------------------------------------


    boolean moveRook(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords);
    /**
     * Returns a list of the fields the rook can possibly go to.
     * @param rook Chess field with rook
     * @return  a list of fields the rook in rook can move to
     */
    ArrayList<Tuple<Integer, Integer>> getRookMoves(Tuple<Integer,Integer> rook);

    //-------------------------KNIGHT------------------------------------------

    boolean moveKnight(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords);
    /**
     * Returns a list of the fields the knight can possibly go to.
     * @param knight Chess field with knight
     * @return  a list of fields the knight in knight can move to
     */
    ArrayList<Tuple<Integer, Integer>> getKnightMoves(Tuple<Integer,Integer> knight);

    //---------------------------BISHOP----------------------------------------

    boolean moveBishop(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords);

    /**
     * Returns a list of the fields the bishop can possibly go to.
     * @param bishop Chess field with bishop
     * @return a list of fields the bishop in bishop can move to
     */
    ArrayList<Tuple<Integer, Integer>> getBishopMoves(Tuple<Integer,Integer> bishop);

    //---------------------------QUEEN----------------------------------------

    boolean moveQueen(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords);

    /**
     * Returns a list of the fields the queen can possibly go to.
     * @param queen Chess field with queen
     * @return a list of fields the queen in queen can move to
     */
    ArrayList<Tuple<Integer, Integer>> getQueenMoves(Tuple<Integer,Integer> queen);

    //---------------------------KING-----------------------------------------

    boolean moveKing(Tuple<Integer,Integer> fromCoords, Tuple<Integer,Integer> toCoords);
    boolean isCheck(int player);
    /**
     * Returns a list of the fields the king can possibly go to.
     * @param king Chess field with king
     * @return a list of fields the king in king can move to
     */
    ArrayList<Tuple<Integer, Integer>> getKingMoves(Tuple<Integer,Integer> king);


    boolean isCheckmate(int player);

    boolean inBounds(Tuple<Integer,Integer> field);

    /**
     * Adds the move to the log
     */
    void log(String s);

    /**
     * Returns the log of all moves of the game in chronological order
     * @return log
     */
    ArrayList<String> getLog();

    int getScore();
}
