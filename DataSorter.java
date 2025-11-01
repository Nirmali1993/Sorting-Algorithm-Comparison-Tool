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

//Welcome to the group projet 02

