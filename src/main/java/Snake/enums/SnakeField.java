package Snake.enums;

public enum SnakeField {
    EMPTY(0), UP(1), DOWN(2), LEFT(3), RIGHT(4), PELLET(5);

    private final int id;

    SnakeField(int id) {
        this.id = id;
    }

    public int getID(){
        return id;
    }
}
