import edu.duke.URLResource;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    
    public void test() 
    {
        for (String item : ur.words()) {
       	   String itemLower = item.toLowerCase();
       	   int pos = itemLower.indexOf("youtube.com");
       	   if (pos != -1) {
       	       int beg = item.lastIndexOf("\"",pos);
       	       int end = item.indexOf("\"", pos+1);
       	       System.out.println(item.substring(beg+1,end));
           }    
        }
    }
}
