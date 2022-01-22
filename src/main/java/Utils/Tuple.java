package Utils;

public class Tuple <X, Y> {
    private X x;
    private Y y;

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
            if(!t.getY().equals(y)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public Tuple<X, Y> clone() {
        return new Tuple<>(getX(),getY());
    }
}
