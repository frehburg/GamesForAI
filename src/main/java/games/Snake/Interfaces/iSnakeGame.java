package games.Snake.Interfaces;

import games.Snake.Model.SnakeState;
import games.Snake.enums.SnakeBoardSize;
import games.Snake.enums.SnakeDirection;
import games.Snake.enums.SnakeSpeed;

public abstract class iSnakeGame {
    //Constants
    public static int SMALL_BOARD_SIZE = 10, STANDARD_BOARD_SIZE = 15, BIG_BOARD_SIZE = 20, HUGE_BOARD_SIZE = 25;
    public static int SIMPLE_SPEED = 400, EASY_SPEED = 250, MEDIUM_SPEED = 200, HARD_SPEED = 125, INSANE_SPEED = 60;

    //
    protected int speed;
    protected int score;
    protected SnakeDirection direction;
    protected boolean gameOver;


    public abstract void options(SnakeBoardSize size, SnakeSpeed speed);

    public abstract void placePellet();

    public abstract void move(SnakeDirection direction);

    public abstract void updateBoard();

    public abstract SnakeState getState();

    public abstract int getSpeed();

}
