public class StudentRegistryFullException extends StudentManagementException {
    public StudentRegistryFullException(String capacity) {
        super("Student registry capacity (" + capacity + ") has been reached.");
    }
}
