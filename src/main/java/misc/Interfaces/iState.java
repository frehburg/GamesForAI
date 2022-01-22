package misc.Interfaces;

public interface iState {
    boolean isGameOver();

    int getScore();

    Object[][] getBoard();

    int[][] getIDBoard();

    boolean isWon();
}
