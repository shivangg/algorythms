import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	int size;

	private class Node{
		Item data;
		Node next = null;
		Node prev = null;
	}

	public Deque()                           // construct an empty deque
	{
		first = null;
		size = 0;
	}

	public boolean isEmpty()                 // is the deque empty?
	{
		return( first == null );
	}

	public int size()                        // return the number of items on the deque
	{
		return size;
	}

	public void addFirst(Item item)          // add the item to the front
	{
		if(item == null) 
			throw new java.lang.IllegalArgumentException() ;
		else
		{
			Node n = new Node();
			n.data = item;
			n.prev = null;

			if (isEmpty()) 
			{
				last = n;
			}
			else
			{
				n.next = first;
				first.prev = n;
			}
			first = n;

			size++;
		}
	}

	public void addLast(Item item)           // add the item to the end
	{
		if(item == null) 
			throw new java.lang.IllegalArgumentException();

		else
		{
			System.out.println("Trying to add at last " + item);
			Node n = new Node();
			n.data = item;
			
			// connect to first if was empty
			if (isEmpty()) 
			{
				first = n;
			}
			else
			{
				// only do last element was present
				n.prev = last;
				last.next = n;
			}
			// make this last also
			last = n;

			size ++;
		}
	}

	public Item removeFirst()                // remove and return the item from the front
	{
		if(isEmpty()) 
			throw new java.util.NoSuchElementException();
		else
		{
			// System.out.println("removing");
			Item out = first.data;
			if (size != 1)
			{
				first = first.next;
				// System.out.println(first.data);
				first.prev = null;				// this was the second element, prev was pointing to oldfirst 
			}
			else
			{
				first = null;
				last = null;
			}

			size--;
			// System.out.println("Size now " + size);

			return out;
		}
	}

	public Item removeLast()                 // remove and return the item from the end
	{
		if(isEmpty()) 
			throw new java.util.NoSuchElementException();
		else
		{
			Item out = last.data;
			// System.out.println(size);
			if (size == 1)
			{
				last = null;
				first = null;
			}
			else
			{
				last = last.prev;
				last.next = null;	// this was the second last element, prev was pointing to oldlast 
			}

			size--;

			return out;
		}
	}

	public void printDeque()
	{
		if (first == null && last == null) 
		{
			System.out.println("first: "+ null + " last: "+ null);	
		}
		else
			System.out.println("first: "+ first.data + " last: "+ last.data);
	}

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>
	{

		public Item next()
		{
			Item k = removeFirst();
			// System.out.println("iterating" + k);
			return(k);
		}

		public boolean hasNext()
		{
			return(first.next != null);
		}

		public void remove()
		{
			throw new java.lang.UnsupportedOperationException();
		}
	}
	public static void main(String[] args)   // unit testing (optional)
	{
		System.out.println("Starting unit testing");
		Deque<Integer> d = new Deque<Integer>();

		d.addLast(3);
		d.printDeque();

		d.addLast(4);
		d.printDeque();

		d.addLast(3543);
		d.printDeque();

		d.addLast(0);
		d.printDeque();

		// for(Integer i : d)
		// 	System.out.println(i);

		System.out.println("current size is " + d.size);

		// for(Integer i : d)
		// 	System.out.println(i);
		
		System.out.println(d.removeFirst());
		d.printDeque();
		System.out.println(d.removeFirst());
		d.printDeque();
		System.out.println(d.removeFirst());
		d.printDeque();
		System.out.println(d.removeFirst());
		d.printDeque();
		
	}
}