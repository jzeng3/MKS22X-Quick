import java.util.*;
import java.io.*;

public class Quick{
  public static void main(String[] args){
    //int[] ary = {1,5,8,4,9,2,7,10,3};
    // int[] ary = {999,999,999,4,1,0,3,2,999,999,999};
    // System.out.println(partition(ary, 0, ary.length - 1));
    int[]ary = { 2, 10, 15, 23, 0,  5};  //sorted :  {0,2,5,10,15,23}
    /*System.out.println(quickselect( ary , 0 )) ; // would return 0
    System.out.println(quickselect( ary , 1 )) ; // would return 2
    System.out.println(quickselect( ary , 2 )) ; // would return 5
    System.out.println(quickselect( ary , 3 )) ; // would return 10
    System.out.println(quickselect( ary , 4 )) ; // would return 15
    System.out.println(quickselect( ary , 5 )) ; // would return 23
    */
     int[] ary1 = {0,0,999,0}; // sorted: {0,0,0,0,1,2,3,4,999,999,999,999,999,999};
    // System.out.println(partition(ary1, 0, ary1.length -1));
     System.out.println(quickselect( ary1 , 0 )) ; // would return 0
    /* System.out.println(quickselect( ary1 , 1 )) ; // would return 0
     System.out.println(quickselect( ary1 , 2 )) ; // would return 0
     System.out.println(quickselect( ary1 , 3 )) ; // would return 0
     System.out.println(quickselect( ary1 , 4 )) ; // would return 1
     System.out.println(quickselect( ary1 , 5 )) ; // would return 2
*/

  }
  /*return the value that is the kth smallest value of the array.
   */
   public static int quickselect(int[] data, int k){
     int tries = 5000;
     for (int i = 0; i < tries; i++){
     int index = partition(data, 0, data.length - 1);
     int numPivots = pivotNum(data, data[0]);
     System.out.println("index: "+index);
     // check if element is at desired index, otherwise call partition again
      if ( (k > index - numPivots && k <= index) ){
        System.out.println("index == k: "+index+" == " + k);
          return data[0];
        }
      }
      return -1;

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
  public static int partition(int[] data, int start, int end){
    Random rand = new Random();
    int pivot = rand.nextInt(data.length);
   System.out.println("pivot index, pivot value: "+pivot+": "+data[pivot]);
    int s = start;
    int s_add = 1; // Where to add from
    int e = end;
    // move pivot to the beginning of the array
    if (start == 0){
      int temp = data[0];
      data[0] = data[pivot];
      data[pivot] = temp;
      s = 1;
    }

    while(s < e && s < data.length && e >= 1){
      // if current element is greater than pivot element, move element to the right end
      if (data[s] > data[0]){
        int temp1 = data[e];
        data[e] = data[s];
        data[s] = temp1;
        e--;
      }
      // if current element is less than pivot element, move element to the left end
      else if (data[s] <= data[0]){
        int temp2 = data[s_add];
        data[s_add] = data[s];
        data[s] = temp2;
        s++;
        s_add++;

      }
    }
    System.out.println(Arrays.toString(data));
    // find where the pivot element belongs and return the index
    int index = 0;
    if (data[0] < data[s]){
      index = s - 1;
    }
    if (data[0] >= data[s]){
      index = s;
    }
    return index;
  }

  private static int pivotNum(int[] data, int pivotVal){
    int pivotNums = 0;
    for (int i = 0; i < data.length; i++){
      if (pivotVal == data[i]){
        pivotNums++;
      }
    }
    return pivotNums;
  }


}
