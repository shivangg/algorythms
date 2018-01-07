import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation 
{
	public static void main(String[] args)
	{
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> rn = new RandomizedQueue<String>();
		while(!StdIn.isEmpty())
		{
			String s = StdIn.readString();
			rn.enqueue(s);
			// System.out.println(s);
		}

		for(int i = 0; i < k; i++)
			System.out.println(rn.dequeue());

	}
}