import java.util.ArrayList;
import java.util.List;


public class StudentDataManager {
    private List<Student> studentList;


    public StudentDataManager() {
        this.studentList = new ArrayList<>();
    }
    public void addStudent(Student student) {
        studentList.add(student);
    }


    public static void validateStudent(Student student) throws StudentDataException {
        // Exception for Null/Empty Data ---
        if (student == null) {
            throw new StudentDataException("Cannot validate a null Student object.");
        }


        //. Exception for Invalid ID (e.g., negative) ---
        if (student.getMatriculationNumber() <= 0) {
            throw new StudentDataException("Invalid ID: Student ID must be a positive number.");
        }


        // Exception for Invalid Age (e.g., out of range) ---
        if (student.getAge() < 16 || student.getAge() > 65) {
            throw new StudentDataException("Invalid Age: Student age must be between 16 and 65.");
        }


        // Exception for Invalid Name (e.g., empty string) ---
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new StudentDataException("Invalid Name: Student name cannot be empty.");
        }
    }
    public void getStudent(int studentId) {
        Student[] studentList = new Student[0];
        for (Student student : studentList) {
            if (student.getMatriculationNumber() == studentId) {
                return;
            }
        }
    }


    public static void main(String[] args) throws StudentDataException {
        System.out.println("--- Starting Task 6: Custom Exception Demonstration ---");


        // Array of students with various invalid data points to trigger different exceptions
        Student[] studentsToTest = {
                null, // Triggers Exception 1: Null Object
                new Student("", "", 1001, 25), // Triggers Exception 4: Invalid Name
                new Student("Alice", "Cooper", -20, 33), // Triggers Exception 2: Invalid ID
                new Student("Bob", "Constructor", 1200, -1), // Triggers Exception 3: Invalid Age
                new Student("Valid", "Student", 2005,16 ) // Valid student (should be processed)
        };


        for (int i = 0; i < studentsToTest.length; i++) {
            String header = "\n--- Testing Case " + (i + 1) + " ---";


            try {
                validateStudent(studentsToTest[i]);


                // This line only executes if NO exception was thrown
                System.out.println(header);
                System.out.println("SUCCESS: " + studentsToTest[i].toString() + " is valid.");


            } catch (StudentDataException e) {
                // Catches our specific custom exception
                String errorOutput = header +
                        "\nCUSTOM EXCEPTION CAUGHT: " + e.getMessage();
                System.err.println(errorOutput);


            } catch (Exception e) {
                // ... (handle unexpected exceptions similarly) ...
                String unexpectedError = header +
                        "\nUNEXPECTED EXCEPTION: " + e.getMessage();
                System.err.println(unexpectedError);
            }
        }


        System.out.println("\n--- Program Finished ---");
    }
}


