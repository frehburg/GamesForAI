package TileGUI.Discrete2DPlus;

import Game2048.Representation.State2048;
import Interfaces.iState;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;

public class TileBoardComponent2dDiscretePlus extends JComponent {
    private final HashMap<Integer, Color> colorMapping;
    private final int size;
    private iState state;

    public TileBoardComponent2dDiscretePlus(iState state, HashMap<Integer, Color> colorMapping, int size) {
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
                g2.setColor(colorMapping.get(board[x][y]));
                g2.fill(new Rectangle2D.Double(x * size, y * size, size, size));
                g2.setColor(g2.getColor().brighter());
                g2.draw(new Rectangle2D.Double(x * size, y * size, size, size));
                if(state instanceof State2048) {
                    g2.setFont(new Font("SansSerif",Font.PLAIN,20));
                    draw2048Strings(board[x][y], g2, x, y);
                }
            }
        }
        if(gameOver) {
            g2.setColor(Color.black);
            g2.fill(new Rectangle2D.Double(0,0,board.length * size, board.length * size));
            g2.setColor(Color.RED);
            g2.drawString("GAME OVER", (int) (board.length * size * 0.38), (int) (board.length * size * 0.5));
        }
        g2.setColor(Color.white);
        g2.fill(new Rectangle2D.Double(0, board.length * size, board.length * size, size));
        g2.setColor(Color.black);
        g2.setFont(new Font("SansSerif",Font.PLAIN,16));
        g2.drawString("SCORE: "+ score + "           WON: " + won, (float)0.2 * size, (float)(board.length + 0.6) * size);
        g2.setColor(Color.RED);
        g2.draw(new Rectangle2D.Double(1,0,board.length * size - 2, board.length * size - 1));
        this.setPreferredSize(new Dimension(board.length * size, (board.length+1) * size));
    }

    private void draw2048Strings(int power, Graphics2D g2, int x, int y) {
        String s;
        if(power == 0) {
            s = "    ";
        }else if(power <= 11 ) {
            s = (int)Math.pow(2, power) + "";
            while(s.length() < 4) {
                s = " " + s;
            }
        } else {
            s = "2^"+power;
        }
        if(power > 0 && power <= 2) {
            g2.setColor(Color.black);
            g2.drawString(s, (int) (x * size + 0.15 * size), (int) (y * size + 0.65 * size));
        } else {
            g2.setColor(Color.white);
            g2.drawString(s, (int) (x * size + 0.2 * size), (int) (y * size + 0.65 * size));
        }
    }

    public void setState(iState state) {
        this.state = state;
    }
}
