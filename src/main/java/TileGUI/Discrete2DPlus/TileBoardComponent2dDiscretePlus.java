package TileGUI.Discrete2DPlus;

import Game2048.Representation.State2048;
import Interfaces.iState;
import Interfaces.iState2dPlus;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class TileBoardComponent2dDiscretePlus extends JComponent {
    private final HashMap<Integer, Color> colorMapping;
    private final int size;
    private final HashMap<Integer, BufferedImage> spriteMapping;
    private iState2dPlus state;
    private boolean useImages;

    public TileBoardComponent2dDiscretePlus(iState2dPlus state, HashMap<Integer, Color> colorMapping, int size) {
        this.state = state;
        this.colorMapping = colorMapping;
        this.spriteMapping = null;
        useImages = false;
        this.size = size;
    }

    public TileBoardComponent2dDiscretePlus(iState2dPlus state, int size, HashMap<Integer, BufferedImage> spriteMapping) {
        this.state = state;
        this.colorMapping = null;
        this.spriteMapping = spriteMapping;
        useImages = true;
        this.size = size;
    }

    public void paintComponent(Graphics g)
    {
        int[][][] board = state.getIDBoard();
        boolean gameOver = state.isGameOver();
        boolean won = state.isWon();
        int score = state.getScore();
        Graphics2D g2 = (Graphics2D)g;
        this.setSize(board[0].length * size, (board[0].length+1) * size);

        if(useImages) {
            paintImages(g, board);
        } else {
            paintColors(g2, board);
        }

        if(gameOver) {
            g2.setColor(Color.black);
            g2.fill(new Rectangle2D.Double(0,0,board[0].length * size, board[0].length * size));
            g2.setColor(Color.RED);
            g2.drawString("GAME OVER", (int) (board[0].length * size * 0.38), (int) (board[0].length * size * 0.5));
        }
        g2.setColor(Color.white);
        g2.fill(new Rectangle2D.Double(0, board[0].length * size, board[0].length * size, size));
        g2.setColor(Color.black);
        g2.setFont(new Font("SansSerif",Font.PLAIN,16));
        g2.drawString("SCORE: "+ score + "           WON: " + won, (float)0.2 * size, (float)(board[0].length + 0.6) * size);
        g2.setColor(Color.RED);
        g2.draw(new Rectangle2D.Double(1,0,board[0].length * size - 2, board[0].length * size - 1));
        this.setPreferredSize(new Dimension(board[0].length * size, (board[0].length+1) * size));
    }

    private void paintColors(Graphics2D g2, int[][][] board) {
        for(int x = 0; x < board[0].length; x++) {
            for(int y = 0; y < board[x].length; y++) {
                g2.setColor(colorMapping.get(board[x][y]));
                g2.fill(new Rectangle2D.Double(x * size, y * size, size, size));
                g2.setColor(g2.getColor().brighter());
                g2.draw(new Rectangle2D.Double(x * size, y * size, size, size));
                if(state instanceof State2048) {
                    g2.setFont(new Font("SansSerif",Font.PLAIN,20));
                }
            }
        }
    }

    private void paintImages(Graphics g, int[][][] board) {
        for(int z = 0; z < board.length; z++) {
            for(int x = 0; x < board[z].length; x++) {
                for(int y = 0; y < board[z][x].length; y++) {
                    g.drawImage(spriteMapping.get(board[z][x][y]), x*size, y*size, size, size, null);
                }
            }
        }
    }

    public void setState(iState2dPlus state) {
        this.state = state;
    }
}
