// Interface for the object that manages the observers (the Subject)
public interface StudentSubject {
    void registerObserver(StudentObserver observer);
    void removeObserver(StudentObserver observer);
    void notifyObservers(String eventType, Student student);
}
