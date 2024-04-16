package domes1;

public class SAList extends AList {

    // Constructor
    public SAList(int maxSize) {
        super(maxSize); // Call the constructor of the parent class
    }

    // Override the insert method to maintain sorting using binary search
    @Override
    public boolean insert(Element element) {
        int index = binarySearch(element.getKey(), 16); // Find the index for insertion
        MultiCounter.increaseCounter(16, 3);
        if (tail < elements.length - 1 && index >= 0) {
            // Shift elements to the right to make space for the new element
        	MultiCounter.increaseCounter(16);
            for (int i = tail + 1; i > index; i--) {
                elements[i] = elements[i - 1];
                MultiCounter.increaseCounter(16, 3);
            }
            elements[index] = element; // Insert the element at the correct position
            tail++; // Update the tail pointer
            MultiCounter.increaseCounter(16, 2);
            return true;
        } else {
            System.out.println("List is full");
            return false;
        }
    }

    // Override the delete method
    @Override
    public boolean delete(int key) {
        int index = binarySearch(key, 17); // Find the index of the element to delete
        MultiCounter.increaseCounter(17, 2);
        if (index != -1) {
        	MultiCounter.increaseCounter(17);
            for (int i = index; i < tail; i++) {
                elements[i] = elements[i + 1]; // Shift elements to the left
                MultiCounter.increaseCounter(17, 3);
            }
            elements[tail] = null; // Remove the last element
            tail--; // Update the tail pointer
            MultiCounter.increaseCounter(17);
            return true;
        } else {
            // Element not found
            return false;
        }
    }

    // Override the search method
    @Override
    public Element search(int key) {
        int index = binarySearch(key, 18); // Find the index of the element
        MultiCounter.increaseCounter(118, 2);
        if (index != -1) {
            return elements[index];
        } else {
            // Element not found
            return null;
        }
    }

    // Helper method for binary search
    private int binarySearch(int key, int cntr) {
        int low = 0;
        int high = tail;
        MultiCounter.increaseCounter(cntr, 3);
        while (low <= high) {
            int mid = (low + high) / 2;
            int midKey = elements[mid].getKey();
            if (midKey < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            MultiCounter.increaseCounter(cntr, 5);
        }
        return low; // Return the index for insertion
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

