package Chess.Model;

import Chess.exceptions.NoPieceInFieldException;
import Chess.interfaces.iChessGame;

public class ChessPlayer{
    private iChessGame game;

    public void setGame(iChessGame game) {
        this.game = game;
    }

    public ChessState move(String from, String to) {
        try {
            game.move(from,to);
        } catch (NoPieceInFieldException e) {
            e.printStackTrace();
        }
        return (ChessState) game.getState();
    }

    public ChessState castling(String rook) {
        //TODO: implement
        return null;
    }
}
