package hw_lab2;

/**
 * Generates random positive ints
 * @author Squirrel Eiserloh
 */
public class Random {
    private static long seed = Math.abs(System.nanoTime());
    public static int random() {
        seed ^= (seed << 13);
        seed ^= (seed >> 17);
        seed ^= (seed << 5);
        seed = Math.abs((int)seed);
        return (int) seed;
    }
}
