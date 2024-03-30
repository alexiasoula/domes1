package domes1;

public class SDList extends DList{
	Node head;
	Node tail;
	
	public SDList() {
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
		Node current = head;
		Node prev = null;
		
		while (current != null && current.data.getKey() < element.getKey()) {
			prev = current;
			current = current.next;
		}
		
		if (prev == null) {
			newNode.next = head;
			head = newNode;
		} else {
			newNode.next = current;
			prev.next = newNode;
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
		
		if(current == head) {
			head = head.next;
			current = null;
		} else if(current == tail) {
			tail = prev;
			current = null;
		} else {
			while(current != null) {
				current = current.next;
			}
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
