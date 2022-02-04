package ai.Snake.agents;

import misc.Interfaces.iAgent;
import games.Snake.Model.SnakeGame;
import games.Snake.Model.SnakePlayer;
import games.Snake.Model.SnakeState;
import games.Snake.enums.SnakeBoardSize;
import games.Snake.enums.SnakeSpeed;
import misc.Utils.Triple;

import java.util.ArrayList;

public class SnakeAgentHandler {
    private final iAgent agent;
    private final SnakePlayer player;
    private final SnakeBoardSize snakeBoardSize;
    private final SnakeSpeed snakeSpeed;

    public SnakeAgentHandler() {
        this.player = new SnakePlayer("Iatros v1");
        this.agent = new SnakeBaseLineAgent(player);
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
