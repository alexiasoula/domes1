package domes1;

public class AList implements List {

    protected Element[] elements;
    protected int tail; // Points to the last element of the list
    protected long totalTimeInsert;
    protected long totalTimeDelete;
    protected long totalTimeSearch;
    protected int insertCount;
    protected int deleteCount;
    protected int searchCount;

    // Constructor
    public AList(int maxSize) {
        elements = new Element[maxSize];
        tail = -1; // Initially the list is empty
        this.totalTimeInsert = 0;
		this.totalTimeDelete = 0;
		this.totalTimeSearch = 0;
		this.insertCount = 0;
		this.deleteCount = 0;
		this.searchCount = 0;
    }

    // Insert an element into the list
    public boolean insert(Element element) {
        if (tail < elements.length - 1) {
            tail++; // Move the tail pointer to the next available position
            elements[tail] = element; // Insert the element at the tail position
            MultiCounter.increaseCounter(13, 3);
            return true;
        } else {
    		MultiCounter.increaseCounter(13);
            System.out.println("List is full");
            return false;
        }
    }

    // Delete an element from the list
    public boolean delete(int key) {
        int index = searchIndex(key, 14);
        if (index != -1) {
        	MultiCounter.increaseCounter(14, 2);
            for (int i = index; i < tail; i++) {
                elements[i] = elements[i + 1]; // Move elements to the left to fill the gap
            }
            elements[tail] = null; // Remove the last element
            tail--; // Update the tail pointer
            return true;
        } else {
            //System.out.println("Element not found");
            return false;
        }
    }

    // Search for an element in the list
    public Element search(int key) {
        int index = searchIndex(key, 15);
        if (index != -1) {
            return elements[index];
        } else {
            //System.out.println("Element not found");
            return null;
        }
    }

    // Helper method to find the index of an element with a given key
    private int searchIndex(int key, int cntr) {
    	MultiCounter.increaseCounter(cntr);
        for (int i = 0; i <= tail; i++) {
        	MultiCounter.increaseCounter(cntr, 3);
            if (elements[i].getKey() == key) {
                return i;
            }
        }
        return -1; // Element not found
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
