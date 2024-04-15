package domes1;

public class SAAList extends AAList{

	private Element[] elements;
    private int[] next;
    private int head;
    private int tail;
    private int nextFree; //points to the first free position to elements
    long totalTimeInsert;
	long totalTimeDelete;
	long totalTimeSearch;
	int insertCount;
	int deleteCount;
	int searchCount;

    //Constructor
    public SAAList(int maxSize) {
    	super(maxSize);
    	this.totalTimeInsert = 0;
		this.totalTimeDelete = 0;
		this.totalTimeSearch = 0;
		this.insertCount = 0;
		this.deleteCount = 0;
		this.searchCount = 0;

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
    		MultiCounter.increaseCounter(1, 6);
            while (current != -1 && elements[current].getKey() < element.getKey()) {
                prev = current;
                current = next[current];
        		MultiCounter.increaseCounter(1, 2);
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
    		MultiCounter.increaseCounter(1, 5);
            // Update tail if necessary
            if (current == -1) {
                tail = index;
        		MultiCounter.increaseCounter(1);
            }
            
        } else {
            System.out.println("List is full");
            return false;
        }
		MultiCounter.increaseCounter(1);
        return true;
    }


    //delete an element from the list
    public boolean delete(int key) {
        int prev = -1;
        int current = head;
		MultiCounter.increaseCounter(2, 2);
        while (current != -1) {
            if (elements[current].getKey() == key) {
                if (prev != -1) {
                    next[prev] = next[current];
                } else {
                    head = next[current];
                }
                if (current == tail) {
                    tail = prev;
            		MultiCounter.increaseCounter(2);
                }
                next[current] = nextFree;
                nextFree = current;
        		MultiCounter.increaseCounter(2, 5);
                return true;
            }
            prev = current;
            current = next[current];
    		MultiCounter.increaseCounter(2, 4);
        }
		MultiCounter.increaseCounter(2);
        System.out.println("Element not found");
        return false;
    }

    public Element search(int key) {
    	int current = head;
		MultiCounter.increaseCounter(3);
    	while(current != -1) {
    		if(elements[current].getKey() == key) {
    			return elements[current];
    		}
    		current = next[current];
    		MultiCounter.increaseCounter(3, 3);
    	}
		MultiCounter.increaseCounter(3);
    	System.out.println("Element not found");
        return null;
    }
    
    public long getTotalTimeInsert() {
		return totalTimeInsert;
	}

	public void setTotalTimeInsert(long totalTimeInsert) {
		this.totalTimeInsert = totalTimeInsert;
	}

	public long getTotalTimeDelete() {
		return totalTimeDelete;
	}

	public void setTotalTimeDelete(long totalTimeDelete) {
		this.totalTimeDelete = totalTimeDelete;
	}

	public long getTotalTimeSearch() {
		return totalTimeSearch;
	}

	public void setTotalTimeSearch(long totalTimeSearch) {
		this.totalTimeSearch = totalTimeSearch;
	}

	public int getInsertCount() {
		return insertCount;
	}

	public void setInsertCount(int insertCount) {
		this.insertCount = insertCount;
	}

	public int getDeleteCount() {
		return deleteCount;
	}

	public void setDeleteCount(int deleteCount) {
		this.deleteCount = deleteCount;
	}

	public int getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}

}
