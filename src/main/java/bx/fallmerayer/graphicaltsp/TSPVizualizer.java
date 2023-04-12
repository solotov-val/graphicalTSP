package bx.fallmerayer.graphicaltsp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A JavaFX application that visualizes the Traveling Salesman Problem (TSP)
 * and its solution.
 */
public class TSPVizualizer extends Application {
    private static final double CANVAS_WIDTH = 800;
    private static final double CANVAS_HEIGHT = 600;
    private static final double CITY_RADIUS = 5;

    /**
     * Finds the shortest route among the given permutations of cities.
     *
     * @param permutations A list of lists, where each inner list represents a permutation of cities.
     * @return A list representing the shortest route among the permutations.
     */
    public static List<City> findShortestRoute(List<List<City>> permutations) {
        double minDistance = Double.MAX_VALUE;
        List<City> shortestRoute = null;

        // Iterate through each permutation of cities.
        for (List<City> route : permutations) {
            double distance = 0;
            // Calculate the total distance for the current route.
            for (int i = 0; i < route.size() - 1; i++) {
                distance += route.get(i).distanceTo(route.get(i + 1));
            }
            // Add the distance from the last city to the first city to complete the loop.
            distance += route.get(route.size() - 1).distanceTo(route.get(0));

            // If the current route's distance is less than the previously found minimum,
            // update the minimum distance and the shortest route.
            if (distance < minDistance) {
                minDistance = distance;
                shortestRoute = route;
            }
        }

        return shortestRoute;
    }

    /**
     * Generates all possible permutations of the given list of cities.
     *
     * @param cities The list of cities for which to generate permutations.
     * @return A list of lists, where each inner list represents a permutation of cities.
     */
    public static List<List<City>> generatePermutations(List<City> cities) {
        // If there's only one city in the list, return a list containing a single list with that city.
        if (cities.size() == 1) {
            return Collections.singletonList(cities);
        }

        // Initialize an empty list to store the generated permutations.
        List<List<City>> permutations = new ArrayList<>();

        // Iterate through each city in the list.
        for (int i = 0; i < cities.size(); i++) {
            // Remove the current city from the list and store it in a variable.
            City city = cities.get(i);
            List<City> remainingCities = new ArrayList<>(cities);
            remainingCities.remove(i);

            // Recursively generate permutations for the remaining cities.
            for (List<City> perm : generatePermutations(remainingCities)) {
                // Add the removed city to each of the generated permutations and
                // append the new permutation to the list of permutations.
                perm.add(city);
                permutations.add(perm);
            }
        }

        // Return the list of generated permutations.
        return permutations;
    }

    /**
     * The main entry point for the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the JavaFX application and sets up the UI components.
     *
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);

        List<City> cities = Arrays.asList(
                new City(100, 100),
                new City(200, 200),
                new City(300, 100),
                new City(400, 300),
                new City(500, 200)
        );

        List<List<City>> permutations = generatePermutations(cities);
        List<City> shortestRoute = findShortestRoute(permutations);

        drawCities(gc, cities);
        drawShortestRoute(gc, shortestRoute);

        primaryStage.setTitle("TSP Visualizer");
        primaryStage.setScene(new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT));
        primaryStage.show();
    }

    /**
     * Draws the cities on the given GraphicsContext.
     *
     * @param gc     The GraphicsContext to draw the cities on.
     * @param cities The list of cities to draw.
     */
    private void drawCities(GraphicsContext gc, List<City> cities) {
        gc.setFill(Color.BLACK);
        for (City city : cities) {
            gc.fillOval(city.getX() - CITY_RADIUS, city.getY() - CITY_RADIUS, CITY_RADIUS * 2, CITY_RADIUS * 2);
        }
    }

    /**
     * Draws the shortest route on the given GraphicsContext.
     *
     * @param gc            The GraphicsContext to draw the shortest route on.
     * @param shortestRoute The list of cities representing the shortest route.
     */
    private void drawShortestRoute(GraphicsContext gc, List<City> shortestRoute) {
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);

        // Draw a line between each pair of consecutive cities in the shortest route.
        for (int i = 0; i < shortestRoute.size() - 1; i++) {
            City current = shortestRoute.get(i);
            City next = shortestRoute.get(i + 1);
            gc.strokeLine(current.getX(), current.getY(), next.getX(), next.getY());
        }

        // Draw a line between the last city and the first city to complete the loop.
        City first = shortestRoute.get(0);
        City last = shortestRoute.get(shortestRoute.size() - 1);
        gc.strokeLine(first.getX(), first.getY(), last.getX(), last.getY());
    }
}
