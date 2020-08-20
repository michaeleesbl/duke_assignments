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
            //coldestSoFar = coldestOfTwoRecords(coldestSoFar, currentRow);
            if (coldestSoFar == null) 
            {
                coldestSoFar = currentRow;
            }
            else
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp < coldestTemp && currentTemp != -9999) coldestSoFar = currentRow;
            }
        }
        return coldestSoFar;
    }
    
    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord coldestHour = coldestHourInFile(parser);
        String time = coldestHour.get("DateUTC");
        String temp = coldestHour.get("TemperatureF");
        System.out.println(time + " " + temp);
    }
    
    public CSVRecord coldestOfTwoRecords(CSVRecord currentRecord, CSVRecord currentRow)
    {
        if (currentRecord == null) 
            {
                currentRecord = currentRow;
            }
            else
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double currentRecordTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
                if (currentTemp > currentRecordTemp) currentRecord = currentRow;
            }
        return currentRecord;
    }
    public String fileWithColdestTemperature() 
    {
        CSVRecord coldestSoFar = null;
        String fileName = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord recordWithColdestTemp = coldestHourInFile(parser);
            if (coldestSoFar == null) 
            {
                coldestSoFar = recordWithColdestTemp;
            }
            else
            {
                double currentTemp = Double.parseDouble(recordWithColdestTemp.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
                if (currentTemp < coldestTemp) 
                {
                    coldestSoFar = recordWithColdestTemp;
                    fileName = f.getName();
                }
            }
        }
        return fileName;
    }
    
    public void testFileWithColdestTemperature()
    {
        System.out.println(fileWithColdestTemperature());
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord lowestSoFar = null;
        for (CSVRecord currentRow : parser)
        {
            if (lowestSoFar == null) 
            {
                lowestSoFar = currentRow;
            }
 
            {
                if (!currentRow.get("Humidity").equals("N/A")) {
                    double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                    double lowestHumidity = Double.parseDouble(lowestSoFar.get("Humidity"));
                    if (currentHumidity < lowestHumidity && currentHumidity != -9999  && currentHumidity != 9999) lowestSoFar = currentRow;
                }
            }
        }
        return lowestSoFar;    
    }
    
    public void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord humidityRec = lowestHumidityInFile(parser);
        System.out.println(humidityRec.get("Humidity"));
        System.out.println(humidityRec.get("DateUTC"));
    }
    
    public CSVRecord lowestHumidityInManyFiles()
    {
        CSVRecord lowestHumiditySoFar = null;
        DirectoryResource dr = new DirectoryResource();
        
        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
            if (lowestHumiditySoFar == null) {
                lowestHumiditySoFar = currentRow; 
            } else {
                double currentHumidity = Double.parseDouble(currentRow.get("Humidity"));
                double lowestHumidity = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
                if (lowestHumidity > currentHumidity) lowestHumiditySoFar = currentRow;
            }
        }
        return lowestHumiditySoFar;
    }
    public void testLowestHumidityInManyFiles() {
        CSVRecord rec = lowestHumidityInManyFiles();
        System.out.println(rec.get("Humidity"));
        System.out.println(rec.get("DateUTC"));
    }
    public double averageTemperatureInFile(CSVParser parser)
    {
        double totalTemp = 0.0;
        double numRecords = 0.0;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (currentTemp != 9999 && currentTemp != -9999) currentTemp = 0;
            totalTemp = totalTemp + currentTemp;
            numRecords = numRecords + 1.0;
        }
        double avgTemp = totalTemp / numRecords;
        return avgTemp;
    }
    
    public void testAverageTemperatureInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(averageTemperatureInFile(parser));
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value)
    {
        double totalTemp = 0.0;
        int numRecords = 0;
        for (CSVRecord currentRow : parser) {
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (Integer.parseInt(currentRow.get("Humidity")) > value)
            {
                if (currentTemp != 9999 && currentTemp != -9999) currentTemp = 0;
                totalTemp = totalTemp + currentTemp;
                numRecords++;
            }
        }
        return (double) totalTemp / numRecords;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile(int value)
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(averageTemperatureWithHighHumidityInFile(parser, value));
    }
}
