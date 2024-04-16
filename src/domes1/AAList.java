package domes1;

public class AAList implements List {

	protected Element[] elements;
	protected int[] next;
	protected int head;
	protected int tail;
	protected int nextFree; // points to the first free position to elements
	protected long totalTimeInsert;
	protected long totalTimeDelete;
	protected long totalTimeSearch;
	protected int insertCount;
	protected int deleteCount;
	protected int searchCount;

	// Constructor
	public AAList(int maxSize) {
		elements = new Element[maxSize];
		next = new int[maxSize];
		head = -1;
		tail = -1;
		nextFree = 0;
		this.next = new int[maxSize];
		for (int i = 0; i < maxSize - 1; i++) {
			next[i] = i + 1;
		}
		this.totalTimeInsert = 0;
		this.totalTimeDelete = 0;
		this.totalTimeSearch = 0;
		this.insertCount = 0;
		this.deleteCount = 0;
		this.searchCount = 0;

	}

	// insert an element to the list
	public boolean insert(Element element) {
		if (nextFree != -1) {
			int index = nextFree;
			nextFree = next[index]; // update free to next free row
			elements[index] = element;
			next[index] = -1; // no next element initially
			MultiCounter.increaseCounter(1, 4);
			if (tail != -1) {
				next[tail] = index; // update next of previous tail to new index
				MultiCounter.increaseCounter(1);
			}
			tail = index;
			MultiCounter.increaseCounter(1, 2);
			if (head == -1) {
				head = index;
				MultiCounter.increaseCounter(1);
			}
			MultiCounter.increaseCounter(1);
		} else {
			System.out.println("List is full");
			return false;
		}
		MultiCounter.increaseCounter(1);
		return true;
	}

	// delete an element from the list
	public boolean delete(int key) {
		int prev = -1;
		int current = head;
		MultiCounter.increaseCounter(2,2);
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
		//System.out.println("Element not found to delete");
		return false;
	}

	public Element search(int key) {
		int current = head;
		while (current != -1) {
			if (elements[current].getKey() == key) {
				return elements[current];
			}
			current = next[current];
			MultiCounter.increaseCounter(3, 3);
		}
		MultiCounter.increaseCounter(3,2);
		//System.out.println("Element not found for search");
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