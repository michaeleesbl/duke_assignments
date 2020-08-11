import edu.duke.URLResource;
import java.lang.Object;
import java.io.File;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    String total = ur.asString();
    String yt = "youtube.com";
    
    public boolean youtubeInWord(String word) {
        word = word.toLowerCase();
        if (word.indexOf(yt) == -1) return false;
        return true;
    }
    public void test() {
        for (String item : ur.words()) {
       	   String itemLower = item.toLowerCase();
       	   int pos = itemLower.indexOf("youtube.com");
       	   if (pos != -1) {
       	       int beg = item.lastIndexOf("\"",pos);
       	       int end = item.indexOf("\"", pos+1);
       	       System.out.println(item.substring(beg+1,end));
               }
   	}
        System.out.println();
        for (String word : ur.words()) {
            
            //System.out.println(word);
            if (youtubeInWord(word)) {
                int idx1 = word.indexOf("\"");
                int idx2 = word.indexOf("\"", idx1 + 1);
                System.out.println(word.substring(idx1 + 1, idx2));
            }
            //System.out.println(youtubeInWord(word));
        }
    }
}


