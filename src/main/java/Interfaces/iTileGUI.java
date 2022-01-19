package Interfaces;

import Interfaces.iGame;
import Interfaces.iHandler;
import Interfaces.iState;
import Snake.Representation.SnakeState;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public interface iTileGUI {
    public abstract void setResources(HashMap<Integer, Color> colorMapping);
    public abstract void setResources(HashMap<Integer, BufferedImage> spriteMapping, String[] locations);
    public abstract void startGame(KeyListener km, MouseListener mm, iHandler h, iGame game);
    public abstract void render(iState state);

    void exit();
}
