package games.Chess.exceptions;

public class NoPieceInFieldException extends Throwable {
    public NoPieceInFieldException(String field) {
        super("There is no piece on " + field);
    }
}
