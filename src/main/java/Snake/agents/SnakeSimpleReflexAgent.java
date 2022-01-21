package Snake.agents;

import Interfaces.iAgent;
import Interfaces.iSimpleReflexAgent;
import Interfaces.iState;
import Snake.Interfaces.iSnake;
import Snake.Representation.SnakeState;
import Snake.enums.SnakeDirection;
import Snake.enums.SnakeField;
import Utils.Tuple;
import Utils.Vector2DUtils;

import java.util.ArrayList;

public class SnakeSimpleReflexAgent implements iSimpleReflexAgent {
    @Override
    public void react(iState state) {
        SnakeState snakeState = (SnakeState) state;
        int move = nextMove(snakeState);
    }

    private int nextMove(SnakeState state) {
        iSnake snake = state.getSnake();
        Tuple<Integer, Integer> pellet = findPellet(state.getIDBoard());
        Tuple<Integer, Integer> head = snake.getHead();
        ArrayList<Tuple<Integer, Integer>> possibleMoves = new ArrayList<>();
        SnakeDirection d = snake.getHeadDirection();
        Tuple<Integer, Integer> top, right, left, down;
        switch (d) {
            case RIGHT:
                top = new Tuple<>(head.getX(), head.getY() - 1);
                right = new Tuple<>(head.getX() + 1, head.getY());
                down = new Tuple<>(head.getX(), head.getY() + 1);
                ArrayList<Tuple<Tuple<Integer,Integer>,Integer>> heuristics = new;
                //check if fields are occupied by snake or out of bounds
                if()
                break;
            case UP:
                break;
            case LEFT:
                break;
            case DOWN:
                break;
        }
    }

    private boolean inBounds(Tuple<Integer, Integer> field, int[][] board) {
        return field.getX() >= 0 && field.getX() < board.length && field.getY() >= 0 && field.getY() < board.length;
    }

    private boolean isNotSnake(Tuple<Integer, Integer> field, int[][] board) {
        return board[field.getX()][field.getY()] == SnakeField.EMPTY.getID()
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


    @Override
    public double heuristic(iState state) {
        SnakeState snakeState = (SnakeState) state;
        iSnake snake = snakeState.getSnake();
        Tuple<Integer, Integer> pellet = findPellet(snakeState.getIDBoard());
        Tuple<Integer, Integer> head = snake.getHead();
        //choose manhattan distance
        int manhattanDistance = Vector2DUtils.manhattanDistance(pellet,head);
        return manhattanDistance;
    }
}
