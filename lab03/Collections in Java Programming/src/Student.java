public class Student extends Person {
    private int matriculationNumber;
    // Starts at 1001 as required
    private static int nextMatriculationNumber = 1001;


    // Constructor calling parent constructor and setting matriculation number
    public Student(String firstName, String lastName, int day, int month, int year) {
        super(firstName, lastName, day, month, year);
        this.matriculationNumber = nextMatriculationNumber++;
    }


    // --- Encapsulation for matriculationNumber ---
    public int getMatriculationNumber() {
        return matriculationNumber;
    }


    // Setter is not strictly needed if number is auto-generated,
    // but included for completeness if needed for de-serialization.
    public void setMatriculationNumber(int matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }


    // Override displayInfo for Task 2/Polymorphism preparation (good practice)
    @Override
    public void displayInfo() {
        System.out.printf("Student ID: %d, ", matriculationNumber);
        super.displayInfo(); // Reuses Person's display logic
    }
}
