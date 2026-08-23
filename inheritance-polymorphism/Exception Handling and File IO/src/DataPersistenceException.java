public class DataPersistenceException extends Exception {


    // Constructor to wrap the underlying exception
    public DataPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }


}
