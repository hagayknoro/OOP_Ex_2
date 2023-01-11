package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

/**

 The LineCounterCallable class is a {@link Callable} that counts the number of lines in a specified file.

 <p>It takes a filename as input in its constructor, and when its {@link #call()} method is invoked, it reads the file and returns
 the total number of lines in the file.

 @author Author Yhonatan Fridman
 @author Author Hagay Knorovich

 @version 1.0

 @since Release
 */

public class LineCounterCallable  implements Callable<Integer> {

    private String file;

    /**

     Constructs a new LineCounterCallable with the given file name
     @param file the name of the file that this LineCounterCallable will count the lines of
     */
    public LineCounterCallable(String file) {
        this.file = file;
    }

    /**

     Returns the number of lines in the specified file
     @return the number of lines in the file
     */

    public Integer call() {
        int numLines = 0;
        try {
            // Open the file
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            // Read each line and count it
            while ((line = reader.readLine()) != null) {
                numLines++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return numLines;
    }
}
