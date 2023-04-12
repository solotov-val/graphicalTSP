package bx.fallmerayer.graphicaltsp;

import java.util.List;

/**
 * Represents a city with x and y coordinates.
 */
public class City {
    private final double x;
    private final double y;

    /**
     * Constructs a city with the given x and y coordinates.
     *
     * @param x The x-coordinate of the city.
     * @param y The y-coordinate of the city.
     */
    public City(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the x-coordinate of the city.
     *
     * @return The x-coordinate of the city.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the city.
     *
     * @return The y-coordinate of the city.
     */
    public double getY() {
        return y;
    }

    /**
     * Calculates the Euclidean distance between this city and another city.
     *
     * @param other The other city to calculate the distance to.
     * @return The Euclidean distance between this city and the other city.
     */
    public double distanceTo(City other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Finds the shortest route among the given permutations of cities.
     *
     * @param permutations A list of lists, where each inner list represents a permutation of cities.
     * @return A list representing the shortest route among the permutations.
     */
    public static List<City> findShortestRoute(List<List<City>> permutations) {
        double minDistance = Double.MAX_VALUE;
        List<City> shortestRoute = null;

        // Iterate through each permutation of cities.
        for (List<City> route : permutations) {
            double distance = 0;
            // Calculate the total distance for the current route.
            for (int i = 0; i < route.size() - 1; i++) {
                distance += route.get(i).distanceTo(route.get(i + 1));
            }
            // Add the distance from the last city to the first city to complete the loop.
            distance += route.get(route.size() - 1).distanceTo(route.get(0));

            // If the current route's distance is less than the previously found minimum,
            // update the minimum distance and the shortest route.
            if (distance < minDistance) {
                minDistance = distance;
                shortestRoute = route;
            }
        }

        return shortestRoute;
    }


}