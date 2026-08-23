public class Student implements Comparable<Student>{
    private String FirstName;
    private String LastName;
    private int MatriculationNumber;
    private int age;


    public Student(String FirstName, String LastName,int MatriculationNumber, int age) throws StudentDataException {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.MatriculationNumber = MatriculationNumber;
        this.age = age;
    }


    public String getName() {
        return FirstName + LastName;
    }


    public void setName(String FirstName, String LastName) {
        this.FirstName = FirstName;
        this.LastName = LastName;
    }


    public int getMatriculationNumber() {
        return MatriculationNumber;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "Student [Name=" + FirstName + LastName +", ID=" + MatriculationNumber + ", Age=" + age + "]";
    }


    @Override
    public int compareTo(Student other) {
        // Compare students based on their matriculation numbers (as strings)
        // If we want numeric sorting, we'd convert them to integers first.
        return Integer.compare(this.MatriculationNumber, other.MatriculationNumber);
    }


    public void setMatriculationNumber(int i) {
    }


}
