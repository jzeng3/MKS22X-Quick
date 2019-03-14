import java.util.*;
import java.io.*;

public class Quick{
  public static void main(String[] args){

    int[]ary = {5,4,3,1,2,7};
    //{0,1,0,1,2,1,0,1,0,0,1,0,5,6,3,8,9,9,9,3,3};
    //{5,4,3,1,2,7};
    //{0,1,0,1,2,1,0,1};
    //{1,1,0,0,1,0,1,0,0,1,0,1,0,10,1,0,0,0,0,0,0,0, 1,1,0,0,1,0,1,0,0,1,0,1,0,1,1,0,0,1,0,1,0,0,1};
    //{ 2, 10, 15, 23, 0,  5};  //sorted :  {0,2,5,10,15,23}
    System.out.println(Arrays.toString(ary));

       System.out.println("value of "+0+" smallest element: "+quickselect( ary , 0 ));

    System.out.println("\n------------------------------------------\n");
//    for (int i = 0; i < ary.length; i++){
    //  System.out.println("QUICKSELECT_DUTCH: value of "+4+" smallest element: "+quickselectDutch( ary , 4 ));
//    }
    System.out.println("\n------------------------------------------\n");

  /*  int[] ary1 = new int[10000]; // sorted: {0,0,0,0,1,2,3,4,999,999,999,999,999,999};
    for (int i = 0; i < 10000; i++){
      ary1[i] = 0;
      if (i%2==1){
        ary1[i] = 1;
      }
    }
    System.out.println(Arrays.toString(ary1));
    for (int i = 0; i < ary1.length; i++){
      System.out.println("value of "+i+" smallest element: "+quickselect( ary1 , i ));
    }*/
    System.out.println("\n------------------------------------------\n");

    // try on arrays from sizes 1 to 100, inclusive
    Random r = new Random();
    for (int i = 1; i < 2; i++){
      int arySize = i;
      //int arySize = r.nextInt(50) + 1;
      int[] aryRandom = new int[arySize];
      int[] aryRandomCopy = new int[arySize];
      for (int j = 0; j < aryRandom.length; j++){
        int nextElement = r.nextInt(1000);
        aryRandom[j] = nextElement;
        aryRandomCopy[j] = nextElement;
      }
    //  System.out.println("Size: "+aryRandom.length);
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

    //  System.out.println("\n------------------------------------------\n");
    }
    System.out.println("PASSED QUICKSELECT FOR EVERY ELEMENT!");
    //int[] ary2 = {9, -3, 5, 2, 6, 8, -6, 1, 3};
    System.out.println("PARTITION DUTCH");
    int[] ary2 = {9,-3,5,2,6,6,6,6,6,6,3,8,-6,1,6,6,6,6,6,6,6,6,6,6,6,6,6};
    //partitionDutch(ary2, 0, ary2.length -1);
    System.out.println(Arrays.toString(ary2));
    quicksort(ary2);
    System.out.println("SORTED: "+Arrays.toString(ary2));
  }

  /*Modify the array to be in increasing order.
   */
   public static void quicksort(int[] data){
     quicksortH(data,0,data.length-1);
   }
   // quicksort helper takes lo, hi

   public static void quicksortH(int[] data, int lo, int hi){
     if (lo >= hi){
       return; // end function if lo is greater than high
     }
     System.out.println("DOING PARTITION DUTCH NOW! "+lo+", "+hi);
     // otherwise record the pivot index
     int pivot = partitionDutch(data,lo,hi);
     System.out.println("pivot: "+pivot);
     // recursively call quicksortH on left and right sides of array
     quicksortH(data, lo, pivot -1);
     System.out.println("after left sort: "+Arrays.toString(data));
     quicksortH(data,pivot+1,hi);
     System.out.println("after right sort: "+Arrays.toString(data));
   }
  /*return the value that is the kth smallest value of the array.
  */
  public static int quickselect(int[] data, int k){
      int startIndex = 0;
      int endIndex = data.length - 1;
      int index = partition(data, startIndex, endIndex);
      //System.out.println(Arrays.toString(data));
      // check if element is at desired index, otherwise call partition again
      while (index != k){
      //  System.out.println("index, k: "+index+" "+k);
        // if index is to the left of desired element, partition the right side of the array
        if (index < k){

          startIndex = index + 1;
          index = partition(data, startIndex, endIndex);
        }else{
          // if index is to the right of the desired element, partition the left side of the array
          endIndex = index;
        //  System.out.println("startIndex, endIndex: "+startIndex+" "+endIndex);
          index = partition(data, startIndex, endIndex);
        }
      //  System.out.println("index now: "+index);
      }
    //  System.out.println("INDEX AT THE END: "+index);
    //  System.out.println(Arrays.toString(data));
    return data[index];

  }

/*  a - When choosing a pivot, use the median value of the lo,hi, and middle elements.
    b - When a data element is equal to the pivot, make a 50% chance that you swap it to the other
    */
  public static int partitionDutch(int[] data,int lo, int hi){
  //  System.out.println("partitionDutch "+Arrays.toString(data)+", "+lo+", "+hi);
    int mid = (lo + hi) / 2; // middle element index
    System.out.println("middle index: "+mid);
    if (hi - lo == 1){
      if (data[lo] > data[hi]){
        int temp4 = data[hi];
        data[hi] = data[lo];
        data[lo] = temp4;
      }
      return lo;
    }
    int pivot = 0;
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

    System.out.println("pivot, inside partitionDutch: "+pivot);
    int s = lo;
    int e = hi;

    if (lo == hi){
      return lo; // if there's only one element, return the element
    }

    // move pivot to the beginning of the array part that's being sorted
      int temp = data[lo];
      data[lo] = data[pivot];
      data[pivot] = temp;
      s++; // update loing index
      // System.out.println("lo, hi, pivot: "+s+" "+e+" "+pivot);


    while(s < e){
      Random rand = new Random();
      int willChange = rand.nextInt(2);

      // if current element is greater than pivot element, move element to the right hi
      if (data[s] > data[lo]){
      //  System.out.println("data["+s+"]: "+data[s]);
        int temp1 = data[e];
        data[e] = data[s];
        data[s] = temp1;
        e--;

      }
      // if current element is less than pivot element, move the lo to the right
      else if (data[s] < data[lo]){
        s++;
      }
      else if (data[s] == data[lo]){
      //  System.out.println("willChange value: "+ willChange);
       if (willChange == 0){ // 50% chance of keeping element on left side
         s++;
       }
       else if (willChange == 1){ // 50% chance of moving element to other side
         int temp1 = data[e];
         data[e] = data[s];
         data[s] = temp1;
         e--;
       }
       else{
         System.out.println("Something is wrong: " + willChange);
       }
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
    System.out.println("AFTER PARTITION, inside partitionDutch: "+Arrays.toString(data));
    System.out.println("index to return: "+index);
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
  //  System.out.println("START OF A PARTITION: "+Arrays.toString(data));
  //  System.out.println("start, end: "+start+", "+end);
    Random rand = new Random();
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
  //System.out.println("start, end, pivot: "+s+" "+e+" "+pivot);

    //  System.out.println("PIVOT AT FRONT: "+Arrays.toString(data));

    while(s < e){
      // if current element is greater than pivot element, move element to the right end
      if (data[s] > data[start]){
        int temp1 = data[e];
        data[e] = data[s];
        data[s] = temp1;
        e--;
      }
      // if current element is less than pivot element, move the start to the right
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
  //  System.out.println("AFTER PARTITION: "+Arrays.toString(data));
    //System.out.println("start: "+start+" end: "+end+" s: "+s+" e: "+e+" return index: "+index);

    return index;
  }



}
