package TileGUI;

import Interfaces.iState;
import Snake.GUI.SnakeBoardComponent;
import Snake.Representation.SnakeState;
import Snake.enums.SnakeField;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class TileBoardComponent extends JComponent {
    private final HashMap<Integer, Color> colorMapping;
    private final int size;
    private iState state;

    public TileBoardComponent(iState state, HashMap<Integer, Color> colorMapping, int size) {
        this.state = state;
        this.colorMapping = colorMapping;
        this.size = size;
    }

    public void paintComponent(Graphics g)
    {
        int[][] board = state.getIDBoard();
        boolean gameOver = state.isGameOver();
        boolean won = state.isWon();
        int score = state.getScore();
        Graphics2D g2 = (Graphics2D)g;
        this.setSize(board.length * size, (board.length+1) * size);

        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[x].length; y++) {
                if(board[x][y] == SnakeField.EMPTY.getID()) {
                    if((x + y & 1) == 1)
                        g2.setColor(colorMapping.get(SnakeField.EMPTY.getID() - 1));
                    else
                        g2.setColor(colorMapping.get(SnakeField.EMPTY.getID()));
                } else {
                    g2.setColor(colorMapping.get(board[x][y]));
                }
                g2.fill(new Rectangle2D.Double(x * size, y * size, size, size));
                g2.setColor(g2.getColor().brighter());
                g2.draw(new Rectangle2D.Double(x * size, y * size, size, size));
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
        g2.drawString("SCORE: "+ score + "           WON: " + won, (float)0.2 * SnakeBoardComponent.FIELD_SIZE, (float)(board.length + 0.6) * SnakeBoardComponent.FIELD_SIZE);
        g2.setColor(Color.RED);
        g2.draw(new Rectangle2D.Double(1,0,board.length * SnakeBoardComponent.FIELD_SIZE - 2, board.length * SnakeBoardComponent.FIELD_SIZE - 1));
        this.setPreferredSize(new Dimension(board.length * SnakeBoardComponent.FIELD_SIZE, (board.length+1) * SnakeBoardComponent.FIELD_SIZE));
    }

    public void setState(iState state) {
        this.state = state;
    }
}
