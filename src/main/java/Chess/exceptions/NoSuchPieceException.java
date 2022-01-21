package Chess.exceptions;

public class NoSuchPieceException extends Throwable {
    public NoSuchPieceException(int piece) {
        super("There is no piece with the id "+piece);
    }
}
