package bx.fallmerayer.graphicaltsp;

/**
 * This class represents an edge between two cities with a weight (distance).
 */
public record Edge(City city1, City city2, double weight) {
}
