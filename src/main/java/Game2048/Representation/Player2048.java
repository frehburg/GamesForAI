package Game2048.Representation;

import Game2048.Interfaces.iGame2048;
import Game2048.enums.Direction2048;

public class Player2048{

    private iGame2048 r;

    public Player2048() {

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

}
