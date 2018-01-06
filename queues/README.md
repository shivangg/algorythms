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

addLast(), removeLast() working fine.

The combination of 
* addFirst
* removeLast

not working. Since, removeLast() works perfectly with addLast(), the problem should be with the addFirst(). Fixed by removing the if for the size = 1.

addFirst, removeLast working fine.