package org.example;

public class Robot implements Runnable {
    private String name;
    private boolean running = false, exitFlag = false;

    private int areaStart, areaEnd;
    private boolean turnFlag = false, started = false;
    private int dir = -1, coldir = 1;

    public long nrPlaced = 0;
    Exploration explore;
    int row, col;
    public Robot(String name, int x, int y, Exploration explore) {
        this.name = name;
        row=x;
        col=y;
        this.explore = explore;
    }

    public void setSearchArea(int start, int end) {
        areaStart = Math.min(start, end);
        areaEnd = Math.max(start, end);
    }

    public String getName() {
        return name;
    }

    public Exploration getExplore() {
        return explore;
    }

    public void setRunning(boolean running) {
        this.running = running;
        if(running == true) {
            System.out.println("******" + this.name + " started at " + MyTime.getTimeStamp());
        }
        else {
            System.out.println("******" + this.name + " stopped at " + MyTime.getTimeStamp());
        }
    }

    public void run() {
        if(exitFlag) {
            return;
        }
        this.running = true;
//        if(running) {
//            System.out.println(this.name + " is running!");
//        }
        while (running && !exitFlag) {
            this.chooseNextVisit();

            //pick a new cell to explore
            if(explore.getMap().visit(row, col, this))
                nrPlaced++;
            //make the robot sleep for some time
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
    public synchronized void chooseNextVisit() {

        if(col < areaStart) {
            if (!started) {
                coldir = 1;
                if (row > 0) {
                    this.row -= 1;
                } else {
                    this.col += 1;
                }
            }
            else {
                coldir = 1;
                col += coldir;
            }
        }
        else if (col >= explore.getMap().getN() - 1 && (row == 0 || row >= explore.getMap().getN() - 1) && turnFlag == true) {
            started = true;
            coldir = -1;
            col += coldir;
        }
        else {
            started = true;
            if (row == 0) {
                if (turnFlag == false) {
                    turnFlag = true;
                    dir *= -1;
                    row += dir;
                } else {
                    turnFlag = false;
                    col += coldir;
                }
            } else if (row >= explore.getMap().getN() - 1) {
                if (turnFlag == false) {
                    turnFlag = true;
                    dir *= -1;
                    row += dir;
                } else {
                    turnFlag = false;
                    col += coldir;
                }
            } else {
                row += dir;
            }
        }
    }

    public void exit() {
        exitFlag = true;
    }

    public boolean getRunning() {
        return running;
    }
}