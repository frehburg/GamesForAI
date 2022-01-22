package games.Game2048.enums;

public enum BoardSize2048 {
    TWO(2), FOUR(4), EIGHT(8), SIXTEEN(16);

    private final int size;

    BoardSize2048(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
