package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**

 The LineCounterThread class is a {@link Thread} that counts the number of lines in a specified file.

 <p>It takes a filename as input in its constructor, and when its {@link #run()} method is invoked, it reads the file and stores the
 total number of lines in the file. The number of lines can be obtained by calling the {@link #getNumLines()} method.

 @author Author Yhonatan Fridman
 @author Author Hagay Knorovich

 @version 1.0

 @since Release
 */
public class LineCounterThread extends Thread {


    private String file;
    private int numLines;

    /**

     Constructs a new LineCounterThread with the given file name
     @param file the name of the file that this LineCounterThread will count the lines of
     */
    public LineCounterThread(String file) {
        this.file = file;
    }

    /**

     Run the thread, counting the lines in the file.
     */
    public void run() {
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
    }

    /**

     Returns the number of lines that have been counted by this thread.
     @return the number of lines counted by this thread.
     */

    public int getNumLines() {
        return numLines;
    }
}