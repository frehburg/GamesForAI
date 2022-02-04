package games.Snake.Model;

import games.Snake.Interfaces.iSnakeGame;
import games.Snake.enums.SnakeDirection;
import misc.Interfaces.iPlayer;

public class SnakePlayer implements iPlayer {

    private final String playerName;
    private iSnakeGame r;

    public SnakePlayer() {
        this.playerName = "Bob";
    }

    public SnakePlayer(String playerName) {
        this.playerName = playerName;
    }

    public void setGame(iSnakeGame r) {
        this.r = r;
    }

    public SnakeState up() {
        r.move(SnakeDirection.UP);
        return r.getState();
    }

    public SnakeState left() {
        r.move(SnakeDirection.LEFT);
        return r.getState();
    }

    public SnakeState right() {
        r.move(SnakeDirection.RIGHT);
        return r.getState();
    }

    public SnakeState down() {
        r.move(SnakeDirection.DOWN);
        return r.getState();
    }

    public String getName() {
        return playerName;
    }
}
