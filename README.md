## The First Assignment from Coursera on Algo - 1

Up until now, I still dont know what to do except that we have to find the 

> the value of the percolation threshold via Monte Carlo simulation

Quite cryptic. Good thing that Environemnt seems to be set after my last failed attempt to conquer this assignment.

## Percolation problem

This is an interesting problem tot solve because there exists no mathematical solution to derive the probaility of percolation `p*` for `n-by-n grid`. 
	
	p = probability of open sites
	thus, (1 - p) = probability of blocked sites 

	p* = probabiilty of open sites when the system almost **always percolates**. This value is 0.593

The problem of percolation is useful to estimate if the material is porous enough for the water to trickle down or if the material is conductive enough to conduct electricity.


## Modelling of the problem
The key to solving any problem efficiently is by modelling it correctly.

1. `n-by-n` grid array.
2. `1` means open site.
3. `0` means blocked site.
4. Full site is site connected to the top row through neighbouring sites.(up, down, right, left)

**System is said to percolate if there exists a full site in the bottom row**

## Using the jar file
This seems to complicated. While it does handle the implementation of WeightedUF, it still dont know how to does its constructor(that instantiates a 1D array and use it for my 2D array grid). Maybe, converting the index position from grid to get the 1D values raning from 1-n2 for n-by-n grid.

Lets Use the API of the jarfile.

## The approach

Introduce 2 virtual sites, one each for top and bottom. The main is now to simply find **if the 2 virtual sites are connected**. If yes, the system percolates.


Grid has `n*n + 2` disconnected sites. But this is a 1D array. 
We need a function to convert 2D coordinates to 1D indices. This is called `gridCoordinates(int row, int col)`.

Now lets see if all our functions sing well with the new 1D DS.
Done.

Q. But how to deal wit the open closed site problem.
A. Maintain a 1D array that stores 0, 1 if open or closed, respectively.

The only state changing operation is the `open(row, col)` function, so we do a `union` operation after every state change by the `open` operation.

Initially, perform the following union operatons:
1. Sites with `row = 0`, union with the top virtual site.
2. Sites with `row = n-1`, union with the bottom virtual site.

Now, check for the neighbours and perform the respective unions.

**Debuged and solved at 93% accuracy.**