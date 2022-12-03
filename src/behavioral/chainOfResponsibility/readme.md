There are a link like field to another handler. If current object can not proccess then ask handler object from link.

This design pattern also decouples the user from the object that is serving the request; the user is not aware which object is actually
serving its request.

You can create different objects which process
different formats of data and chain them together. When a request comes to a single object, it will check whether it can process
and handle the specfic file format. If it can, it will process it; otherwise, it will forward it to the next object chained to it.