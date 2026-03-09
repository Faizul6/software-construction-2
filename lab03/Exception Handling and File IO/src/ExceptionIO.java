import java.util.Arrays;
import java.util.List;


public class ExceptionIO {
    public static void demonstrate() throws StudentDataException {
        System.out.println("\n--- Task 6: Exception Handling and File I/O ---");
        StudentDataManager registry = new StudentDataManager();
        StudentDataManager manager = new StudentDataManager();
        String filename = "students.csv";




        // Create sample students
        List<Student> students = Arrays.asList(
                new Student("Doug", "Dimmadome", 10, 65), // ID 1001
                new Student("Joseph", "Joestar", 5, 45)   // ID 1002
        );


        // Input Validation Demonstration ---
        System.out.println("\nValidation Demo:");
        try {
            InputValidator.validateName("Valid-Name ü");
            System.out.println("Name 'Valid-Name ü' is valid.");
        } catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
        }


        try {
            InputValidator.validateName("Inva1id Name");
        } catch (IllegalArgumentException e) {
            System.err.println("Validation Error: " + e.getMessage());
        }


        // Custom Exception Demonstration ---
        try {
            InputValidator.validateMatriculationNumber(500); // Too low
        } catch (InvalidMatriculationNumberException e) {
            // Exception handling at this level
            System.err.println("Custom Exception Handled: " + e.getMessage());
        }
        try {
            registry.getStudent(99999); // A number that isn't there
        } catch (StudentNotFoundException e) {
            System.out.println("Custom Exception Handled: " + e.getMessage());
        }
        System.out.println("\nRegistry Full Exception Demo:");
        try {
            // Attempt to add a third student, which triggers the exception
            manager.addStudent(new Student("Giorno", "Giovanna", 1009, 16));
        } catch (StudentRegistryFullException e) {
            // Exception handling at this level
            System.err.println("Custom Exception Handled: " + e.getMessage());
        }




    }


    public static void main(String[] args) throws StudentDataException {
        demonstrate();
    }
}
