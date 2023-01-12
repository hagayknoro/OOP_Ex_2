package Ex2_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**

 The Ex2_1 class provides several methods for working with text files.

 <p>It contains three main functionalities:
 <ul>
 <li>createTextFiles: creates N text files with random number of lines within the given range.</li>
 <li>getNumOfLines: Given an array of file names, returns the total number of lines in those files.</li>
 <li>getNumOfLinesThreads: Given an array of file names, returns the total number of lines in those files using threads.</li>
 <li>getNumOfLinesThreadPool: Given an array of file names, returns the total number of lines in those files using thread pool.</li>
 </ul>
 @author Author Yhonatan Fridman
 @author Author Hagay Knorovich

 @version 1.0

 @since Release
 */
public class Ex2_1 {

    /**

     Creates n number of text files, with a random number of lines within a given bound
     @param n The number of files to be created
     @param seed The seed for the random number generator
     @param bound The upper bound for the number of lines generated for each file
     @return An array of filenames created
     */

    public static String[] createTextFiles(int n, int seed, int bound)
    {
        String[] files = new String[n];
        Random rand = new Random(seed);
        for (int i = 0; i < n; i++) {
            // Generate a random number of lines for the file
            int numLines = rand.nextInt(bound) + 1;
            try {
                // Create the file and open it in write mode
                FileWriter writer = new FileWriter(String.format("file_%d.txt", i));
                files[i] = ("file_"+i+".txt");
                // Write the lines to the file
                for (int j = 0; j < numLines; j++) {
                    writer.write("Hello world, I'm learning OOP course.\n");
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return files;
    }

    /**

     Given an array of file names, returns the total number of lines in those files
     @param fileNames The array of file names
     @return The total number of lines in the specified files
     */

    public static int getNumOfLines(String[] fileNames)
    {
        int totalLines = 0;
        for (String file : fileNames) {
            try {
                // Open the file
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                // Read each line and count it
                while ((line = reader.readLine()) != null) {
                    totalLines++;
                }
                reader.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return totalLines;
    }

    /**
     * Given an array of file names, returns the total number of lines in those files using threads.
     *
     * <p>For each file in the array, a new {@link LineCounterThread} is created and started, and the result of each thread is added
     * to the total number of lines.
     *
     * @param fileNames The array of file names
     * @return The total number of lines in the specified files
     */
    public int getNumOfLinesThreads(String[] fileNames){
        int totalLines = 0;
        for (String file : fileNames) {
            // Create a new thread to count the lines in the file
            LineCounterThread thread = new LineCounterThread(file);
            // Start the thread
            thread.start();
            try {
                // Wait for the thread to finish
                thread.join();
            } catch (InterruptedException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            // Add the number of lines counted by the thread to the total
            totalLines += thread.getNumLines();
        }
        return totalLines;
    }

    /**
     * Given an array of file names, returns the total number of lines in those files using a thread pool.
     *
     * <p>A fixed thread pool with one thread for each file is created, and a {@link LineCounterCallable} task is submitted for each file.
     * The results of the tasks are collected and added to the total number of lines.
     *
     * @param fileNames The array of file names
     * @return The total number of lines in the specified files
     */
    public int getNumOfLinesThreadPool(String[] fileNames)
    {
        int amountOfCours = Runtime.getRuntime().availableProcessors() - 1;
        int totalLines = 0;
        // Create a thread pool with one thread for each file
        ExecutorService executor = Executors.newFixedThreadPool(amountOfCours);
        // Create a list to store the futures that are returned by the threads
        List<Future<Integer>> futures = new ArrayList<>();
        // Submit a task for each file
        for (String file : fileNames) {
            LineCounterCallable task = new LineCounterCallable(file);
            Future<Integer> future = executor.submit(task);
            futures.add(future);
        }
        // Wait for all the tasks to complete and add their results to the total
        for (Future<Integer> future : futures) {
            try {
                totalLines += future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        // Shutdown the thread pool
        executor.shutdown();
        return totalLines;
    }
}



