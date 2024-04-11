package domes1;

import java.util.Random;

public class PerformanceExperiment {
    private static final int[] N_VALUES = {30, 50, 100, 200, 500, 800, 1000, 5000, 10000, 100000};
    private static final int[] K_VALUES = {10, 50, 100};

    private static final Random random = new Random();

    public static void main(String[] args) {
        // Run experiment for each N value
        for (int n : N_VALUES) {
            runExperiment(n);
        }
    }

    private static void runExperiment(int n) {
        System.out.println("Experiment for N = " + n + " elements:");

        // Generate random keys
        int[] keys = generateRandomKeys(n);

        // Initialize each implementation
        List[] implementations = {
                new DList()/*, new SDList()*/};

        // Run each operation for each implementation
        for (List implementation : implementations) {
            long totalTimeInsert = 0;
            long totalTimeDelete = 0;
            long totalTimeSearch = 0;
            long totalOperations = 0;
            MultiCounter.resetCounter(1);
            MultiCounter.resetCounter(2);
            MultiCounter.resetCounter(3);
            for (int k : K_VALUES) {
                for (int i = 0; i < k; i++) {
                    int randomIndex = random.nextInt(n);
                    MyElement element = new MyElement(keys[randomIndex]);

                    // Measure insert operation
                    long startTime = System.nanoTime();
                    implementation.insert(element);
                    long endTime = System.nanoTime();
                    totalTimeInsert += (endTime - startTime);

                    // Measure delete operation
                    startTime = System.nanoTime();
                    implementation.delete(keys[randomIndex]);
                    endTime = System.nanoTime();
                    totalTimeDelete += (endTime - startTime);

                    // Measure search operation
                    startTime = System.nanoTime();
                    implementation.search(keys[randomIndex]);
                    endTime = System.nanoTime();
                    totalTimeSearch += (endTime - startTime);

                    totalOperations += 3; // Each loop iteration performs 3 operations
                }
            }

            // Calculate averages
            double averageTimeInsert = (double) totalTimeInsert / (double) MultiCounter.getCount(1);
            double averageTimeDelete = (double) totalTimeDelete / (double) MultiCounter.getCount(2);
            double averageTimeSearch = (double) totalTimeSearch / (double) MultiCounter.getCount(3);

            System.out.println("Implementation: " + implementation.getClass().getSimpleName());
            System.out.println("Average Insert Time: " + averageTimeInsert + " nanoseconds");
            //System.out.println(MultiCounter.getCount(1));
            System.out.println("Average Delete Time: " + averageTimeDelete + " nanoseconds");
            //System.out.println(MultiCounter.getCount(2));
            System.out.println("Average Search Time: " + averageTimeSearch + " nanoseconds");
            //System.out.println(MultiCounter.getCount(3));
            System.out.println();
        }
    }

    private static int[] generateRandomKeys(int n) {
        int[] keys = new int[n];
        for (int i = 0; i < n; i++) {
            keys[i] = random.nextInt(2 * n) + 1;
        }
        return keys;
    }
}