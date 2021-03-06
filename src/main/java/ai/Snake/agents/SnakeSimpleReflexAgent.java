package ai.Snake.agents;

import misc.Interfaces.iSimpleReflexAgent;
import misc.Interfaces.iState;
import games.Snake.Interfaces.iSnake;
import games.Snake.Model.SnakePlayer;
import games.Snake.Model.SnakeState;
import games.Snake.enums.SnakeDirection;
import games.Snake.enums.SnakeField;
import misc.Utils.Tuple;
import misc.Utils.Vector2DUtils;

import java.util.ArrayList;

public class SnakeSimpleReflexAgent implements iSimpleReflexAgent {
    public static final int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;
    public static final boolean DEBUG = false;

    private final SnakePlayer player;

    public SnakeSimpleReflexAgent(SnakePlayer player) {
        this.player = player;
    }

    @Override
    public void react(iState state) {
        SnakeState snakeState = (SnakeState) state;
        int move = nextMove(snakeState);
        if(DEBUG)System.out.println(move);
        switch (move) {
            case UP:
                if(DEBUG)System.out.println("up");
                player.up();
                break;
            case LEFT:
                if(DEBUG)System.out.println("left");
                player.left();
                break;
            case DOWN:
                if(DEBUG)System.out.println("down");
                player.down();
                break;
            case RIGHT:
                if(DEBUG)System.out.println("right");
                player.right();
                break;
            case -1:
                //do nothing
                break;
        }
    }

    private int nextMove(SnakeState state) {
        iSnake snake = state.getSnake();
        Tuple<Integer, Integer> pellet = findPellet(state.getIDBoard());
        Tuple<Integer, Integer> head = snake.getHead();
        SnakeDirection d = snake.getHeadDirection();
        Tuple<Integer, Integer> up, right, left, down;
        up = new Tuple<>(head.getX(), head.getY() - 1);
        right = new Tuple<>(head.getX() + 1, head.getY());
        down = new Tuple<>(head.getX(), head.getY() + 1);
        left = new Tuple<>(head.getX() - 1, head.getY());
        ArrayList<Tuple<Integer, Integer>> heuristics = new ArrayList<>();
        switch (d) {
            case RIGHT -> {
                //check if fields are occupied by snake or out of bounds
                if (inBounds(up, state.getIDBoard())) {
                    if (isNotSnake(up, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(UP, (int) heuristic(up, pellet)));
                    }
                }
                if (inBounds(right, state.getIDBoard())) {
                    if (isNotSnake(right, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(RIGHT, (int) heuristic(right, pellet)));
                    }
                }
                if (inBounds(down, state.getIDBoard())) {
                    if (isNotSnake(down, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(DOWN, (int) heuristic(down, pellet)));
                    }
                }
            }
            case UP -> {
                //check if fields are occupied by snake or out of bounds
                if (inBounds(up, state.getIDBoard())) {
                    if (isNotSnake(up, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(UP, (int) heuristic(up, pellet)));
                    }
                }
                if (inBounds(right, state.getIDBoard())) {
                    if (isNotSnake(right, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(RIGHT, (int) heuristic(right, pellet)));
                    }
                }
                if (inBounds(left, state.getIDBoard())) {
                    if (isNotSnake(left, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(LEFT, (int) heuristic(left, pellet)));
                    }
                }
            }
            case LEFT -> {
                //check if fields are occupied by snake or out of bounds
                if (inBounds(up, state.getIDBoard())) {
                    if (isNotSnake(up, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(UP, (int) heuristic(up, pellet)));
                    }
                }
                if (inBounds(left, state.getIDBoard())) {
                    if (isNotSnake(left, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(LEFT, (int) heuristic(left, pellet)));
                    }
                }
                if (inBounds(down, state.getIDBoard())) {
                    if (isNotSnake(down, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(DOWN, (int) heuristic(down, pellet)));
                    }
                }
            }
            case DOWN -> {
                //check if fields are occupied by snake or out of bounds
                if (inBounds(left, state.getIDBoard())) {
                    if (isNotSnake(left, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(LEFT, (int) heuristic(left, pellet)));
                    }
                }
                if (inBounds(right, state.getIDBoard())) {
                    if (isNotSnake(right, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(RIGHT, (int) heuristic(right, pellet)));
                    }
                }
                if (inBounds(down, state.getIDBoard())) {
                    if (isNotSnake(down, state.getIDBoard())) {
                        heuristics.add(new Tuple<>(DOWN, (int) heuristic(down, pellet)));
                    }
                }
            }
        }
        if(DEBUG)System.out.println("------------------------------");
        if(DEBUG)System.out.println("head "+head);
        if(DEBUG)System.out.println("direction "+d);
        if(DEBUG)System.out.println("pellet "+pellet);
        if(DEBUG)System.out.println("unsorted "+heuristics);
        heuristics.sort((o1, o2) -> o1.getY() - o2.getY());
        if(DEBUG)System.out.println("sorted "+heuristics);
        return heuristics.size() == 0 ? -1 : heuristics.get(0).getX();
    }

    private boolean inBounds(Tuple<Integer, Integer> field, int[][] board) {
        return field.getX() >= 0 && field.getX() < board.length && field.getY() >= 0 && field.getY() < board.length;
    }

    private boolean isNotSnake(Tuple<Integer, Integer> field, int[][] board) {
        return board[field.getX()][field.getY()] == SnakeField.EMPTY.getID()
                || board[field.getX()][field.getY()] == SnakeField.EMPTY.getID() -1
                || board[field.getX()][field.getY()] ==   SnakeField.PELLET.getID();
    }

    private Tuple<Integer, Integer> findPellet(int[][] board) {
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board.length; y++) {
                if(board[x][y] == SnakeField.PELLET.getID()) {
                    return new Tuple<>(x,y);
                }
            }
        }
        return null;
    }


    public double heuristic(Tuple<Integer, Integer> head, Tuple<Integer, Integer> pellet) {
        //choose manhattan distance
        return Vector2DUtils.manhattanDistance(pellet,head);
    }
}
