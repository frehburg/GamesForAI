package games.Game2048.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager2048 implements KeyListener{

    private Player2048 p;

    public KeyManager2048() {

    }

    public void setPlayer(Player2048 p) {
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
