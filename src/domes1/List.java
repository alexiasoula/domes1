package domes1;

/**
* An interface describing a list data structure storing element using int as key.
* The list has a state in form of a pointer to a current element
*/
public interface List {
	/**
	 * Inserts an element into the list
	 * @param element
	 * @return true if the insertion was successful. Otherwise false
	 */
	public boolean insert(Element element);
	
	/**
	 * Deletes the first element found in the list with key equal to the given key
	 * @param key
	 * @return true if a deletion is made. Otherwise false
	 */
	public boolean delete(int key);
	
	/**
	 * Returns the first element found in the list with key equal to the given key
	 * @param key
	 * @return The first matched element, otherwise null
	 */
	public Element search(int key);
}