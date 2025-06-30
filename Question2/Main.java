package Question2;

import java.util.ArrayList;


class Main {

  // main method
  // reads in input from the command line
  // and passes this input to the processCommand method in SRPN 

  public static void main(String[] args) {
    // Code to take input from the command line 
    // This input is passed to the processCommand
    // method in SRPN.java 
    CustomSort custSort = new CustomSort();
    ArrayList inValues = new ArrayList<Double>();
    
    inValues.add(1.38);
    inValues.add(1.0);
    inValues.add(4.0);
    inValues.add(7.0);
    inValues.add(10.0);
    inValues.add(-2.0);
    inValues.add(-10.0);


  

    custSort.setValues(inValues);

    //custSort.getGaps();

    //custSort.add(null);

    //custSort.remove(0);

    //custSort.sort();

  } 
}