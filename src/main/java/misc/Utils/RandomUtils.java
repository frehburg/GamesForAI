package misc.Utils;
import java.util.Random;

public class RandomUtils {
    public static int getRandom(int min, int max) {
        max++;
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }
}
