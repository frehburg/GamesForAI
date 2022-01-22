package misc.Interfaces;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;

public interface iTileGUI {
    public abstract void setResources(HashMap<Integer, Color> colorMapping);
    public abstract void setResources(HashMap<Integer, String> locationMapping, int k);

    public abstract void startGame(KeyListener km, MouseListener mm, iHandler h, iGame game);
    public abstract void render(iState state);

    void exit();
}
