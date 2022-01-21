package Snake.agents;

import Interfaces.iAgent;
import Interfaces.iState;
import Snake.Representation.SnakePlayer;
import Snake.Representation.SnakeState;

public class SnakeGoalBasedAgent implements iAgent {
    public static final int UP = 0, LEFT = 1, DOWN = 2, RIGHT = 3;
    public static final boolean DEBUG = false;

    private final SnakePlayer player;

    public SnakeGoalBasedAgent(SnakePlayer player) {
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

    private int nextMove(SnakeState snakeState) {
        /*create a plan:
        * find unobstructed path of minimum length to pellet
        * to achieve this, model the field as a graph
        * - empty and pellet fields are vertices
        * - edges connect vertically and horizontally neighboring vertices
        * run algorithm for shortest path
        */
        //execute first step of plan
        return 0;
    }
}
