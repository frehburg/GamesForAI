package Snake.GUI;

import Snake.Representation.SnakeGame;
import Snake.Representation.SnakeHandler;
import Snake.Representation.SnakePlayer;
import Snake.Representation.SnakeState;
import Snake.enums.SnakeBoardSize;
import Snake.enums.SnakeField;
import Snake.enums.SnakeSpeed;
import UserInput.KeyManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakeGUI {
    private final SnakeHandler h;
    private final SnakePlayer km;
    private JFrame frame, frame2;
    private JPanel p;
    private BoardComponent bc;
    public SnakeGUI(SnakePlayer km, SnakeHandler h, SnakeGame game) {
        this.km = km;
        this.h = h;
        //JFrame
        frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);//to place it in the center of the
        //frame.setResizable(false);

        //init main menu
        SnakeState state = game.getState();
        SnakeField[][] board = state.getBoard();
        frame.setSize(board.length * BoardComponent.FIELD_SIZE, board.length * BoardComponent.FIELD_SIZE);

        //Component
        bc = new BoardComponent(state);

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

    public void initPlay(SnakeGame game) {
        SnakeState state = game.getState();
        SnakeField[][] board = state.getBoard();
        frame.setSize(board.length * BoardComponent.FIELD_SIZE, board.length * BoardComponent.FIELD_SIZE);

        //Component
        bc = new BoardComponent(state);

        //
        p = new JPanel(new BorderLayout());
        p.setSize(board.length * BoardComponent.FIELD_SIZE, (board.length+1) * BoardComponent.FIELD_SIZE);
        p.setPreferredSize(new Dimension(board.length * BoardComponent.FIELD_SIZE, (board.length+1) * BoardComponent.FIELD_SIZE));
        p.validate();
        p.add(bc, BorderLayout.CENTER);

        frame.add(p);
        frame.pack();
        frame.setPreferredSize(new Dimension(board.length * BoardComponent.FIELD_SIZE, board.length * BoardComponent.FIELD_SIZE));
    }

    public void render(SnakeState state) {
        bc.setState(state);
        frame2.invalidate();
        frame2.validate();
        frame2.repaint();
    }

    public void mainMenu() {
        //board size
        ButtonGroup bg = new ButtonGroup();
        JPanel boardSize = new JPanel(new FlowLayout());
        JRadioButton smallBoard, mediumBoard, bigBoard, hugeBoard;
        smallBoard = new JRadioButton("Small");
        mediumBoard = new JRadioButton("Medium");
        bigBoard = new JRadioButton("Big");
        hugeBoard = new JRadioButton("Huge");
        bg.add(smallBoard);
        bg.add(mediumBoard);
        bg.add(hugeBoard);
        bg.add(bigBoard);
        JLabel l = new JLabel("Board size:");
        boardSize.add(l);
        boardSize.add(smallBoard);
        boardSize.add(mediumBoard);
        boardSize.add(bigBoard);
        boardSize.add(hugeBoard);
        //speed
        ButtonGroup bg2 = new ButtonGroup();
        JPanel speed = new JPanel(new FlowLayout());
        JRadioButton simpleSpeed, easySpeed, mediumSpeed, hardSpeed, insaneSpeed;
        simpleSpeed = new JRadioButton("Simple");
        easySpeed = new JRadioButton("Easy");
        mediumSpeed = new JRadioButton("Medium");
        hardSpeed = new JRadioButton("Hard");
        insaneSpeed = new JRadioButton("Insane");
        bg2.add(simpleSpeed);
        bg2.add(easySpeed);
        bg2.add(mediumSpeed);
        bg2.add(hardSpeed);
        bg2.add(insaneSpeed);
        JLabel l2 = new JLabel("Difficulty:");
        speed.add(l2);
        speed.add(simpleSpeed);
        speed.add(easySpeed);
        speed.add(mediumSpeed);
        speed.add(hardSpeed);
        speed.add(insaneSpeed);
        //name
        JPanel name = new JPanel(new FlowLayout());
        JButton start = new JButton("Start Game");
        SnakeGUI temp = this;
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SnakeBoardSize snakeBoardSize = SnakeBoardSize.STANDARD;
                SnakeSpeed snakeSpeed = SnakeSpeed.MEDIUM;
                snakeBoardSize = SnakeBoardSize.STANDARD;
                snakeSpeed = SnakeSpeed.MEDIUM;

                //board size
                if(smallBoard.isSelected()) {
                    snakeBoardSize = SnakeBoardSize.SMALL;
                }
                if(mediumBoard.isSelected()) {
                    snakeBoardSize = SnakeBoardSize.STANDARD;
                }
                if(bigBoard.isSelected()) {
                    snakeBoardSize = SnakeBoardSize.BIG;
                }
                if(hugeBoard.isSelected()) {
                    snakeBoardSize = SnakeBoardSize.HUGE;
                }

                //speed
                if(simpleSpeed.isSelected()) {
                    snakeSpeed = SnakeSpeed.SIMPLE;
                }
                if(easySpeed.isSelected()) {
                    snakeSpeed = SnakeSpeed.EASY;
                }
                if(mediumSpeed.isSelected()) {
                    snakeSpeed = SnakeSpeed.MEDIUM;
                }
                if(hardSpeed.isSelected()) {
                    snakeSpeed = SnakeSpeed.HARD;
                }
                if(insaneSpeed.isSelected()) {
                    snakeSpeed = SnakeSpeed.INSANE;
                }
                h.setGUI(temp);
                h.newGame(snakeBoardSize,snakeSpeed);
            }
        });
        JLabel l3 = new JLabel("Name:");
        JTextField tf = new JTextField("Bob");
        name.add(l3);
        name.add(tf);
        name.add(start);

        //Join all 3
        p = new JPanel(new BorderLayout());
        p.add(boardSize, BorderLayout.NORTH);
        p.add(speed, BorderLayout.CENTER);
        p.add(name, BorderLayout.SOUTH);

        frame.add(p);
        frame.pack();
    }

    public void addToHighscores() {

    }
}
