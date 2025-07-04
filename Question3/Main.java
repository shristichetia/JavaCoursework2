package Question3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
public static void main(String[] args) {
    // Code to take input from the command line 
    // This input is passed to the processCommand
    // method in SRPN.java 
    VigenereCipher vc = new VigenereCipher();
    //vc.setVigenereTable();
    //vc.generateKey("GEEKSFORGEEKS","SHRISTI");
    vc.encrypt("encrypt_check.txt","key_check.txt");
    //vc.readFile("encrypt_check.txt");

    vc.decrypt("decrypt_check.txt","key_check.txt");

  } 
}