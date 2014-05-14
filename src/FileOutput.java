import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FileOutput {
    private PrintWriter out;

    public void openFile(String string) {
        try {
            out = new PrintWriter(string);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public void printf(String format, String address, String tasks) {
        out.printf(format, address, tasks);
    }

    public void closeFile() {
        out.close();
    }
}
