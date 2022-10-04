import java.util.Arrays;
import java.util.Random;

import static java.lang.System.nanoTime;

public class Sorting {

    public static void selectionSort(int[] arr) {
        int tmp;
        int min_index;

        for(int i = 0; i < arr.length - 1; i++) {
            min_index = i; // assign first value as index of smallest number
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[min_index] > arr[j]) { // if there is a number in the array that is smaller than min_index, make this the new min_index
                    min_index = j;
                }
            }
            tmp = arr[i]; // swap the first unsorted element with the minimum element
            arr[i] = arr[min_index];
            arr[min_index] = tmp;
        }
    }

    public static void insertionSort(int[] arr) {
        int key;

        for(int i = 0; i < arr.length; i++) {
            key = arr[i]; // first unsorted element
            int j = i-1;

            while(j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j]; // find spot in sorted part of list for key
                j--;
                arr[j+1] = key;
            }
        }
    }

    public static int[] bogoSort(int[] arr) {
        while(!sorted(arr)) { // check if array is sorted, if not shuffle
            shuffle(arr);
        }
        return arr;
    }

    public static void mergeSort (int[] arr){
        int n = arr.length;

        // base case
        if (n == 1){
            return;
        }

        // create left and right sub-arrays
        int mid = n/2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, n - mid);

        // recursively sort left and right sub-arrays
        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right, mid, n - mid);
    }

    public static void improvedMergeSort (int[] arr){
        int n = arr.length;
        int CUTOFF = 15;

        if(n <= CUTOFF) {
            insertionSort(arr);
        } else {

            // create left and right sub-arrays
            int mid = n / 2;
            int[] left = new int[mid];
            int[] right = new int[n - mid];

            System.arraycopy(arr, 0, left, 0, mid);
            System.arraycopy(arr, mid, right, 0, n - mid);

            // recursively sort left and right sub-arrays
            improvedMergeSort(left);
            improvedMergeSort(right);

            merge(arr, left, right, mid, n - mid);
        }
    }

    public static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;

        // combine left and right sub-array in sorted order
        while (i < left && j < right) {
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        }
        // if left or right sub-array is empty fill array with rest of remaining sub-array
        while (i < left)
            a[k++] = l[i++];
        while (j < right)
            a[k++] = r[j++];
    }

    public static void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi-1); // sort left of pi
            quickSort(arr, pi+1, high); // sort right of pi
        }

    }

    public static void improvedQuickSort(int[] arr, int low, int high) {
        int CUTOFF = 15;
        if(low + CUTOFF > high) {
            insertionSortForQuickSort(arr, low, high);
        } else {
            // arrange 3 starting points
            int mid = (low + high) / 2;
            if (arr[mid] < arr[low]) {
                swap(arr, mid, low);
            }
            if (arr[high] < arr[low]) {
                swap(arr, low, high);
            }
            if (arr[high] < arr[mid]) {
                swap(arr, high, mid);
            }

            // place pivot at high-1
            swap(arr, mid, high-1);
            int pivot = arr[high - 1];

            // Begin partitioning
            int i, j;
            for (i = low, j = high - 1; ; ) {
                while (arr[++i] < pivot)
                    ;
                while (pivot < arr[--j])
                    ;
                if (i >= j)
                    break;
                swap(arr, i, j);
            }
            //move pivot back
            swap(arr, i, high - 1);

            // recursively sort left and right sub-array
            improvedQuickSort(arr, low, i - 1);
            improvedQuickSort(arr, i + 1, high);
        }
    }

    private static void insertionSortForQuickSort(int[] arr, int low, int high) {
        for( int p = low+1; p <= high; p++ ) {
            int tmp = arr[p];
            int j;

            for( j = p; j > low && tmp < arr[j-1]; j--)
                arr[j] = arr[j-1];
            arr[j] = tmp;
        }
    }

    public static int partition(int[] arr, int low, int high) {
        // element to be stored at right position
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element

        for(int j = low; j <= high; j++) {
            if(arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);

        return (i + 1);
    }

    public static void shuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [0, i]
            int r = (int) (Math.random() * (i + 1));
            int swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i]; // swap two elements
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    // return true if array is sorted
    public static boolean sorted(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] < arr[i-1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //START of practical 4
        /*
        int[] arrInsertionSort = new int[100];
        int[] arrSelectionSort = new int[arrInsertionSort.length];
        int[] arrBogoSort = new int[arrInsertionSort.length];

        // create 3 arrays that all have the same random elements
        Random rand = new Random();
        for(int i = 0; i < arrInsertionSort.length; i++) {
            arrInsertionSort[i] = rand.nextInt(arrInsertionSort.length) + 1;
            arrSelectionSort[i] = arrInsertionSort[i];
            arrBogoSort[i] = arrInsertionSort[i];
        }

        System.out.println("Original array: " + Arrays.toString(arrSelectionSort));

        final long startTimeIns = nanoTime();
        insertionSort(arrInsertionSort);
        final long timeTakenIns = nanoTime() - startTimeIns;
        System.out.println("Time taken for Insertion sort: " + timeTakenIns + "\n");

        final long startTimeSel = nanoTime();
        selectionSort(arrSelectionSort);
        final long timeTakenSel = nanoTime() - startTimeSel;
        System.out.println("Time taken for Selection sort: " + timeTakenSel + "\n");

        final long startTimeBogo = nanoTime();
        System.out.println("Bogo sort:      " + Arrays.toString(bogoSort(arrBogoSort)));
        final long timeTakenBogo = nanoTime() - startTimeBogo;
        System.out.println("Time taken for Bogo sort: " + timeTakenBogo + "\n");
        */
        //END of practical 4

        //Start of practical 5
        /*
        int[] arrMer = new int[10000];
        int[] arrImpMer = new int[arrMer.length];
        int[] arrIns = new int[arrMer.length];

        // create 3 arrays that all have the same random elements
        Random rand = new Random();
        for(int i = 0; i < arrMer.length; i++) {
            arrMer[i] = rand.nextInt(arrMer.length) + 1;
            arrImpMer[i] = arrMer[i];
            arrIns[i] = arrMer[i];
        }

        final long startTimeMer = nanoTime();
        mergeSort(arrMer);
        final long timeTakenMer = nanoTime() - startTimeMer;
        System.out.println("Time taken for Merge sort: " + timeTakenMer + "\n");

        final long startTimeIns = nanoTime();
        insertionSort(arrIns);
        final long timeTakenIns = nanoTime() - startTimeIns;
        System.out.println("Time taken for Insertion sort: " + timeTakenIns);

        shuffle(arrImpMer);
        final long startTimeImpMer = nanoTime();
        improvedMergeSort(arrImpMer);
        final long timeTakenImpMer = nanoTime() - startTimeImpMer;
        System.out.println("\nTime taken for Improved Merge sort: " + timeTakenImpMer);
        */
        //END of practical 5

        //START of practical 6

        int[] arrQuick = new int[100000000];
        int[] arrImpQuick = new int[arrQuick.length];
        int[] arrMerge = new int[arrQuick.length];

        // create 3 arrays that all have the same random elements
        Random rand = new Random();
        for(int i = 0; i < arrQuick.length; i++) {
            arrQuick[i] = rand.nextInt(arrQuick.length) + 1;
            arrImpQuick[i] = arrQuick[i];
            arrMerge[i] = arrQuick[i];
        }

        final long startTimeQuick = nanoTime();
        quickSort(arrQuick, 0, arrQuick.length-1);
        final long timeTakenQuick = nanoTime() - startTimeQuick;
        System.out.println("Time taken for Quick sort: " + timeTakenQuick);

        shuffle(arrImpQuick);
        final long startTimeImpQuick = nanoTime();
        improvedQuickSort(arrImpQuick, 0, arrImpQuick.length-1);
        final long timeTakenImpQuick = nanoTime() - startTimeImpQuick;
        System.out.println("\nTime taken for Improved Quick sort: " + timeTakenImpQuick + "\n");

        final long startTimeMer = nanoTime();
        mergeSort(arrMerge);
        final long timeTakenMer = nanoTime() - startTimeMer;
        System.out.println("Time taken for Merge sort: " + timeTakenMer + "\n");

        //END of practical 6

    }
}
