package Question2;

import java.util.ArrayList;

public class CustomSort implements SortingInterface {

    public static ArrayList<Double> arrList = new ArrayList<Double>();

    public static void setArrList(ArrayList<Double> arrList) {
        CustomSort.arrList = arrList;
    }

    public static ArrayList<Integer> gaps = new ArrayList<Integer>();

    public static void setGaps(ArrayList<Integer> gaps) {
        CustomSort.gaps = gaps;
    }

    public void setValues(ArrayList<Double> values) {
        arrList = values;
        sort();
        //System.out.println("After sort in setValues fn : " + arrList);

    }

    /**
     * Returns the gaps used by the sorting algorithm.
     *
     * @return The gaps used by the sorting algorithm to sort the ArrayList
     */
    public ArrayList<Integer> getGaps() {
        gaps = calculate_gaps(arrList);
        return gaps;
    }

    /**
     * Adds a value to the sorted ArrayList in ascending order.
     *
     * @param value the double to be added to the array list
     */
    public void add(Double value) {
        arrList.add(value);
        sort();
    }

    /**
     * Removes a value at the specified index from the sorted ArrayList.
     *
     * @param index the index of the double to be removed
     */
    public void remove(int index) {
        //System.out.println("In the remove fn : " + arrList + "");
        arrList.remove(index);
        //System.out.println("In the remove fn after remove : " + arrList + "");
        //sort();
    }

    /**
     * Sorts the ArrayList in ascending order.
     *
     */
    public void sort() {
        getGaps();
        //System.out.println("gaps in sort fn 1 after getGaps call : " + gaps + " gaps size : " + gaps.size());
        //System.out.println("Before the sort starts : arrList : " + arrList);
        //System.out.println("Before the sort starts : gaps : " + gaps);
        sort(arrList, gaps);
    }

    /*
     * Java-fication of the sort pseudocode function from the coursework
     */
    private void sort(ArrayList<Double> arrList, ArrayList<Integer> gaps) {
        int n = arrList.size();
        getGaps();
        //System.out.println("In the sort fn after getGaps call gaps : " + gaps);
        //System.out.println("gaps in sort fn after getGaps call : " + gaps + " gaps size : " + gaps.size());
        for (int g = 0; g < gaps.size(); g++) {// each gap in gaps:
            int gap = gaps.get(g);
            //System.out.println("in sort fn inside first for : gap : " + gap + " n : " + n);
            for (int i = gap; i <= (n - 1); i++) {// i from gap to (n - 1) in increments of 1:
                double temp = arrList.get(i);
                int j = 0;
                //System.out.println("in sort fn inside second for : gap : " + gap + " n : " + n + " i : " + i + " j : " + j + " temp : " + temp );
                
                //j=i;
                //System.out.println("testing j calcs : " + j);
                //j-=gap;
                //System.out.println("testing j calcs : " + j);

                for (j = i; j >= gap; j-=gap) { // j from i to gap in decrements of j -= gap
                    //System.out.println("in sort fn inside third for before valHere: gap : " + gap + " n : " + n + " i : " + i + " j : " + j + " temp : " + temp );
                    double valHere = arrList.get(j - gap);
                    //System.out.println("in sort fn inside third for : gap : " + gap + " j : " + j + " arrList.get(j - gap) : " + valHere);
                    if (valHere <= temp) { // arrList[j-gap] is less than or equal to temp:
                        //System.out.println("in sort fn inside if ");
                        break;
                    }
                    arrList.set(j, valHere);
                    //System.out.println("After sort in sort 92 : " + arrList);
                }
                arrList.set(j, temp);
                //System.out.println("After sort in sort 95 : " + arrList);
            }
        }
    }

    /*
     * Java-fication of the calculate_gaps pseudocode function from the coursework
     */
    private ArrayList<Integer> calculate_gaps(ArrayList<Double> arrListIn) {
        
        ArrayList<Integer> gaps = new ArrayList<Integer>();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        try {
            int n = arrListIn.size();
            int gap = 1, i = 2;

            while (gap < n) {
                temp.add(gap);
                gap = (calToPower(2, i)) - 1;
                i++;
            }
            for (i = temp.size() - 1; i >= 0; i--) {
                gaps.add(temp.get(i));
            }
        } catch (Exception e) {
            System.out.println("Exception = " + e);
        }
        return gaps;
    }

    static int calToPower(int b, int p) {

        int result = 1;
        while (p != 0) {
            result = result * b;
            p--;
        }
        return result;
    }

}
