package Snake.agents;

import Interfaces.iAgent;
import Snake.Representation.SnakeGame;
import Snake.Representation.SnakePlayer;
import Snake.Representation.SnakeState;
import Snake.enums.SnakeBoardSize;
import Snake.enums.SnakeSpeed;
import Utils.RandomUtils;

import java.util.ArrayList;

public class SnakeAgentHandler {
    private final iAgent agent;
    private final SnakePlayer player;
    private final SnakeBoardSize snakeBoardSize;
    private final SnakeSpeed snakeSpeed;

    public SnakeAgentHandler() {
        this.player = new SnakePlayer();
        this.agent = new SnakeBaseLineAgent(player);
        this.snakeBoardSize = SnakeBoardSize.STANDARD;
        this.snakeSpeed = SnakeSpeed.EASY;
        System.out.println(play(1000000000));
    }
    private double play(int games) {
        double avg = 0.0;
        double sum = 0.0;
        for(int i = 0; i < games; i++) {
            int score = play();
            avg += (double) (score/games);
            sum += score;
        }
        return avg;
    }

    private int play() {
        SnakeGame game = new SnakeGame(snakeBoardSize, snakeSpeed, player);
        ArrayList<SnakeState> log = new ArrayList<>();
        SnakeState state = game.getState();
        while(state.isGameOver()) {
            agent.react(state);
            game.updateBoard();
            state = game.getState();
            log.add(state);
        }
        return state.getScore();
    }
}
