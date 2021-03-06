package misc.Utils;

public class Tuple <X, Y> {
    private final X x;
    private final Y y;

    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    @Override
    public String toString() {
        return "X: " + x.toString() + " Y: " + y.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Tuple<?,?>) {
            Tuple t = (Tuple) o;
            if(!t.getX().equals(x)) {
                return false;
            }
            return t.getY().equals(y);
        } else {
            return false;
        }
    }

    @Override
    public Tuple<X, Y> clone() {
        return new Tuple<>(getX(),getY());
    }
}
