import edu.duke.*;
import org.apache.commons.csv.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public void tester()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
    }
    
    public String countryInfo(CSVParser parser, String country)
    {   
        for (CSVRecord record : parser)
        {
            // find record for which country is "country"
            String c = record.get("Country");
            if (c.contains(country)) 
            {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                return c + ": " + exports + ": " + value;
            }
        }
        return "NOT FOUND";
    }
    
    public void testCountryInfo(String country)
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String info = countryInfo(parser, country);
        System.out.println(info);
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        for (CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2))
            {
                System.out.println(record.get("Country"));
            }
        }
    }
    
        public void testListExportersTwoProducts(String exportItem1, String exportItem2)
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        listExportersTwoProducts(parser, exportItem1, exportItem2);
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem)
    {
        int numExporters = 0;
        for (CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) numExporters++;
        }
        return numExporters;
    }
    
    public void testNumOfExporters(String exportItem)
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println(numberOfExporters(parser, exportItem));
    }
    
    public void bigExporters(CSVParser parser, String amount) 
    {
        int lenAmount = amount.length();
        for (CSVRecord record : parser)
        {
            String value = record.get("Value (dollars)");
            if (value.length() > lenAmount)
            {
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }        
    }
}
