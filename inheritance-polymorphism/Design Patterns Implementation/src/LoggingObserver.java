public class LoggingObserver implements StudentObserver {
    @Override
    public void onStudentAdded(Student student) {
        System.out.println("[LOG:INFO] Student added: " + student.getLastName() + " (ID: " + student.getMatriculationNumber() + ")");
    }


    @Override
    public void onStudentRemoved(Student student) {
        System.out.println("[LOG:WARNING] Student removed: " + student.getLastName());
    }


    @Override
    public void onStudentModified(Student student) {
        System.out.println("[LOG:INFO] Student modified: " + student.getLastName());
    }
    @Override
    public void update(String message, Student student) {
        // This method handles the notification and logs the event.


        System.out.println("--- LOGGING OBSERVER ---");
        System.out.println("EVENT: " + message);
        System.out.println("Student: " + student.getFirstName() + " " + student.getLastName());
        // In a real application, you would write this to a file or database.
        System.out.println("------------------------");
    }
}
