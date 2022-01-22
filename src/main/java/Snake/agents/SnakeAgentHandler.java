package Snake.agents;

import Interfaces.iAgent;
import Snake.Model.SnakeGame;
import Snake.Model.SnakePlayer;
import Snake.Model.SnakeState;
import Snake.enums.SnakeBoardSize;
import Snake.enums.SnakeSpeed;
import Utils.Triple;

import java.util.ArrayList;

public class SnakeAgentHandler {
    private final iAgent agent;
    private final SnakePlayer player;
    private final SnakeBoardSize snakeBoardSize;
    private final SnakeSpeed snakeSpeed;

    public SnakeAgentHandler() {
        this.player = new SnakePlayer();
        this.agent = new SnakeSimpleReflexAgent(player);
        this.snakeBoardSize = SnakeBoardSize.STANDARD;
        this.snakeSpeed = SnakeSpeed.EASY;
        Triple<Double, Double, Double> scores = play(10000);
        System.out.println("Point average: "+scores.getX()+
                " maximum points: " + scores.getY() +
                " minimum score: " + scores.getZ());
    }
    private Triple<Double, Double, Double> play(int games) {
        double sum = 0.0;
        double max = 0.0;
        double min = 1000;
        for(int i = 0; i < games; i++) {
            System.out.println(i);
            int score = play();
            sum += score;
            if(score < min)
                min = score;
            if(score > max)
                max = score;
            System.out.println("Score: "+score+
                    " maximum points: " + max +
                    " minimum score: " + min);
        }
        double avg = sum /(double) games;
        return new Triple<>(avg, max, min);
    }

    private int play() {
        SnakeGame game = new SnakeGame(snakeBoardSize, snakeSpeed, player);
        ArrayList<SnakeState> log = new ArrayList<>();
        SnakeState state = game.getState();
        while(!state.isGameOver()) {
            System.out.println(state.getSnake().getHead());
            agent.react(state);
            game.updateBoard();
            state = game.getState();
            System.out.println(state.getScore());
            log.add(state);
        }
        state = game.getState();
        return state.getScore();
    }
}
