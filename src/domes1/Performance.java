package domes1;

import java.util.Random;

public class Performance {
    private static final int[] N_VALUES = {30, 50, 100, 200, 500, 800, 1000, 5000, 10000, 100000};

    private static final Random random = new Random();

    public static void main(String[] args) {
        // Run experiment for each N value
        for (int n : N_VALUES) {
            List[] implementations = runExperiment(n);
            printResults(n, implementations);
        }
    }

    private static List[] runExperiment(int n) {
        // Preparation
        List[] implementations = {new DList(), new SDList(), new AAList(n + 10000), new SAAList(n + 10000), new AList(n + 10000)/*, new SAList(n + 10000)*/};

        int[] keys = generateRandomKeys(n, n);

        for (List implementation : implementations) {
            for (int key : keys) {
                MyElement element = new MyElement(key);
                implementation.insert(element);
            }
        }

        // Performance check
        int K = 0;

        if (n < 201) {
            K = 10;
        } else if (n > 200 && n < 1001) {
            K = 50;
        } else if (n > 1000) {
            K = 100;
        }

        int[] nums = generateRandomKeys(n, K);
        for (int num : nums) {
            MyElement element = new MyElement(num);
            for (List implementation : implementations) {
                // Measure insert operation
                long startTime = System.nanoTime();
                implementation.insert(element);
                long endTime = System.nanoTime();
                implementation.setTotalTimeInsert(implementation.getTotalTimeInsert() + (endTime - startTime));
                implementation.setInsertCount(implementation.getInsertCount() + 1);
            }
        }

        nums = generateRandomKeys(n, K);
        for (int num : nums) {
            for (List implementation : implementations) {
                // Measure delete operation
                long startTime = System.nanoTime();
                implementation.delete(num);
                long endTime = System.nanoTime();
                implementation.setTotalTimeDelete(implementation.getTotalTimeDelete() + (endTime - startTime));
            	implementation.setDeleteCount(implementation.getDeleteCount() + 1);
            }
        }

        nums = generateRandomKeys(n, K);
        for (int num : nums) {
            for (List implementation : implementations) {
                // Measure search operation
                long startTime = System.nanoTime();
                implementation.search(num);
                long endTime = System.nanoTime();
                implementation.setTotalTimeSearch(implementation.getTotalTimeSearch() + (endTime - startTime));
            	implementation.setSearchCount(implementation.getSearchCount() + 1);
            }
        }
        return implementations;
    }

    private static int[] generateRandomKeys(int n, int num) {
        int[] keys = new int[num];
        for (int i = 0; i < num; i++) {
            keys[i] = random.nextInt(2 * n) + 1;
        }
        return keys;
    }

    private static void printResults(int n, List[] implementations) {
        System.out.println("Results for N = " + n + " elements:");
        for (List implementation : implementations) {
            System.out.println("Data Structure: " + implementation.getClass().getSimpleName());
            System.out.println("Average Insertion Time (ns): " + (float) implementation.getTotalTimeInsert() / (float) implementation.getInsertCount());
            System.out.println("Average Deletion Time (ns): " + (float) implementation.getTotalTimeDelete() /  (float) implementation.getDeleteCount());
            System.out.println("Average Search Time (ns): " + (float) implementation.getTotalTimeSearch() / (float) implementation.getSearchCount());
            System.out.println();
        }
    }
}
