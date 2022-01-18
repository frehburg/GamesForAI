package Game2048;

import Game2048.enums.BoardSize2048;
import Game2048.enums.Direction2048;
import Game2048.enums.Field2048;

public abstract class iGame2048 {
    protected BoardSize2048 size;

    public abstract void addRandom();
    public abstract void swipe(Direction2048 direction);
    public abstract boolean isSwipable(Direction2048 direction);
    public abstract BoardSize2048 getSize();
    public abstract Field2048[][] getBoard();
}
