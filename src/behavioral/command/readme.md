Much easier to understand: We have multi thread application. We describe some some work
with interface and implement method run. Then it simple just to execute 
one method in each object.

The above class is used to create n threads (worker threads). Each worker thread will wait for a job in a queue and then execute
the job and will go back to waiting state. The class contains a job queue; when a new job will be added into the queue, a worker
thread from the pool will execute the job.

We have set different type of jobs, but by using the Command Design Pattern, we decouple the job from the invoker thread.
The thread will execute any kind of object that implements the Job interface. The different command objects encapsulate the
different object and executed the requested operations on these objects.