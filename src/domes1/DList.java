package domes1;

public class DList implements List {
	
	Node head;
	Node tail;
	
	public DList() {
		this.head = null;
		this.tail = null;
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
		System.out.println(MultiCounter.getCount(1));
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
		return false;
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
		return null;
	}
}

