package bx.fallmerayer.graphicaltsp;

public class Edge {
    private final City city1;
    private final City city2;
    private final double weight;

    public Edge(City city1, City city2, double weight) {
        this.city1 = city1;
        this.city2 = city2;
        this.weight = weight;
    }

    public City getCity1() {
        return city1;
    }

    public City getCity2() {
        return city2;
    }

    public double getWeight() {
        return weight;
    }
}
