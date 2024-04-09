package domes1;

public class AAList implements List {

    private Element[] elements;
    private int[] next;
    private int head;
    private int tail;
    private int nextFree; //points to the first free position to elements

    //Constructor
    public AAList(int maxSize) {
    	elements = new Element[maxSize];
    	next = new int[maxSize];
    	head = -1;
    	tail = -1;
    	nextFree = 0;
    	for (int i = 0; i < maxSize - 1; i++) {
    	    next[i] = i + 1;
    	}
    	next[maxSize - 1] = -1; // -1 indicates end of free list

    }
    //insert an element to the list
    public boolean insert(Element element) {
    	        if (nextFree != -1) {
    	            int index = nextFree;
    	            nextFree = next[index]; // update free to next free row
    	            elements[index] = element;
    	            next[index] = -1; // no next element initially
    	            if (tail != -1) {
    	                next[tail] = index; // update next of previous tail to new index
    	            }
    	            tail = index;
    	            if (head == -1) {
    	                head = index;
    	            }
    	        } else {
    	            System.out.println("List is full");
    	            return false;
    	        }
    	    
        return true;
    }

    //delete an element from the list
    public boolean delete(int key) {
        int prev = -1;
        int current = head;
        while (current != -1) {
            if (elements[current].getKey() == key) {
                if (prev != -1) {
                    next[prev] = next[current];
                } else {
                    head = next[current];
                }
                if (current == tail) {
                    tail = prev;
                }
                next[current] = nextFree;
                nextFree = current;
                return true;
            }
            prev = current;
            current = next[current];
        }
        System.out.println("Element not found");
        return false;
    }

    public Element search(int key) {
    	int current = head;
    	while(current != -1) {
    		if(elements[current].getKey() == key) {
    			return elements[current];
    		}
    		current = next[current];
    	}
    	
    	System.out.println("Element not found");
        return null;
    }
}