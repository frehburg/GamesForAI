package Snake.Model;

import Snake.Interfaces.iSnakeGame;
import Snake.enums.SnakeDirection;

public class SnakePlayer{

    private iSnakeGame r;

    public SnakePlayer() {

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

}
