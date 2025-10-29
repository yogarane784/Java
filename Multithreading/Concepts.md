- A process is an independent program in execution.Each process has its own memory space, stack, heap, and system resources.
- Thread is a lightweight unit of execution managed by JVM. Runs independently within the same process.
    - Main Thread id the first thread started by JVM to execute main() method.
    - Background service threads (e.g., GC). JVM exits when only daemon threads remain. Created by thread.setDaemon(true).
   
- Time slicing is how a CPU divides its time among multiple threads/processes in a multitasking OS.
    - The CPU runs one thread for a short duration (a time slice, e.g., 10ms), then switches to another.
    - This happens so fast that it appears multiple programs are running at the same time.
    - It’s part of preemptive multitasking, managed by the OS scheduler.
- Multithreading vs Parallelism
    - Multithreading	Multiple threads run concurrently within a process, possibly interleaved on a single core (time-slicing)
    - Parallelism	Actual simultaneous execution of multiple tasks on multiple CPU cores
    - Example	Web server handling 1,000 requests (multithreading); data processing split across 8 cores (parallelism).
- Thread vs Main thread vs Daemon Thread
- Fixed Thread pool
    - Fixed number of threads. Extra tasks wait in queue.
    - Good for CPU-bound tasks, predictable load.
- Cached Thread Pool
    - Creates new threads as needed, reuses idle ones. No queue; unbounded threads.
    - Good for Short-lived I/O-bound tasks, bursty workloads.
    -   
-  vs Synchronous Pool
5. IO Bound vs CPU Bound, when to use which Pool
6. How to decide optimal number of threads in pool ?
    - For CPU-bound: threads = cores
    - For IO-bound:  threads = cores * (1 + waitTime / computeTime)
7. Why does memory increase in multi threaded environment ?
8. Importance of Heap Dump/ Profiling?
9. Monitoring best practices
10. Thread safe collections
  - Concurrent HashMap vs Collections.synchronizedMap(new HashMap<>())
  - CopyOnWriteArrayList : is a thread-safe Java list that is optimized for read-heavy applications.
       - It achieves thread safety by creating a new copy of its internal array for every modification (like add or remove),
       - while reads operate on a snapshot of the original array, avoiding the need for synchronization during reads.
       - The main drawback is that it is inefficient for frequent write operations due to the overhead of creating new array copies
  - ConcurrentHashSet
  - ConcurrentSkipListMap/ ConcurrentSkipListSet : Thread-safe sorted maps/sets (non-blocking).Use when order + concurrency both needed.
  - ConcurrentLinkedQueue / BlockingQueue (ArrayBlockingQueue, LinkedBlockingQueue) : For thread-safe producer-consumer patterns, Used in executors and messaging.
  - others ...

