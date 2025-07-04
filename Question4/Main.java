package Question4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        String[] arrList1 = { "2003-08-27.txt", "1831-06-01.txt" };
        String[] arrList2 = { "1961-04-12.txt", "1972-12-11.txt" };

        ArrayList<String[]> arrLists = new ArrayList<String[]>();

        arrLists.add(arrList1);
        arrLists.add(arrList2);

        // PopThread pt1 = new PopThread(arrList1);
 
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < arrLists.size(); i++) {
            PopThread pt = new PopThread(arrLists.get(i));
            executor.execute(pt);
        }

    }

}
