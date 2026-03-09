import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class StudentRegistry {
    // 1. Convert Student array to use ArrayList<Student>
    private List<Student> students = new ArrayList<>();


    public void addStudent(Student student) {
        students.add(student);
    }


    public List<Student> getAllStudents() {
        return new ArrayList<>(students); // Return copy for safety
    }


   // Implements multiple sorting options ---


    /** Sort by name (alphabetical) */
    public List<Student> sortByName() {
        // Use Comparator.comparing and a lambda expression
        List<Student> sortedList = new ArrayList<>(students);
        Collections.sort(sortedList, Comparator.comparing(Student::getLastName)
                .thenComparing(Student::getFirstName));
        return sortedList;
    }


    /** Sort by matriculation number */
    public List<Student> sortByMatriculationNumber() {
        List<Student> sortedList = new ArrayList<>(students);
        Collections.sort(sortedList, (s1, s2) -> Integer.compare(s1.getMatriculationNumber(), s2.getMatriculationNumber()));
        return sortedList;
    }


    /** Sort by age */
    public List<Student> sortByAge() {
        List<Student> sortedList = new ArrayList<>(students);
        // Sort by age, then by name for stable sorting
        Collections.sort(sortedList, Comparator.comparing(Student::getAge)
                .thenComparing(Student::getLastName));
        return sortedList;
    }
    //Adds advanced search functionality ---
    /** Find students by partial name match */
    public List<Student> findByPartialName(String partialName) {
        final String searchName = partialName.toLowerCase();
        return students.stream()
                // Uses a lambda (Predicate) to filter students
                .filter(s -> s.getFirstName().toLowerCase().contains(searchName) ||
                        s.getLastName().toLowerCase().contains(searchName))
                .collect(Collectors.toList());
    }
    /** Find students by matriculation number range */
    public List<Student> findByMatriculationNumberRange(int min, int max) {
        return students.stream()
                // Uses a lambda (Predicate) to filter students
                .filter(s -> s.getMatriculationNumber() >= min && s.getMatriculationNumber() <= max)
                .collect(Collectors.toList());
    }
    /** Find students older/younger than specific age */
    public List<Student> findByAgePredicate(String condition, int age) {
        if ("older".equalsIgnoreCase(condition)) {
            return students.stream().filter(s -> s.getAge() > age).collect(Collectors.toList());
        } else if ("younger".equalsIgnoreCase(condition)) {
            return students.stream().filter(s -> s.getAge() < age).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
