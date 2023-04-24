package org.example;

public class MyTime {

    private static long startTime;

    public static void start() {
        startTime = System.currentTimeMillis();
    }
    public static long getTimeStamp() {
        return System.currentTimeMillis() - startTime;
    }

    public static void sleep(long millisToSleep) {
        long startTime = System.currentTimeMillis(), leftToSleep = millisToSleep;

        while(leftToSleep > 0) {
            try {
                Thread.sleep(leftToSleep);
            } catch (InterruptedException e) {
                System.out.println(e);
            } finally {
                long now = System.currentTimeMillis();
                leftToSleep = millisToSleep - (now - startTime);
            }
            //System.out.println(leftToSleep);
        }
    }
}
