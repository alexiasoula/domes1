package domes1;

public class SAAList extends AAList{

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
    		MultiCounter.increaseCounter(10, 6);
            while (current != -1 && elements[current].getKey() < element.getKey()) {
                prev = current;
                current = next[current];
        		MultiCounter.increaseCounter(10, 2);
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
    		MultiCounter.increaseCounter(10, 5);
            // Update tail if necessary
            if (current == -1) {
                tail = index;
        		MultiCounter.increaseCounter(10);
            }
            
        } else {
            System.out.println("List is full");
            return false;
        }
		MultiCounter.increaseCounter(10);
        return true;
    }


    //delete an element from the list
    public boolean delete(int key) {
        int prev = -1;
        int current = head;
		MultiCounter.increaseCounter(11, 2);
        while (current != -1) {
            if (elements[current].getKey() == key) {
                if (prev != -1) {
                    next[prev] = next[current];
                } else {
                    head = next[current];
                }
                if (current == tail) {
                    tail = prev;
            		MultiCounter.increaseCounter(11);
                }
                next[current] = nextFree;
                nextFree = current;
        		MultiCounter.increaseCounter(11, 5);
                return true;
            }
            prev = current;
            current = next[current];
    		MultiCounter.increaseCounter(11, 4);
        }
		MultiCounter.increaseCounter(11);
        //System.out.println("Element not found");
        return false;
    }

    public Element search(int key) {
    	int current = head;
		MultiCounter.increaseCounter(12);
    	while(current != -1) {
    		if(elements[current].getKey() == key) {
    			return elements[current];
    		}
    		current = next[current];
    		MultiCounter.increaseCounter(12, 3);
    	}
		MultiCounter.increaseCounter(12);
    	//System.out.println("Element not found");
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
