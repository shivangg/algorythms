import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int size;

	private class Node
	{
		Item data;
		Node next = null;
	}
	public RandomizedQueue()                 // construct an empty randomized queue
	{
		first = null;
		last = null;
		size = 0;
	}
	public boolean isEmpty()                 // is the randomized queue empty?
	{
		return(size==0);
	}
	public int size()                        // return the number of items on the randomized queue
	{
		return size();
	}
	public void enqueue(Item item)           // add the item
	{
		if (item == null) {
			throw new java.lang.IllegalArgumentException();
		}
		else
		{
			Node n = new Node();
			n.data = item;

			if (isEmpty())
			{
				first = n;
				last = n;
			}
			else
			{
				last.next = n;
				last = n;
			}

			size++;
		}
	}
	public Item dequeue()                    // remove and return a random item
	{
		Item out = first.data;
		if(size == 1)
		{
			first = null;
		}
		else
		{
			first = first.next;
		}

		size--;
		return out;
	}
	// public Item sample()             // return a random item (but do not remove it)
	public Iterator<Item> iterator()         // return an independent iterator over items in random order
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>
	{
		public Item next()
		{
			return(dequeue());
		}
		public boolean hasNext()
		{
			return(!isEmpty());
		}
	}

	public static void main(String[] args)   // unit testing (optional)
	{
		RandomizedQueue<Integer> rn = new RandomizedQueue<Integer>();

		rn.enqueue(3);
		rn.enqueue(63);
		rn.enqueue(241);
		rn.enqueue(4359);

		for(Integer i : rn)
		{
			System.out.println(i);
		}

	}
}