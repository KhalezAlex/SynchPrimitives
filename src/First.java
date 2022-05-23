import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class First {
    public static void main(String[] args) throws IOException {
        String path = "First.txt";
        BufferedWriter bWr = new BufferedWriter(new FileWriter(path));
        for (int i = 0; i < 101; i++) {
            bWr.write(i + "\n");
            bWr.flush();
        }
        bWr.close();
    }
}
