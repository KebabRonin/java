package org.example;

public class Robot implements Runnable {
    private String name;
    private boolean running;
    Exploration explore;
    int row, col;
    public Robot(String name, int x, int y, Exploration explore) {
        this.name = name;
        row=x;
        col=y;
        this.explore = explore;
    }

    public String getName() {
        return name;
    }

    public Exploration getExplore() {
        return explore;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void run() {
        if(running) {
            System.out.println(this.name + " is running!");
        }
        while (running) {
            this.chooseNextVisit();

            //pick a new cell to explore
            explore.getMap().visit(row, col, this);
            //make the robot sleep for some time
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
    public synchronized void chooseNextVisit() {
        System.out.println("Choosing");
        int dir = (int)(Math.random()*10) % 4;
        while(true) {
            if(dir == 0) {
                if(this.row > 0) {
                    this.row -= 1;
                    return;
                }
            }
            else if(dir == 1) {
                if(this.row < explore.getMap().getN() - 1) {
                    this.row += 1;
                    return;
                }
            }
            else if(dir == 2) {
                if(this.col > 0) {
                    this.col -= 1;
                    return;
                }
            }
            else {
                if(this.col < explore.getMap().getN() - 1) {
                    this.col += 1;
                    return;
                }
            }
            dir = (dir + 1) % 4;
        }
    }
}