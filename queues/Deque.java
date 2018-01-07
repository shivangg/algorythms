import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int size;

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
		if (item == null) 
			throw new IllegalArgumentException() ;
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
			throw new IllegalArgumentException();

		else
		{
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
			throw new NoSuchElementException();
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
			throw new NoSuchElementException();
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

	// return an iterator over items in order from front to end
	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;

		public Item next()
		{	
			Item out;
			if (!hasNext())
				throw new NoSuchElementException();
			else
			{
				out = current.data;
				current = current.next;
				// System.out.println("iterating" + k);
				return(out);
			}
		}

		public boolean hasNext()
		{
			return(current != null);
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

	// public void printDeque()
	// {
	// 	if (first == null && last == null) 
	// 	{
	// 		System.out.println("first: "+ null + " last: "+ null);	
	// 	}
	// 	else
	// 		System.out.println("first: "+ first.data + " last: "+ last.data);
	// }

	public static void main(String[] args)   // unit testing (optional)
	{
		System.out.println("Starting unit testing");
		Deque<Integer> d = new Deque<Integer>();

		d.addFirst(1);
		d.addLast(2);
		// d.printDeque();
		// d.printDeque();
		d.addFirst(3);
		// d.printDeque();
		d.addLast(3345);
		// d.printDeque(); 3 1 2 3345

		for(Integer i : d)
			System.out.println(i);

		// d.addFirst(1);
		// d.addLast(2);
		// System.out.println(d.removeLast());
		// System.out.println(d.removeLast());

		
		// System.out.println(d.removeLast());
		// d.printDeque();
		// System.out.println(d.removeFirst());
		// d.printDeque();
		// System.out.println(d.removeFirst());
		// d.printDeque();
		// System.out.println(d.removeLast());
		// d.printDeque();
		
	}
}