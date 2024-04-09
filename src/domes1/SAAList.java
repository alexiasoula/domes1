package domes1;

public class SAAList extends AAList{

	private Element[] elements;
    private int[] next;
    private int head;
    private int tail;
    private int nextFree; //points to the first free position to elements

    //Constructor
    public SAAList(int maxSize) {
    	super(maxSize);

    }
 // Insert an element to the list in sorted order
    public boolean insert(Element element) {
        if (nextFree != -1) {
            int index = nextFree;
            nextFree = next[index]; // update free to next free row
            elements[index] = element;
            next[index] = -1; // no next element initially
            
            // Find the correct position to insert the element
            int prev = -1;
            int current = head;
            while (current != -1 && elements[current].getKey() < element.getKey()) {
                prev = current;
                current = next[current];
            }
            
            if (prev == -1) {
                // Insert at the beginning
                next[index] = head;
                head = index;
            } else {
                // Insert after the previous element
                next[index] = next[prev];
                next[prev] = index;
            }
            
            // Update tail if necessary
            if (current == -1) {
                tail = index;
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
