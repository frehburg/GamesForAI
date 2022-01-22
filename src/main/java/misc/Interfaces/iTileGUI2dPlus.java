package misc.Interfaces;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.HashMap;

public interface iTileGUI2dPlus {
    public abstract void setResources(HashMap<Integer, Color> colorMapping);
    public abstract void setResources(HashMap<Integer, String> locationMapping, int k);

    public abstract void startGame(KeyListener km, MouseListener mm, iHandler h, iGame2dPlus game);
    public abstract void render(iState2dPlus state);

    void exit();
}
