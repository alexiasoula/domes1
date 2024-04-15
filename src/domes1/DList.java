package domes1;

public class DList implements List {
	
	Node head;
	Node tail;
	long totalTimeInsert;
	long totalTimeDelete;
	long totalTimeSearch;
	int insertCount;
	int deleteCount;
	int searchCount;
	
	public DList() {
		
		this.head = null;
		this.tail = null;
		this.totalTimeInsert = 0;
		this.totalTimeDelete = 0;
		this.totalTimeSearch = 0;
		this.insertCount = 0;
		this.deleteCount = 0;
		this.searchCount = 0;
	}
	
	/**
	 * Inserts an element into the list
	 * @param element
	 * @return true if the insertion was successful. Otherwise false
	 */
	public boolean insert(Element element) {
		Node newNode = new Node(element);
		if(head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			tail = newNode;
		}
		MultiCounter.increaseCounter(1, 4);
		return true;
	}
	
	/**
	 * Deletes the first element found in the list with key equal to the given key
	 * @param key
	 * @return true if a deletion is made. Otherwise false
	 */
	public boolean delete(int key) {
		
		Node current = head;
		Node prev = null;
		MultiCounter.increaseCounter(2, 2);
		while(current != null && current.data.getKey() != key) {
			prev = current ;
			current = current.next;
			MultiCounter.increaseCounter(2, 4);
		}
		MultiCounter.increaseCounter(2);
		if(current == null) {
			return false;
		}
		
		if (prev == null) {
			head = head.next;
		} else {
			prev.next = current.next;
		}
		if (current == tail) {
			tail = prev;
			MultiCounter.increaseCounter(2);
		}
		MultiCounter.increaseCounter(2, 3);
		return true;
	}
	
	/**
	 * Returns the first element found in the list with key equal to the given key
	 * @param key
	 * @return The first matched element, otherwise null
	 */
	public Element search(int key) {
		Node current = head;
		MultiCounter.increaseCounter(3);
		while(current != null) {
			if(current.data.getKey() == key) {
				return current.data;
			}
			current = current.next;
			MultiCounter.increaseCounter(3, 3);
		}
		MultiCounter.increaseCounter(3);
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

