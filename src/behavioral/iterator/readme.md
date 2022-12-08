The Iterator pattern lets you do all this. The key idea in this pattern is to take the responsibility for access and traversal out
of the list object and put it into an iterator object. The Iterator class defines an interface for accessing the list’s elements.
An iterator object is responsible for keeping track of the current element; that is, it knows which elements have been traversed
already.

Difference between Iterator and Enumeration:

The functionality of Enumeration and the Iterator are same. Using Enumeration you can only traverse and fetch the objects, where as using Iterator we can also add and remove the objects. So Iterator can be useful if you want
to manipulate the list and Enumeration is for read-only access. 