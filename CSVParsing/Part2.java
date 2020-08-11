import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldestSoFar = null;
        for (CSVRecord currentRow : parser)
        {
            if (coldestSoFar == null) 
            {
                coldestSoFar = currentRow;
            }
            else
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp > coldestTemp) coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHour = coldestHourInFile(parser);
        String time = coldestHour.get("TimeEST");
        String temp = coldestHour.get("TemperatureF");
        System.out.println(time + " " + temp);
    }
}
