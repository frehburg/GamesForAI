package Game2048;

import Snake.Interfaces.iSnakeGame;
import Snake.Representation.SnakeState;
import Snake.enums.SnakeDirection;

public class Player2048{

    private iSnakeGame r;

    public Player2048() {

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
