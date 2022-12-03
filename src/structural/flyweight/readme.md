The Flyweight Pattern is designed to control such kind of object creation and provides you with a basic caching mechanism. It
allows you to create one object per type (the type here differs by a property of that object), and if you ask for an object with the
same property (already created), it will return you the same object instead of creating a new one.

Consider an application scenario that involves creating a large number of objects that are unique only in terms of a few parameters.
In other words, these objects contain some intrinsic, invariant data that are common among all objects. This intrinsic data needs
to be created and maintained as part of every object that is being created. The overall creation and maintenance of a large group
of such objects can be very expensive in terms of memory-usage and performance. The Flyweight pattern can be used in such
scenarios to design a more efficient way of creating objects.
