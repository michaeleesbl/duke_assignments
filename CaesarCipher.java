import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = alphabetUpper.toLowerCase();
        String shiftedUpper = shiftAlphabet(alphabetUpper, key);
        String shiftedLower = shiftAlphabet(alphabetLower, key);
       
        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            if (Character.isUpperCase(currChar)) {
                int idx = alphabetUpper.indexOf(currChar);
                if (idx != -1) encrypted.setCharAt(i, shiftedUpper.charAt(idx));
            } else {
                int idx = alphabetLower.indexOf(currChar);
                if (idx != -1) encrypted.setCharAt(i, shiftedLower.charAt(idx));
            }
        }
        return encrypted.toString();
    }
    
    public String shiftAlphabet(String alphabet, int key) {
        return(alphabet.substring(key) + alphabet.substring(0, key));
    }
    
    public void testCaesar() {
        int key = 15; 
        FileResource fr = new FileResource();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";//fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println("key is " + key + "\n" + encrypted);
        /*FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
        int key1 = 4;
        int key2 = 12;
        String message2 = fr.asString();
        String encryptedTwoKeys = encryptTwoKeys(message, key1, key2);
        System.out.println("key1 and key2 are " + key1 + ", " + key2 + "\n" + encryptedTwoKeys);*/
    }
    
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder inputEvensB = new StringBuilder(input);
        StringBuilder inputOddsB = new StringBuilder(input);
        StringBuilder encryptedTwoKeys = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            if (i % 2 == 1) {
                inputEvensB.setCharAt(i, ' ');
            } else {
                inputOddsB.setCharAt(i, ' ');
            }
        }
        
        String encryptedEvens = encrypt(inputEvensB.toString(), key1);
        String encryptedOdds = encrypt(inputOddsB.toString(), key2);
        
        for (int k = 0; k < input.length(); k++) {
            if (k % 2 == 0) {
                encryptedTwoKeys.setCharAt(k, encryptedEvens.charAt(k));
            } else {
                encryptedTwoKeys.setCharAt(k, encryptedOdds.charAt(k));
            }
        }
        //StringBuilder encrypted = new StringBuilder(input);
        return encryptedTwoKeys.toString(); //TODO
    }
    
    public void testEncryptTwoKeys() {
        int key1 = 8;
        int key2 = 21;
        FileResource fr = new FileResource();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";//"First Legion";fr.asString();
        String encryptedTwoKeys = encryptTwoKeys(message, key1, key2);
        System.out.println(encryptedTwoKeys);
    }
        
    public void testFingerPrint(String s) {
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        int[] counters = new int[26];
        for(int k=0; k < s.length(); k++) {
            char ch = s.charAt(k);
            int index = alpha.indexOf(Character.toLowerCase(ch));
            if (index != -1){
                counters[index] += 1;
            }
        }
        for(int k=0; k < counters.length; k++) {
            System.out.println(alpha.charAt(k) + "\t" + counters[k]);
        }
    }
}
