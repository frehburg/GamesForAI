package Snake.Representation;

import Snake.Interfaces.iSnakeGame;
import Snake.enums.SnakeDirection;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeKeyManager implements KeyListener{

    private SnakePlayer p;

    public SnakeKeyManager() {

    }

    public void setPlayer(SnakePlayer p) {
        this.p = p;
    }



    @Override
    public void keyPressed(KeyEvent e) {
        //up 38
        //left 37
        //right 39
        //down 40
        if(e.getKeyCode() == 37) {
            p.left();
        }
        if(e.getKeyCode() == 38) {
            p.up();
        }
        if(e.getKeyCode() == 39) {
            p.right();
        }
        if(e.getKeyCode() == 40) {
            p.down();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
