package Question3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class VigenereCipher implements Cipher {

    /*
     * public static char[][] vigenereTable = new char[26][26];
     * 
     * public static void setVigenereTable() {
     * 
     * char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
     * 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
     * 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
     * for (int r = 0; r < alphabet.length; r++) {
     * vigenereTable[r] = new char[alphabet.length];
     * for (int cp = 0; cp < alphabet.length; cp++) {
     * int c = cp + r;
     * if (c > alphabet.length - 1) {
     * c = c - alphabet.length;
     * }
     * vigenereTable[r][cp] = alphabet[c];// - alphabet.length
     * 
     * }
     * }
     * // System.out.println(Arrays.deepToString(vigenereTable));
     * }
     */

    public String encrypt(String message_filename, String key_filename) {

        String strToEncrypt = "GEEKSFORGEEKS";
        String key = "SHRISTI";

        
        String lengthenedKey = generateKey(strToEncrypt, key);

        String retVal = "";

        for (int i = 0; i < strToEncrypt.length(); i++) {
            int x = (strToEncrypt.charAt(i) + lengthenedKey.charAt(i)) % 26;
            x += 'A';
            System.out.println(x);
            retVal += (char) (x);
        }
        System.out.println(retVal);
        return retVal;
    }

    public String decrypt(String message_filename, String key_filename) {
        String retVal = "";
        String strToDecrypt = "YLVSKYWJNVMCL";
        String key = "SHRISTI";
        String lengthenedKey = generateKey(strToDecrypt, key);

        for (int i = 0; i < strToDecrypt.length() &&
                i < lengthenedKey.length(); i++) {
            // converting in range 0-25
            int x = (strToDecrypt.charAt(i) - lengthenedKey.charAt(i) + 26) % 26;

            x += 'A';
            retVal += (char) (x);
        }
        System.out.println(retVal);

        return retVal;
    }

    public static String readFile(String fileName) {
        String retVal = "";
        String currentWorkDir = System.getProperty("user.dir");
        // convert the package name to path
        String packagePath = VigenereCipher.class.getPackage().getName().replace(".", "/");

        try (Scanner sc = new Scanner(new File(currentWorkDir + "/" + packagePath + "/" + fileName))) {
            retVal = sc.useDelimiter("\\Z").next();
            System.out.println(retVal);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(retVal);
        return retVal;
    }

    public static String generateKey(String str, String key) {
        int strLen = str.length();

        for (int i = 0; key.length() < strLen; i++) {
            if (strLen == i)
                i = 0;
            key += (key.charAt(i));
        }
        // System.out.println(key);
        return key;
    }

}
