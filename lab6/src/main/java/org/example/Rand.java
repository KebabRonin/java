package org.example;

public class Rand {
    static long seed = System.nanoTime();
    public static double rand() {
        seed ^= seed >> 13;
        seed ^= seed << 5;
        seed ^= seed >> 7;
        return (double) seed % 1001 / 1000;
    }
}
