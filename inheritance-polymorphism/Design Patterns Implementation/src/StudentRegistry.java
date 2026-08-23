import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class StudentRegistry implements StudentSubject {
    // --- Data Storage  ---
    private List<Student> students = new ArrayList<>();
    private List<StudentObserver> observers = new ArrayList<>();




    private static class SingletonHelper {
        private static final StudentRegistry INSTANCE = new StudentRegistry();
    }


    private StudentRegistry() {
        // Initializes collections as thread-safe lists
        this.students = Collections.synchronizedList(new ArrayList<>());
        this.observers = Collections.synchronizedList(new ArrayList<>());
    }


    public static StudentRegistry getInstance() {
        return SingletonHelper.INSTANCE;
    }
    public void addObserver(StudentObserver observer) {
        observers.add(observer);
    }
    // --- StudentSubject Implementation (Observer Management) ---


    @Override
    public void registerObserver(StudentObserver observer) {
        observers.add(observer);
        System.out.println("Observer registered: " + observer.getClass().getSimpleName());
    }


    @Override
    public void removeObserver(StudentObserver observer) {
        observers.remove(observer);
        System.out.println("Observer removed: " + observer.getClass().getSimpleName());
    }


    @Override
    public void notifyObservers(String eventType, Student student) {
        for (StudentObserver observer : observers) {
            observer.update(eventType, student);
        }
    }
    // --- Core Data Methods---
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("\nRegistry: Student added: " + student.getName());
        notifyObservers("STUDENT_ADDED", student);
    }


    public boolean removeStudent(int matriculationNumber) {
        Student removedStudent = null;
        for (Student s : students) {
            if (s.getMatriculationNumber() == matriculationNumber) {
                removedStudent = s;
                break;
            }
        }


        if (removedStudent != null) {
            students.remove(removedStudent);
            System.out.println("\nRegistry: Student removed: " + removedStudent.getName());
            notifyObservers("STUDENT_REMOVED", removedStudent);
            return true;
        }
        return false;
    }


    public void modifyStudentDepartment(int matriculationNumber, String newDepartment) {
        Student modifiedStudent = students.stream()
                .filter(s -> s.getMatriculationNumber() == matriculationNumber)
                .findFirst()
                .orElse(null);


        if (modifiedStudent != null) {
            String oldDept = modifiedStudent.getDepartment();
            modifiedStudent.setDepartment(newDepartment);
            System.out.println("\nRegistry: Student department modified from " + oldDept + " to " + newDepartment);
            notifyObservers("STUDENT_MODIFIED", modifiedStudent);
        }
    }
}
