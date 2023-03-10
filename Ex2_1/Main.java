package Ex2_1;

import java.io.File;

public class Main {
    public static void main(String[] args){
        int n =1000;
        long starTime, endTime;
        float sec;
        //Create files
        String[] file = Ex2_1.createTextFiles(n, 2,400);

        //Time measure without thread
        starTime = System.currentTimeMillis();
        int numOfLines = Ex2_1.getNumOfLines(file);
        endTime = System.currentTimeMillis();
        sec = (endTime - starTime)/1000F;
        System.out.println("No Thread use: \nThe number of lines is " + numOfLines + " and the time it took "+ sec);

        //Time measure with thread
        Ex2_1 thread =  new Ex2_1();
        starTime = System.currentTimeMillis();
        int numOfLinesThreads = thread.getNumOfLinesThreads(file);
        endTime = System.currentTimeMillis();
        sec = (endTime - starTime)/1000F;
        System.out.println("Thread use: \nThe number of lines is " + numOfLinesThreads + " and the time it took "+ sec);

        //Time measure with thread-pool
        Ex2_1 thread_pool =  new Ex2_1();
        starTime = System.currentTimeMillis();
        int NumOfLinesThreadPool = thread_pool.getNumOfLinesThreadPool(file);
        endTime = System.currentTimeMillis();
        sec = (endTime - starTime)/1000F;
        System.out.println("Thread pool use: \nThe number of lines is " + NumOfLinesThreadPool + " and the time it took "+ sec);

        cleanFinder(n);
    }
    public static void cleanFinder(int fileNumber)
    {
      for (int i = 0 ; i < fileNumber; i++){
          String fileName = ("file_"+i+".txt");
          File file = new File(fileName);
          file.delete();
      }
    }
}
