
/**
 * Write a description of Part2 here.
 * 
 * @MichaelSLee
 * @08022020
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
    int numHowMany = 0;
    int startIndex = 0;
    int currIndex = stringb.indexOf(stringa, startIndex);
    while (currIndex != -1) {
        currIndex = stringb.indexOf(stringa, currIndex + stringa.length());
        numHowMany++;
    }
    return numHowMany;
    }
    
    public void testHowMany() {
    int howMany = howMany("GAA", "ATGAACGAATTGAAGAA");
    System.out.println(howMany);
    }
}
