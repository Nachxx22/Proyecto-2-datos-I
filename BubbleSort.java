
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Java program for implementation of Bubble Sort
class BubbleSort
{
    void bubbleSort(List<Integer> arr)
    {
        int n = arr.size();
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr.get(j) > arr.get(j+1))
                {
                    Collections.swap(arr,j,j+1);
                    // swap arr[j+1] and arr[i]
                    //int temp = arr.get(j);
                    //arr[j] = arr.get(j+1);
                    //arr[j+1] = temp;
                }
    }

    /* Prints the array */
    void printArray(List<Integer> arr)
    {
        int n = arr.size();
        for (int i=0; i<n; ++i)
            System.out.print(arr.get(i) + " ");
        System.out.println();
    }

    // Driver method to test above
    public static void main(String args[])
    {
        BubbleSort ob = new BubbleSort();
        List<Integer> arr = new ArrayList<Integer>();
        arr.add(1);arr.add(6);arr.add(20);arr.add(7);arr.add(50);arr.add(13);arr.add(25);
        arr.add(8);
        ob.bubbleSort(arr);
        System.out.println("Sorted array");
        ob.printArray(arr);
    }
}
