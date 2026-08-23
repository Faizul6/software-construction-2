public class StudentNotFoundException extends StudentManagementException {
    public StudentNotFoundException(String matriculationNumber) {
        super("Student with matriculation number " + matriculationNumber + " not found.");
    }
}
