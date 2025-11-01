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

// Member 4: Main Application Class - UI, Data Generation, Performance Comparison
public class DataSorter {
    private int[] data;
    private Scanner scanner;
    
    public DataSorter() {
        this.data = new int[0];
        this.scanner = new Scanner(System.in);
    }
    
    public void displayMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("--- Data Sorter: Sorting Algorithm Comparison Tool ---");
        System.out.println("=".repeat(60));
        System.out.println("1. Enter numbers manually");
        System.out.println("2. Generate random numbers");
        System.out.println("3. Perform Bubble Sort");
        System.out.println("4. Perform Merge Sort");
        System.out.println("5. Perform Quick Sort");
        System.out.println("6. Compare all algorithms (show performance table)");
        System.out.println("7. Exit");
        System.out.println("=".repeat(60));
    }
    
    public void enterNumbersManually() {
        try {
            System.out.println("\nEnter numbers separated by spaces:");
            System.out.print("> ");
            String input = scanner.nextLine();
            
            String[] numbers = input.trim().split("\\s+");
            data = new int[numbers.length];
            
            for (int i = 0; i < numbers.length; i++) {
                data[i] = Integer.parseInt(numbers[i]);
            }
            
            System.out.println("✓ Successfully stored " + data.length + " numbers: " + 
                             Arrays.toString(data));
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Please enter valid integers only.");
        }
    }
    
    public void generateRandomNumbers() {
        try {
            System.out.print("\nEnter the number of elements to generate: ");
            int size = Integer.parseInt(scanner.nextLine());
            
            if (size <= 0) {
                System.out.println("✗ Error: Please enter a positive number.");
                return;
            }
            
            System.out.print("Enter minimum value: ");
            int minVal = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Enter maximum value: ");
            int maxVal = Integer.parseInt(scanner.nextLine());
            
            if (minVal > maxVal) {
                System.out.println("✗ Error: Minimum value cannot be greater than maximum value.");
                return;
            }
            
            Random random = new Random();
            data = new int[size];
            
            for (int i = 0; i < size; i++) {
                data[i] = random.nextInt(maxVal - minVal + 1) + minVal;
            }
            
            System.out.println("✓ Generated " + data.length + " random numbers");
            System.out.print("Data preview: ");
            printArrayPreview(data);
        } catch (NumberFormatException e) {
            System.out.println("✗ Error: Please enter valid integers.");
        }
    }
    
    public void performSort(String algorithmName, SortResult result) {
        if (data.length == 0) {
            System.out.println("✗ Error: No data available. Please enter or generate data first.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Performing " + algorithmName + "...");
        System.out.println("=".repeat(60));
        System.out.print("Original data: ");
        printArrayPreview(data);
        
        System.out.print("\nSorted data: ");
        printArrayPreview(result.sortedArray);
        
        System.out.println("\n📊 Performance Metrics:");
        System.out.printf("   Steps/Operations: %,d%n", result.steps);
        System.out.printf("   Execution Time: %.4f ms%n", result.executionTime);
    }
    
    public void compareAllAlgorithms() {
        if (data.length == 0) {
            System.out.println("✗ Error: No data available. Please enter or generate data first.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Comparing All Sorting Algorithms");
        System.out.println("=".repeat(60));
        System.out.println("Dataset size: " + data.length + " elements");
        System.out.print("Data preview: ");
        printArrayPreview(data);
        
        // Run all algorithms
        List<SortResult> results = new ArrayList<>();
        
        System.out.println("\nRunning Bubble Sort...");
        results.add(BubbleSort.sort(data));
        
        System.out.println("Running Merge Sort...");
        results.add(MergeSort.sort(data));
        
        System.out.println("Running Quick Sort...");
        results.add(QuickSort.sort(data));
        
        // Sort results by execution time for ranking
        results.sort(Comparator.comparingDouble(r -> r.executionTime));
        
        // Display comparison table
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Performance Comparison Results");
        System.out.println("=".repeat(60));
        System.out.printf("%-15s %-15s %-15s %-10s%n", "Algorithm", "Steps", "Time (ms)", "Rank");
        System.out.println("-".repeat(60));
        
        for (int i = 0; i < results.size(); i++) {
            SortResult result = results.get(i);
            System.out.printf("%-15s %-15s %-15.4f #%d%n", 
                            result.name, 
                            String.format("%,d", result.steps), 
                            result.executionTime, 
                            i + 1);
        }
        
        System.out.println("=".repeat(60));
        
        // Display analysis
        SortResult fastest = results.get(0);
        SortResult slowest = results.get(results.size() - 1);
        
        System.out.println("\n📈 Analysis:");
        System.out.printf("   Fastest Algorithm: %s (%.4f ms)%n", fastest.name, fastest.executionTime);
        System.out.printf("   Slowest Algorithm: %s (%.4f ms)%n", slowest.name, slowest.executionTime);
        
        if (fastest.executionTime > 0) {
            double speedup = slowest.executionTime / fastest.executionTime;
            System.out.printf("   Speed Difference: %.2fx faster%n", speedup);
        }
    }
    
    private void printArrayPreview(int[] arr) {
        int previewLimit = 20;
        if (arr.length <= previewLimit) {
            System.out.println(Arrays.toString(arr));
        } else {
            System.out.print("[");
            for (int i = 0; i < previewLimit; i++) {
                System.out.print(arr[i]);
                if (i < previewLimit - 1) System.out.print(", ");
            }
            System.out.println("...]");
        }
    }
    
    public void run() {
        System.out.println("\n🎯 Welcome to Data Sorter!");
        
        while (true) {
            displayMenu();
            
            try {
                System.out.print("\nEnter your choice (1-7): ");
                String choice = scanner.nextLine().trim();
                
                switch (choice) {
                    case "1":
                        enterNumbersManually();
                        break;
                    
                    case "2":
                        generateRandomNumbers();
                        break;
                    
                    case "3":
                        if (data.length > 0) {
                            SortResult result = BubbleSort.sort(data);
                            performSort("Bubble Sort", result);
                        } else {
                            System.out.println("✗ Error: No data available.");
                        }
                        break;
                    
                    case "4":
                        if (data.length > 0) {
                            SortResult result = MergeSort.sort(data);
                            performSort("Merge Sort", result);
                        } else {
                            System.out.println("✗ Error: No data available.");
                        }
                        break;
                    
                    case "5":
                        if (data.length > 0) {
                            SortResult result = QuickSort.sort(data);
                            performSort("Quick Sort", result);
                        } else {
                            System.out.println("✗ Error: No data available.");
                        }
                        break;
                    
                    case "6":
                        compareAllAlgorithms();
                        break;
                    
                    case "7":
                        System.out.println("\n👋 Thank you for using Data Sorter. Goodbye!");
                        scanner.close();
                        return;
                    
                    default:
                        System.out.println("✗ Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (Exception e) {
                System.out.println("✗ An error occurred: " + e.getMessage());
            }
        }
    }
    
    public static void main(String[] args) {
        DataSorter app = new DataSorter();
        app.run();
    }
}




