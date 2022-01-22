package games.Snake.GUI;

import games.Snake.Model.SnakeState;
import games.Snake.enums.SnakeField;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class SnakeBoardComponent extends JComponent {

    public static int FIELD_SIZE = 20;
    //public static Color EMPTY = Color.decode("#005c34"), EMPTY2 = Color.decode("#1c9725"), SNAKE = Color.decode("#6a329f"), PELLET = Color.decode("#e51313");
    private final HashMap<Integer, Color> colorMapping;

    private SnakeState state;

    public SnakeBoardComponent(SnakeState state, HashMap<Integer, Color> colorMapping) {
        this.state = state;
        this.colorMapping = colorMapping;
    }

    public void paintComponent(Graphics g)
    {
        SnakeField[][] board = state.getBoard();
        boolean gameOver = state.isGameOver();
        int score = state.getScore();
        Graphics2D g2 = (Graphics2D)g;
        this.setSize(board.length * SnakeBoardComponent.FIELD_SIZE, (board.length+1) * SnakeBoardComponent.FIELD_SIZE);

        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[x].length; y++) {
                if(board[x][y].getID() == SnakeField.EMPTY.getID()) {
                    if((x + y & 1) == 1)
                        g2.setColor(colorMapping.get(SnakeField.EMPTY.getID() - 1));
                    else
                        g2.setColor(colorMapping.get(SnakeField.EMPTY.getID()));
                } else {
                    g2.setColor(colorMapping.get(board[x][y].getID()));
                }
                g2.fill(new Rectangle2D.Double(x * FIELD_SIZE, y * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE));
                g2.setColor(g2.getColor().brighter());
                g2.draw(new Rectangle2D.Double(x * FIELD_SIZE, y * FIELD_SIZE, FIELD_SIZE, FIELD_SIZE));
            }
        }
        if(gameOver) {
            g2.setColor(Color.black);
            g2.fill(new Rectangle2D.Double(0,0,board.length * SnakeBoardComponent.FIELD_SIZE, board.length * SnakeBoardComponent.FIELD_SIZE));
            g2.setColor(Color.RED);
            g2.drawString("GAME OVER", (int) (board.length * SnakeBoardComponent.FIELD_SIZE * 0.38), (int) (board.length * SnakeBoardComponent.FIELD_SIZE * 0.5));
        }
        g2.setColor(Color.white);
        g2.fill(new Rectangle2D.Double(0, board.length * SnakeBoardComponent.FIELD_SIZE, board.length * SnakeBoardComponent.FIELD_SIZE, SnakeBoardComponent.FIELD_SIZE));
        g2.setColor(Color.black);
        g2.drawString("SCORE: "+ score, (float)0.2 * SnakeBoardComponent.FIELD_SIZE, (float)(board.length + 0.6) * SnakeBoardComponent.FIELD_SIZE);
        g2.setColor(Color.RED);
        g2.draw(new Rectangle2D.Double(1,0,board.length * SnakeBoardComponent.FIELD_SIZE - 2, board.length * SnakeBoardComponent.FIELD_SIZE - 1));
        this.setPreferredSize(new Dimension(board.length * SnakeBoardComponent.FIELD_SIZE, (board.length+1) * SnakeBoardComponent.FIELD_SIZE));
    }

    public void setState(SnakeState state) {
        this.state = state;
    }
}
