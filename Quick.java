import java.util.*;
import java.io.*;

public class Quick{
  public static void main(String[] args){
    //int[] ary = {1,5,8,4,9,2,7,10,3};
    // int[] ary = {999,999,999,4,1,0,3,2,999,999,999};
    // System.out.println(partition(ary, 0, ary.length - 1));
    int[]ary = { 2, 10, 15, 23, 0,  5};  //sorted :  {0,2,5,10,15,23}
    System.out.println(quickselect( ary , 0 )) ; // would return 0
    System.out.println(quickselect( ary , 1 )) ; // would return 2
    System.out.println(quickselect( ary , 2 )) ; // would return 5
    System.out.println(quickselect( ary , 3 )) ; // would return 10
    System.out.println(quickselect( ary , 4 )) ; // would return 15
    System.out.println(quickselect( ary , 5 )) ; // would return 23
  }
  /*return the value that is the kth smallest value of the array.
   */
   public static int quickselect(int[] data, int k){
      for (int i = 0; i < data.length; i++){
      if (k == partition(data, 0, data.length - 1)){
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
  //  System.out.println(Arrays.toString(data));
    Random rand = new Random();
    int pivot = rand.nextInt(data.length - 1) + 1;
  //  System.out.println("pivot: "+data[pivot]);
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
  /*  System.out.println(Arrays.toString(data));
    System.out.println("s: "+ s);
    System.out.println("e: "+ e);*/

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


}
