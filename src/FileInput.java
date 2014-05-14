import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileInput {
    private BufferedReader in;

    public void openFile(String string) {
        try {
            in = new BufferedReader(new FileReader(string));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            System.out.println("File didn't opened.");
            return null;
        }
    }

    public void closeFile() {
        try {
            in.close();
        } catch (IOException e) {
            System.out.println("File didn't opened.");
        }
    }
}
