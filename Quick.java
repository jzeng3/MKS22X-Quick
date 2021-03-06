import java.util.*;
import java.io.*;

public class Quick{
  //Sort testing code
  private static final int INCREASE = 0;
  private static final int DECREASE = 1;
  private static final int STANDARD = 2;
  private static final int SMALL_RANGE = 3;

  private static String name(int i){
    if(i==INCREASE)return "Increassing";
    if(i==DECREASE)return "Decreassing";
    if(i==STANDARD)return "Normal Random";
    if(i==SMALL_RANGE)return "Random with Few Values";
    return "Error categorizing array";

  }

  private static int create(int min, int max){
    return min + (int)(Math.random()*(max-min));
  }

  private static int[]makeArray(int size,int type){
    int[]ans =new int[size];
    if(type == STANDARD){
      for(int i = 0; i < size; i++){
        ans[i]= create(-1000000,1000000);
      }
    }
    if(type == INCREASE){
      int current = -5 * size;
      for(int i = 0; i < size; i++){
        ans[i]= create(current,current + 10);
        current += 10;
      }
    }
    if(type == DECREASE){
      int current = 5 * size;
      for(int i = 0; i < size; i++){
        ans[i]= create(current,current + 10);
        current -= 10;
      }
    }
    if(type == SMALL_RANGE){
      for(int i = 0; i < size; i++){
        ans[i]= create(-5,5);
      }
    }
    else{
      ans = new int[0];//empty is default
    }
    return ans;
  }

  /*public static void main(String[]args){

    if(args.length < 2)return;

    int size =  Integer.parseInt(args[0]);
    int type =   Integer.parseInt(args[1]);

    int [] start = makeArray(size,type);
    int [] result = Arrays.copyOf(start,start.length);
    Arrays.sort(result);

    long startTime = System.currentTimeMillis();
    /*
     * Test your sort here //yoursort(start);
     * Add code to switch which sort is tested by changing one of the args!
     */
  /*   quicksort(start);
    long elapsedTime = System.currentTimeMillis() - startTime;
    if(Arrays.equals(start,result)){
      System.out.println("PASS Case "+name(type)+"\t array, size:"+start.length+"\t"+elapsedTime/1000.0+"sec ");
    }else{
      System.out.println("FAIL ! ERROR ! "+name(type)+" array, size:"+size+"  ERROR!");
      System.out.println(Arrays.toString(start));
      System.out.println(Arrays.toString(result));
    }
  }*/
  public static void main(String[]args){
   System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
    int[]MAX_LIST = {1000000000,500,10};
    for(int MAX : MAX_LIST){
      for(int size = 31250; size < 2000001; size*=2){
        long qtime=0;
        long btime=0;
        //average of 5 sorts.
        for(int trial = 0 ; trial <=5; trial++){
          int []data1 = new int[size];
          int []data2 = new int[size];
          for(int i = 0; i < data1.length; i++){
            data1[i] = (int)(Math.random()*MAX);
            data2[i] = data1[i];
          }
          long t1,t2;
          t1 = System.currentTimeMillis();
          Quick.quicksort(data2);
          t2 = System.currentTimeMillis();
          qtime += t2 - t1;
          t1 = System.currentTimeMillis();
          Arrays.sort(data1);
          t2 = System.currentTimeMillis();
          btime+= t2 - t1;
          if(!Arrays.equals(data1,data2)){
            System.out.println("FAIL TO SORT!");
            System.exit(0);
          }
        }
        System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);

      }
      System.out.println();
    }
  }

  /*Modify the array to be in increasing order.
  */
  public static void quicksort(int[] data){
    quicksortH(data,0,data.length-1); // call helper function
  }

  // quicksort helper takes lo, hi indices
  public static void quicksortH(int[] data, int lo, int hi){
    if (lo >= hi){
      return; // end function if lo is greater than high
    }
    // call insertionSort if subsection of array is less than or equal to 30 elements
    if (hi - lo <= 14){
      insertionSort(data,lo,hi);
    }
    // otherwise record the pivot index
    int pivot = partitionDutch(data,lo,hi);
    // recursively call quicksortH on left and right sides of array
    quicksortH(data, lo, pivot -1);
    quicksortH(data,pivot+1,hi);
  }

  /*  a - When choosing a pivot, use the median value of the lo,hi, and middle elements.
  b - When a data element is equal to the pivot, make a 50% chance that you swap it to the other
  */
  private static int partitionDutch(int[] data,int lo, int hi){
    if (lo == hi){
      return lo; // if there's only one element, return the element
    }
    // if the section is only two elements, sort the two elements and return the lower index
    if (hi - lo == 1){
      if (data[lo] > data[hi]){
        int temp4 = data[hi];
        data[hi] = data[lo];
        data[lo] = temp4;
      }
      return lo;
    }

    int mid = (lo + hi) / 2; // middle index
    // s and e keep track of the low and high indices
    int s = lo;
    int e = hi;

    int pivot = 0; // pivot index
    // set pivot as the median value of lo,hi,mid elements
    if (data[lo] > data[hi] && data[lo] < data[mid]
    ||  data[lo] < data[hi] && data[lo] > data[mid]){
      pivot = lo;
    }
    if (data[mid] > data[hi] && data[mid] < data[lo]
    || data[mid] < data[hi] && data[mid] > data[lo]){
      pivot = mid;
    }
    else{
      pivot = hi;
    }

    // move pivot to the beginning of the array part that's being sorted
    int temp = data[lo];
    data[lo] = data[pivot];
    data[pivot] = temp;
    s++; // update start index

    /* Random for deciding whether equal value of pivot element
    should be moved to the other side of the array*/
    Random rand = new Random();

    // while start is less than end
    while(s < e){
      // willChange is either 0 or 1: 0 for moving equal value to other side and 1 for not
      int willChange = rand.nextInt(2);

      /* if current element is greater than pivot element or
      if current element is equal to pivot and willChange equals 0
      switch lo and hi elements and update the hi index */

      if (data[s] > data[lo] ||
      (data[s] == data[lo] && willChange == 0)){
        int temp1 = data[e];
        data[e] = data[s];
        data[s] = temp1;
        e--;
      }
      /* if current element is less than pivot element or
      if current element is equal to pivot and willChange equals 1
      update the lo element index */
      else if (data[s] < data[lo] ||
      (data[s] == data[lo] && willChange == 1)){
        s++;
      }
    }

    // find where the pivot element belongs, place it in that position, and return the index
    int index = 0;
    if (data[lo] <= data[s]){
      index = s - 1;
    }
    if (data[lo] > data[s]){
      index = s;
    }
    int temp3 = data[index];
    data[index] = data[lo];
    data[lo] = temp3;

    return index;
  }

  /*return the value that is the kth smallest value of the array.
  */
  public static int quickselect(int[] data, int k){
    int startIndex = 0;
    int endIndex = data.length - 1;
    int index = partitionDutch(data, startIndex, endIndex);

    // check if element is at desired index, otherwise call partition again
    while (index != k){

      // if index is to the left of desired element, partition the right side of the array
      if (index < k){
        startIndex = index + 1;
        index = partitionDutch(data, startIndex, endIndex);
      }else{
        // if index is to the right of the desired element, partition the left side of the array
        endIndex = index;
        index = partitionDutch(data, startIndex, endIndex);
      }
    }
    return data[index];

  }

  /*Choose a random pivot element between the start and end index inclusive,
  Then modify the array such that:
  *1. Only the indices from start to end inclusive are considered in range
  *2. A random index from start to end inclusive is chosen, the corresponding
  *   element is designated the pivot element.
  *3. all elements in range that are smaller than the pivot element are placed before the pivot element.
  *4. all elements in range that are larger than the pivot element are placed after the pivot element.
  *@return the index of the final position of the pivot element.
  */
  private static int partition(int[] data, int start, int end){
    Random rand = new Random();
    // pivot in range of start and end
    int pivot = Math.abs(rand.nextInt()) % (end - start + 1) + start;

    int s = start;
    int e = end;

    if (start == end){
      return start; // if there's only one element, return the element
    }

    // move pivot to the beginning of the array part that's being sorted
    int temp = data[start];
    data[start] = data[pivot];
    data[pivot] = temp;
    s++; // update starting index

    while(s < e){
      // if current element is greater than pivot element, swap start and end elements and update end index
      if (data[s] > data[start]){
        int temp1 = data[e];
        data[e] = data[s];
        data[s] = temp1;
        e--;
      }
      // if current element is less than or equal to the pivot element, move the start index to the right
      else if (data[s] <= data[start]){
        s++;
      }
    }
    // find where the pivot element belongs, place it in that position, and return the index
    int index = 0;
    if (data[start] <= data[s]){
      index = s - 1;
    }
    if (data[start] > data[s]){
      index = s;
    }
    int temp3 = data[index];
    data[index] = data[start];
    data[start] = temp3;

    return index;
  }

  // sorts array in increasing order
 public static void insertionSort(int[] data, int lo, int hi){
   if (hi - lo >= 2){
     // loop through array, checking number at current index relative to previous numbers
     for (int i = lo; i <= hi; i++){
       // current number
       int original = data[i];
       int index = i-1;

       while (index >= lo && data[index] > original){

         // store the previous number
         int temp = data[index];

        // if current num for outer loop is less than the previous number(s)
             // previous number moves up a space
             data[index+1] = temp;

             // current number will be put at a smaller index
             index--;
         }
         // set element at index equal to current number (sorted)

         data[index+1] = original;
       }
     }
   }

}
