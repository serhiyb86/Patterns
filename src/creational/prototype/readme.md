The Prototype design pattern is used to specify the kinds of objects to create using a prototypical instance, and create new objects
by copying this prototype.
In Java, there are certain ways to copy an object in order to create a new one. One way to achieve this is using the Cloneable
interface. Java provides the clone method, which an object inherits from the Object class. You need to implement the
Cloneable interface and override this `clone`method according to your needs.