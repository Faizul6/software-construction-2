public class InvalidMatriculationNumberException extends StudentManagementException {
    public InvalidMatriculationNumberException(String message) {
        super(message);
    }
    public InvalidMatriculationNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
