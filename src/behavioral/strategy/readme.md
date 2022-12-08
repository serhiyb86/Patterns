The Strategy Design Pattern defines a family of algorithms, encapsulating each one, and making them interchangeable. Strategy
lets the algorithm vary independently from the clients that use it.
The Strategy pattern is useful when there is a set of related algorithms and a client object needs to be able to dynamically pick
and choose an algorithm from this set that suits its current need. The Strategy pattern suggests keeping the implementation of
each of the algorithms in a separate class. Each such algorithm encapsulated in a separate class is referred to as a strategy.
An object that uses a Strategy object is often referred to as a context object.

• Many related classes differ only in their behavior. Strategies provide a way to configure a class with one of many behaviors.