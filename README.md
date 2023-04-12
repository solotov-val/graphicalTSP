Graphical TSP Solver
This repository contains a JavaFX application that generates and visualizes an approximate solution to the Traveling Salesman Problem (TSP) using several algorithms, including Kruskal's algorithm, Dijkstra's algorithm, Preorder Tree Walk, and 2-opt optimization.

Graphical TSP Solver Screenshot

Features
Generate a set of random cities.
Construct a graph with weighted edges representing distances between cities.
Create an approximate TSP path using Kruskal's algorithm (Minimum Spanning Tree), Dijkstra's algorithm, and Preorder Tree Walk.
Optimize the TSP path using the 2-opt algorithm.
Visualize the TSP solution with an animated step-by-step representation.
Requirements
Java 11 or higher
JavaFX 17 or higher
Maven
Installation
Clone the repository:

bash
Copy code
git clone https://github.com/solotov-val/graphical-tsp.git
cd graphical-tsp
Build the project with Maven:

Copy code
mvn clean install
Run the application:

arduino
Copy code
mvn javafx:run
Usage
Run the application as described in the Installation section.
The application will generate a set of random cities and display them on the screen.
Press the "Start" button to start the TSP solution process.
Watch the animated visualization of the TSP solution.
License
This project is licensed under the MIT License.
