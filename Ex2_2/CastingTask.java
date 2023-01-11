
/**

 The CastingTask class is a generic class that extends the {@link FutureTask} class and implements the {@link Comparable} interface.

 It is used to store and compare {@link Task} objects.

 @param <T> The type of the result of the task.

 @author Author Yhonatan Fridman
 @author Author Hagay Knorovich

 @version 1.0

 @since Release
 */
package Ex2_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CastingTask<T> extends FutureTask<T> implements Comparable<CastingTask<T>> {
    private Task<T> tempTask;

    /**

     Constructs a new CastingTask with the given {@link Task}
     @param task the task that is to be wrapped by this CastingTask
     */
    public CastingTask(Task<T> task) {
        super(task);
        this.tempTask = task;
    }

    /**

     Returns the {@link Task} object wrapped by this CastingTask
     @return the task object
     */
    public Task<T> getTempTask() {
        return tempTask;
    }

    /**

     Sets the {@link Task} object wrapped by this CastingTask
     @param tempTask the task object to set
     */
    public void setTempTask(Task<T> tempTask) {
        this.tempTask = tempTask;
    }

    /**

     Compares this CastingTask to another one based on the underlying {@link Task}'s natural ordering.
     @param o the other CastingTask to compare this one to
     @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(CastingTask o) {
        return tempTask.compareTo(o.getTempTask());
    }

//    @Override
//    public String toString() {
//        return super.toString() + ("Task: "+this.tempTask);
//    }
}
