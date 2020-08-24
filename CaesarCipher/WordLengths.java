import edu.duke.*;
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLengths {
    public void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            int len = word.length();
            char firstChar = word.charAt(0);
            char lastChar = word.charAt(len - 1);
            if (!Character.isLetter(firstChar)) len -= 1;
            if (!Character.isLetter(lastChar)) len -= 1;
            if (len >= 30) len = 30;
            //System.out.println(len + word);
            if (len != -1) counts[len] = counts[len] + 1;
        }
    }
    
    public void testCountWordLengths() {
        FileResource fr = new FileResource();
        int[] counts = new int[31];
        countWordLengths(fr, counts);
        for (int i = 0; i < counts.length; i++) {
            System.out.println("Length: " + i + "\t" + counts[i]);
        }
    }
}