package Question2;

import java.util.ArrayList;


public class CustomSort implements SortingInterface {

    ArrayList<Double> arrList = null;
    ArrayList<Integer> gaps = null;

    public void setValues(ArrayList<Double> values) {
        arrList = values;
        sort();
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

        sort(arrList, gaps);
    }

    /*
     * Java-fication of the sort pseudocode function from the coursework
     */
    private void sort(ArrayList<Double> arrList, ArrayList<Integer> gaps) {
        int n = arrList.size();
        getGaps();
        for (int gap = 0; gap < gaps.size(); gap++) {// each gap in gaps:
            for (int i = gap; i < (n - 1); i++) {// i from gap to (n - 1) in increments of 1:
                double temp = arrList.get(i);
                int j = 0;
                for (j = i; j < gap; j--) {// j from i to gap in decrements of j -= gap:
                    if (arrList.get(j - gap) <= temp) { // arrList[j-gap] is less than or equal to temp:
                        break;
                    }
                    arrList.set(j, arrList.get(j - gap));
                }
                arrList.set(j, temp);
            }
        }
    }

    /*
     * Java-fication of the calculate_gaps pseudocode function from the coursework
     */
    private ArrayList<Integer> calculate_gaps(ArrayList<Double> arrListIn) {
        ArrayList<Integer> gaps = null;
        ArrayList<Integer> temp = null;

        int n = arrListIn.size();
        int ng = 0;
        int gap = 1, i = 2;
        while (gap < n) {
            temp.set(i, gap);
            gap = (calToPower(2, i)) - 1;
            i++;
        }
        for (i = (temp.size()) - 1; i > 0; i--) {
            gaps.set(ng, temp.get(i));
            ng++;
        }
        return gaps;
    }



    static int calToPower(int b, int p) {

        int result = 1;
        // running loop while the power > 0
        while (p != 0) {
            result = result * b;
            // power will get reduced after
            // each multiplication
            p--;
        }
        System.out.println("Result =  " + result);
        return result;
    }

    /*static ArrayList<Integer> convertIntArrToArrListInt(int[] intArr) {
        ArrayList<Integer> intList = new ArrayList<Integer>(intArr.length);
        for (int i : intArr) {
            intList.add(i);
        }
        return intList;
    }

    static int[] convertArrListIntToIntArr(ArrayList<Integer> intArrList) {
        int[] intArr = new int[intArrList.size()];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = intArrList.get(i);
        }
        return intArr;
    }*/

}
