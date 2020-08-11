
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        int numHowMany = 0;
        int startIndex = 0;
        int currIndex = stringb.indexOf(stringa, startIndex);
        while (currIndex != -1) {
            numHowMany++;
            currIndex = stringb.indexOf(stringa, currIndex + stringa.length());
        }
        return numHowMany;
    }
    
    public void testHowMany() {
        int howMany = howMany("GAG", "ATGAGAGAGTTGAGAG");
        System.out.println(howMany);
    }
}
