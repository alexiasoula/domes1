package domes1;

public class AAList implements List {

	private Element[] elements;
	private int[] next;
	private int head;
	private int tail;
	private int nextFree; // points to the first free position to elements

	// Constructor
	public AAList(int maxSize) {
		elements = new Element[maxSize];
		next = new int[maxSize];
		head = -1;
		tail = -1;
		nextFree = 0;
		for (int i = 0; i < maxSize - 1; i++) {
			next[i] = i + 1;
		}
		next[maxSize - 1] = -1; // -1 indicates end of free list

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
		System.out.println("Element not found");
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
		System.out.println("Element not found");
		return null;
	}
}