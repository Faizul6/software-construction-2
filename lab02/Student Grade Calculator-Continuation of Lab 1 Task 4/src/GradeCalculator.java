import java.util.Scanner;
import java.util.Arrays; // Needed for array sorting and other utilities


/** * UML Class Diagram: * * ===================================================
 * | GradeCalculator | * ===================================================
 * | No Attributes (Data is passed via int[] grades) |
 * ===================================================
 * |
 + calculateAverage(grades: int[]): double | (static)
 * |
 + findHighest(grades: int[]): int | (static)
 * |
 + findLowest(grades: int[]): int | (static)
 * |
 + countPassing(grades: int[]): int | (static)
 * |
 + deduplicatedGrades(grades: int[]): int[] | (static)
 * |
 + getLetterGrade(grade: double): char | (static)
 * |
 + main(args: String[]): void | (static)
 * ===================================================
 */




// Class structure for the grade calculator system
public class GradeCalculator {


    // Helper method (from Lab 1)
    private static char getLetterGrade(double grade) {
        if (grade >= 90) {
            return 'A';
        } else if (grade >= 80) {
            return 'B';
        } else if (grade >= 70) {
            return 'C';
        } else if (grade >= 60) {
            return 'D';
        } else {
            return 'F';
        }
    }


    // 1. Calculate Average
    // The requirement specified 'int calculateAverage', but the example and best practice suggests 'double'
    // for a precise average. Sticking to 'double' for accuracy.
    public static double calculateAverage(int[] grades) {
        int sum = 0;
        // Use a loop to process the array
        for (int grade : grades) {
            sum += grade; // Accumulate sum
        }
        // Calculate and return average
        return (double) sum / grades.length;
    }


    // 2. Find Highest Grade
    public static int findHighest(int[] grades) {
        int highest = 0; // Grades are 0-100, so 0 is a safe start
        for (int grade : grades) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }


    // 3. Find Lowest Grade
    public static int findLowest(int[] grades) {
        // Initialize to 101 or the first element, since max grade is 100
        int lowest = 101;
        for (int grade : grades) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }


    // 4. Count Passing Grades (grade >= 60)
    public static int countPassing(int[] grades) {
        int count = 0;
        for (int grade : grades) {
            if (grade >= 60) {
                count++;
            }
        }
        return count;
    }


    // 5. Deduplicate Grades
    public static int[] deduplicatedGrades(int[] grades) {
        // Use a boolean array (or Set in more advanced Java) to track seen grades (0-100)
        boolean[] seen = new boolean[101];
        int uniqueCount = 0;


        // 1. Count unique grades
        for (int grade : grades) {
            // Check if within valid range (0-100)
            if (grade >= 0 && grade <= 100) {
                if (!seen[grade]) {
                    seen[grade] = true;
                    uniqueCount++;
                }
            }
        }


        // 2. Create the new array and populate it
        int[] result = new int[uniqueCount];
        int resultIndex = 0;


        // Reset 'seen' array to re-use or re-iterate through grades
        // Since we iterated through all possible values (0-100) and marked them,
        // we can now iterate through the 'seen' array to get the unique grades.
        for (int i = 0; i < seen.length; i++) {
            if (seen[i]) {
                result[resultIndex++] = i;
            }
        }


        return result;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.println("--- Student Grade Manager v2 ---");


        // The task specifies storing grades for 5 students
        final int NUM_STUDENTS = 5;
        // Array to store the grades
        int[] grades = new int[NUM_STUDENTS];


        // Prompt the user to enter 5 student grades (0-100)
        System.out.println("\n--- Entering Grades ---");
        for (int i = 0; i < NUM_STUDENTS; i++) {
            System.out.print("Enter grade for student " + (i + 1) + " (0-100): ");
            // Basic input reading, assuming inputs are meaningful integers (0-100)
            if (scanner.hasNextInt()) {
                grades[i] = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Consume invalid input
                i--; // Repeat the current loop iteration
            }
        }



// --- Calculate Statistics using separate methods [ ---
        double averageGrade = calculateAverage(grades);
        int highestGrade = findHighest(grades);
        int lowestGrade = findLowest(grades);
        int passingCount = countPassing(grades);
        int failedCount = NUM_STUDENTS - passingCount;
        int[] uniqueGrades = deduplicatedGrades(grades);


        // --- Display Results —--
        System.out.println("\n            === GRADE REPORTS ===             ");


        System.out.println("--- Statistics ---");
        System.out.printf("Average Grade: %.2f\n", averageGrade);
        System.out.printf("Highest Grade: %d\n", highestGrade);
        System.out.printf("Lowest Grade:  %d\n", lowestGrade);
        System.out.println("Students Passed (>=60): " + passingCount);
        System.out.println("Students Failed (<60):  " + failedCount);
        // Sort unique grades for better display
        Arrays.sort(uniqueGrades);
        System.out.println("Unique Grades Entered: " + Arrays.toString(uniqueGrades));




        // Display each student's grade
        System.out.println("\n--- Individual Student Grades ---");
        for (int i = 0; i < NUM_STUDENTS; i++) {
            double grade = grades[i];
            char letter = getLetterGrade(grade);


            // Display results with score and grade
            System.out.printf("Student %d: %d (%.2f) (%c)\n", i + 1, grades[i], grade, letter);
        }
        System.out.println("------------------------------------------------------");
    }
}
