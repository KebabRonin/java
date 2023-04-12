package org.example;

import java.util.*;

public class Exploration {
    private final SharedMemory mem;
    private final ExplorationMap map;
    private final List<Robot> robots = new ArrayList<>();

    public SharedMemory getMem() {
        return mem;
    }

    public Exploration(int n) {
        mem = new SharedMemory(n);
        map = new ExplorationMap(n);
    }
    public void start() {
        for (Robot robot : robots) {
            //start a new Thread representing the robot;
            robot.setRunning(true);
            new Thread(robot).start();
        }
    }

    public ExplorationMap getMap() {
        return map;
    }

    public static void main(String args[]) {
        int n = 4;
        Exploration explore = new Exploration(n);
        explore.addRobot(new Robot("Wall-E", ((int)(Math.random()* (n+1)))%n, ((int)(Math.random()* (n+1)))%n, explore));
        explore.addRobot(new Robot("R2D2", ((int)(Math.random()* (n+1)))%n, ((int)(Math.random()* (n+1)))%n, explore));
        explore.addRobot(new Robot("Optimus Prime", ((int)(Math.random()* (n+1)))%n, ((int)(Math.random()* (n+1)))%n, explore));
        explore.start();

        while(!explore.getMap().isFull()) {
            try {
                Thread.sleep(2400);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        for(Robot r : explore.robots) {
            r.setRunning(false);
        }
        System.out.println(explore.getMap());
    }

    public void addRobot(Robot r) {
        robots.add(r);
    }
}