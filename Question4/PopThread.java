package Question4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class PopThread implements Runnable {

    // Thread related variables
    Thread thread;

    public static AtomicInteger counter = new AtomicInteger(0);
    public static AtomicInteger firstThreadIdentifier = new AtomicInteger(0);
    public static AtomicInteger firstThreadCounterVal = new AtomicInteger(0);

    // Action related variables
    ArrayList<String> filenames = new ArrayList<String>();
    String[] strPrepToWrite = new String[10];

    String currentWorkDir = System.getProperty("user.dir");
    String packagePath = PopThread.class.getPackage().getName().replace(".", "/");
    File statText = new File(currentWorkDir + "/" + packagePath + "/result.txt");

    // constructor to set arraylist and also initiate the current thread
    public PopThread(ArrayList<String> filenamesIn) {
        thread = new Thread(this);
        this.filenames = filenamesIn;

    }

    public void run() {
        int counterF = counter.getAndIncrement();
        callReadFile(filenames, counterF);

    }

    public void callReadFile(ArrayList<String> filenameArr, int counterF) {

        // if (counterF < 2) {
        String readContent = "";

        for (int i = 0; i < filenameArr.size(); i++) {
            readContent = readFile(filenameArr.get(i) + "");
            if (readContent != null && readContent != "") {
                String orderStr = readContent.substring(readContent.indexOf("#") + 1, readContent.indexOf("/"))
                        .replaceAll("0", "");

                if (orderStr != null) {
                    int rownum = Integer.parseInt(orderStr) - 1;
                    strPrepToWrite[rownum] = readContent;

                }
            }
        }
        // }

        printResult(strPrepToWrite, counterF);

    }

    private synchronized void printResult(String[] strPrepToWrite, int counterF) {

        try {
            //System.out.println(
                    //" Thread starts : " + thread.threadId() + " cpounter : " + counter.get() + " counterF : "
                          //  + counterF);
            if (firstThreadIdentifier.intValue() == 0) {
                firstThreadIdentifier.getAndIncrement();
                firstThreadCounterVal.set(counterF);
            }
            if (counterF != firstThreadCounterVal.intValue()) {
                this.wait(10000);
            }

            String readresultContent = readFile("result.txt");
            // System.out.println("readresultContent : " + readresultContent);
            if (readresultContent != null && readresultContent != "" && counterF != firstThreadCounterVal.intValue()) { //   && counterF < 2

                String[] readresultContentArr = readresultContent.split("#");

                for (int r = 0; r < readresultContentArr.length - 1; r++) {

                    int firstIndex = 0;
                    int endIndex = readresultContent.indexOf("#") + 8;
                    String subStringR = "";
                    // System.out.println("subStringR b4 subbing : " + subStringR + "
                    // readresultContent : " +readresultContent + " readresultContentArr.length : "+
                    // readresultContentArr.length );
                    if (readresultContent != "") {
                        subStringR = readresultContent.substring(firstIndex, endIndex);
                        readresultContent = readresultContent.substring(endIndex, readresultContent.length());
                    }
                    // System.out.println("subStringR after subbing : " + subStringR + "
                    // readresultContent : " +readresultContent);
                    if (subStringR != "") {
                        String orderStr = subStringR
                                .substring(subStringR.indexOf("#") + 1, subStringR.indexOf("/"))
                                .replaceAll("0", "");

                        if (orderStr != null) {
                            int rownum = Integer.parseInt(orderStr) - 1;
                            strPrepToWrite[rownum] = subStringR;

                        }
                    }
                }
            }

            try {
                FileOutputStream is = new FileOutputStream(statText);
                OutputStreamWriter osw = new OutputStreamWriter(is);
                Writer w = new BufferedWriter(osw);
                String val = "";
                for (int k = 0; k < strPrepToWrite.length; k++) {
                    if (strPrepToWrite[k] != null && strPrepToWrite[k] != "") {
                        if (val == "") {
                            val = (strPrepToWrite[k] + "").strip();
                        } else {
                            val = val + "\n" + strPrepToWrite[k].strip();
                        }

                        
                    }
                }
                //System.out.println("Val to write : " + val );
                if (val != "") {
                    w.write(val);
                }
                w.close();
                this.notifyAll();

            } catch (UnsupportedEncodingException u) {
                System.out.println("UnsupportedEncodingException ? : " + u);
            } catch (Exception e) {
                System.out.println("Exception in  printResult ? : " + e);
            }

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("InterruptedException in  printResult ? : " + e);
            e.printStackTrace();
        }

    }

    public static String readFile(String fileName) {
        String retVal = "";
        String currentWorkDir = System.getProperty("user.dir");
        // convert the package name to path
        String packagePath = PopThread.class.getPackage().getName().replace(".", "/");

        try (Scanner sc = new Scanner(new File(currentWorkDir + "/" + packagePath + "/" + fileName))) {
            retVal = sc.useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            return "";
        } catch (NoSuchElementException e) {
            return "";
        }
        return retVal;
    }

}
