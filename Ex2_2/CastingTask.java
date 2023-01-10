package Ex2_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CastingTask<T> extends FutureTask<T> implements Comparable<CastingTask<T>> {
    private Task<T> tempTask;


    public CastingTask(Task<T> task) {
        super(task);
        this.tempTask = task;
    }

    public Task<T> getTempTask() {
        return tempTask;
    }

    public void setTempTask(Task<T> tempTask) {
        this.tempTask = tempTask;
    }

    @Override
    public int compareTo(CastingTask o) {
        return tempTask.compareTo(o.getTempTask());
    }

//    @Override
//    public String toString() {
//        return super.toString() + ("Task: "+this.tempTask);
//    }
}
