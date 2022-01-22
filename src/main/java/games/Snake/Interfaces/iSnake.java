package games.Snake.Interfaces;

import games.Snake.enums.SnakeDirection;
import games.Snake.enums.SnakeField;
import misc.Utils.Tuple;

import java.util.ArrayList;

public abstract class iSnake {
    public abstract void addToFront(SnakeDirection direction);

    public abstract void growAtBack();

    public abstract int getLength();

    public abstract Object getBody();

    public abstract SnakeDirection getHeadDirection();

    public abstract SnakeDirection getTailDirection();

    public abstract Tuple<Integer, Integer> getHead();

    public abstract Tuple<Integer, Integer> getTail();

    public abstract void move(SnakeDirection direction);

    public abstract ArrayList<Tuple<SnakeField, Tuple<Integer, Integer>>> getSnakeFields();
}
