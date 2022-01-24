package misc.TileGUI.Discrete2D;

import misc.Interfaces.iGame;
import misc.Interfaces.iHandler;
import misc.Interfaces.iState;
import misc.Interfaces.iTileGUI;
import misc.TileGUI.enums.TileSize;
import misc.Utils.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TileGUI2dDiscrete implements iTileGUI {
    private iHandler h;
    private KeyListener km;
    private MouseListener ml;
    private JFrame frame;
    private JPanel p;
    private TileBoardComponent2dDiscrete bc;
    private HashMap<Integer, Color> colorMapping;
    private HashMap<Integer, BufferedImage> spriteMapping;
    private String[] locations;
    private int size;
    private String title;
    private boolean useImages;

    public TileGUI2dDiscrete(KeyListener km, MouseListener ml, iHandler h, iGame game, TileSize tileSize, String title, HashMap<Integer, Color> colorMapping) {
        this.title = title;
        this.size = tileSize.getSize();
        this.useImages = false;
        setResources(colorMapping);
        startGame(km, ml, (MouseMotionListener) ml, h, game);
    }

    public TileGUI2dDiscrete(KeyListener km, MouseListener ml, iHandler h, iGame game, TileSize tileSize, HashMap<Integer, String> locationMapping, String title) {
        this.title = title;
        this.size = tileSize.getSize();
        this.useImages = true;
        setResources(locationMapping, 0);
        startGame(km, ml, (MouseMotionListener) ml, h, game);
    }

    @Override
    public void setResources(HashMap<Integer, Color> colorMapping) {
        this.colorMapping = colorMapping;
    }

    @Override
    public void setResources(HashMap<Integer, String> locationMapping, int k) {
        this.spriteMapping = new HashMap<>();
        Set<Map.Entry<Integer, String>> entrySet = locationMapping.entrySet();
        for(Map.Entry<Integer, String> entry : entrySet) {
            spriteMapping.put(entry.getKey(), ImageLoader.loadImage(entry.getValue()));
        }
    }

    @Override
    public void startGame(KeyListener km, MouseListener ml, MouseMotionListener mm, iHandler h, iGame g) {
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
        //Component
        if(useImages) {
            bc = new TileBoardComponent2dDiscrete(state, size, spriteMapping);
        } else {
            bc = new TileBoardComponent2dDiscrete(state, colorMapping, size);
        }
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
        if(mm != null) {
            frame.addMouseListener(ml);
            frame.addMouseMotionListener(mm);
        }
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
