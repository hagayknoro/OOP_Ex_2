package Ex2_2;

import java.util.concurrent.Callable;

public class Task<T> implements Callable<T>,Comparable <Task<T>>{
    private Callable<T> func;
    private Integer Priorty;

    private Task(Callable<T> callable,TaskType taskType){
        this.func = callable;
        Priorty = taskType.getPriorityValue();
    }
    private Task(Callable<T> callable){
        this.func = callable;
        Priorty=3;
    }

    public static Task createTask(Callable callable,TaskType taskType){
        return new Task(callable, taskType);
    }

    public static Task createTask(Callable callable){
        return new Task(callable);
    }


    //Getters and Setters
    public Callable<T> getFunc() {
        return func;
    }

    public void setFunc(Callable<T> func) {
        this.func = func;
    }

    public Integer getPriorty() {
        return Priorty;
    }

    public void setPriorty(Integer priorty) {
        this.Priorty = priorty;
    }

    //Compare function
    @Override
    public int compareTo(Task<T> o) {
        int temp = o.Priorty - this.Priorty;
        return Integer.signum(temp);
    }

    //Implement call function
    @Override
    public T call() throws Exception {
        return this.func.call();
    }

    @Override
    public String toString() {
        return "Task{" +
                "Callable=" + func +
                ", priority=" + Priorty +
                '}';
    }
}
