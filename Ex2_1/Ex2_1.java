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

public class Ex2_1 {

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

    public int getNumOfLinesThreadPool(String[] fileNames)
    {
        int totalLines = 0;
        // Create a thread pool with one thread for each file
        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);
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



