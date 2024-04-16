package domes1;

public class SAList extends AList {
	
    // Constructor
    public SAList(int maxSize) {
        super(maxSize); // Call the constructor of the parent class
    }

    // Override the insert method to maintain sorting
    public boolean insert(Element element) {
        if (tail < elements.length - 1) {
            int index = binarySearch(element.getKey()); // Find the index for insertion
            for (int i = tail + 1; i > index; i--) {
                elements[i] = elements[i - 1]; // Shift elements to the right
            }
            elements[index] = element; // Insert the element at the correct position
            tail++; // Update the tail pointer
            return true;
        } else {
            System.out.println("List is full");
            return false;
        }
    }

    // Override the delete method
    public boolean delete(int key) {
        int index = binarySearch(key); // Find the index of the element to delete
        if (index != -1) {
            for (int i = index; i < tail; i++) {
                elements[i] = elements[i + 1]; // Shift elements to the left
            }
            elements[tail] = null; // Remove the last element
            tail--; // Update the tail pointer
            return true;
        } else {
            //System.out.println("Element not found");
            return false;
        }
    }

    // Override the search method
    public Element search(int key) {
        int index = binarySearch(key); // Find the index of the element
        if (index != -1) {
            return elements[index];
        } else {
            //System.out.println("Element not found");
            return null;
        }
    }

    // Helper method for binary search
    private int binarySearch(int key) {
        int low = 0;
        int high = tail;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (elements[mid].getKey() < key) {
                low = mid + 1;
            } else if (elements[mid].getKey() > key) {
                high = mid - 1;
            } else {
                return mid; // Found the element
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

