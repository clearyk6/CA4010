import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {

    public static void main(String[] args) {

        String csvFile = "./master.csv/train.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        int lineCount = 0;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                if (lineCount == 0) {
                    String joinedString = String.join(", ", row);
                    System.out.println("Column names are: {" + joinedString + "}");

                }

                lineCount++;
                //System.out.println("Country [code= " + row[4] + " , name=" + row[5] + "]");

            }

            System.out.println("Processed " + lineCount + " lines.");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}