package Question3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class VigenereCipher implements Cipher {

    /*
     * public VigenereCipher() {
     * setVigenereTable();
     * }
     */

    public static char[][] vigenereTable = new char[26][26];

    public static void setVigenereTable() {

        char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        for (int r = 0; r < alphabet.length; r++) {
            vigenereTable[r] = new char[alphabet.length];
            for (int cp = 0; cp < alphabet.length; cp++) {
                int c = cp + r;
                if (c > alphabet.length - 1) {
                    c = c - alphabet.length;
                }
                vigenereTable[r][cp] = alphabet[c];// - alphabet.length

            }
        }
        // System.out.println(Arrays.deepToString(vigenereTable));
    }

    public String encrypt(String message_filename, String key_filename) {
        
        String strToEncrypt = "";
        String key = "";

        try {
            System.out.println(" here : ");
            Scanner in = new Scanner(new FileReader(message_filename));
            StringBuilder sb = new StringBuilder();
            while (in.hasNext()) {
                sb.append(in.next());
            }
            in.close();
            strToEncrypt = sb.toString();
            
            //key = new String(Files.readString(Paths.get(key_filename)));

            System.out.println(" strToEncrypt : " + strToEncrypt + " key : " + key);

        } catch (IOException ioe) {
            // TODO: handle exception
        }
        String retVal = "";

        return retVal;
    }

    public String decrypt(String message_filename, String key_filename) {
        String retVal = "";
        return retVal;
    }

}
