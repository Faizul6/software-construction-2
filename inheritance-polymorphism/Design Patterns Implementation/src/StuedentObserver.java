// Observer Interface
public interface StudentObserver {
    void update(String action, Student student);
    void onStudentAdded(Student student);
    void onStudentRemoved(Student student);
    void onStudentModified(Student student);
}
