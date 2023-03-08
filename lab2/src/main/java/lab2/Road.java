/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

import java.util.Objects;

/**
 *
 * @author KebabRonin
 */
public class Road {
    private Location location1;
    private Location location2;
    private long length;

    public void setLocation1(Location location1) {
        this.location1 = location1;
    }

    public void setLocation2(Location location2) {
        this.location2 = location2;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public Location getLocation1() {
        return location1;
    }

    public Location getLocation2() {
        return location2;
    }

    public long getLength() {
        return length;
    }
    
    public Road(Location l1, Location l2, long len) {
        this.location1 = l1;
        this.location2 = l2;
        this.length = len;
        
        this.location1.addRoad(this);
        this.location2.addRoad(this);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Road other = (Road) obj;
        if (this.length != other.length) {
            return false;
        }
        if (!Objects.equals(this.location1, other.location1)) {
            return false;
        }
        return Objects.equals(this.location2, other.location2);
    }

    @Override
    public String toString() {
        return "Road{" + "location1=" + location1 + ", location2=" + location2 + ", length=" + length + '}';
    }
}
