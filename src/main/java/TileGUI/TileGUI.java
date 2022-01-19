package TileGUI;

import Interfaces.iGame;
import Interfaces.iHandler;
import Interfaces.iState;
import Interfaces.iTileGUI;
import Snake.GUI.SnakeBoardComponent;
import Snake.Representation.SnakeGame;
import Snake.Representation.SnakeHandler;
import Snake.Representation.SnakeKeyManager;
import Snake.Representation.SnakeState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class TileGUI implements iTileGUI {
    private iHandler h;
    private KeyListener km;
    private MouseListener mm;
    private JFrame frame;
    private JPanel p;
    private TileBoardComponent bc;
    private HashMap<Integer, Color> colorMapping;
    private HashMap<Integer, BufferedImage> spriteMapping;
    private String[] locations;
    private int size;
    private String title;

    public TileGUI(KeyListener km, MouseListener mm, iHandler h, iGame game, TileSize tileSize, String title, HashMap<Integer, Color> colorMapping) {
        this.title = title;
        this.size = tileSize.getSize();
        setResources(colorMapping);
        startGame(km, mm, h, game);
    }

    public TileGUI(KeyListener km, MouseListener mm, iHandler h, iGame game, TileSize tileSize, String title, HashMap<Integer, BufferedImage> spriteMapping, String[] locations) {
        this.title = title;
        this.size = tileSize.getSize();
        setResources(spriteMapping,locations);
        startGame(km, mm, h, game);
    }

    @Override
    public void setResources(HashMap<Integer, Color> colorMapping) {
        this.colorMapping = colorMapping;
    }

    @Override
    public void setResources(HashMap<Integer, BufferedImage> spriteMapping, String[] locations) {
        this.spriteMapping = spriteMapping;
        this.locations = locations;
        //TODO: load sprites
    }

    @Override
    public void startGame(KeyListener km, MouseListener mm, iHandler h, iGame g) {
        //TODO: with sprites
        this.km = km;
        this.h = h;
        iGame game = g;

        //JFrame
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);//to place it in the center of the
        //frame.setResizable(false);

        iState state = game.getState();
        int[][] board = state.getIDBoard();
        frame.setSize(board.length * size, board.length * size);
        System.out.println("Hello");
        //Component
        bc = new TileBoardComponent(state, colorMapping,size);
        System.out.println("Hello");
        //
        p = new JPanel(new BorderLayout());
        p.setSize(bc.getWidth(), bc.getHeight());
        p.setPreferredSize(new Dimension(board.length * size, (board.length+1) * size));
        p.validate();
        p.add(bc, BorderLayout.CENTER);

        frame.add(p);
        frame.pack();
        frame.setPreferredSize(new Dimension(board.length * size, (board.length+1) * size));

        //input
        if(km != null)
            frame.addKeyListener(km);
        if(mm != null)
            frame.addMouseListener(mm);
        frame.setVisible(true);
    }

    @Override
    public void render(iState state) {
        //TODO: rendering with sprites
        bc.setState(state);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    @Override
    public void exit() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
