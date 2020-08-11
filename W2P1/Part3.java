
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb)
    {
        int idx = stringb.indexOf(stringa);
        if (idx == -1) return false;
        
        int idx2 = stringb.indexOf(stringa, idx + 1);
        if (idx2 != -1) return true;        
        return false;
    }
    
    public void testing() {
        String a = "a";
        String b = "banana";
        System.out.println(a + " " + b + " " + " " + twoOccurrences(a, b));
        
        a = "atg";
        b = "ctgtatgta";
        System.out.println(a + " " + b + " " + " " + twoOccurrences(a, b));
        
        a = "by";
        b = "A story by Abby Long";
        System.out.println(a + " " + b + " " + " " + twoOccurrences(a, b));
        
        String c = "an";
        String d = "banana";
        /*System.out.println(c + " " + d + " " + lastPart(c, d));
        c = "zoo";
        d = "forest";
        System.out.println(c + " " + d + " " + lastPart(c, d));*/
    }
    
    public String lastPart(String stringa, String stringb) {
        int idx = stringb.indexOf(stringa);
        if (idx == -1) return stringb;
        return stringb.substring(idx + stringa.length());
    }
}
