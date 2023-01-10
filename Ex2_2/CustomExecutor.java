package Ex2_2;

import java.util.concurrent.*;

public class CustomExecutor extends ThreadPoolExecutor {

    private int [] Priorties = new int [10];

    public CustomExecutor() {
        super(Runtime.getRuntime().availableProcessors() / 2,
                Runtime.getRuntime().availableProcessors() - 1,
                300, TimeUnit.MILLISECONDS,new PriorityBlockingQueue<>()) ;
    }


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

    public <T> Future<T> submit
            (Callable <T> task){
        Task<T> newTask = Task.createTask(task);
        return submitTask(newTask);
    }

    public <T> Future<T> submit
            (Callable<T> task, TaskType taskType){
        Task<T> newTask = Task.createTask(task,taskType);
        return submitTask(newTask);
    }

    public int getCurrentMax(){
        for (int i = 1; i <= 10; i++)
        {
            if (Priorties[i]>0) {
                return i;
            }
        }
        return 0;
    }

    public void gracefullyTerminate() {
        super.shutdown();
    }

    protected void beforeExecute(Thread t, Runnable r) {
        int priority = getCurrentMax();
        if (1 <= priority && priority <= 10) {
            Priorties[priority - 1]--;
        }
    }


}

