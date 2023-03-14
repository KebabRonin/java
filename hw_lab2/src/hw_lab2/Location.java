package hw_lab2;

import java.util.Objects;
import java.util.Vector;

/**
 * The 'nodes' in the network
 */
public class Location {
    /**
     * The list of edges(Roads) linked to this Location
     */
    private Vector<Road> roads;
    /**
     * The x coordinate of the Location
     */
    private int x;
    /**
     * The y coordinate of the Location
     */
    private int y;
    /**
     * The name of the Location
     */
    private String name;

    public Location(String name, int x, int y) {
        this.name = name;
        this.roads = new Vector<>();
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Vector<Location> getNeighbours() {
        Vector<Location> neighbours = new Vector<>();
        for (Road road : roads) {
            if (!road.getLocation1().equals(this)) {
                neighbours.add(road.getLocation1());
            }
            else if (!road.getLocation2().equals(this)) {
                neighbours.add(road.getLocation2());
            }
            else {
                System.out.println("Something fishy..");
            }
        }
        return neighbours;
    }

    public Vector<Road> getRoads() {
        return this.roads;
    }
    public void setRoads(Vector<Road> roads) {
        if(roads == null) {
            System.out.println("Road vector null");
            return;
        }
        for (Road road:  roads) {
            if (!(road.getLocation1().equals(this) || road.getLocation2().equals(this))) {
                return;
            }
        }
        this.roads = roads;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return x == location.x && y == location.y && name.equals(location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, name);
    }
}
