package org.example;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class Exploration {
    private final SharedMemory mem;
    private final ExplorationMap map;
    final List<Robot> robots = new ArrayList<>();

    public SharedMemory getMem() {
        return mem;
    }

    public Exploration(int n) {
        mem = new SharedMemory(n);
        map = new ExplorationMap(n);
    }
    public void begin(int i, int i1) {

        Daemon d = new Daemon(this, i, i1);

        //Each Robot gets assigned robArea rows to search


        int area = this.getMap().getN();
        int robArea = area / robots.size();
        int rest = area % robots.size();
        int currentStart = 0;

        for (Robot robot : robots) {
            //start a new Thread representing the robot;
            robot.setSearchArea(currentStart, currentStart + Math.max(robArea + rest, 1));
            rest = 0;
            new Thread(robot).start();
        }
    }
    public void start(int index) {
        try {
            new Thread(robots.get(index)).start();
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds, applying for all robots");
            for (Robot r : robots) {
                new Thread(r).start();
            }
        }
    }

    public ExplorationMap getMap() {
        return map;
    }

    public static void main(String[] args) {
        int n = 7;
        Exploration explore = new Exploration(n);
        explore.addRobot(new Robot("Wall-E", ((int)(Math.random()* (n+1)))%n, ((int)(Math.random()* (n+1)))%n, explore));
        explore.addRobot(new Robot("R2D2", ((int)(Math.random()* (n+1)))%n, ((int)(Math.random()* (n+1)))%n, explore));
        explore.addRobot(new Robot("Optimus Prime", ((int)(Math.random()* (n+1)))%n, ((int)(Math.random()* (n+1)))%n, explore));


        explore.begin(12, 2000);

//        Scanner keyboard = new Scanner(System.in);
//        boolean exit = false;
//        while (!exit) {
//            String input = keyboard.nextLine();
//            if(input != null) {
//                System.out.println("Your input is : " + input);
//                if ("quit".equals(input)) {
//                    exit = true;
//                } else {
//                    Matcher m1 = Pattern.compile("[+-]?(\\d+) (\\d+)").matcher(input);
//                    Matcher m2 = Pattern.compile("[+-]?(\\d+)").matcher(input);
//
//                    if(m1.find()) {
//                        if(explore.robots.get(parseInt(m1.group(1))).getRunning() == true) {
//                            explore.pause(parseInt(m1.group(1)));
//
//                        }
//                    }
//
//                }
//            }
//        }
//        keyboard.close();


        System.out.println("=======Waiting 5000 ms...");
        explore.pause(-1, 5000);

        int indx = 0;

        MyTime.sleep(300);
        System.out.println("=======Pausing " + explore.robots.get(indx).getName() + " for 1000ms");
        explore.pause(indx, 1000);

        indx = 1;

        MyTime.sleep(100);
        System.out.println("=======Permanently pausing " + explore.robots.get(indx).getName());
        explore.pause(indx);

    }

    public void pause(int index) {
        try {
            robots.get(index).setRunning(false);
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds, applying for all robots");
            for (Robot r : robots) {
                r.setRunning(false);
            }
        }
    }

    public void pause(int index, long millisToSleep) {
        this.pause(index);

        MyTime.sleep(millisToSleep);

        this.start(index);
    }


    public void addRobot(Robot r) {
        robots.add(r);
    }

    public void exitAll() {
        for (Robot r : robots) {
            r.exit();
        }
    }
}