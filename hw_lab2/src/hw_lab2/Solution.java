package hw_lab2;

import java.util.Arrays;
import java.util.Vector;

public class Solution extends Problem{

    public Solution(Problem p) {
        super(p.getContext(), p.getSource(), p.getDestination());
    }
    public Solution(Context context, Location source, Location destination) {
        super(context, source, destination);
    }

    /**
     * Uses Dijkstra's algorithm to find the shortest path
     * @return the length of the path (0xFFFFF if non-existent)
     */
    public double solve() {
        if (!this.isValid()) {
            System.out.println("Problem instance is invalid, cannot solve it");
            return 0;
        }
        //Dijkstra
        Vector<Location> locations = this.context.locationVector;
        double[] distances = new double[locations.size()];
        boolean[] done = new boolean[locations.size()];
        int steps = 0;
        Location src = this.getSource();
        Location dest = this.getDestination();

        final double INF = 0xFFFFFFF;

        //Initialisation with +inf
        Arrays.fill(distances, INF);

        distances[locations.indexOf(src)] = 0;

        while(steps < locations.size() && distances[locations.indexOf(dest)] == INF) {
            //find index of minimum distance Location
            int index = -1;
            double dist = INF;

            for (int i = 0; i < locations.size(); i++) {
                if (!done[i] && distances[i] < dist) {
                    index = i;
                    dist = distances[i];
                }
            }

            if (index == -1) {
                System.out.println("No path found");
                return 0;
            }

            done[index] = true;

            Location loc = locations.elementAt(index);
            for (Road i : loc.getRoads()) {
                int loc1Index = locations.indexOf(i.getLocation1());
                int loc2Index = locations.indexOf(i.getLocation2());

                if(!done[loc1Index] && distances[loc1Index] > distances[index] + i.getLength()) {
                    distances[loc1Index] = distances[index] + i.getLength();
                    for(Road j : locations.elementAt(loc1Index).getRoads()) {
                        int innerLoc1Index = locations.indexOf(j.getLocation1());
                        int innerLoc2Index = locations.indexOf(j.getLocation1());

                        if(!done[innerLoc1Index] && distances[innerLoc1Index] > distances[loc1Index] + i.getLength()) {
                            distances[innerLoc1Index] = distances[loc1Index] + i.getLength();
                        }
                        if(!done[innerLoc2Index] && distances[innerLoc2Index] > distances[loc1Index] + i.getLength()) {
                            distances[innerLoc2Index] = distances[loc1Index] + i.getLength();
                        }
                    }
                }
                if(!done[loc2Index] && distances[loc2Index] > distances[index] + i.getLength()) {
                    distances[loc2Index] = distances[index] + i.getLength();
                    for(Road j : locations.elementAt(loc2Index).getRoads()) {
                        int innerLoc1Index = locations.indexOf(j.getLocation1());
                        int innerLoc2Index = locations.indexOf(j.getLocation1());

                        if(!done[innerLoc1Index] && distances[innerLoc1Index] > distances[loc2Index] + j.getLength()) {
                            distances[innerLoc1Index] = distances[loc2Index] + j.getLength();
                        }
                        if(!done[innerLoc2Index] && distances[innerLoc2Index] > distances[loc2Index] + j.getLength()) {
                            distances[innerLoc2Index] = distances[loc2Index] + j.getLength();
                        }
                    }
                }
            }

            steps++;
        }
        return distances[locations.indexOf(this.getDestination())];
    }
}
