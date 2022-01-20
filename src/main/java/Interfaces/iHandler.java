package Interfaces;

import Snake.Representation.SnakeState;
import Snake.enums.SnakeBoardSize;
import Snake.enums.SnakeSpeed;

public interface iHandler {
    void init();

    void newGame();

    void addToHighScores(iState state);

    void game();
}
