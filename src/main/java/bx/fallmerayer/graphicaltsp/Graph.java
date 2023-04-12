package bx.fallmerayer.graphicaltsp;

import java.util.List;

/**
 * This class represents an edge between two cities with a weight (distance).
 */
public class Graph {
    private final List<City> nodes;
    private final double[][] adjacencyMatrix;

    public Graph(List<City> nodes) {
        this.nodes = nodes;
        this.adjacencyMatrix = new double[nodes.size()][nodes.size()];
        populateAdjacencyMatrix();
    }

    private void populateAdjacencyMatrix() {
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                adjacencyMatrix[i][j] = nodes.get(i).distanceTo(nodes.get(j));
            }
        }
    }

    public List<City> getNodes() {
        return nodes;
    }

    public double getWeight(int i, int j) {
        return adjacencyMatrix[i][j];
    }

    public int size() {
        return nodes.size();
    }
}
