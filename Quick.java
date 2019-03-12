import java.util.*;
import java.io.*;

public class Quick{
  public static void main(String[] args){

    int[]ary = {0,0,0,0,0,0,0};//{ 2, 10, 15, 23, 0,  5};  //sorted :  {0,2,5,10,15,23}
    System.out.println(Arrays.toString(ary));
    for (int i = 0; i < ary.length; i++){
      System.out.println("value of "+i+" smallest element: "+quickselect( ary , i ));
    }
    System.out.println("\n------------------------------------------\n");

    int[] ary1 = {-999,999,999,4,1,0,0,0,0,3,2,999,-999,999}; // sorted: {0,0,0,0,1,2,3,4,999,999,999,999,999,999};
    System.out.println(Arrays.toString(ary1));
    for (int i = 0; i < ary1.length; i++){
      System.out.println("value of "+i+" smallest element: "+quickselect( ary1 , i ));
    }
    System.out.println("\n------------------------------------------\n");

    // try on arrays from sizes 1 to 100, inclusive
    Random r = new Random();
    for (int i = 1; i < 101; i++){
      int arySize = i;
      //int arySize = r.nextInt(50) + 1;
      int[] aryRandom = new int[arySize];
      int[] aryRandomCopy = new int[arySize];
      for (int j = 0; j < aryRandom.length; j++){
        int nextElement = r.nextInt(1000);
        aryRandom[j] = nextElement;
        aryRandomCopy[j] = nextElement;
      }
      System.out.println("Size: "+aryRandom.length);
    //  System.out.println("SIZE: "+ aryRandom.length + "\n" +Arrays.toString(aryRandom));
      Arrays.sort(aryRandomCopy);
    //  System.out.println("SORTED: "+Arrays.toString(aryRandomCopy));
      for (int k = 0; k < aryRandom.length; k++){
        int element = quickselect( aryRandom , k );
        if (aryRandomCopy[k] != element){
          System.out.println("INCORRECT VALUE FOR "+k+" SMALLEST ELEMENT: "+
                              aryRandomCopy[k]+" VS "+element);
        }
      }
      System.out.println("PASSED QUICKSELECT FOR EVERY ELEMENT!");
      System.out.println("\n------------------------------------------\n");
    }
  }
  /*return the value that is the kth smallest value of the array.
  */
  public static int quickselect(int[] data, int k){
    int tries = 5000;
    for (int i = 0; i < tries; i++){
      int index = partition(data, 0, data.length - 1);
      int numPivots = pivotNum(data, data[index]);
      // System.out.println("index: "+index);
      // check if element is at desired index, otherwise call partition again
      if ( (k > index - numPivots && k <= index) ){
        return data[index];
      }
    }
    return -1; // should not reach this, but written in to compile

  }

  private int partitionDutch(int[] data,int lo, int hi){
    int pivot = lo;
    int s = 0;
    int s_add = 1; // Where to add from
    int e = data.length-1;
    // base case: if array is size 1, just return 0
    if (data.length <= 1){
      return 0;
    }
    // move pivot to the beginning of the array
    if (s == 0){
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

      // find where the pivot element belongs and return the index
      int index = 0;
      if (data[0] < data[s]){
        index = s - 1;
      }
      if (data[0] >= data[s]){
        index = s;
      }
      int temp3 = data[index];
      data[index] = data[0];
      data[0] = temp3;
      return index;

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
    int s = start;
    int s_add = 1; // Where to add from
    int e = end;
    // base case: if array is size 1, just return 0
    if (data.length <= 1){
      return 0;
    }
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
    //   System.out.println(Arrays.toString(data));
    // find where the pivot element belongs and return the index
    int index = 0;
    if (data[0] < data[s]){
      index = s - 1;
    }
    if (data[0] >= data[s]){
      index = s;
    }
    int temp3 = data[index];
    data[index] = data[0];
    data[0] = temp3;
    return index;
  }

  // find number of occurrences of the pivot value in the array
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
