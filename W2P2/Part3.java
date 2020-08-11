/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class Part3 {
    public int findStopCodon(String dna, int startIndex,  String stopCodon) {
    int currIndex = dna.indexOf(stopCodon, startIndex + 3);
    while (currIndex != -1) {
        if ((currIndex - startIndex) % 3 == 0) {
            return currIndex;
        } else {
            currIndex = dna.indexOf(stopCodon, currIndex + 1);
        }
    }
    return -1;
    }
    
    public String findGene(String dna, int where)
    {   
        //dna = dna.toUpperCase();
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) return "";
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if (taaIndex == -1 ||
            (tgaIndex != -1 && tgaIndex < taaIndex)) {
             minIndex = tgaIndex;
        }
        else {
            minIndex = taaIndex;
        }
        
        if (minIndex == -1 || 
            (tagIndex != -1 && tagIndex < minIndex)) {
             minIndex = tagIndex;
        }
        
        if (minIndex == -1) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void printAllGenes(String dna) {
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) break;
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    public int countGenes(String dna) {
        int numGenes = 0;
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) break;
            numGenes++;
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return numGenes;
    }
    
    public void testCountGenes() {
        String gene = "ATGATCATAAGAAGATAATAGAGGGCCATGTAAATGDKDTAAJATGDKFJELTGA";
        System.out.println(countGenes(gene));
    }
}

