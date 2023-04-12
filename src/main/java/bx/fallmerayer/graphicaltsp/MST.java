package bx.fallmerayer.graphicaltsp;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the Minimum Spanning Tree algorithm.
 */
public class MST {
    /**
     * Find the Minimum Spanning Tree of the given graph.
     *
     * @param graph The input graph.
     * @return The list of edges in the MST.
     */

    public static List<Edge> findMST(Graph graph) {
        List<Edge> mstEdges = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];

        for (int node = 0; node < graph.size(); node++) {
            if (!visited[node]) {
                visited[node] = true;

                List<City> path = Dijkstra.findShortestPath(graph, 0, node);
                for (int i = 1; i < path.size(); i++) {
                    City city1 = path.get(i - 1);
                    City city2 = path.get(i);
                    int index1 = graph.getNodes().indexOf(city1);
                    int index2 = graph.getNodes().indexOf(city2);
                    double weight = graph.getWeight(index1, index2);
                    mstEdges.add(new Edge(city1, city2, weight));
                }
            }
        }

        return mstEdges;
    }
}

