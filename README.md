# OOP_Ex_2   
### The project were written by:
- [Yehonatan Friedman](https://github.com/YehonatanFr)
- [Hagay Knorovich](https://github.com/hagayknoro)      

Ex2 in OOP course. The Ex have two parts.    
# Part 1   
In This part we want to create files and count the lines in multiple files and measure times in three different methods.     
## Thread & ThreadPool    
### Thread    
A thread is a path of execution within a process. A process can contain multiple threads.    
A thread is also known as lightweight process. The idea is to achieve parallelism by dividing a process into multiple threads. For example, in a browser, multiple tabs can be different threads. MS Word uses multiple threads: one thread to format the text, another thread to process inputs, etc. More advantages of multithreading are discussed below.    
The primary difference is that threads within the same process run in a shared memory space, while processes run in separate memory spaces.
Threads are not independent of one another like processes are, and as a result threads share with other threads their code section, data section, and OS resources (like open files and signals). But, like process, a thread has its own program counter (PC), register set, and stack space.    
### Advantages of Thread over Process    
1. Responsiveness: If the process is divided into multiple threads, if one thread completes its execution, then its output can be immediately returned.   
2. Faster context switch: Context switch time between threads is lower compared to process context switch. Process context switching requires more overhead from the CPU.    
3. Effective utilization of multiprocessor system: If we have multiple threads in a single process, then we can schedule multiple threads on multiple processor. This will make process execution faster.     
4. Resource sharing: Resources like code, data, and files can be shared among all threads within a process.
Note: stack and registers can’t be shared among the threads. Each thread has its own stack and registers.    
5. Communication: Communication between multiple threads is easier, as the threads shares common address space. while in process we have to follow some specific communication technique for communication between two process.     
6. Enhanced throughput of the system: If a process is divided into multiple threads, and each thread function is considered as one job, then the number of jobs completed per unit of time is increased, thus increasing the throughput of the system.
### ThreadPool    
Background

Server Programs such as database and web servers repeatedly execute requests from multiple clients and these are oriented around processing a large number of short tasks. An approach for building a server application would be to create a new thread each time a request arrives and service this new request in the newly created thread. While this approach seems simple to implement, it has significant disadvantages. A server that creates a new thread for every request would spend more time and consume more system resources in creating and destroying threads than processing actual requests.

Since active threads consume system resources, a JVM creating too many threads at the same time can cause the system to run out of memory. This necessitates the need to limit the number of threads being created.    
A thread pool reuses previously created threads to execute current tasks and offers a solution to the problem of thread cycle overhead and resource thrashing. Since the thread is already existing when the request arrives, the delay introduced by thread creation is eliminated, making the application more responsive.

Java provides the Executor framework which is centered around the Executor interface, its sub-interface –ExecutorService and the class-ThreadPoolExecutor, which implements both of these interfaces. By using the executor, one only has to implement the Runnable objects and send them to the executor to execute.
They allow you to take advantage of threading, but focus on the tasks that you want the thread to perform, instead of thread mechanics.
To use thread pools, we first create a object of ExecutorService and pass a set of tasks to it. ThreadPoolExecutor class allows to set the core and maximum pool size.The runnables that are run by a particular thread are executed sequentially.

## Method 1   
Method 1 implement in `getNumOfLines` function.    
In this function we move on all the files and we count the lines of each file.   
## Method 2    
This method is implement by Thread.    
Meaning, we write a `run` function (in `LineCounterThread` Class), and in this function we count the lines of file.    
In the main program we move on all the files in loop, and each file we run thread that his job is to count the lines, and than we merge together all the result.   
Fot this method we create a class name `LineCounterThread`, and we implement `run` function.   
## Methd 3    
This method we use ThreadPool.    
We implements `call` function in class we create. In the main we create ThreaPool for count lines of multiple files in parallel.     
For this method we create `LineCounterCallable` class, and implement `call` function.     
### UML Diagram For visual explantion.       
![project_diagram](https://user-images.githubusercontent.com/118724971/211522147-8fd58dde-8589-4b13-a178-d5612d56f049.png)

## Measure Times     
For deep conclusion we measure time three times, in first time we create 100 files, second time we create 500 files and in the three time we create 1000 files.    
### 100 Files    
<img width="269" alt="image" src="https://user-images.githubusercontent.com/118724971/211595691-758af1ed-37f1-4515-be27-6c535a74c0f4.png">   

### 500 Files     
<img width="265" alt="image" src="https://user-images.githubusercontent.com/118724971/211595929-828fa0bf-a329-4e54-aa72-97e9b02a5444.png">       

### 1000 Files     
<img width="269" alt="image" src="https://user-images.githubusercontent.com/118724971/211596325-7333a927-e362-4c74-9223-c7407d7bde4d.png">     

## Conclusion     


# Part 2    
In this part we use in Design Pattern Factory.   
## Factory    
Factory pattern is one of the most used design patterns in Java. This type of design pattern comes under creational pattern as this pattern provides one of the best ways to create an object.     
In Factory pattern, we create object without exposing the creation logic to the client and refer to newly created object using a common interface.    

The factory design pattern is used when we have a superclass with multiple sub-classes and based on input, we need to return one of the sub-class. This pattern takes out the responsibility of the instantiation of a class from the client program to the factory class. Let’s first learn how to implement a factory design pattern in java and then we will look into factory pattern advantages. We will see some of the factory design pattern usage in JDK. Note that this pattern is also known as Factory Method Design Pattern.


## OverView    
The Java platform includes a package of concurrency utilities. These are classes that are designed to
be used as building blocks in building concurrent classes and applications.
Handling the design complexity for advanced use-cases demands developers to extend the
functionality of these built-in concurrency utilities.    

According to Javadoc, a thread is a thread of execution in a program. The Java Virtual Machine (JVM)
allows a Java application to have multiple threads of execution running concurrently. The Thread class
supports the creation of platform threads that are typically mapped 1:1 to kernel threads, scheduled
by the operating system.
The JVM implements platform threads as wrappers around operating system (OS) threads and will
usually have a large stack and other resources that are maintained by the operating system. The
Thread class defines 6 constructors that declare a Runnable as parameter.
Runnable is an interface representing an operation that does not return a value. This operation may
be executed in a separate thread, i.e., asynchronously using the run() method.     

The Java Virtual machine (JVM) schedules threads using a preemptive, priority-based policy.
Every thread has a priority ʹ Threads with higher priority are executed in preference to threads with
lower priority. When code running in a thread creates a new Thread object, the new thread has its
initial priority set automatically equal to the priority of the creating thread.
If a thread was created using a different ThreadGroup, the priority of the newly created thread is the
smaller of priority of the thread creating it and the maximum permitted priority of the thread group.     
If a thread with a higher priority than the currently running thread enters the RUNNABLE state, the
scheduler preempts the executing thread schedules the thread with the higher priority to run.
The Scheduler may also invoke a different thread to run if the currently running thread changes state
from RUNNABLE to a different state such as BLOCKED, WAITING or TERMINATED.       

### UML Diagram For visual explantion.  
![Diagaram2](https://user-images.githubusercontent.com/118724971/211613449-96952b63-e01a-49f2-8a51-47607f615ab8.png)   


Hope you enjoyed :blush:


