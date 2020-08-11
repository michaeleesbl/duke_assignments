
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String stopCodon)
    {   
        String originalDna = dna;
        dna = dna.toUpperCase();
        
        startCodon = startCodon.toUpperCase();
        stopCodon = stopCodon.toUpperCase();
        int startIndex = dna.indexOf(startCodon);
        if (startIndex == -1) return "";
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        if (stopIndex == -1) return "";
        if ((stopIndex - startIndex) % 3 == 0) 
        {
            String substr = dna.substring(startIndex, stopIndex + 3);
            if (substr.equals(originalDna.substring(startIndex, stopIndex + 3))) return substr;
            return originalDna.substring(startIndex, stopIndex + 3);
        }
        return "";
    }
    
    public void testSimpleGene()
    {
        String dna1 = "DFLKDJSLIENLXTAA";
        String dna2 = "DLSATGDLKFJSLT";
        String dna3 = "FKUHEFDKJS";
        String dna4 = "ATGAJJKAATAADFLDS";
        String dna5 = "atgajjkaataadflds";
        String dna6 = "ATGAJJKAFATAADFLDS";
        String[] dnas = new String[] {dna1, dna2, dna3, dna4, dna5, dna6};
        for (String dna : dnas)
        {
            System.out.println(dna);
            System.out.println(findSimpleGene(dna, "atg", "taa"));
        }
    }
}
