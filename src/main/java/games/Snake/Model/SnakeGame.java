package games.Snake.Model;

import misc.Interfaces.iGame;
import games.Snake.Interfaces.iSnakeGame;
import games.Snake.Interfaces.iSnake;
import games.Snake.enums.SnakeBoardSize;
import games.Snake.enums.SnakeDirection;
import games.Snake.enums.SnakeField;
import games.Snake.enums.SnakeSpeed;
import misc.Utils.RandomUtils;
import misc.Utils.Tuple;

import java.util.ArrayList;
import java.util.Arrays;

//TODO: Check bug where snake cannot pick up pellet in bottom left corner coming straight down
public class SnakeGame extends iSnakeGame implements iGame {
    private SnakeField[][] board;
    private iSnake snake;

    public SnakeGame() {
        options(SnakeBoardSize.STANDARD, SnakeSpeed.MEDIUM);
    }

    public SnakeGame(SnakeBoardSize size, SnakeSpeed speed, SnakePlayer p) {
        options(size,speed);
        p.setGame(this);
    }

    @Override
    public void options(SnakeBoardSize size, SnakeSpeed speed) {
        gameOver = false;
        score = 0;
        direction = SnakeDirection.RIGHT;
        switch (speed) {
            case SIMPLE -> this.speed = SIMPLE_SPEED;
            case EASY -> this.speed = EASY_SPEED;
            case MEDIUM -> this.speed = MEDIUM_SPEED;
            case HARD -> this.speed = HARD_SPEED;
            case INSANE -> this.speed = INSANE_SPEED;
        }
        int iSize = switch (size) {
            case SMALL -> SMALL_BOARD_SIZE;
            case STANDARD -> STANDARD_BOARD_SIZE;
            case BIG -> BIG_BOARD_SIZE;
            case HUGE -> HUGE_BOARD_SIZE;
        };

        board = new SnakeField[iSize][iSize];
        for (SnakeField[] snakeFields : board) {
            Arrays.fill(snakeFields, SnakeField.EMPTY);
        }

        snake = new Snake(new Tuple<>(SnakeDirection.RIGHT, new Tuple<>(board.length/2,board.length/2)),
                new Tuple<>(SnakeDirection.RIGHT, new Tuple<>(board.length/2 - 1,board.length/2)),
                new Tuple<>(SnakeDirection.RIGHT, new Tuple<>(board.length/2 - 2,board.length/2)));

        addSnakeHelper();

        placePellet();
    }

    @Override
    public void placePellet() {
        ArrayList<Tuple<Integer, Integer>> placable = new ArrayList<>();
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[x].length; y++) {
                if(board[x][y] == SnakeField.EMPTY) {
                    //if field is empty then can place pellet
                    placable.add(new Tuple<>(x,y));
                }
                if(board[x][y] == SnakeField.PELLET) {
                    //if there is already a pellet on the field, do not add another one
                    return;
                }
            }
        }

        int pelletPosition = RandomUtils.getRandom(0, placable.size() - 1);
        Tuple<Integer, Integer> field = placable.get(pelletPosition);
        board[field.getX()][field.getY()] = SnakeField.PELLET;
    }

    @Override
    public void move(SnakeDirection d) {
        if(d == SnakeDirection.RIGHT && this.direction == SnakeDirection.LEFT ||
                d == SnakeDirection.LEFT && this.direction == SnakeDirection.RIGHT ||
                d == SnakeDirection.UP && this.direction == SnakeDirection.DOWN ||
                d == SnakeDirection.DOWN && this.direction == SnakeDirection.UP) {
            return;
        }
        this.direction = d;
    }

    @Override
    public void updateBoard() {
        if(gameOver)
            return;
        Tuple<Integer,Integer> delta = mapDirectionToDelta(this.direction);
        int xDelt = delta.getX(), yDelt = delta.getY();
        //check if snake runs into pellet
        //if the next move is not in bounds then game over
        boolean growSnake = false;
        if(inBounds(snake.getHead().getX() + xDelt, snake.getHead().getY() + yDelt)) {
            if(board[snake.getHead().getX() + xDelt][snake.getHead().getY() + yDelt] == SnakeField.PELLET) {
                growSnake = true;
            }
        } else {
            gameOver = true;
            return;
        }

        // now move the snake
        if(growSnake) {
            //the block after the next move is also in bounds
            if(inBounds(snake.getHead().getX() + 2*xDelt, snake.getHead().getY() + 2*yDelt)) {
                //move stored position of head
                snake.addToFront(this.direction);
                addSnakeToBoard();
            } else {
                //grow at the back
                //TODO: Check bug when pellet in corner
                //move forward
                snake.move(this.direction);
                //move stored position of head
                snake.growAtBack();
                //System.out.println(snake.getBody());
                addSnakeToBoard();
            }
            //increase score
            score++;
            placePellet();
        } else {
            //need to move all blocks
            snake.move(this.direction);
            addSnakeToBoard();
        }

    }

    /**
     * Check whether the provided position is within the bounds og the field
     * also check whether the provided position is occupied by the snake itself
     * @param x
     * @param y
     * @return
     */
    private boolean inBounds(int x, int y) {
        if(x >= board.length || x < 0)
            return false;
        if(y >= board[0].length || y < 0)
            return false;
        //check for self collision
        //exclude last body segment
        Tuple<Integer, Integer> t = new Tuple<>(x,y);
        Tuple<Integer, Integer> tail = snake.getTail();
        if(!t.equals(tail)) {
            if(board[t.getX()][t.getY()] == SnakeField.LEFT ||
                    board[t.getX()][t.getY()] == SnakeField.RIGHT ||
                    board[t.getX()][t.getY()] == SnakeField.UP ||
                    board[t.getX()][t.getY()] == SnakeField.DOWN) {
                gameOver = true;
                return false;
            }
        }
        return true;
    }

    /**
     * Maps a direction to a field for moving in the update method
     * @return
     */
    public static SnakeField mapDirectionToField(SnakeDirection d) {
        return switch (d) {
            case DOWN -> SnakeField.DOWN;
            case LEFT -> SnakeField.LEFT;
            case UP -> SnakeField.UP;
            case RIGHT -> SnakeField.RIGHT;
        };
    }

    public static Tuple<Integer, Integer> mapDirectionToDelta(SnakeDirection d) {
        int xDelt = 0, yDelt = 0;
        switch (d) {
            case DOWN -> yDelt = +1;
            case UP -> yDelt = -1;
            case RIGHT -> xDelt = +1;
            case LEFT -> xDelt = -1;
        }
        return new Tuple<>(xDelt, yDelt);
    }

    private void addSnakeToBoard() {
        //clear previous snake off the board
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[x].length; y++) {
                if(
                        board[x][y] == SnakeField.RIGHT ||
                        board[x][y] == SnakeField.UP ||
                        board[x][y] == SnakeField.DOWN ||
                        board[x][y] == SnakeField.LEFT
                ) {
                    board[x][y] = SnakeField.EMPTY;
                }
            }
        }
        // add new snake
        addSnakeHelper();
    }

    /**
     * Performs the actual adding to the board
     * ATTENTION: Board needs to be cleared first
     */
    private void addSnakeHelper() {
        ArrayList<Tuple<SnakeField,Tuple<Integer,Integer>>> fields = snake.getSnakeFields();
        for(Tuple<SnakeField,Tuple<Integer,Integer>> bodySegment : fields) {
            int x = bodySegment.getY().getX(), y = bodySegment.getY().getY();;
            SnakeField f = bodySegment.getX();
            board[x][y] = f;
        }
    }

    @Override
    public SnakeState getState() {
        return new SnakeState(gameOver, score, board, snake);
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }
}
