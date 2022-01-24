package games.Chess.Model;

import games.Chess.interfaces.iChessGame;
import misc.TileGUI.enums.TileSize;
import misc.Utils.Tuple;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ChessMouseManager implements MouseListener, MouseMotionListener {
    private final ChessPlayer player;
    private int mouseX, mouseY;
    private String firstClick, secondClick;
    public static final String EMPTY_STRING = "";

    public ChessMouseManager(ChessPlayer player) {
        this.player = player;
        firstClick = EMPTY_STRING;
        secondClick = EMPTY_STRING;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1) {//left
            if(firstClick == EMPTY_STRING) {
                //set first click
                firstClick = mapCoordsToField();
                System.out.println("FROM:" + firstClick);
            } else if(secondClick == EMPTY_STRING) {
                //set second click
                secondClick = mapCoordsToField();
                System.out.println("TO:" + secondClick);
                //move piece on board
                player.move(firstClick,secondClick);
                //reset fields
                firstClick = EMPTY_STRING;
                secondClick = EMPTY_STRING;
            }
        }

    }

    private String mapCoordsToField() {
        int x = 0, y = 0;
        int tilesize = TileSize.HUGE.getSize();
        //x
        if(mouseX >= tilesize && mouseX < 9 * tilesize) {
            x = mouseX / tilesize - 1;
        }
        //y
        if(mouseY >= tilesize && mouseY < 9 * tilesize) {
            y = mouseY / tilesize - 1;
        }
        return iChessGame.convertCoordsToString(new Tuple<>(x,y));
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  {@code MOUSE_DRAGGED} events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX() - 4;
        mouseY = e.getY() - 30;
    }
}
