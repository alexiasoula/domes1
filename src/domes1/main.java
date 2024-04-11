package domes1;

import java.util.Random;

public class main {

	public final static int MAX_SIZE = 50;
    public static void main(String[] args) {
        // Δημιουργία της δομών δεδομένων
        DList dlist = new DList();
        SDList sdlist = new SDList();
        AAList aalist = new AAList(MAX_SIZE);
        SAAList saalist = new SAAList(MAX_SIZE);
        
        Object[] obj = {dlist, sdlist, aalist, saalist};

        // Πίνακες για την αποθήκευση των μετρήσεων
        double[][] insertTimes = new double[10][6];
        int[][] insertOperations = new int[10][6];
        double[][] deleteTimes = new double[10][6];
        int[][] deleteOperations = new int[10][6];
        double[][] searchTimes = new double[10][6];
        int[][] searchOperations = new int[10][6];

        // Επανάληψη για κάθε μέγεθος εισόδου N
        int[] N = {30, 50, 100, 200, 500, 800, 1000, 5000, 10000, 100000};
        for (int i = 0; i < N.length; i++) {
            int size = N[i];
            System.out.println("Size: " + size);

            // Προετοιμασία δεδομένων
            Random random = new Random();
            MyElement[] elements = new MyElement[size];
            for (int j = 0; j < size; j++) {
                int key = random.nextInt(2 * size) + 1;
                elements[j] = new MyElement(key);
                dlist.insert(elements[j]);
            }

            // Εκτέλεση μετρήσεων για την εισαγωγή
            System.out.println("Insertions:");
            runMeasurements(dlist, elements, size, insertTimes[i], insertOperations[i], 0);

            // Εκτέλεση μετρήσεων για τη διαγραφή
            System.out.println("Deletions:");
            runMeasurements(dlist, elements, size, deleteTimes[i], deleteOperations[i], 1);

            // Εκτέλεση μετρήσεων για την αναζήτηση
            System.out.println("Searches:");
            runMeasurements(dlist, elements, size, searchTimes[i], searchOperations[i], 2);
        }

        // Εμφάνιση αποτελεσμάτων
        // Εδώ μπορείτε να εμφανίσετε τα αποτελέσματα στον τρόπο που ταιριάζει στην προγραμματική σας αρχιτεκτονική
    }

    private static void runMeasurements(DList dlist, MyElement[] elements, int size, double[] times, int[] operations, int cs) {
        // Παράγετε τους τυχαίους αριθμούς
        Random random = new Random();
        int K = 0;
        if(size < 201) {
        	K = 10;
        } else if(size > 20 && size < 1001) {
        	K = 50;
        } else if(size > 1000) {
        	K = 100;
        }
        int[] randomKeys = new int[K];
        for (int i = 0; i < K; i++) {
            randomKeys[i] = random.nextInt(2 * size) + 1;
        }

        // Εκτέλεση μετρήσεων
        long startTime = System.nanoTime();
        int operationCount = 0;
        for (int j = 0; j < 100; j++) {
            switch (cs) {
                case 0:
                    for (int key : randomKeys) {
                        MyElement element = new MyElement(key);
                        dlist.insert(element);
                        operationCount++;
                    }
                    break;
                case 1:
                    for (MyElement element : elements) {
                    	dlist.delete(element.getKey());
                        operationCount++;
                    }
                    break;
                case 2:
                    for (int key : randomKeys) {
                    	dlist.search(key);
                        operationCount++;
                    }
                    break;
            }
        }
        long endTime = System.nanoTime();

        // Καταγραφή των μέσων τιμών του χρόνου και των πράξεων
        double averageTime = (endTime - startTime) / 1e6 / 100; // μέσος όρος σε ms
        times[cs] = averageTime;
        operations[cs] = operationCount / 100;
        System.out.println("Operation: " + (char)('A' + cs) + ", Time: " + averageTime + " ms, Operations: " + operations[cs]);
    }
}
