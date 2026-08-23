public class Student extends Person {
    private int matriculationNumber;
    private String name;
    private String department;
    private static int nextMatriculationNumber = 1001;


    public Student(int matriculationNumber, String firstName,String LastName, String department) {
        super(firstName, LastName);
        this.matriculationNumber = matriculationNumber;
        this.name = firstName + LastName;
        this.department = department;
        this.matriculationNumber = nextMatriculationNumber++;
    }


    public int getMatriculationNumber() {
        return matriculationNumber;
    }


    public String getName() {
        return name;
    }


    public String getDepartment() {
        return department;
    }


    // Setter for modification example
    public void setDepartment(String department) {
        this.department = department;
    }


    @Override
    public String toString() {
        return "Matriculation: " + matriculationNumber + ", Name: " + name + ", Dept: " + department;
    }
}
