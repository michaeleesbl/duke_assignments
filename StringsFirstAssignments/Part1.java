import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) return "";
        int stopIndex = dna.indexOf("TAA", startIndex + 3);
        if (stopIndex == -1) return "";
        if ((stopIndex - startIndex) % 3 == 0) {
            return dna.substring(startIndex, stopIndex + 3);
        }
        return "";
    }
    
    public void testSimpleGene() {
        String dna1 = "DFLKDJSLIENLXTAA";
        String dna2 = "DLSATGDLKFJSLT";
        String dna3 = "FKUHEFDKJS";
        String dna4 = "ATGAJJKAATAADFLDS";
        String dna5 = "ATGAJJKAFATAADFLDS";
        String[] dnas = new String[] {dna1, dna2, dna3, dna4, dna5};
        for (String dna : dnas) {
            System.out.println(dna);
            String dnaString = findSimpleGene(dna);
            System.out.println(dnaString);
        }
    }
}
