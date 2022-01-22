package misc.Utils;

public class ArrayUtils {
    public static int[][] copyArray(int[][] a) {
        int[][] b = new int[a.length][a[0].length];
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a[i].length; j++) {
                b[i][j] = a[i][j];
            }
        }
        return b;
    }
}
