public abstract class Course {
    protected String name;
    protected String code;


    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }


    public abstract void displayDetails();


    // Abstract method for Factory requirement (e.g., calculation based on type)
    public abstract double calculateECTS();
}
