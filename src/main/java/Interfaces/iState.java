package Interfaces;

import Snake.enums.SnakeField;

public interface iState {
    boolean isGameOver();

    int getScore();

    Object[][] getBoard();

    boolean isWon();
}
