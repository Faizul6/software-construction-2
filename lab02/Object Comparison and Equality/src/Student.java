import java.util.Arrays;
import java.util.Comparator;


/**
 * UML Class Diagram:
 * * ===========================================================
 * | Student |
 * =============================================================
 * | - studentID: int (private) |
 * | - name: String (private) |
 * | - grade: double (private) |
 * =============================================================
 * | + Student(id: int, name: String, grade: double) |
 * | + getStudentID(): int |
 * | + getName(): String |
 * | + getGrade(): double |
 * | + equals(obj: Object): boolean |
 * | + hashCode(): int |
 * | + compareTo(other: Student): int |
 * | + NameComparator: Comparator<Student> | (static)
 * | + toString(): String |
 * ============================================================
 */


public class Student implements Comparable<Student> { // Implements Comparable for natural ordering


    // Private fields
    private int studentID;
    private String name;
    private double grade;


    // Constructor
    public Student(int studentID, String name, double grade) {
        this.studentID = studentID;
        this.name = name;
        this.grade = grade;
    }


    // Getters for external access
    public int getStudentID() { return studentID; }
    public String getName() { return name; }
    public double getGrade() { return grade; }


    // --- Object Comparison Methods ---


    // 1. Override equals() for proper equality checking
    @Override
    public boolean equals(Object obj) {
        // Check if the object is the same instance
        if (this == obj) return true;
        // Check if the object is null or of a different class
        if (obj == null || getClass() != obj.getClass()) return false;


        // Cast the object to Student
        Student other = (Student) obj;


        // Two students are considered equal if they have the same ID AND name
        // Comparing ID is typically sufficient for real-world uniqueness,
        // but adding name for a more robust lab example.
        return studentID == other.studentID &&
                name.equals(other.name);
    }


    // 2. Override hashCode()
    @Override
    public int hashCode() {
        // Simple hash calculation based on fields used in equals()
        int result = 17;
        result = 31 * result + studentID;
        result = 31 * result + name.hashCode();
        return result;
    }


    // 3. Implement compareTo() for natural ordering (Sort by Grade)
    @Override
    public int compareTo(Student other) {
        // Sort in ascending order of grade.
        // Using Double.compare for accurate double comparison.
        return Double.compare(this.grade, other.grade);
    }


    // 4. Custom Comparator for sorting by Name
    public static Comparator<Student> NameComparator = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            // Sort in ascending (alphabetical) order of name
            return s1.getName().compareTo(s2.getName());
        }
    };


    // Helper method to represent the object as a string for display
    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Grade: %.2f", studentID, name, grade);
    }


    public static void main(String[] args) {
        // 1. Create an array of Student objects with sample data
        Student[] students = {
                new Student(101, "Alice", 88.5),
                new Student(103, "Bob", 72.0),
                new Student(102, "Charlie", 95.5),
                new Student(104, "David", 65.0),
                new Student(101, "Alice", 88.5) // Duplicate student
        };


        System.out.println("--- Object Comparison and Equality Demo ---");


        // --- Equality Checking ---
        System.out.println("\n--- Equality Checking ---");
        Student s1 = students[0];
        Student s5 = students[4];
        Student s2 = students[1];


        // 2. Demonstrate equality checking between students
        System.out.println("Student 1: " + s1);
        System.out.println("Student 5: " + s5);
        System.out.println("s1 equals s5? " + s1.equals(s5)); // Should be true (same ID and Name)
        System.out.println("s1 hash code: " + s1.hashCode());
        System.out.println("s5 hash code: " + s5.hashCode());


        System.out.println("\nStudent 1: " + s1);
        System.out.println("Student 2: " + s2);
        System.out.println("s1 equals s2? " + s1.equals(s2)); // Should be false (different ID and Name)


        // --- Sorting ---
        System.out.println("\n--- Sorting ---");


        // 3. Sort students by grade (natural ordering using compareTo)
        Arrays.sort(students);
        System.out.println("Sorted by Grade (Natural Order):");
        for (Student s : students) {
            System.out.println("  " + s);
        }


        // 4. Sort students by name using a custom comparator
        Arrays.sort(students, Student.NameComparator);
        System.out.println("Sorted by Name (Custom Comparator):");
        for (Student s : students) {
            System.out.println("  " + s);
        }


        // --- Finding Duplicates ---
        System.out.println("\n--- Finding Duplicate Students ---");
        // 5. Find duplicate students and display them
        // This is a simple O(n^2) approach for a learning context
        boolean[] isChecked = new boolean[students.length];
        boolean foundDuplicate = false;


        for (int i = 0; i < students.length; i++) {
            if (isChecked[i]) continue;
            for (int j = i + 1; j < students.length; j++) {
                if (students[i].equals(students[j])) {
                    System.out.println("Duplicate Found: " + students[j]);
                    isChecked[j] = true;
                    foundDuplicate = true;
                }
            }
            isChecked[i] = true;
        }
        if (!foundDuplicate) {
            System.out.println("No duplicates found based on ID and Name.");
        }


        // --- Searching ---
        System.out.println("\n--- Searching for Students ---");


        // 6. Search for students by ID and name
        // Simple linear search by ID
        int searchID = 102;
        System.out.println("Searching for ID: " + searchID);
        for (Student s : students) {
            if (s.getStudentID() == searchID) {
                System.out.println("  Found by ID: " + s);
                break;
            }
        }


        // Simple linear search by Name
        String searchName = "David";
        System.out.println("Searching for Name: " + searchName);
        for (Student s : students) {
            if (s.getName().equals(searchName)) {
                System.out.println("  Found by Name: " + s);
                break;
            }
        }
    }
}
