/**

 CustomExecutor class is a thread pool executor that prioritizes the order of tasks based on their priority.
 It has a priority queue that holds the tasks and a int array that keeps track of the priorities.
 @author Author Yhonatan Fridman
 @author Author Hagay Knorovich
 @version 1.0
 */

package Ex2_2;

import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {

    /**
     * Priorties is an int array used to keep track of the number of tasks
     * that have been assigned a certain priority.
     */
    private int [] Priorties = new int [10];


    /**
     * Creates a new CustomExecutor instance with the specified number of threads
     * and a default task queue.
     */

    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2,
                Runtime.getRuntime().availableProcessors() - 1,
                300, TimeUnit.MILLISECONDS,new PriorityBlockingQueue<>()) ;
    }

    /**
     * Submits a task to the executor and increments the priority count.
     *
     * @param task the task that needs to be executed
     * @return Future<T> representing the task
     * @throws NullPointerException if task or task.func is null
     */
    private  <T> Future<T> submitTask
            (Task<T> task) {
        this.Priorties[task.getPriorty()-1] += 1;
        if (task == null|| task.getFunc() == null)
        {
            throw new NullPointerException();
        }
        RunnableFuture <T> TaskTemp = new CastingTask<>(task);
        execute(TaskTemp);
        return TaskTemp;
    }

    /**
     * Submits a task to the executor with the default priority.
     *
     * @param task the task that needs to be executed
     * @return Future<T> representing the task
     */
    public <T> Future<T> submit
            (Callable <T> task){
        Task<T> newTask = Task.createTask(task);
        return submitTask(newTask);
    }

    /**
     * Submits a task to the executor with the specified priority.
     *
     * @param task the task that needs to be executed
     * @param taskType the taskType which represents the priority of the task
     * @return Future<T> representing the task
     */

    public <T> Future<T> submit
            (Callable<T> task, TaskType taskType){
        Task<T> newTask = Task.createTask(task,taskType);
        return submitTask(newTask);
    }

    /**
     * Returns the current maximum priority of a task in the queue.
     *
     * @return the maximum priority of a task
     */

    public int getCurrentMax(){
        for (int i = 1; i <= 10; i++)
        {
            if (Priorties[i]>0) {
                return i;
            }
        }
        return 0;
    }


    /**
     * Gracefully terminates the executor service.
     */
    public void gracefullyTerminate() {
        super.shutdown();
    }

    /**

     This method is called before a task is executed and updates the priority count.
     @param t the thread that will run the task
     @param r the task to be executed
     */
    protected void beforeExecute(Thread t, Runnable r) {
        int priority = getCurrentMax();
        if (1 <= priority && priority <= 10) {
            Priorties[priority - 1]--;
        }
    }
}

