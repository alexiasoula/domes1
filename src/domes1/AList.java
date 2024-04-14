package domes1;

public class AList implements List {

    private Element[] elements;
    private int tail; // Points to the last element of the list

    // Constructor
    public AList(int maxSize) {
        elements = new Element[maxSize];
        tail = -1; // Initially the list is empty
    }

    // Insert an element into the list
    public boolean insert(Element element) {
        if (tail < elements.length - 1) {
            tail++; // Move the tail pointer to the next available position
            elements[tail] = element; // Insert the element at the tail position
            MultiCounter.increaseCounter(1, 3);
            return true;
        } else {
    		MultiCounter.increaseCounter(1);
            System.out.println("List is full");
            return false;
        }
    }

    // Delete an element from the list
    public boolean delete(int key) {
        int index = searchIndex(key);
        if (index != -1) {
            for (int i = index; i < tail; i++) {
                elements[i] = elements[i + 1]; // Move elements to the left to fill the gap
            }
            elements[tail] = null; // Remove the last element
            tail--; // Update the tail pointer
            return true;
        } else {
            System.out.println("Element not found");
            return false;
        }
    }

    // Search for an element in the list
    public Element search(int key) {
        int index = searchIndex(key);
        if (index != -1) {
            return elements[index];
        } else {
            System.out.println("Element not found");
            return null;
        }
    }

    // Helper method to find the index of an element with a given key
    private int searchIndex(int key) {
        for (int i = 0; i <= tail; i++) {
            if (elements[i].getKey() == key) {
                return i;
            }
        }
        return -1; // Element not found
    }
}
