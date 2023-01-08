import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class LineCounterCallable  implements Callable<Integer> {

    private String file;

    public LineCounterCallable(String file) {
        this.file = file;
    }

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
