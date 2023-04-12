package bx.fallmerayer.graphicaltsp;

import java.util.ArrayList;
import java.util.List;

public class PreorderTreeWalk {
    public static List<City> walk(List<Edge> edges, City start) {
        List<City> path = new ArrayList<>();
        walkHelper(edges, start, path);
        return path;
    }

    private static void walkHelper(List<Edge> edges, City current, List<City> path) {
        path.add(current);

        for (Edge edge : edges) {
            if (edge.getCity1().equals(current) && !path.contains(edge.getCity2())) {
                walkHelper(edges, edge.getCity2(), path);
            } else if (edge.getCity2().equals(current) && !path.contains(edge.getCity1())) {
                walkHelper(edges, edge.getCity1(), path);
            }
        }
    }
}
