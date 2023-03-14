package hw_lab2;

import java.util.Vector;

import static com.sun.org.apache.xalan.internal.lib.ExsltMath.sqrt;

/**
 * A collection of Roads and Locations
 */
public class Context {
    Vector<Location> locationVector;
    Vector<Road> roadVector;

    public Context() {
        locationVector = new Vector<>();
        roadVector = new Vector<>();
    }

    /**
     * A Context is valid if all its Roads link Locations in the Context and if no Locations or Roads repeat
     * @return Whether the current Context is valid
     */
    public boolean isValid() {
        Road[] roads = roadVector.toArray(new Road[0]);
        for(int i = 0; i < roads.length; i++) {
            if(!(locationVector.contains(roads[i].getLocation1()) && locationVector.contains(roads[i].getLocation2()))) {
                System.out.println("Road contains locations not in context");
                return false;
            }
            for(int j = i + 1; j < roads.length; j++) {
                if(roads[i].equals(roads[j])) {
                    System.out.println("Duplicate road");
                    return false;
                }
            }
        }

        Location[] locs = locationVector.toArray(new Location[0]);
        for(int i = 0; i < locs.length; i++) {
            for(int j = i + 1; j < locs.length; j++) {
                if(locs[i].equals(locs[j])) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean has(Location location) {
        return locationVector.contains(location);
    }
    public boolean has(Road road) {
        return roadVector.contains(road);
    }
    public boolean add(Location location) {
        for(Location loc : locationVector) {
            if(loc.equals(location)) {
                System.out.println("Location already exists!");
                return false;
            }
        }
        locationVector.add(location);
        return true;
    }

    /**
     * Adds the Road given as parameter to the current Context
     * If the Road is already in the Context or it links Locations not in Context, an error message is displayed and the road is not added
     * @param road the Road to add
     */
    public boolean add(Road road) {
        for(Road road1 : roadVector) {
            if(road1.equals(road)) {
                System.out.println("Road already in context");
                return false;
            }
        }
        if(road.getLocation1() != null && road.getLocation2() != null) {
            if(locationVector.contains(road.getLocation1()) && locationVector.contains(road.getLocation2())) {
                roadVector.add(road);
                return true;
            }
            System.out.println("Road contains locations not in context");
        }
        else {
            System.out.println("Road location is null");
        }
        return false;
    }

    /**
     *
     * @param name - Location to search for in the current Context
     * @return The first occurrence of a Location with name equal to the parameter
     */
    public Location getLocation(String name) {
        for(Location location : locationVector) {
            if (location.getName().equals(name)) {
                return location;
            }
        }
        return null;
    }
    /**
     * <p>
     *     Adds to the current Context a Road between the specified locations if they both belong to the current Context,
     *     having the length equal to the distance between the Locations it connects
     * </p>
     * <p>
     *     Shows an error message and does not add the Road if either the Road already exists or the Locations
     *     given by name are not found in the Context
     * </p>
     * @param n1 name of the first Location in this context to link
     * @param n2 name of the second Location in this context to link
     * @return Whether the operation was successful
     *
     */
    public boolean link(String n1, String n2) {
        return this.link(this.getLocation(n1), this.getLocation(n2));
    }
    /**
     * <p>
     *     Adds to the current Context a Road between the specified locations if they both belong to the current Context,
     *     having the length equal to the distance between the Locations it connects
     * </p>
     * <p>
     *     Shows an error message and does not add the Road if either the Road already exists or the Locations
     *     given by name are not found in the Context
     * </p>
     * @param location1 the first Location in this context to link
     * @param location2 the second Location in this context to link
     * @return Whether the operation was successful
     *
     */
    public boolean link(Location location1, Location location2) {
        if(location1 == null || location2 == null) {
            System.out.println("Location does not exist in this context");
            return false;
        }
        if(location1.equals(location2)) {
            System.out.println("Can't have road to and from the same location");
            return false;
        }

        double myDistance = sqrt(
                ((location1.getX() - location2.getX()) * (location1.getX() - location2.getX())) +
                        ((location1.getY() - location2.getY()) * (location1.getY() - location2.getY())));

        Road myRoad = new Road(location1, location2);
        myRoad.unlink();

        Vector<Road> roads = location1.getRoads();
        for(Road road : roads) {
            if(road.equals(myRoad)) {
                System.out.println("Road already exists!");
                return false;
            }
        }
        myRoad.link();
        this.add(myRoad);
        return true;
    }
    /**
     * <p>
     *     Adds to the current Context a Road between the specified locations if they both belong to the current Context,
     *     having the length equal to the distance provided
     * </p>
     * <p>
     *     Shows an error message and does not add the Road if either the Road already exists or the Locations
     *     given by name are not found in the Context
     * </p>
     * @param location1 the first Location in this context to link
     * @param location2 the second Location in this context to link
     * @return Whether the operation was successful
     *
     */
    public boolean link(Location location1, Location location2, long length) {
        if(location1 == null || location2 == null) {
            System.out.println("Location does not exist in this context");
            return false;
        }
        if(location1.equals(location2)) {
            System.out.println("Can't have road to and from the same location");
            return false;
        }

        Road myRoad = new Road(location1, location2);
        myRoad.setLength(length);
        myRoad.unlink();

        Vector<Road> roads = location1.getRoads();
        for(Road road : roads) {
            if(road.equals(myRoad)) {
                System.out.println("Road already exists!");
                return false;
            }
        }
        myRoad.link();
        this.add(myRoad);
        return true;
    }

    /**
     * <p>
     *     Adds to the current Context a Road between the specified locations if they both belong to the current Context,
     *     having the length equal to the last parameter
     * </p>
     * <p>
     *     Shows an error message and does not add the Road if either the Road already exists or the Locations
     *     given by name are not found in the Context
     * </p>
     * @param n1 name of the first Location in this context to link
     * @param n2 name of the second Location in this context to link
     * @param length of the resulting road
     * @return Whether the operation was successful
     *
     */
    public boolean link(String n1, String n2, long length) {
        return this.link(this.getLocation(n1), this.getLocation(n2), length);
    }
    public void unlink(String n1, String n2) {
        Location loc1 = this.getLocation(n1);
        Location loc2 = this.getLocation(n2);
        for(Road road : roadVector) {
            if((road.getLocation2().equals(loc2) && road.getLocation1().equals(loc1)) || (road.getLocation2().equals(loc1) && road.getLocation1().equals(loc2))) {
                this.remove(road);
            }
        }
    }
    public void remove(String name) {
        for (Location location : locationVector) {
            if (location.getName().equals(name)) {
                locationVector.remove(location);
            }
        }
    }
    public void remove(Location location) {
        this.locationVector.remove(location);
    }
    public void remove(Road road) {
        this.roadVector.remove(road);
    }
}
