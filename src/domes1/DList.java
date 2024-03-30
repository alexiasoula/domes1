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
		
		while(current != null && current.data.getKey() != key) {
			prev = current ;
			current = current.next;
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
		}
		
		return false;
	}
	
	/**
	 * Returns the first element found in the list with key equal to the given key
	 * @param key
	 * @return The first matched element, otherwise null
	 */
	public Element search(int key) {
		Node current = head;
		while(current != null) {
			if(current.data.getKey() == key) {
				return current.data;
			}
			current = current.next;
		}
		return null;
	}
}

