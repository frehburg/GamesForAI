package misc.Interfaces;

public interface iHandler {
    void init();

    void newGame();

    void addToHighScores(iState state, String playerName);

    void game();
}
