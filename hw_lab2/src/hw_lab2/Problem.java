package hw_lab2;

import java.util.Vector;

/**
 * <p>
    * Describes an instance of the problem, with a 'source' and a 'destination' Location and a Context as a set of Roads and Locations
 * </p>
 * <p>
    * {@link #run()} method should be called in order to solve the Problem
 * </p>
 */
public class Problem {

    protected Location source;
    protected Location destination;
    /**
     * The set of Roads and Locations to consider when searching for a path from the source to the destination
     */
    protected Context context;
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    /**
     * Creates a new problem, with the 'source' and 'destination' Locations given as objects
     * @param context context of the problem
     * @param source the 'source' Location in the context
     * @param destination the 'destination' Location in the context
     */
    public Problem(Context context, Location source, Location destination) {
        this.source = source;
        this.destination = destination;
        this.context = context;
    }

    /**
     * Creates a new problem, searching by name the 'source' and 'destination' Locations in the context
     * @param context context of the problem
     * @param source name of the 'source' Location in the context
     * @param destination name of the 'destination' Location in the context
     */
    public Problem(Context context, String source, String destination) {
        this.context = context;
        this.source = this.context.getLocation(source);
        this.destination = this.context.getLocation(destination);
    }

    /**
     * Checks for the source and destination to be non-null and belong to the Context, and the Context to be valid
     */
    public boolean isValid() {
        if(source == null || destination == null) {
            System.out.println("Source or destination is null");
            return false;
        }
        if(!(context.has(source) && context.has(destination))) {
            System.out.println("Source and destination not in context");
            return false;
        }
        return context.isValid();
    }

    /**
     * Solves the problem by executing a DFS starting in the given 'source' Location, trying to reach the given 'destination' Location
     * @return Whether there exists a path from the source to the destination
     */
    public boolean run() {
        ///DFS

        if(!this.isValid()) {
            System.out.println("Problem instance is invalid");
            return false;
        }

        Vector<Location> coada = new Vector<>();
        int in = 0;

        coada.add(in, source);
        while(in < coada.size()) {
            Location current = coada.elementAt(in);

            if (current.equals(destination)) {
                return true;
            }

            Vector<Location> neighbours = current.getNeighbours();
            for(Location neighbour : neighbours) {
                if (!coada.contains(neighbour)) {
                    coada.add(neighbour);
                }
            }
            in += 1;
        }
        return false;
    }
    public Location getSource() {
        return source;
    }
    public void setSource(Location source) {
        this.source = source;
    }
    public Location getDestination() {
        return destination;
    }
    public void setDestination(Location destination) {
        this.destination = destination;
    }
}