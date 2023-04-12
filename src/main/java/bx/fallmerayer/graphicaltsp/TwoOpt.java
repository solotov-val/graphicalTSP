package bx.fallmerayer.graphicaltsp;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the 2-opt optimization algorithm.
 */
public class TwoOpt {
    /**
     * Optimize the given path using the 2-opt algorithm.
     *
     * @param path The initial path.
     * @return The optimized path.
     */
    public static List<City> optimize(List<City> path) {
        int n = path.size();
        boolean improvement = true;

        while (improvement) {
            improvement = false;
            double bestDistance = totalDistance(path);

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    List<City> newPath = swap(path, i, j);
                    double newDistance = totalDistance(newPath);

                    if (newDistance < bestDistance) {
                        path = newPath;
                        bestDistance = newDistance;
                        improvement = true;
                    }
                }
            }
        }

        return path;
    }

    /**
     * Swap the path segment between indices i and k, inclusive.
     *
     * @param path The path.
     * @param i    The starting index.
     * @param k    The ending index.
     * @return The new path with the segment swapped.
     */
    private static List<City> swap(List<City> path, int i, int k) {
        List<City> newPath = new ArrayList<>(path);

        while (i < k) {
            City temp = newPath.get(i);
            newPath.set(i, newPath.get(k));
            newPath.set(k, temp);
            i++;
            k--;
        }

        return newPath;
    }

    /**
     * Calculate the total distance of the path.
     *
     * @param path The path.
     * @return The total distance.
     */
    private static double totalDistance(List<City> path) {
        double total = 0;
        int n = path.size();

        for (int i = 0; i < n - 1; i++) {
            total += path.get(i).distanceTo(path.get(i + 1));
        }

        total += path.get(n - 1).distanceTo(path.get(0));

        return total;
    }
}

