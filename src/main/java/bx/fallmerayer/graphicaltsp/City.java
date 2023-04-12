package bx.fallmerayer.graphicaltsp;

/**
 * Represents a city with x and y coordinates.
 */
public record City(double x, double y) {
    /**
     * Constructs a city with the given x and y coordinates.
     *
     * @param x The x-coordinate of the city.
     * @param y The y-coordinate of the city.
     */
    public City {
    }

    /**
     * Returns the x-coordinate of the city.
     *
     * @return The x-coordinate of the city.
     */
    @Override
    public double x() {
        return x;
    }

    /**
     * Returns the y-coordinate of the city.
     *
     * @return The y-coordinate of the city.
     */
    @Override
    public double y() {
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

}