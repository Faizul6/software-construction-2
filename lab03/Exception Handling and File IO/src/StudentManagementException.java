public class StudentManagementException extends RuntimeException {


    // Constructor 1: Accepts only a message
    public StudentManagementException(String message) {
        super(message);
    }


    // Constructor 2: ACCEPTS MESSAGE AND CAUSE
    public StudentManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
