## Generics
Helps design single implementaion for **different datatypes**.

## Iterables

Allows the client to iterate through the stuff in the collection without knowing its inside design(array, linkedList, etc)

## Deque

A doubly-ended queue.

Q. How to remove the last element from the deque?
A. Doubly linked list

Added check :
1. if the item to be added is null;
2. if size is 0; make it first and last node

AddLast and removeFirst working fine but the not the `iterator` implementation

hasNext() returns if next possible, it shouldn't return ifEmpty()

1. addLast(), removeLast() working fine.

The combination of 
* addFirst
* removeLast

not working. Since, removeLast() works perfectly with addLast(), the problem should be with the addFirst(). Fixed by removing the if for the size = 1.

2. addFirst(), removeLast() working fine.

3. addLast(), removeFirst() working fine.

4. addFirst(), removeFirst() 

** Deque working as expected.

## Randomized Queue

Basic queue implemented first, and tested.
Generate random numbers as per the provided package.

But for constant worst time amortized operations for random number pop from queue, the array implementation will be better.

As suggested, the generic array type creation is not permitted in Java, so we will need to (sadly) resolve to the type casting. 