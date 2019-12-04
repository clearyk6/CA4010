import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;


public class CSVWriter {

    public static void usingBufferedWriter() throws IOException {
        String fileContent = "Hello";

        BufferedWriter writer = new BufferedWriter(new FileWriter("./master.csv/new.csv"));
        writer.write(fileContent);
        writer.close();
    }

    public static void main(String[] args) {

        System.out.println("Writing file..");
        String fileContent = "Overwrite";

        List<List<String>> rows = Arrays.asList(
                Arrays.asList("Jean", "author", "Java"),
                Arrays.asList("David", "editor", "Python"),
                Arrays.asList("Scott", "editor", "Node.js")
        );

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/Kevin Cleary/CASE4/CA4010/master.csv/new.csv"));
            writer.write(fileContent);
            writer.append("\n");
            writer.append("Name");
            writer.append(", ");
            writer.append("Role");
            writer.append(", ");
            writer.append("Topic");
            writer.append("\n");

            for (List<String> rowData : rows) {
                writer.append(String.join(", ", rowData));
                writer.append("\n");
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}