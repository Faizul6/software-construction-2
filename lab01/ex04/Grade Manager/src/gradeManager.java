import java.util.Scanner;

public class gradeManager {

    // --- Letter Grade Scale ---
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Student Grade Manager ---");

        //Asks the user how many students they want to enter grades for
        System.out.print("Enter the number of students: ");
        int numOfStudents = scanner.nextInt();
        //Array to store the grades
        double[] grades = new double[numOfStudents]; //This initializes the array
        double totalSum = 0;
        double highestGrade = 0;
        double lowestGrade = 100;
        int studentsPassed = 0; // Grade >= 60
        int studentsFailed = 0; // Grade < 60

        // Prompt the user to enter each student's grade (0-100)
        System.out.println("\n--- Entering Grades ---");
        //This loop does the collecting and processing
        for (int i = 0; i < numOfStudents; i++) {
            System.out.printf("Enter grade for student %d (0-100): ", i + 1);
            double currentGrade = scanner.nextDouble();
            grades[i] = currentGrade; // Stores the grade in the array

            // Accumulates the sum for the average
            totalSum += currentGrade;
            //Determines the highest grade
            if (currentGrade > highestGrade) {
                highestGrade = currentGrade;
            }
            //Determines the lowest grade
            if (currentGrade < lowestGrade) {
                lowestGrade = currentGrade;
            }
            //Counts passes and fails
            if (currentGrade >= 60) {
                studentsPassed++;
            } else {
                studentsFailed++;
            }
        }
        scanner.close();

        // Calculate statistics
        double averageGrade = totalSum / numOfStudents;


        // --- Display Results ---
        System.out.println("            === GRADE REPORTS ===             ");

        // Display calculated statistics
        System.out.println("--- Statistics ---");
        System.out.printf("Average Grade: %.2f\n", averageGrade);
        System.out.printf("Highest Grade: %.2f\n", highestGrade);
        System.out.printf("Lowest Grade:  %.2f\n", lowestGrade);
        System.out.println("Students Passed: " + studentsPassed);
        System.out.println("Students Failed:  " + studentsFailed);



        // Display each student's grade
        System.out.println("\n--- Individual Student Grades ---");
        for (int i = 0; i < numOfStudents; i++) {
            double grade = grades[i];
            char letter = getLetterGrade(grade);

            // Display results with score and grade
            System.out.printf("Student %d: %.2f (%c)\n", i + 1, grade, letter);
        }
        System.out.println("------------------------------------------------------");
    }
}
