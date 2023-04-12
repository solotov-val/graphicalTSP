package bx.fallmerayer.graphicaltsp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

import static javafx.application.Application.launch;

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

}