import edu.duke.*;

/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex,  String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
    }

    public void testFindStopCodon() {
        String dna = "xxxyyyzzzTAGxxxyyyzzTAAxx";
        String dna2 = "xxxyyyzzzTAAxxxyyyzzTAAxx";
        int dex = findStopCodon(dna, 0 , "TAG");
        System.out.println(dex);
        int dex2 = findStopCodon(dna2, 0 , "TAA");
        System.out.println(dex2);
    }
    
    public String findGene(String dna, int where)
    {   
        //dna = dna.toUpperCase();
        if (where >= dna.length()) return "";
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
    
    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    
    public void test() {
        //      ATGv  TAAv  ATG   v  v  TGA  
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        testOn("AATGCTAACTAGCTGACTAAT");
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        int startIndex = 0;
        while (startIndex < dna.length() - 3) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) break;
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return sr;
    }
    
    public void testGetAllGenes() {
        StorageResource sr = getAllGenes("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        for (String gene : sr.data()) {
            System.out.println(gene);
        }
    }
    
    // Part2
    public float cgRatio(String dna)
    {   
        int cg = 0;
        int len = dna.length();
        char[] chars = dna.toCharArray();
        for (char ch : chars) {
            if (ch == 'C' || ch == 'G') cg++;
        }
        return (float) cg / len;
    }
    
    public int countCTG(String dna)
    {
        int ctgCount = 0;
        int currIndex = dna.indexOf("CTG", 0);
        while (currIndex != -1)
        {
            ctgCount++;
            currIndex = dna.indexOf("CTG", currIndex + 3);
        }
        return ctgCount;
    }
    
    public void testcgRatio()
    {
        String dna = "ATGCCATAG";
        System.out.println(cgRatio(dna));
    }
    
    public void testCountCTG()
    {
        //                ^^^      ^^^     ^^^
        String dna = "DSAFCTGDKFADLCTGDLKFSCTG";
        System.out.println(countCTG(dna));
    }
    
    // Part3
    public void processGenes(StorageResource sr)
    {
        int lengthLongestGene = -1;
        StorageResource longerThan9 = new StorageResource();
        StorageResource cgRatiogt35 = new StorageResource();
        for (String s : sr.data())
        {
            if (s.length() > 9) longerThan9.add(s);
            if (cgRatio(s) > (float) 0.35) cgRatiogt35.add(s);
            if (s.length() > lengthLongestGene) lengthLongestGene = s.length();
        }
        
        for (String s : longerThan9.data())
        {
            System.out.println(s);
        }
        System.out.println("Num of strings whose length gt 9 " + longerThan9.size());
        
        for (String s : cgRatiogt35.data())
        {
            System.out.println(s);
        }
        System.out.println("Num of strings whose cg ratio gt .35 " + cgRatiogt35.size());
        System.out.println("Length of longest gene " + lengthLongestGene);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        System.out.println(dna);
        int lt60 = 0;
        //printAllGenes(dna);
        StorageResource genes = getAllGenes(dna);
        /*for (String g : genes.data()) {
            System.out.println(g);
            if (g.length() > 60) lt60++;
        }*/
        processGenes(genes);
        System.out.println(lt60);
        //processGenes(genes);
        System.out.println(countCTG(dna));
    }
}
