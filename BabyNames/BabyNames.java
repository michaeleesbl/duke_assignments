import edu.duke.*;
import org.apache.commons.csv.*;
/**
 * Write a description of BabyNames here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BabyNames {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) + 
                               " Gender " + rec.get(1) +
                               " Num Born " + rec.get(2));
            }
        }
    }
    
    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalGirlsNames = 0;
        int totalBoysNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            System.out.println(rec.get(1));
            if(rec.get(1).equals("F")){
                totalGirlsNames++;
            } else {
                totalBoysNames++;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("total girls names = " + totalGirlsNames);
        System.out.println("total boys names = " + totalBoysNames);
        System.out.println("total names = " + (totalGirlsNames + totalBoysNames));
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    
    public int getRank(int year, String name, String gender) {
        int currentRank = 1;
        String fileName = "us_babynames\\us_babynames_by_year\\yob" + year + ".csv";
        boolean nameMatch = false;
        //System.out.println(fileName);
        FileResource fr = new FileResource(fileName);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                //System.out.println(rec.get(0) + " " + rec.get(1) + " " + rec.get(2));
                if (rec.get(0).equals(name)) {
                    nameMatch = true;
                    break;
                }
                currentRank++;
            }
        }
        //System.out.println(currentRank);
        if (nameMatch) {
            return currentRank;
        } else {
            return -1;
        }
    }

    public void testGetRank() {
        System.out.println(getRank(1880, "Michael", "M"));
    }
    
    public String getName(int year, int rank, String gender) {
        int currentRank = 1;

        String fileName = "us_babynames\\us_babynames_by_year\\yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (currentRank == rank) return rec.get(0);
                currentRank++;
            }
        }
        return "";
    }
}
