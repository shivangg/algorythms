import java.util.NoSuchElementException;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;



public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item arr[];
	private int lastIndex;

	public RandomizedQueue()                 // construct an empty randomized queue
	{
		arr = (Item[]) new Object[1];
		lastIndex = -1;

	}
	public boolean isEmpty()                 // is the randomized queue empty?
	{
		return (lastIndex == -1);
	}
	public int size()                        // return the number of items on the randomized queue
	{
		return lastIndex + 1;
	}
	public void enqueue(Item item)           // add the item
	{
		if (item == null) {
			throw new IllegalArgumentException();
		}
		else
		{
			if (arr.length == lastIndex+1)	
			{
				resize(2 * arr.length);
			}

			arr[++lastIndex] = item;
		}
	}
	private void resize(int capacity)
	{
		Item copy[] = (Item[]) new Object[capacity];
		for (int i = 0; i <= lastIndex; i++)
			copy[i] = arr[i];

		arr = copy;
	}
	public Item dequeue()                    // remove and return a random item
	{	
		Item out = sample();
		// fill the hole with the last element
		arr[randomIndex] = arr[lastIndex];
		lastIndex--;
		
		if (lastIndex ==  arr.length / 4)
		{
			resize(arr.length / 2);
		}
		
		return out;
	}

	private int randomIndex;

	public Item sample()             // return a random item (but do not remove it)
	{
		if (isEmpty())
		{
			throw new NoSuchElementException();			
		}
		if (lastIndex > 0)
			randomIndex = StdRandom.uniform(lastIndex+1); 	// get random index
		else
			randomIndex = 0;

		// sample the element at random index
		Item out = arr[randomIndex];
		
		return out;
	}
	public Iterator<Item> iterator()         // return an independent iterator over items in random order
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>
	{
		public Item next()
		{
			return (dequeue());
		}
		public boolean hasNext()
		{
			return (!isEmpty());
		}
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

	public static void main(String[] args)   // unit testing (optional)
	{
		RandomizedQueue<Integer> rn = new RandomizedQueue<Integer>();

		rn.enqueue(3);
		rn.enqueue(63);
		rn.enqueue(241);
		rn.enqueue(4359);
		rn.enqueue(34359);
		rn.enqueue(443359);
		rn.enqueue(4353449);

		System.out.println(rn.sample());
		System.out.println(rn.sample());
		System.out.println(rn.sample());
		System.out.println(rn.sample());
		System.out.println(rn.sample());

		// for(Integer i : rn)
		// {
		// 	// System.out.println("Queue size: " + rn.size() + "Array size: " + rn.arrlength());
		// 	System.out.println(i);
		// }

	}
}