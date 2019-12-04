import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import java.math.RoundingMode;
import java.text.DecimalFormat;


public class CSVManipulator {

    private static DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {

        String csvFile = "./master.csv/train.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        String headerString = "";

        String rowString = "";

        int lineCount = 1;

        try {

            br = new BufferedReader(new FileReader(csvFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Users/Kevin Cleary/CASE4/CA4010/master.csv/suicide_rate_trends.csv"));

            String currentCountry = "";

            int groupInterval = 12;
            float groupedPop = 0;
            float groupedSNo = 0;
            float groupedSRate = 0;

            float lastYearSRate = 0;
            float changeInSRate = 0;
            String trend = "Remain";
            String changeInSRateStr = "N/A";

            String[] rowOne = br.readLine().split(cvsSplitBy);
            headerString = String.join(", ", rowOne[0], rowOne[1], rowOne[4], rowOne[5], rowOne[6], "suicide_rate_trend");
            System.out.println("Column names are: {" + headerString + "}");
            writer.append(headerString);
            writer.append("\n");


            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] row = line.split(cvsSplitBy);

                groupedSNo += Float.valueOf(row[4]);
                groupedPop += Float.valueOf(row[5]);
                groupedSRate += Float.valueOf(row[6]);

                if ((lineCount % 12) == 0) {
                    System.out.println(currentCountry);
                    changeInSRate = groupedSRate - lastYearSRate;
                    if (currentCountry.equals(row[0])) {
                        if (changeInSRate == 0) {
                            trend = "Remain";
                        } else if (changeInSRate > 0) {
                            trend = "Increase";
                        } else {
                            trend = "Decrease";
                        }
                        changeInSRateStr = df.format(changeInSRate);
                    } else { currentCountry = row[0]; trend = "N/A"; changeInSRateStr = "N/A"; }
                    rowString = String.join(", ", row[0], row[1], String.valueOf(groupedSNo), String.valueOf(groupedPop), String.valueOf(df.format(groupedSRate)), trend, changeInSRateStr);
                    System.out.println("Row " + lineCount + " values are: {" + rowString + "}");
                    writer.append(rowString);
                    writer.append("\n");

                    lastYearSRate = groupedSRate;
                    groupedSNo = 0;
                    groupedPop = 0;
                    groupedSRate = 0;
                    changeInSRate = 0;
                }

                lineCount++;
            }

            System.out.println("Processed " + lineCount + " lines.");

            writer.flush();
            writer.close();

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