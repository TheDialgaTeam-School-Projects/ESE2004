package system.collections.generic;

import java.util.Iterator;
import java.util.Vector;

/**
 * Represents a strongly typed list of objects that can be accessed by index.
 * Provides methods to search, sort, and manipulate lists.
 *
 * @param <T> The type of elements in the list.
 */
public class List<T> implements Iterable<T>
{
	protected Vector<T> list;

	/**
	 * Initializes a new instance of the {@code List<T>} class that is empty and
	 * has the default initial capacity.
	 */
	public List()
	{
		list = new Vector<T>();
	}

	/**
	 * Adds an object to the end of the {@code List<T>}.
	 *
	 * @param item The object to be added to the end of the {@code List<T>}. The
	 * value can be null for reference types.
	 */
	public void add(T item)
	{
		list.addElement(item);
	}

	/**
	 * Removes all elements from the {@code List<T>}.
	 */
	public void clear()
	{
		list.clear();
	}

	/**
	 * Determines whether an element is in the {@code List<T>}.
	 *
	 * @param item The object to locate in the {@code List<T>}. The value can be
	 * null for reference types.
	 * @return true if item is found in the {@code List<T>}; otherwise, false.
	 */
	public boolean contains(T item)
	{
		for (T t : list)
			if (item == t)
				return true;

		return false;
	}

	/**
	 * Gets the total number of elements the internal data structure can hold
	 * without resizing.
	 *
	 * @return The number of elements that the {@code List<T>} can contain
	 * before resizing is required.
	 */
	public int getCapacity()
	{
		return list.capacity();
	}

	/**
	 * Gets the number of elements contained in the {@code List<T>}.
	 */
	public int getCount()
	{
		return list.size();
	}

	/**
	 * Gets the element at the specified index.
	 *
	 * @param index The zero-based index of the element to get or set.
	 * @return The element at the specified index.
	 * @throws Exception ArgumentOutOfRangeException.
	 */
	public T getItem(int index) throws Exception
	{
		if (index < 0 || index >= getCount())
			throw new Exception("ArgumentOutOfRangeException: index is less than 0 or index is equal to or greater than Count.");
		else
			return list.get(index);
	}

	/**
	 * Inserts an element into the {@code List<T>} at the specified index.
	 *
	 * @param index The zero-based index at which item should be inserted.
	 * @param item The object to insert. The value can be null for reference
	 * types.
	 * @throws Exception ArgumentOutOfRangeException.
	 */
	public void insert(int index, T item) throws Exception
	{
		if (index < 0 || index > getCount())
			throw new Exception("ArgumentOutOfRangeException: index is less than 0 or index is greater than Count.");
		else
			list.insertElementAt(item, index);
	}

	@Override
	public Iterator<T> iterator()
	{
		return list.iterator();
	}

	/**
	 * Removes the element at the specified index of the {@code List<T>}.
	 *
	 * @param index The zero-based index of the element to remove.
	 * @throws Exception ArgumentOutOfRangeException.
	 */
	public void removeAt(int index) throws Exception
	{
		if (index < 0 || index > getCount())
			throw new Exception("ArgumentOutOfRangeException: index is less than 0 or index is greater than Count.");
		else
			list.removeElementAt(index);
	}

	/**
	 * Reverses the order of the elements in the entire {@code List<T>}.
	 */
	public void reverse()
	{
		Vector<T> Result = new Vector<T>(getCapacity());

		for (int i = 0; i < getCount(); i++)
			Result.addElement(list.elementAt(getCount() - 1 - i));

		list = Result;
	}

	/**
	 * Sets the element at the specified index.
	 *
	 * @param index The zero-based index of the element to get or set.
	 * @param item Item to set.
	 */
	public void setItem(int index, T item)
	{
		list.set(index, item);
	}

	public int size()
	{
		return list.size();
	}
}
