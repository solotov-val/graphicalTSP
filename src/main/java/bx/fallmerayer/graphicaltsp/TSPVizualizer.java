package bx.fallmerayer.graphicaltsp;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

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

        List<City> cities = generateRandomCities(99, 100, CANVAS_WIDTH, CANVAS_HEIGHT);
        Graph graph = new Graph(cities);
        List<Edge> mstEdges = MST.findMST(graph);
        List<City> approximatePath = PreorderTreeWalk.walk(mstEdges, cities.get(0));
        List<City> optimizedPath = TwoOpt.optimize(approximatePath);


        drawCities(gc, cities);

        // Create the animation
        DoubleProperty progress = new SimpleDoubleProperty(0);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0), new KeyValue(progress, 0)),
                new KeyFrame(Duration.seconds(5), new KeyValue(progress, 1)));
        timeline.setCycleCount(1);

        progress.addListener((observable, oldValue, newValue) -> {
            gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
            drawCities(gc, cities);
            drawShortestRoute(gc, optimizedPath, progress.doubleValue());
        });

        timeline.play();

        primaryStage.setTitle("TSP Visualizer");
        primaryStage.setScene(new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT));
        primaryStage.show();
    }


    /**
     * Generates a list of random cities with x and y coordinates within the specified bounds.
     *
     * @param minCities The minimum number of cities to generate.
     * @param maxCities The maximum number of cities to generate.
     * @param maxWidth  The maximum x-coordinate for the cities.
     * @param maxHeight The maximum y-coordinate for the cities.
     * @return A list of randomly generated cities.
     */
    private List<City> generateRandomCities(int minCities, int maxCities, double maxWidth, double maxHeight) {
        Random random = new Random();
        int numCities = random.nextInt(maxCities - minCities + 1) + minCities;
        List<City> cities = new ArrayList<>();

        for (int i = 0; i < numCities; i++) {
            double x = random.nextDouble() * maxWidth;
            double y = random.nextDouble() * maxHeight;
            cities.add(new City(x, y));
        }

        return cities;
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
     * @param progress      The progress of the animation, between 0 and 1.
     */
    private void drawShortestRoute(GraphicsContext gc, List<City> path, double progress) {
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(2);

        int steps = (int) (path.size() * progress);
        for (int i = 0; i < steps - 1; i++) {
            City city1 = path.get(i);
            City city2 = path.get(i + 1);
            gc.strokeLine(city1.getX(), city1.getY(), city2.getX(), city2.getY());
        }
    }

}
