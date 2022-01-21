package Snake.Representation;

import Snake.Interfaces.iSnake;
import Snake.enums.SnakeDirection;
import Snake.enums.SnakeField;
import Utils.Tuple;

import java.util.ArrayList;

import static Snake.Representation.SnakeGame.mapDirectionToField;

public class Snake extends iSnake {
    private ArrayList<Tuple<SnakeDirection,Tuple<Integer,Integer>>> body;
    private SnakeDirection headDirection;
    public Snake(Tuple<SnakeDirection,Tuple<Integer,Integer>> head, Tuple<SnakeDirection,Tuple<Integer,Integer>> body1, Tuple<SnakeDirection,Tuple<Integer,Integer>> body2) {
        body = new ArrayList<>();
        body.add(head);
        body.add(body1);
        body.add(body2);
        headDirection = SnakeDirection.RIGHT;
    }

    @Override
    public void addToFront(SnakeDirection direction) {
        Tuple<Integer,Integer> delta = SnakeGame.mapDirectionToDelta(direction);
        ArrayList<Tuple<SnakeDirection,Tuple<Integer,Integer>>> temp = new ArrayList<>();
        Tuple<Integer, Integer> grown = new Tuple<>(getHead().getX() + delta.getX(), getHead().getY() + delta.getY());
        temp.add(new Tuple<SnakeDirection,Tuple<Integer,Integer>>(getHeadDirection(), grown));
        temp.addAll(body);
        body = temp;
    }

    @Override
    public void growAtBack() {
        Tuple<Integer,Integer> delta = SnakeGame.mapDirectionToDelta(getTailDirection());
        ArrayList<Tuple<SnakeDirection,Tuple<Integer,Integer>>> temp = new ArrayList<>();
        Tuple<Integer, Integer> grown = new Tuple<>(getTail().getX() - delta.getX(), getTail().getY() - delta.getY());
        body.add(new Tuple<SnakeDirection,Tuple<Integer,Integer>>(getTailDirection(), grown));
    }

    @Override
    public int getLength() {
        return body.size();
    }

    @Override
    public ArrayList<Tuple<SnakeDirection,Tuple<Integer,Integer>>> getBody() {
        return body;
    }

    @Override
    public SnakeDirection getHeadDirection() {
        return headDirection;
    }

    @Override
    public SnakeDirection getTailDirection() {
        return body.get(body.size() - 1).getX();
    }

    @Override
    public Tuple<Integer, Integer> getHead() {
        return body.get(0).getY();
    }

    @Override
    public Tuple<Integer, Integer> getTail() {
        return body.get(body.size() - 1).getY();
    }

    @Override
    public void move(SnakeDirection direction) {
        //remove end of tail
        body.remove(body.size() - 1);
        //add in front of head
       addToFront(direction);
       this.headDirection = direction;
    }

    @Override
    public ArrayList<Tuple<SnakeField, Tuple<Integer, Integer>>> getSnakeFields() {
        ArrayList<Tuple<SnakeField, Tuple<Integer, Integer>>> fields = new ArrayList<>();
        for(Tuple<SnakeDirection,Tuple<Integer,Integer>> bodySegment : body) {
            int x = bodySegment.getY().getX(), y = bodySegment.getY().getY();;
            SnakeDirection d = bodySegment.getX();
            SnakeField f = mapDirectionToField(d);
            fields.add(new Tuple<>(f, new Tuple<>(x,y)));
        }
        return fields;
    }
}
