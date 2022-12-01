The Mediator Pattern defines an object that encapsulates how a set of objects interact. Mediator promotes loose coupling by
keeping objects from referring to each other explicitly, and it lets you vary their interaction independently.
Rather than interacting directly with each other, objects ask the Mediator to interact on their behalf which results in reusability
and loose coupling. It encapsulates the interaction between the objects and makes them independent from each other. This allows
them to vary their interaction with other objects in a totally different way by implementing a different mediator. The Mediator
helps to reduce the complexity of the classes. Each object no longer has to know in detail about how to interact with the other
objects. The coupling between objects goes from tight and brittle to loose and agile.