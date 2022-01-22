package ai.Snake.agents;

import misc.Interfaces.iAgent;
import misc.Interfaces.iState;
import games.Snake.Model.SnakePlayer;
import misc.Utils.RandomUtils;

/**
 * Does not get a point in 1,000,000 games
 */
public class SnakeBaseLineAgent implements iAgent {

    private final SnakePlayer player;

    public SnakeBaseLineAgent(SnakePlayer player) {
        this.player = player;
    }

    @Override
    public void react(iState state) {
        int move = RandomUtils.getRandom(0,3);
        switch (move) {
            case 0:
                player.up();
                break;
            case 1:
                player.left();
            case 2:
                player.down();
            case 3:
                player.right();
        }
    }
}
