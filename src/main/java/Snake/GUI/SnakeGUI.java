package Snake.GUI;

import Interfaces.iGame;
import Interfaces.iHandler;
import Interfaces.iState;
import Snake.Representation.SnakeGame;
import Snake.Representation.SnakeHandler;
import Snake.Representation.SnakeKeyManager;
import Snake.Representation.SnakeState;
import Snake.enums.SnakeField;
import Interfaces.iTileGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class SnakeGUI implements iTileGUI{
    private SnakeHandler h;
    private SnakeKeyManager km;
    private JFrame frame;
    private JPanel p;
    private BoardComponent bc;
    private HashMap<Integer, Color> colorMapping;

    public SnakeGUI(SnakeKeyManager km, SnakeHandler h, SnakeGame game) {
        colorMapping = new HashMap<>();
        colorMapping.put(SnakeField.EMPTY.getID() - 1, Color.decode("#005c34"));
        colorMapping.put(SnakeField.EMPTY.getID(), Color.decode("#1c9725"));
        colorMapping.put(SnakeField.UP.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.DOWN.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.LEFT.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.RIGHT.getID(), Color.decode("#6a329f"));
        colorMapping.put(SnakeField.PELLET.getID(), Color.decode("#e51313"));

        startGame(km,null,h,game);
    }

    @Override
    public void startGame(KeyListener km, MouseListener mm, iHandler h, iGame g) {
        this.km = (SnakeKeyManager) km;
        this.h = (SnakeHandler) h;
        SnakeGame game = (SnakeGame) g;
        //JFrame
        frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);//to place it in the center of the
        frame.setResizable(false);

        //init main menu
        SnakeState state = game.getState();
        SnakeField[][] board = state.getBoard();
        frame.setSize(board.length * BoardComponent.FIELD_SIZE, board.length * BoardComponent.FIELD_SIZE);

        //Component
        bc = new BoardComponent(state, colorMapping);

        //
        p = new JPanel(new BorderLayout());
        p.setSize(board.length * BoardComponent.FIELD_SIZE, (board.length+1) * BoardComponent.FIELD_SIZE);
        p.setPreferredSize(new Dimension(board.length * BoardComponent.FIELD_SIZE, (board.length+1) * BoardComponent.FIELD_SIZE));
        p.validate();
        p.add(bc, BorderLayout.CENTER);

        frame.add(p);
        frame.pack();
        frame.setPreferredSize(new Dimension(board.length * BoardComponent.FIELD_SIZE, board.length * BoardComponent.FIELD_SIZE));

        //input
        frame.addKeyListener(km);

        frame.setVisible(true);
    }

    @Override
    public void render(iState state) {
        SnakeState snakeState = (SnakeState) state;
        bc.setState(snakeState);
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    @Override
    public void setResources(HashMap<Integer, Color> colorMapping) {
        this.colorMapping = colorMapping;
    }

    @Override
    public void setResources(HashMap<Integer, BufferedImage> spriteMapping, String[] locations) {

    }

    public void exit() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
