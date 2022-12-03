Using the Builder pattern, the process of constructing such an object can be designed more effectively. The Builder pattern
suggests moving the construction logic out of the object class to a separate class referred to as a builder class. There can be more
than one such builder classes, each with different implementations for the series of steps to construct the object. Each builder
implementation results in a different representation of the object.

There are two types of builder pattern
1. With completed parameters for built. Another object is going to build product.
2. Each field will be added independently, except mandatory
