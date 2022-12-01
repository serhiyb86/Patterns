The Facade Pattern makes a complex interface easier to use, using a Facade class. The Facade Pattern provides a unified interface
to a set of interface in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use.

Clients communicate with the subsystem by sending requests to Facade, which forwards them to the appropriate subsystem
object(s).

Also, please note that, although the facade class has provided a simple interface to the complex subsystem, it has not encapsulated
the subsystem. A client can still access the low-level interfaces of the subsystem. So, a facade provides an extra layer, a simple
interface to the complex subsystem, but it does not completely hide the direct accessibility to the low-level interfaces of the
complex subsystem.

