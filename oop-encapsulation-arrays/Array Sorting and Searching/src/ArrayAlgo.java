import java.util.Arrays;
import java.util.Random;


public class ArrayAlgo {


    // Method to display array contents
    public static void printArray(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }


    // Implement bubble sort to sort the array
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        // Outer loop for passes
        for (int i = 0; i < n - 1; i++) {
            // Inner loop for comparisons and swaps
            for (int j = 0; j < n - i - 1; j++) {
                // If the current element is greater than the next element, swap them
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    // Implement linear search
    // Returns index or -1
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;
        // Examine each element sequentially
        for (int i = 0; i < arr.length; i++) {
            comparisons++; // Increment comparison count
            if (arr[i] == target) {
                System.out.println("Linear Search Comparisons: " + comparisons); // Display comparison count
                return i; // Target found
            }
        }
        System.out.println("Linear Search Comparisons: " + comparisons); // Display comparison count
        return -1; // Target not found
    }


    // Implement binary search (requires sorted array)
    // Returns index or -1
    public static int binarySearch(int[] arr, int target) {
        int comparisons = 0;
        int low = 0;
        int high = arr.length - 1;


        while (low <= high) {
            int mid = low + (high - low) / 2;


            comparisons++; // One comparison: is arr[mid] the target?
            if (arr[mid] == target) {
                System.out.println("Binary Search Comparisons: " + comparisons); // Display comparison count
                return mid; // Target found
            }


            comparisons++; // Second comparison: is arr[mid] less than target?
            if (arr[mid] < target) {
                low = mid + 1; // Search the right half
            } else {
                high = mid - 1; // Search the left half
            }
        }
        System.out.println("Binary Search Comparisons: " + comparisons); // Display comparison count
        return -1; // Target not found
    }


    public static void main(String[] args) {
        // 1. Generate and display an array of 10 random integers (1-100)
        Random random = new Random(); // Initialize Random object
        int[] numbers = new int[10]; // Create an array of 10 integers


        for (int i = 0; i < numbers.length; i++) {
            // Generate random number between 1 and 100
            numbers[i] = random.nextInt(100) + 1;
        }


        System.out.println("--- Array Sorting and Searching Demo ---");
        System.out.print("Original Array: ");
        printArray(numbers); // Display original array


        // --- Bubble Sort ---
        System.out.println("\n--- Sorting (Bubble Sort) ---");
        bubbleSort(numbers); // Sort the array
        System.out.print("Sorted Array:   ");
        printArray(numbers); // Display sorted array


        // --- Searching Demonstration ---


        // Define a target value to search for
        int target1 = numbers[random.nextInt(10)]; // Choose a value guaranteed to be found
        // Define a target value that is likely not in the array (e.g., 101)
        int target2 = 101;


        // --- Linear Search ---
        System.out.println("\n--- Linear Search ---");


        // Search 1: Target is in the array
        int index1 = linearSearch(numbers, target1);
        if (index1 != -1) {
            System.out.printf("Target %d found at index %d in %d steps.\n", target1, index1, numbers.length - (index1));
        } else {
            System.out.printf("Target %d not found.\n", target1);
        }


        // Search 2: Target is NOT in the array
        int index2 = linearSearch(numbers, target2);
        if (index2 != -1) {
            System.out.printf("Target %d found at index %d.\n", target2, index2);
        } else {
            System.out.printf("Target %d not found.\n", target2);
        }


        // --- Binary Search (requires sorted array) ---
        System.out.println("\n--- Binary Search ---");


        // Search 1: Target is in the array
        int index3 = binarySearch(numbers, target1);
        if (index3 != -1) {
            System.out.printf("Target %d found at index %d.\n", target1, index3);
        } else {
            System.out.printf("Target %d not found.\n", target1);
        }


        // Search 2: Target is NOT in the array
        int index4 = binarySearch(numbers, target2);
        if (index4 != -1) {
            System.out.printf("Target %d found at index %d.\n", target2, index4);
        } else {
            System.out.printf("Target %d not found.\n", target2);
        }


        // Since no user input was explicitly required for the search *values* in the main method
        // (only demonstrating the methods), the code uses pre-defined targets.
        // If user input for search values is needed, a Scanner must be added here.
    }
}
