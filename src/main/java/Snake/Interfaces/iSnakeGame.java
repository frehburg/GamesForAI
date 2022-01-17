package Snake.Interfaces;

import Snake.enums.SnakeBoardSize;
import Snake.enums.SnakeDirection;
import Snake.enums.SnakeSpeed;

public abstract class iSnakeGame {
    //Constants
    public static int SMALL_BOARD_SIZE = 10, STANDARD_BOARD_SIZE = 15, BIG_BOARD_SIZE = 20, HUGE_BOARD_SIZE = 25;
    public static int SIMPLE_SPEED = 1, EASY_SPEED = 2, MEDIUM_SPEED = 3, HARD_SPEED = 4, INSANE_SPEED = 5;

    //
    protected int speed;
    protected int score;
    protected SnakeDirection direction;
    protected boolean gameOver;


    public abstract void options(SnakeBoardSize size, SnakeSpeed speed);

    public abstract void placePellet();

    public abstract void move(SnakeDirection direction);

    public abstract void updateBoard();

}
