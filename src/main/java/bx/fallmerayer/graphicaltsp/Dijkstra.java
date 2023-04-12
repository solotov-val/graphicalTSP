package bx.fallmerayer.graphicaltsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Dijkstra {
    public static List<City> findShortestPath(Graph graph, int source, int destination) {
        int n = graph.size();
        double[] dist = new double[n];
        int[] prev = new int[n];
        boolean[] visited = new boolean[n];

        Arrays.fill(dist, Double.MAX_VALUE);
        dist[source] = 0;

        for (int i = 0; i < n; i++) {
            int u = -1;

            for (int j = 0; j < n; j++) {
                if (!visited[j] && (u == -1 || dist[j] < dist[u])) {
                    u = j;
                }
            }

            if (dist[u] == Double.MAX_VALUE) break;

            visited[u] = true;

            for (int v = 0; v < n; v++) {
                double edgeWeight = graph.getWeight(u, v);
                if (edgeWeight != 0 && dist[u] + edgeWeight < dist[v]) {
                    dist[v] = dist[u] + edgeWeight;
                    prev[v] = u;
                }
            }
        }

        List<City> path = new ArrayList<>();
        for (int v = destination; v != source; v = prev[v]) {
            path.add(graph.getNodes().get(v));
        }
        path.add(graph.getNodes().get(source));
        Collections.reverse(path);

        return path;
    }
}
