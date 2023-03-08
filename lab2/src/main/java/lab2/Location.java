/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

import java.util.Arrays;



/**
 *
 * @author KebabRonin
 */
public class Location {
    public enum LocationEnum {
        invalid,
        airport,
        school,
        shop,
        supermarket
    }
    
    private long x;
    private long y;
    private LocationEnum locationType;

    public void setX(long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public void setLocationType(LocationEnum locationType) {
        this.locationType = locationType;
    }

    public void setRoads(Road[] roads) {
        this.roads = roads;
    }
    
    public void addRoad(Road road) {
        if (road.getLocation1() == this || road.getLocation2() == this) {
            Road[] t = roads;
            roads = new Road[roads.length + 1];
            for(int i = 0; i < t.length; i++) {
//                if (roads[i].equals(road)) {
//                    roads = t;
//                    System.out.println("Road already exists!");
//                    return;
//                }
                roads[i] = t[i];
            }
            roads[roads.length - 1] = road;
        }
    }
    public void removeRoad(Road road) {
        int roadAt = -1;
        for(int i = 0; i < roads.length; i++) {
            if(roads[i] == road) {
                roadAt = i;
                break;
            }
        }
        if (roadAt != -1) {
            Road[] t = roads;
            roads = new Road[roads.length - 1];
            for(int i = 0; i < t.length; i++) {
                if(i != roadAt) {
                    roads[i] = t[i];
                }
            }   
            roads[roads.length - 1] = road;
        }
        
    }

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public LocationEnum getLocationType() {
        return locationType;
    }

    public Road[] getRoads() {
        return roads;
    }
    private Road[] roads;

    public Location() {
        this.x = 0;
        this.y = 0;
        this.locationType = LocationEnum.invalid;
        this.roads = new Road[0];
    }
    public Location(long myX, long myY, LocationEnum myLoc) {
        this.x = myX;
        this.y = myY;
        this.locationType = myLoc;
        this.roads = new Road[0];
    }

    @Override
    public String toString() {
        return "Location{" + "x=" + x + ", y=" + y + ", locationType=" + locationType + '}';
    }
}
