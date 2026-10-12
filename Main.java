import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("       BITMASK TSP ROUTE PLANNER");
        System.out.println("==========================================");

        System.out.print("\nEnter number of cities: ");
        int n = scanner.nextInt();

        if (n < 2) {
            System.out.println(
                    "At least 2 cities are required."
            );
            scanner.close();
            return;
        }

        if (n > 20) {
            System.out.println(
                    "For Bitmask DP, use 20 or fewer cities."
            );
            scanner.close();
            return;
        }

        List<City> cities = new ArrayList<>();

        System.out.println("\nEnter city names:");

        for (int i = 0; i < n; i++) {

            System.out.print(
                    "City " + (i + 1) + ": "
            );

            String name = scanner.next();

            // Automatically arrange cities
            // in a circular layout
            double angle =
                    (2 * Math.PI * i) / n;

            double x =
                    450 + 250 * Math.cos(angle);

            double y =
                    300 + 200 * Math.sin(angle);

            cities.add(
                    new City(
                            i,
                            name,
                            x,
                            y
                    )
            );
        }

        // Distance / Cost Matrix
        double[][] distance =
                new double[n][n];

        System.out.println(
                "\nEnter the travel cost matrix:"
        );

        System.out.println(
                "Enter " + n + " rows with "
                        + n + " values each."
        );

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                distance[i][j] =
                        scanner.nextDouble();
            }
        }

        // Create TSP solver
        TSP tsp =
                new TSP(distance);

        // Measure execution time
        long startTime =
                System.nanoTime();

        TSPResult result =
                tsp.solve();

        long endTime =
                System.nanoTime();

        double executionTime =
                (endTime - startTime) / 1_000_000.0;

        // Display result
        System.out.println(
                "\n=========================================="
        );

        System.out.println(
                "              RESULT"
        );

        System.out.println(
                "=========================================="
        );

        System.out.println(
                "\nOptimal Route:"
        );

        List<Integer> path =
                result.getPath();

        for (int i = 0;
             i < path.size();
             i++) {

            System.out.print(
                    cities.get(
                            path.get(i)
                    ).getName()
            );

            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.println();

        System.out.printf(
                "\nMinimum Travel Cost: %.2f%n",
                result.getCost()
        );

        System.out.printf(
                "Execution Time: %.4f ms%n",
                executionTime
        );

        System.out.println(
                "\nOpening route visualization..."
        );

        // Display graphical route
        RouteVisualizer.showRoute(
                cities,
                path
        );

        scanner.close();
    }
}
