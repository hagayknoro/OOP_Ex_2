package Ex2_2;

import java.util.concurrent.Callable;

/**

 The Task class is a wrapper for a {@link Callable} that also has a priority associated with it.

 <p>It takes a {@link Callable} and a {@link TaskType} as input in its constructor. The {@link Callable} is the task that needs
 to be executed, and the {@link TaskType} is used to set the priority of the task. The class also has a default priority if no

 task type is provided. The class implements {@link java.util.concurrent.Callable} and {@link java.lang.Comparable}, allowing it to be

 executed and compared to other tasks based on priority.

 @author Author Yhonatan Fridman
 @author Author Hagay Knorovich

 @version 1.0

 @since Release
 */
public class Task<T> implements Callable<T>,Comparable <Task<T>>{
    private Callable<T> func;
    private Integer Priorty;

    /**

     Constructs a new Task with the given Callable and TaskType.
     @param callable the task that needs to be executed
     @param taskType the task type used to set the priority of the task
     */
    private Task(Callable<T> callable,TaskType taskType){
        this.func = callable;
        Priorty = taskType.getPriorityValue();
    }

    /**

     Constructs a new Task with the given Callable with default priority
     @param callable the task that needs to be executed
     */
    private Task(Callable<T> callable){
        this.func = callable;
        Priorty=3;
    }

    /**
     * Factory method that creates a Task with the given callable and task type.
     *
     * @param callable the task that needs to be executed
     * @param taskType the task type used to set the priority of the task
     * @return the created task
     */

    public static Task createTask(Callable callable,TaskType taskType){
        return new Task(callable, taskType);
    }

    /**
     * Factory method that creates a Task with the given callable and default priority
     *
     * @param callable the task that needs to be executed
     * @return the created task
     */

    public static Task createTask(Callable callable){
        return new Task(callable);
    }

    //Getters and Setters

    /**
     * Returns the {@link Callable} wrapped by this task
     *
     * @return the wrapped callable
     */
    public Callable<T> getFunc() {
        return func;
    }


    /**
     * Sets the {@link Callable} wrapped by this task
     *
     * @param func the callable to be wrapped by this task
     */
    public void setFunc(Callable<T> func) {
        this.func = func;
    }


    /**
     * Returns the priority of this task
     *
     * @return the priority of this task
     */
    public Integer getPriorty() {
        return Priorty;
    }

    /**
     * Sets the priority of this task
     *
     * @param priority the priority of this task
     */
    public void setPriorty(Integer priorty) {
        this.Priorty = priorty;
    }

    //Compare function

    /**
     * Compares this task to another task based on their priorities
     *
     * @param o the other task to compare to
     * @return a negative integer, zero, or a positive integer as this task is less than, equal to, or greater than the
     * specified task
     */
    @Override
    public int compareTo(Task<T> o) {
        int temp = o.Priorty - this.Priorty;
        return Integer.signum(temp);
    }

    //Implement call function


    /**
     * Invokes the wrapped {@link Callable}
     *
     * @return the result of the wrapped callable
     * @throws Exception if the wrapped callable throws an exception
     */
    @Override
    public T call() throws Exception {
        return this.func.call();
    }

    /**
     * Returns a string representation of this task
     *
     * @return a string representation of this task
     */
    @Override
    public String toString() {
        return "Task{" +
                "Callable=" + func +
                ", priority=" + Priorty +
                '}';
    }
}
