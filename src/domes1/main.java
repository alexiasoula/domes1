package domes1;

import java.util.Random;

public class main {

    public static void main(String[] args) {
        // Δημιουργία της δομής δεδομένων DList
        DList dList = new DList();

        // Πίνακες για την αποθήκευση των μετρήσεων
        double[][] insertTimes = new double[10][6];
        int[][] insertOperations = new int[10][6];
        double[][] deleteTimes = new double[10][6];
        int[][] deleteOperations = new int[10][6];
        double[][] searchTimes = new double[10][6];
        int[][] searchOperations = new int[10][6];

        // Επανάληψη για κάθε μέγεθος εισόδου N
        int[] sizes = {30, 50, 100/*, 200, 500, 800, 1000, 5000, 10000, 100000*/};
        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            System.out.println("Size: " + size);

            // Προετοιμασία δεδομένων
            Random random = new Random();
            MyElement[] elements = new MyElement[size];
            for (int j = 0; j < size; j++) {
                int key = random.nextInt(2 * size) + 1;
                elements[j] = new MyElement(key);
                dList.insert(elements[j]);
            }

            // Εκτέλεση μετρήσεων για την εισαγωγή
            System.out.println("Insertions:");
            runMeasurements(dList, elements, size, insertTimes[i], insertOperations[i]);

            // Εκτέλεση μετρήσεων για τη διαγραφή
            System.out.println("Deletions:");
            runMeasurements(dList, elements, size, deleteTimes[i], deleteOperations[i]);

            // Εκτέλεση μετρήσεων για την αναζήτηση
            System.out.println("Searches:");
            runMeasurements(dList, elements, size, searchTimes[i], searchOperations[i]);
        }

        // Εμφάνιση αποτελεσμάτων
        // Εδώ μπορείτε να εμφανίσετε τα αποτελέσματα στον τρόπο που ταιριάζει στην προγραμματική σας αρχιτεκτονική
    }

    private static void runMeasurements(DList dList, MyElement[] elements, int size, double[] times, int[] operations) {
        // Παράγετε τους τυχαίους αριθμούς
        Random random = new Random();
        int[] randomKeys = new int[1000];
        for (int i = 0; i < 1000; i++) {
            randomKeys[i] = random.nextInt(2 * size) + 1;
        }

        // Εκτέλεση μετρήσεων
        for (int i = 0; i < 6; i++) {
            long startTime = System.nanoTime();
            int operationCount = 0;
            for (int j = 0; j < 100; j++) {
                switch (i) {
                    case 0:
                        for (int key : randomKeys) {
                            MyElement element = new MyElement(key);
                            dList.insert(element);
                            operationCount++;
                        }
                        break;
                    case 1:
                        for (MyElement element : elements) {
                            dList.delete(element.getKey());
                            operationCount++;
                        }
                        break;
                    case 2:
                        for (int key : randomKeys) {
                            dList.search(key);
                            operationCount++;
                        }
                        break;
                }
            }
            long endTime = System.nanoTime();

            // Καταγραφή των μέσων τιμών του χρόνου και των πράξεων
            double averageTime = (endTime - startTime) / 1e6 / 100; // μέσος όρος σε ms
            times[i] = averageTime;
            operations[i] = operationCount / 100;
            System.out.println("Operation: " + (char)('A' + i) + ", Time: " + averageTime + " ms, Operations: " + operations[i]);
        }
    }
}
