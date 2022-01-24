package misc.Interfaces;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

public interface iTileGUI {
    void setResources(HashMap<Integer, Color> colorMapping);

    void setResources(HashMap<Integer, String> locationMapping, int k);

    void startGame(KeyListener km, MouseListener ml, MouseMotionListener mm, iHandler h, iGame g);

    void render(iState state);

    void exit();
}
