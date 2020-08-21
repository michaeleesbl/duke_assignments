import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

/**
 * Write a description of BabyNames here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BabyNames {
    public void printNames() {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
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
            if (rec.get(1).equals("F")) {
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

    public static int getRank(int year, String name, String gender) {
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

    public static void testGetRank() {
        System.out.println(getRank(1999, "John", "M"));
    }

    public String getName(int year, int rank, String gender) {
        int currentRank = 1;
        String name = "";
        boolean rankMatch = false;
        String fileName = "us_babynames\\us_babynames_by_year\\yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)) {
                if (currentRank == rank) {
                    name = rec.get(0);
                    rankMatch = true;
                    break;
                } else {
                    currentRank++;
                }
            }
        }
        System.out.println(name);
        if (rankMatch) return name;
        return "NO NAME";
    }

    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        String pronoun = "";
        if (gender.equals("F")) pronoun = " she ";
        pronoun = " he ";
        System.out.println(name + " born in " + year + " would be " + newName + " if " + pronoun + "was born in " + newYear + ".");
    }

    public static int yearOfHighestRank(String name, String gender) {
        int year = -1;
        int highestRank = 99999999;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String nameOfFile = f.getName();
            int yearOfFile = Integer.parseInt(nameOfFile.substring(3, 7));
            int rankInFile = getRank(yearOfFile, name, gender);
            if (rankInFile < 0) continue;
            if (rankInFile < highestRank) {
                highestRank = rankInFile;
                year = yearOfFile;
            }
        }
        //System.out.println(year);
        return year;
    }

    public double getAverageRank(String name, String gender) {
        int sumRanks = 0;
        int denominator = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String nameOfFile = f.getName();
            int yearOfFile = Integer.parseInt(nameOfFile.substring(3, 7));
            int rankInFile = getRank(yearOfFile, name, gender);
            if (rankInFile < 0) rankInFile = 0;
            sumRanks = sumRanks + rankInFile;
            denominator++;
        }
        double averageRank = (double) sumRanks / denominator;
        System.out.println(averageRank);
        return averageRank;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirthsRankedHigher = 0;
        return totalBirthsRankedHigher;
    }

    public static void main(String[] args) {
        String name = "Mich";
        String gender = "M";
        System.out.println(yearOfHighestRank(name, gender));
    }
}
