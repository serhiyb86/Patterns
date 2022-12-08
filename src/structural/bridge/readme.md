The Bridge Pattern’s intent is to decouple an abstraction from its implementation so that the two can vary independently. It puts
the abstraction and implementation into two different class hierarchies so that both can be extend independently.

You should use the Bridge Pattern when:
• You want to avoid a permanent binding between an abstraction and its implementation. This might be the case, for example,
when the implementation must be selected or switched at run-time.
• Both the abstractions and their implementations should be extensible by sub-classing. In this case, the Bridge pattern lets you
combine the different abstractions and implementations and extend them independently.

Divide for two hierarchies. And extend the class this way:
1. Abstraction - implementation layer, refinedAbstraction
2. Implementation - Different concrete implementation
Just link particular abstraction to particular implementation.
Like Italian pizza Carbonara you can build in Italian restraint