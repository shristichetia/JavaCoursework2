package Question4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class PopThread implements Runnable{

    String[] filenames;

    public PopThread(String[] filenamesIn) {
        filenames = filenamesIn;
    }

    public void run(){
        callReadFile(filenames);
    }

    private void callReadFile(String[] filenameArr){
        String readContent = "";
        for (int i = 1; i < filenameArr.length; i++) {
            readContent = readFile(filenameArr[i]);
            System.out.println("readContent for " + filenameArr[i] + " :: " + readContent);
        }
        System.out.println("DONE");
    }

    public static String readFile(String fileName) {
        String retVal = "";
        String currentWorkDir = System.getProperty("user.dir");
        // convert the package name to path
        String packagePath = PopThread.class.getPackage().getName().replace(".", "/");

        try (Scanner sc = new Scanner(new File(currentWorkDir + "/" + packagePath + "/" + fileName))) {
            retVal = sc.useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //System.out.println(fileName + " :: " + retVal);
        return retVal;
    }
    
}
