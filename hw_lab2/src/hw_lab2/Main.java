package hw_lab2;

import hw_lab2.locations.Airport;
import hw_lab2.locations.GasStation;
import hw_lab2.locations.University;

public class Main {
    /**
     * Generates a random GasStation, Airport or University with a 10-symbol name and coordinates as positive ints less than 1000
     */
    private static Location generateLocation() {
        int x = Random.random() %1000;
        int y = Random.random() %1000;
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            nameBuilder.append((char) ('A' + Random.random() % 57));
        }
        String name = nameBuilder.toString();
        double p = Random.random();
        if (p < 0.33) {
            return new GasStation(name,x,y);
        }
        else if (p < 0.66) {
            return new Airport(name,x,y);
        }
        else {
            return new University(name,x,y);
        }
    }

    /**
     * Generates an instance of a Problem with a specified number of Roads and Locations. The Locations will have random 10-symbol names.
     * @param numberOfLocations The number of Locations to generate (not including the source and destination)
     * @param numberOfRoads The number of Roads to generate (There can be fewer Roads in the end due to conflicts)
     * @return An instance of Problem
     */
    private static Problem generateProblem(int numberOfLocations, int numberOfRoads) {

        if (numberOfRoads > numberOfLocations*(numberOfLocations-1)/2) {
            System.out.println("Too many roads");
            return null;
        }

        Location src = generateLocation();
        Location dest = generateLocation();
        Context c = new Context();
        c.add(src);
        c.add(dest);

        int pathLength = Random.random()%(Math.min(numberOfRoads, numberOfLocations - 1));
        switch(pathLength) {
            case 0 : {System.out.println("This instance will have no solution.");}break;
            case 1 : {System.out.println("Trivial path");
                c.link(src, dest, Random.random()%1000);
                numberOfRoads--;
            } break;
            default: {
                Location[] path = new Location[pathLength];
                path[0] = generateLocation();
                numberOfLocations--;
                c.add(path[0]);
                for (int i = 1; i < (pathLength - 1); i++) {
                    numberOfLocations--;
                    do {
                        path[i] = generateLocation();
                    } while(!c.add(path[i]));
                    c.link(path[i], path[i - 1], Random.random()%1000);
                    numberOfRoads--;
                }
                c.link(src, path[0], Random.random()%1000);
                c.link(dest, path[pathLength - 2], Random.random()%1000);
                numberOfRoads -= 2;
                System.out.println("Long path(" + pathLength + ")");
            }
        }
        for(int i = 0; i < (numberOfLocations - 2); i++) {
            while(!c.add(generateLocation()));
        }

        int r1, r2;
        for(int i = 0; i < numberOfRoads; i++) {
            //do {
                r1 = Random.random() % c.locationVector.size();
                r2 = Random.random() % c.locationVector.size();
                if(r1 == r2) r2 = (r2 + 1) % c.locationVector.size();
            //} while(!c.link(c.locationVector.elementAt(r1), c.locationVector.elementAt(r2), Random.random()%1000));
            c.link(c.locationVector.elementAt(r1), c.locationVector.elementAt(r2), Random.random()%1000);
        }

        return new Problem(c, src, dest);
    }
    public static void main (String[] args) {
        Context context = new Context();
        context.add(new Airport("Henri Coanda", 100, 200));
        context.add(new University("Al. Ioan Cuza", 700, 650));
        context.add(new University("Al. Ioan Cuza", 700, 650));
        context.add(new GasStation("Petrom", 800, 630));
        context.link("Henri Coanda", "Petrom");
        context.link("Henri Coanda", "Petrom");
        context.link("Henri Coanda", "Petrom", 22);
        context.link("Al. Ioan Cuza", "Petrom", 43);


        Problem p = new Problem(context, "Henri Coanda", "Al. Ioan Cuza");
        System.out.println("Is there a path from " + p.getSource().getName() + " to " + p.getDestination().getName() + "?: " + p.run());

        Solution s = new Solution(p);
        System.out.println("From " + s.getSource().getName() + " to " + s.getDestination().getName() + " the shortest path is " + s.solve() + " m");

        System.out.println("----------------------- Bonus -----------------------");

        Problem randomProblem = generateProblem(800,10000);
        Solution performance_test = new Solution(randomProblem);

        Runtime runtime = Runtime.getRuntime();
        long usedMem = runtime.totalMemory() - runtime.freeMemory();
        long timeTaken = System.nanoTime();

        System.out.println("Generated " + performance_test.getContext().locationVector.size() + " locations and "
                + performance_test.getContext().roadVector.size() + " roads");
        System.out.println(performance_test.solve());

        usedMem = (runtime.totalMemory() - runtime.freeMemory()) - usedMem;
        timeTaken = System.nanoTime() - timeTaken;

        System.out.println("Execution took " + ((double)timeTaken/1000000) + " ms and used " + usedMem);
    }
}
