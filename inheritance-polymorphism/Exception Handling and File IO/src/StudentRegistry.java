import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class StudentRegistry {
    private List<Student> students;


    public StudentRegistry() {
        this.students = new ArrayList<>();
    }


    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }


    public void addStudent(Student student) {
        this.students.add(student);
    }


    // Assumes Student implements Comparable<Student> for sorting by MN
    public void sortStudents() {
        Collections.sort(this.students);
    }
    // Method to save students to a file
    public void saveStudents(String filename) throws StudentManagementException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {


            oos.writeObject(this.students);


        } catch (IOException e) {
            throw new StudentManagementException("Error saving student data to file: " + filename, e);
        }
    }


    // Method to load students from a file
    public void loadStudents(String filename) throws StudentManagementException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {




            this.students = (List<Student>) ois.readObject();


        } catch (FileNotFoundException e) {

            System.out.println("No existing data file found. Starting with an empty registry.");
            this.students = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            throw new StudentManagementException("Error loading student data from file: " + filename, e);
        }
    }


}
