package Snake.GUI;

import Interfaces.iGame;
import Interfaces.iHandler;
import Interfaces.iState;
import Snake.Model.SnakeGame;
import Snake.Model.SnakeHandler;
import Snake.Model.SnakeKeyManager;
import Snake.Model.SnakeState;
import Snake.enums.SnakeField;
import Interfaces.iTileGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;

public class SnakeGUI implements iTileGUI{
    private SnakeHandler h;
    private SnakeKeyManager km;
    private JFrame frame;
    private JPanel p;
    private SnakeBoardComponent bc;
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

        SnakeState state = game.getState();
        SnakeField[][] board = state.getBoard();
        frame.setSize(board.length * SnakeBoardComponent.FIELD_SIZE, board.length * SnakeBoardComponent.FIELD_SIZE);

        //Component
        bc = new SnakeBoardComponent(state, colorMapping);

        //
        p = new JPanel(new BorderLayout());
        p.setSize(board.length * SnakeBoardComponent.FIELD_SIZE, (board.length+1) * SnakeBoardComponent.FIELD_SIZE);
        p.setPreferredSize(new Dimension(board.length * SnakeBoardComponent.FIELD_SIZE, (board.length+1) * SnakeBoardComponent.FIELD_SIZE));
        p.validate();
        p.add(bc, BorderLayout.CENTER);

        frame.add(p);
        frame.pack();
        frame.setPreferredSize(new Dimension(board.length * SnakeBoardComponent.FIELD_SIZE, board.length * SnakeBoardComponent.FIELD_SIZE));

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
    public void setResources(HashMap<Integer, String> colorMapping, int k) {

    }
    @Override
    public void exit() {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }
}
