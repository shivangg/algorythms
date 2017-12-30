import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


	private WeightedQuickUnionUF grid;
	private boolean[] openSite;
	private int nSitesOpen;
	private int n;
	public Percolation(int N)                // create n-by-n grid, with all sites blocked
	{
		if (N < 1) {
			throw new java.lang.IllegalArgumentException();
		}
		n = N;
		nSitesOpen = 0;
		openSite = new boolean[n*n + 2];
		openSite[0] = true;
		openSite[n*n+1] = true;
		// site 0 : top virtual site
		// site n*n + 1 : bottom virtual site
		grid = new  WeightedQuickUnionUF(n * n + 2);
	}

	private int gridCoordinates(int row, int col)
	{
		if (row > n || row < 1 || col > n || col < 1) {
			throw new java.lang.IllegalArgumentException();
		}

		int value = (row-1)*n + col;
		// if(openSite[value])
		// {
		// 	System.out.println(row + " , " + col);
		// 	System.out.println( value + " open? "+ openSite[value] );
		// }
		return value;
	}

	public void open(int row, int col)    // open site (row, col) if it is not open already
	{
		// System.out.println("In open(), sending row = " + row+ ", col = "+ col);
		int sitePositionIn1D = gridCoordinates(row, col);
		// System.out.println(row + " , " + col + " | \t sitePositionIn1D = " + sitePositionIn1D);
		if (!openSite[sitePositionIn1D]) 
		{
			openSite[sitePositionIn1D] = true;
			nSitesOpen++;
		}
		unite(row, col);
		// System.out.println("open operation completed");
	}

	// after opening a site, try the percolation
	private void unite(int row, int col)
	{
		// union with top vitual site
		if(row == 1)
		{
			grid.union( gridCoordinates(row, col),0);
		}
		// union with bottom vitual site
		if(row == n )
		{
			grid.union( gridCoordinates(row, col), n*n+1);
		}
		// union with the neighbors
		if (row > 1 ) 
		{
			if( isOpen(row - 1, col) )
			{
				// System.out.println("connecting with the open upper site");
				grid.union( gridCoordinates(row , col), gridCoordinates(row - 1, col) );
			}	
		}
		if (row < n) 
		{
			if( isOpen(row + 1, col) )
			{
				// System.out.println("connecting with the open bottom site");
				grid.union( gridCoordinates(row , col), gridCoordinates(row + 1, col) );
			}
		}
		if ( col > 1) 
		{
			if( isOpen(row, col - 1))
			{
				// System.out.println("connecting with the open left site");
				grid.union( gridCoordinates(row , col), gridCoordinates(row , col - 1) );
			}		
		}
		if (col < n) 
		{
			if( isOpen(row, col + 1))
			{
				// System.out.println("connecting with the open right site");
				grid.union( gridCoordinates(row , col), gridCoordinates(row, col + 1) );
			}
		}
	}

	// private void printMatrix()
	// {
	// 	for(int i = 0; i < n*n + 2 ; i++)
	// 	{
	// 		if(i % n == 0)
	// 			System.out.println();
	// 		System.out.print(openSite[i] + "\t");
	// 	}
	// }

	public boolean isOpen(int row, int col)  // is site (row, col) open?
	{
		int sitePositionIn1D = gridCoordinates(row, col);
		return (  openSite[sitePositionIn1D] );
	}

	public boolean isFull(int row, int col)  // is site (row, col) full?
	{
		// return if the site is in the union of the top row.
		if(isOpen(row, col))
		{
			int sitePositionIn1D = gridCoordinates(row, col);
			// full if connected to top virtual site.
			return(grid.connected(0, sitePositionIn1D));
		}
		else
			return false;
	}
	public int numberOfOpenSites()       // number of open sites
	{
		return nSitesOpen;
	}
	public boolean percolates()              // does the system percolate?
	{
		return(grid.connected(0, n*n + 1));
	}

	public static void main(String[] args)   // test client (optional)
	{
		Percolation pc = new Percolation(3);
		pc.open(1,1);
		pc.open(2,1);
		pc.open(3,1);
		System.out.println("Checking if the site is connected.");
		System.out.println("Is (1,1) full => " + pc.isFull(1,1));
		System.out.println("Is (2,1) full => " + pc.isFull(2,1));
		System.out.println("Is (2,2) full => " + pc.isFull(2,2));
		System.out.println("Is (2,2) full => " + pc.isFull(3,1));
		System.out.println("7, 10 connected =>" + pc.grid.connected(7, 10));
		System.out.println("7, 4 connected =>" + pc.grid.connected(7, 4));
		System.out.println("4, 1 connected =>" + pc.grid.connected(4, 1));
		System.out.println("1, 0 connected =>" + pc.grid.connected(1, 0));
	}
}