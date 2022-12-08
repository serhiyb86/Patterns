The intent of the Decorator Design Pattern is to attach additional responsibilities to an object dynamically. Decorators provide a
flexible alternative to sub-classing for extending functionality.
The Decorator Pattern is used to extend the functionality of an object dynamically without having to change the original class
source or using inheritance. This is accomplished by creating an object wrapper referred to as a Decorator around the actual
object.
The Decorator object is designed to have the same interface as the underlying object. This allows a client object to interact
with the Decorator object in exactly the same manner as it would with the underlying actual object. The Decorator object
contains a reference to the actual object.

The Key is object decorator stores the same object (implemented by interface)