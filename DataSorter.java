import java.util.*;

// Member 1: Bubble Sort Implementation
class BubbleSort {
    public static SortResult sort(int[] arr) {
        int[] arrCopy = arr.clone();
        int n = arrCopy.length;
        long steps = 0;
        
        long startTime = System.nanoTime();
        
        for (int i = 0; i < n; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                steps++;
                if (arrCopy[j] > arrCopy[j + 1]) {
                    int temp = arrCopy[j];
                    arrCopy[j] = arrCopy[j + 1];
                    arrCopy[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;
        
        return new SortResult("Bubble Sort", arrCopy, steps, executionTime);
    }
}

// Member 2: Merge Sort Implementation

class MergeSort {
    private static long steps;
    
    public static SortResult sort(int[] arr) {
        int[] arrCopy = arr.clone();
        steps = 0;
        
        long startTime = System.nanoTime();
        
        mergeSortRecursive(arrCopy, 0, arrCopy.length - 1);
        
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;
        
        return new SortResult("Merge Sort", arrCopy, steps, executionTime);
    }
    
    private static void mergeSortRecursive(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            mergeSortRecursive(arr, left, mid);
            mergeSortRecursive(arr, mid + 1, right);
            
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        for (int i = 0; i < n1; i++)
            leftArr[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            rightArr[j] = arr[mid + 1 + j];
        
        int i = 0, j = 0, k = left;
        
        while (i < n1 && j < n2) {
            steps++;
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
}

// Member 3: Quick Sort Implementation
class QuickSort {
    private static long steps;
    
    public static SortResult sort(int[] arr) {
        int[] arrCopy = arr.clone();
        steps = 0;
        
        long startTime = System.nanoTime();
        
        quickSortRecursive(arrCopy, 0, arrCopy.length - 1);
        
        long endTime = System.nanoTime();
        double executionTime = (endTime - startTime) / 1_000_000.0;
        
        return new SortResult("Quick Sort", arrCopy, steps, executionTime);
    }
    
    private static void quickSortRecursive(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSortRecursive(arr, low, pi - 1);
            quickSortRecursive(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            steps++;
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        
        return i + 1;
    }
}

// Result class to store sorting results
class SortResult {
    String name;
    int[] sortedArray;
    long steps;
    double executionTime;
    
    public SortResult(String name, int[] sortedArray, long steps, double executionTime) {
        this.name = name;
        this.sortedArray = sortedArray;
        this.steps = steps;
        this.executionTime = executionTime;
    }
}
//Welcome to the group projet 02



