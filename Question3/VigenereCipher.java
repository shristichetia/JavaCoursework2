package Question3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class VigenereCipher implements Cipher {

    public String encrypt(String message_filename, String key_filename) {

        String strToEncrypt = readFile(message_filename).toUpperCase();
        String key = readFile(key_filename);

        String lengthenedKey = generateKey(strToEncrypt, key);

        String retVal = "";

        for (int i = 0; i < strToEncrypt.length(); i++) {

            char charToConvert = strToEncrypt.charAt(i);
            //System.out.println(Character.isAlphabetic(charToConvert));
            if (Character.isAlphabetic(charToConvert)) {
                int x = (charToConvert + lengthenedKey.charAt(i)) % 26;
                x += 'A';
                //System.out.println(x);
                retVal += (char) (x);
            } else {
                retVal += (char) (charToConvert);
            }
        }
        //System.out.println("encrypted value : " + retVal);
        return retVal;
    }

    public String decrypt(String message_filename, String key_filename) {
        String retVal = "";

        String strToDecrypt = readFile(message_filename).toUpperCase();
        String key = readFile(key_filename);

        String lengthenedKey = generateKey(strToDecrypt, key);

        for (int i = 0; i < strToDecrypt.length() &&
                i < lengthenedKey.length(); i++) {
            char charToConvert = strToDecrypt.charAt(i);
            //System.out.println(Character.isAlphabetic(charToConvert));
            if (Character.isAlphabetic(charToConvert)) {
                // converting in range 0-25
                int x = (strToDecrypt.charAt(i) - lengthenedKey.charAt(i) + 26) % 26;

                x += 'A';
                retVal += (char) (x);
            } else {
                retVal += (char) (charToConvert);
            }
        }
        //System.out.println("decrypted value : " + retVal);

        return retVal;
    }

    public static String readFile(String fileName) {
        String retVal = "";
        String currentWorkDir = System.getProperty("user.dir");
        // convert the package name to path
        String packagePath = VigenereCipher.class.getPackage().getName().replace(".", "/");

        try (Scanner sc = new Scanner(new File(currentWorkDir + "/" + packagePath + "/" + fileName))) {
            retVal = sc.useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println(fileName + " :: " + retVal);
        return retVal;
    }

    public static String generateKey(String str, String key) {
        int strLen = str.length();

        for (int i = 0; key.length() < strLen; i++) {
            if (strLen == i)
                i = 0;
            key += (key.charAt(i));
        }
        //System.out.println("generated key : " + key);
        return key;
    }

}
