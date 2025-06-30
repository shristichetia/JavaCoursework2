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
        System.out.println(arrList);
        sort();
        System.out.println("After sort in setValues fn : " + arrList);

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
        int n = arrList.size();
        arrList.set(n, value);
        sort();
    }

    /**
     * Removes a value at the specified index from the sorted ArrayList.
     *
     * @param index the index of the double to be removed
     */
    public void remove(int index) {
        sort();
        arrList.remove(index);
    }

    /**
     * Sorts the ArrayList in ascending order.
     *
     */
    public void sort() {
        getGaps();
        //System.out.println("gaps in sort fn 1 after getGaps call : " + gaps + " gaps size : " + gaps.size());
        sort(arrList, gaps);
    }

    /*
     * Java-fication of the sort pseudocode function from the coursework
     */
    private void sort(ArrayList<Double> arrList, ArrayList<Integer> gaps) {
        int n = arrList.size();
        getGaps();
        //System.out.println("gaps in sort fn after getGaps call : " + gaps + " gaps size : " + gaps.size());
        for (int gap = 0; gap < gaps.size(); gap++) {// each gap in gaps:
            System.out.println("in sort fn inside first for : gap : " + gap + " n : " + n);
            for (int i = gap; i <= (n - 1); i++) {// i from gap to (n - 1) in increments of 1:
                double temp = arrList.get(i);
                int j = 0;
                System.out.println("in sort fn inside second for : gap : " + gap + " n : " + n + " i : " + i + " j : " + j + " temp : " + temp );
                
                for (j = i; j <= gap; j-=gap) {// j from i to gap in decrements of j -= gap:
                    System.out.println("in sort fn inside third for before valHere: gap : " + gap + " n : " + n + " i : " + i + " j : " + j + " temp : " + temp );
                    double valHere = arrList.get(j - gap);
                    System.out.println("in sort fn inside third for : gap : " + gap + " j : " + j + " arrList.get(j - gap) : " + valHere);
                    if (valHere <= temp) { // arrList[j-gap] is less than or equal to temp:
                        System.out.println("in sort fn inside if ");
                        break;
                    }
                    arrList.set(j, valHere);
                }
                //arrList.set(j, temp);
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

    /*
     * static ArrayList<Integer> convertIntArrToArrListInt(int[] intArr) {
     * ArrayList<Integer> intList = new ArrayList<Integer>(intArr.length);
     * for (int i : intArr) {
     * intList.add(i);
     * }
     * return intList;
     * }
     * 
     * static int[] convertArrListIntToIntArr(ArrayList<Integer> intArrList) {
     * int[] intArr = new int[intArrList.size()];
     * for (int i = 0; i < intArr.length; i++) {
     * intArr[i] = intArrList.get(i);
     * }
     * return intArr;
     * }
     */

}
