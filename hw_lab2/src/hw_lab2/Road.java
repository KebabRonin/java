package hw_lab2;


import java.util.Objects;
import java.util.Vector;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.sqrt;
/**
 * The 'edges' in the network
 */
public class Road {
    private Location location1;
    private Location location2;
    /**
     * Length of the road, given in meters
     */
    private double length;

    public Road(Location location1, Location location2) {
        if(location1.equals(location2)) {
            System.out.println("Can't have road to and from the same location");
        }
        this.length = sqrt(
                ((location1.getX() - location2.getX()) * (location1.getX() - location2.getX())) +
                      ((location1.getY() - location2.getY()) * (location1.getY() - location2.getY())));
        this.location1 = location1;
        this.location2 = location2;

        this.link();
    }

    @Override
    public String toString() {
        return "Road{" +
                "location1=" + location1 +
                ", location2=" + location2 +
                ", length=" + length +
                '}';
    }

    /**
     * Removes the current Road from the adjacency list of the Locations it connects
     */
    public void unlink() {
        Vector<Road> roads;

        roads = this.location1.getRoads();
        roads.remove(this);
        roads = this.location2.getRoads();
        roads.remove(this);
    }

    /**
     * Adds the current Road to the adjacency list of the Locations it connects
     */
    public void link() {
        Vector<Road> roads;

        roads = this.location1.getRoads();
        roads.add(this);
        roads = this.location2.getRoads();
        roads.add(this);
    }

    public Location getLocation1() {
        return location1;
    }
    public void setLocation1(Location location1) {
        if(location1.equals(this.location2)) {
            System.out.println("Can't have road to and from the same location");
        }

        Vector<Road> roads;

        roads = this.location1.getRoads();
        roads.remove(this);
        this.location1 = location1;
    }
    public Location getLocation2() {
        return location2;
    }
    public void setLocation2(Location location2) {
        if(this.location1.equals(location2)) {
            System.out.println("Can't have road to and from the same location");
        }

        Vector<Road> roads;

        roads = this.location2.getRoads();
        roads.remove(this);
        this.location2 = location2;
        roads = this.location2.getRoads();
        roads.add(this);
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        if(length < 0 ) {
            System.out.println("Road cannot have negative length!");
        }
        else {
            this.length = length;
        }
    }

    /**
     * Two roads are equal if they link the same Locations and they have the same length
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Road)) return false;
        Road road = (Road) o;
        return length == road.length && ((location1.equals(road.location1) && location2.equals(road.location2)) ||
                                         (location1.equals(road.location2) && location2.equals(road.location1)));
    }

    @Override
    public int hashCode() {
        return Objects.hash(location1, location2, length);
    }
}
