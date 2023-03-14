package hw_lab2.locations;

import hw_lab2.Location;

import java.util.Date;

public class Airport extends Location {
    private int[] busses;

    public Airport(String name, int x, int y) {
        super(name, x ,y);
    }

    public int[] getBusses() {
        return busses;
    }

    public void setBusses(int[] busses) {
        this.busses = busses;
    }
}
