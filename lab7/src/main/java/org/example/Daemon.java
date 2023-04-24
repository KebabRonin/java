package org.example;

public class Daemon implements Runnable{
    private Exploration e;
    private int cyclesSpent;
    private long cycleLen;

    public Daemon(Exploration e, long time, int cycles) {
        this.e = e;
        cyclesSpent = cycles;
        cycleLen = time;
        MyTime.start();
        new Thread(this).start();
    }

    public void run() {
        while(cyclesSpent > 0 && !e.getMap().isFull()) {
            MyTime.sleep(cycleLen);
            cyclesSpent--;
        }

        e.exitAll();
        System.out.println("==========================================");
        System.out.print("Exploration ended at " + MyTime.getTimeStamp());
        if(!e.getMap().isFull())
            System.out.println(" prematurely");
        else {
            System.out.println(" successfully");
        }
        for(Robot r : e.robots) {
            System.out.println(r.getName() + " inserted " + r.nrPlaced + " tokens");
        }
        System.out.println("==========================================");

        System.out.println(e.getMap());
        System.out.println("==========================================");
        System.out.println("Simulation done");
        System.out.println("==========================================");
    }
}
