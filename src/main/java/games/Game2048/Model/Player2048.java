package games.Game2048.Model;

import games.Game2048.Interfaces.iGame2048;
import games.Game2048.enums.Direction2048;
import misc.Interfaces.iPlayer;

public class Player2048 implements iPlayer {

    private final String playerName;
    private iGame2048 r;

    public Player2048() {
        this.playerName = "Chris";
    }

    public Player2048(String playerName) {
        this.playerName = playerName;
    }

    public void setGame(iGame2048 r) {
        this.r = r;
    }

    public State2048 up() {
        r.swipe(Direction2048.UP);
        return r.getState();
    }

    public State2048 left() {
        r.swipe(Direction2048.LEFT);
        return r.getState();
    }

    public State2048 right() {
        r.swipe(Direction2048.RIGHT);
        return r.getState();
    }

    public State2048 down() {
        r.swipe(Direction2048.DOWN);
        return r.getState();
    }

    @Override
    public String getName() {
        return playerName;
    }
}
