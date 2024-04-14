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
		MultiCounter.increaseCounter(1, 2);
		while (current != null && current.data.getKey() < element.getKey()) {
			prev = current;
			current = current.next;
			MultiCounter.increaseCounter(1, 4);
		}
		
		if (prev == null) {
			newNode.next = head;
			head = newNode;
		} else {
			newNode.next = current;
			prev.next = newNode;
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
		MultiCounter.increaseCounter(2);
		
		while(current != null && current.data.getKey() != key) {
			prev = current ;
			current = current.next;
			MultiCounter.increaseCounter(2, 4);
		}
		if(current == null) {
			return false;
		}
		MultiCounter.increaseCounter(2);
		if(current == head) {
			head = head.next;
			current = null;
			MultiCounter.increaseCounter(2, 3);
		} else if(current == tail) {
			tail = prev;
			current = null;
			MultiCounter.increaseCounter(2, 3);
		} else {
			while(current != null) {
				current = current.next;
				MultiCounter.increaseCounter(2, 2);
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
