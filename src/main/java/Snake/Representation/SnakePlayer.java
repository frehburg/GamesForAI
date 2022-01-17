package Snake.Representation;

import Snake.Interfaces.iSnakeGame;
import Snake.enums.SnakeDirection;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakePlayer implements KeyListener{

    private iSnakeGame r;

    public SnakePlayer() {

    }

    public void setGame(iSnakeGame r) {
        this.r = r;
    }



    @Override
    public void keyPressed(KeyEvent e) {
        //up 38
        //left 37
        //right 39
        //down 40
        if(e.getKeyCode() == 37) {
            r.move(SnakeDirection.LEFT);
        }
        if(e.getKeyCode() == 38) {
            r.move(SnakeDirection.UP);
        }
        if(e.getKeyCode() == 39) {
            r.move(SnakeDirection.RIGHT);
        }
        if(e.getKeyCode() == 40) {
            r.move(SnakeDirection.DOWN);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
