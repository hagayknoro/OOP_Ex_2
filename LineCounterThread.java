import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LineCounterThread extends Thread {


    private String file;
    private int numLines;

    public LineCounterThread(String file) {
        this.file = file;
    }

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

    public int getNumLines() {
        return numLines;
    }
}