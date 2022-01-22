package misc.Utils;

public class Triple <X, Y, Z> {
    private X x;
    private Y y;
    private Z z;

    public Triple(X x, Y y, Z z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public X getX() {
        return x;
    }

    public Y getY() {
        return y;
    }

    public Z getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "X: " + x.toString() + " Y: " + y.toString() + " Z: " + z.toString();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Triple<?,?,?>) {
            Triple t = (Triple) o;
            if(!t.getX().equals(x)) {
                return false;
            }
            if(!t.getY().equals(y)) {
                return false;
            }
            if(!t.getZ().equals(z)) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}
