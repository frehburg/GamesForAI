package TileGUI.enums;

public enum TileSize {
    TINY(16), SMALL(20), MEDIUM (24), BIG(32), HUGE(64);

    private final int size;

    TileSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
