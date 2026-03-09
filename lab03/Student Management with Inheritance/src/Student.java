public class Student extends Person {
    private int matriculationNumber;
    // Starts at 1001 as required
    private static int nextMatriculationNumber = 1001;


    // Constructor and setting matriculation number
    public Student(String firstName, String lastName, int day, int month, int year) {
        super(firstName, lastName, day, month, year);
        this.matriculationNumber = nextMatriculationNumber++;
    }


    // --- Encapsulation for matriculationNumber ---
    public int getMatriculationNumber() {
        return matriculationNumber;
    }
    public void setMatriculationNumber(int matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }


}
