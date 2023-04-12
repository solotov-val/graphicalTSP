package bx.fallmerayer.graphicaltsp;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Preorder Tree Walk algorithm.
 */
public class PreorderTreeWalk {
    /**
     * Generate an approximate TSP path using Preorder Tree Walk on the given tree.
     *
     * @param edges The edges of the tree.
     * @param start The starting city.
     * @return The approximate TSP path.
     */
    public static List<City> walk(List<Edge> edges, City start) {
        List<City> path = new ArrayList<>();
        walkHelper(edges, start, path);
        return path;
    }

    /**
     * Helper function for the Preorder Tree Walk algorithm.
     *
     * @param edges   The edges of the tree.
     * @param current The current city.
     * @param path    The path constructed so far.
     */
    private static void walkHelper(List<Edge> edges, City current, List<City> path) {
        path.add(current);

        for (Edge edge : edges) {
            if (edge.city1().equals(current) && !path.contains(edge.city2())) {
                walkHelper(edges, edge.city2(), path);
            } else if (edge.city2().equals(current) && !path.contains(edge.city1())) {
                walkHelper(edges, edge.city1(), path);
            }
        }
    }
}
