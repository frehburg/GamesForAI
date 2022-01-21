package Utils;

public class Vector2DUtils {
    public static Tuple<Integer,Integer> add(Tuple<Integer,Integer> a, Tuple<Integer,Integer> b) {
        return new Tuple<>(a.getX() + b.getX(), a.getY() + b.getY());
    }

    public static Tuple<Integer,Integer> sub(Tuple<Integer,Integer> a, Tuple<Integer,Integer> b) {
        return new Tuple<>(a.getX() - b.getX(), a.getY() - b.getY());
    }

    public static int manhattanDistance(Tuple<Integer,Integer> a, Tuple<Integer,Integer> b) {
        int sum = Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
        return sum;
    }

    public static double euclidianDistance(Tuple<Integer,Integer> a, Tuple<Integer,Integer> b) {
        double sum = Math.sqrt(Math.pow(a.getX() - b.getX(),2)) + Math.sqrt(Math.pow(a.getY() - b.getY(),2));
        return sum;
    }
}
